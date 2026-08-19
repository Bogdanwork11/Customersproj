package org.example.customes.service;

import org.example.customes.entity.*;
import org.example.customes.repository.*;
import org.example.customes.role.Role;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;

@Service
public class UsersSvc {
    private final UsersRp usersRepository;
    private final StatusRp statusRepository;
    private final DepartamentRp departamentRepository;
    private final VacationRp vacationRepository;
    private final EmployeesRp employeesRepository;

    public UsersSvc(UsersRp usersRepository, StatusRp statusRepository, DepartamentRp departamentRepository, VacationRp vacationRepository, EmployeesRp employeesRepository){
        this.usersRepository = usersRepository;
        this.statusRepository = statusRepository;
        this.departamentRepository = departamentRepository;
        this.vacationRepository = vacationRepository;
        this.employeesRepository = employeesRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void loadUsers() {

        //-----developer-----
        Users developer = new Users();
        developer.setEmail("Bogdankosyanenko@icloud.com");
        developer.setPassword("28085678");
        developer.setRole(Role.DEVELOPER);
        usersRepository.save(developer);

        Vacations vacations = new Vacations();
        vacations.setStartDate(LocalDate.of(2026, 8, 1));
        vacations.setEndDate(LocalDate.of(2026, 8, 15));
        vacationRepository.save(vacations);

        Employees employeesDeveloper = new Employees();
        employeesDeveloper.setUserId(developer);
        employeesDeveloper.setFullName("Косьяненко Богдан Владимирович");
        employeesDeveloper.setBirthDate(LocalDate.of(2006,5, 29));
        employeesDeveloper.setPhoneNumber("+998 55 999 99 99");

        //прогружается с repo а репо в sql status не забууууудь
        StatusJob developerStatus = statusRepository.findByTitleSt("Developer");
        employeesDeveloper.setStatusJob(developerStatus);

        Departament developDp = departamentRepository.findByTitleDp("Java Development");
        employeesDeveloper.setDepartamentId(developDp);

        employeesDeveloper.setVacationId(vacations);

        employeesDeveloper.setPhoto(getPhotoBytes("images/bogdan.jpg"));

        employeesRepository.save(employeesDeveloper);

        //-----hr-----
        Users hr = new Users();
        hr.setEmail("emirmus69@gmail.com");
        hr.setPassword("12345678");
        hr.setRole(Role.HR);
        usersRepository.save(hr);

        Vacations vacations1 = new Vacations();
        vacations1.setStartDate(LocalDate.of(2026,7,1));
        vacations1.setEndDate(LocalDate.of(2026, 7, 14));
        vacationRepository.save(vacations1);

        Employees employeesHr = new Employees();
        employeesHr.setUserId(hr);
        employeesHr.setFullName("Павлов Эмир Эдуардов ич");
        employeesHr.setBirthDate(LocalDate.of(2000, 5, 9));
        employeesHr.setPhoneNumber("+998 77 999 99 99");

        StatusJob HrStatus = statusRepository.findByTitleSt("HR");
        employeesHr.setStatusJob(HrStatus);

        Departament hrDp = departamentRepository.findByTitleDp("HR");
        employeesHr.setDepartamentId(hrDp);

        employeesHr.setVacationId(vacations1);

        employeesHr.setPhoto(getPhotoBytes("images/emir.jpg"));

        employeesRepository.save(employeesHr);

        //-----recruiter-----
        Users recruiter = new Users();
        recruiter.setEmail("emirmus60@gmail.com"); //---< email заменен на нолик не забудь при сверке менять местами emailы, работает при проверки фото{id} только нолик убирай в случае чего)
        recruiter.setPassword("12345678");
        recruiter.setRole(Role.RECRUITER);
        usersRepository.save(recruiter);

        Vacations vacations2 = new Vacations();
        vacations2.setStartDate(LocalDate.of(2026,6,1));
        vacations2.setEndDate(LocalDate.of(2026, 6, 13));
        vacationRepository.save(vacations2);

        Employees employeesRec = new Employees();
        employeesRec.setUserId(recruiter);
        employeesRec.setFullName("Лютикова Люся Батьковна");
        employeesRec.setBirthDate(LocalDate.of(1885, 8, 4));
        employeesRec.setPhoneNumber("+998 91 999 99 99");

        StatusJob RecStatus = statusRepository.findByTitleSt("Recruiter");
        employeesRec.setStatusJob(RecStatus);

        Departament RecDp = departamentRepository.findByTitleDp("Recruiting");
        employeesRec.setDepartamentId(RecDp);

        employeesRec.setVacationId(vacations2);

        employeesRec.setPhoto(getPhotoBytes("images/lusya.jpg"));

        employeesRepository.save(employeesRec);

        //-----devops-----
        Users devops = new Users();
        devops.setEmail("Bogdankkkkkkkkosyanenko@icloud.com"); //email замени при проверке тут три kkk <---
        devops.setPassword("28085678");
        devops.setRole(Role.DEVOPS);
        usersRepository.save(devops);

        Vacations vacations3 = new Vacations();
        vacations3.setStartDate(LocalDate.of(2026, 5, 1));
        vacations3.setEndDate(LocalDate.of(2026, 5, 12));
        vacationRepository.save(vacations3);

        Employees employeesDevops = new Employees();
        employeesDevops.setUserId(devops);
        employeesDevops.setFullName("Галустян Денис Валентинович");
        employeesDevops.setBirthDate(LocalDate.of(1987,12,31));
        employeesDevops.setPhoneNumber("+998 91 999 99 99");

        StatusJob devopStatus = statusRepository.findByTitleSt("Devops");
        employeesDevops.setStatusJob(devopStatus);

        Departament DevopDp = departamentRepository.findByTitleDp("DevOps");
        employeesDevops.setDepartamentId(DevopDp);

        employeesDevops.setVacationId(vacations3);

        employeesDevops.setPhoto(getPhotoBytes("images/Deniska.jpg"));

        employeesRepository.save(employeesDevops);

        //-----team lead-----

        Users teamlead = new Users();
        teamlead.setEmail("Bogdankkkkkosyanenko@icloud.com");
        teamlead.setPassword("28085678");
        teamlead.setRole(Role.TEAMLEAD);
        usersRepository.save(teamlead);

        Vacations vacations4 = new Vacations();
        vacations4.setStartDate(LocalDate.of(2026, 4, 1));
        vacations4.setEndDate(LocalDate.of(2026, 4, 11));
        vacationRepository.save(vacations4);

        Employees employeesTeamlead = new Employees();
        employeesTeamlead.setUserId(teamlead);
        employeesTeamlead.setFullName("Статик Джейсон Эдуардович");
        employeesTeamlead.setBirthDate(LocalDate.of(1977, 1, 2));
        employeesTeamlead.setPhoneNumber("+998 90 999 99 99");

        StatusJob teamleadStatus = statusRepository.findByTitleSt("TeamLead");
        employeesTeamlead.setStatusJob(teamleadStatus);

        Departament teamleadDp = departamentRepository.findByTitleDp("IT");
        employeesTeamlead.setDepartamentId(teamleadDp);

        employeesTeamlead.setVacationId(vacations4);

        employeesTeamlead.setPhoto(getPhotoBytes("images/static.jpg"));
        employeesRepository.save(employeesTeamlead);

}
    //документация если забуду про метод начала чтения фото файла https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/core/io/ClassPathResource.html#%3Cinit%3E(java.lang.String)
    private byte[] getPhotoBytes(String path) {
        try {
            ClassPathResource resource = new ClassPathResource(path);
            return resource.getInputStream().readAllBytes();
        } catch (IOException e) {
            System.err.println("Не удалось прочитать картинку по пути: " + path);
            return new byte[0];
        }

    }
}
