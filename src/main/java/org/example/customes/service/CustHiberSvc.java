package org.example.customes.service;

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
    public CustResponseDto createInfo(CustCreateDto request){
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
    public CustPatchDto updateInfo(Integer id, CustResponseDto request){
        Employees employees = employeesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Человек не найден"));
        return
    }

}

