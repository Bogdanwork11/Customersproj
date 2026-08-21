package org.example.customes.service;

import java.io.IOException;
import org.example.customes.dto.CustCreateDto;
import org.example.customes.dto.CustPatchDto;
import org.example.customes.dto.CustResponseDto;
import org.example.customes.entity.Departament;
import org.example.customes.entity.Employees;
import org.example.customes.entity.StatusJob;
import org.example.customes.repository.DepartamentRp;
import org.example.customes.repository.EmployeesRp;
import org.example.customes.repository.StatusRp;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.temporal.ChronoUnit;

@Service
public class CustHiberSvc {
    private final EmployeesRp employeesRepository;
    private final DepartamentRp departamentRepository;
    private final StatusRp statusRepository;

    public CustHiberSvc(EmployeesRp employeesRepository, DepartamentRp departamentRepository, StatusRp statusRepository){
        this.employeesRepository = employeesRepository;
        this.departamentRepository = departamentRepository;
        this.statusRepository = statusRepository;
    }
    //postmethod
    public CustResponseDto createInfo(CustCreateDto request, MultipartFile photo)throws IOException {
        Departament departament = departamentRepository.findById(request.departamentId())
                .orElseThrow(() -> new RuntimeException("Департамент не найден"));

        StatusJob statusJob = statusRepository.findById(request.statusJobId())
                .orElseThrow(() -> new RuntimeException("Такой работы не было найдено"));

        Employees employees = new Employees();
        employees.setFullName(request.fullName());
        employees.setPhoneNumber(request.phoneNumber());
        employees.setBirthDate(request.birthDate());
        employees.setDepartamentId(departament);
        employees.setStatusJob(statusJob);
        if (photo != null){
            employees.setPhoto(photo.getBytes());
        }

        Employees save = employeesRepository.save(employees);

        return toResponce(save);
    }

    private CustResponseDto toResponce(Employees employees){
        String DepartamentTitle = employees.getDepartamentId().getTitleDp();
        String StatusTitle = employees.getStatusJob().getTitleSt();

        int daysLeft = 28;
        if(employees.getVacationId() != null){
            long used = ChronoUnit.DAYS.between(
                    employees.getVacationId().getStartDate(),
                    employees.getVacationId().getEndDate());
                    daysLeft = 28 - (int) used;
        }

        return new CustResponseDto(
                employees.getId(),
                employees.getFullName(),
                employees.getBirthDate(),
                employees.getPhoneNumber(),
                DepartamentTitle,
                StatusTitle,
                daysLeft,
                "/photo/" + employees.getId()
        );


    }
    //getmethod
    public CustResponseDto requestInfo(Integer id){
        Employees employees = employeesRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Человек не найден"));
        return toResponce(employees);
    }
    //patchmethod
    public CustResponseDto updateInfo(Integer id, CustPatchDto request, MultipartFile photo) throws IOException {
        Employees employees = employeesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Человек не найден"));

        if (request != null && request.phoneNumber() != null) {
            employees.setPhoneNumber(request.phoneNumber());
        }

        if (photo != null && !photo.isEmpty()) {
            employees.setPhoto(photo.getBytes());
        }

        Employees saved = employeesRepository.save(employees);

        return toResponce(saved);
    }
    //deletemethod
    public CustResponseDto deleteInfo(Integer id, CustResponseDto request){
        Employees employees = employeesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Человек не найден"));
        if (request != null) {
            employeesRepository.deleteById(id);
        }
        Employees saved = employeesRepository.save(employees);

        return toResponce(saved);
    }


}

