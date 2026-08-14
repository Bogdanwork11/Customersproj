package org.example.customes.service;

import java.io.IOException;
import java.time.LocalDate;

import org.example.customes.entity.Employees;
import org.example.customes.entity.StatusJob;
import org.example.customes.repository.StatusRp;
import org.example.customes.repository.UsersRp;
import org.example.customes.role.Role;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.example.customes.entity.Users;

@Service
public class UsersSvc {
    private final UsersRp usersRepository;
    private final StatusRp statusRepository;

    public UsersSvc(UsersRp usersRepository, StatusRp statusRepository){
        this.usersRepository = usersRepository;
        this.statusRepository = statusRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void loadUsers() {




        //-----developer-----
        Users developer = new Users();
        developer.setEmail("Bogdankosyanenko@icloud.com");
        developer.setPassword("28085678");
        developer.setRole(Role.DEVELOPER);
        usersRepository.save(developer);

        Employees employeesDeveloper = new Employees();
        employeesDeveloper.setUserId(developer);
        employeesDeveloper.setFullName("Косьяненко Богдан Владимирович");
        employeesDeveloper.setBirthDate(LocalDate.of(2006 ,5, 29));
        employeesDeveloper.setPhoneNumber("+998 55 999 99 99");
        //прогружается с repo а репо в sql status не забууууудь
        StatusJob developerStatus = statusRepository.findByTitleSt("Developer");
        employeesDeveloper.setStatusJob(developerStatus);
        employeesDeveloper.setPhoto(getPhotoBytes("images/bogdan.jpg"));










        Users user = new Users();
        user.setId(2);
        user.setEmail("emirmus69@gmail.com");
        user.setPassword("12345678");
        user.setFio("Павлов Эмир Эдуардович");
        user.setDataBirth("09-05-2000");
        user.setJob("Devops");
        user.setStatusJob("client");
        user.setNumber("+998 77 999 99 99");
        user.setRole(Role.USER);
        user.setPhoto(getPhotoBytes("images/emir.jpg"));
        usersRepository.save(user);

        Users hr = new Users();
        hr.setId(3);
        hr.setEmail("emirmus69@gmail.com");
        hr.setPassword("12345678");
        hr.setFio("Ашотикова Люся Дмитриевна");
        hr.setDataBirth("04-03-2001");
        hr.setJob("HR");
        hr.setStatusJob("hr");
        hr.setNumber("+998 88 999 99 99");
        hr.setRole(Role.HR);
        hr.setPhoto(getPhotoBytes("images/lusya.jpg"));
        usersRepository.save(hr);

        Users recruiter = new Users();
        recruiter.setId(4);
        recruiter.setEmail("emirmus69@gmail.com");
        recruiter.setPassword("12345678");
        recruiter.setFio("Галустян Денис Валентинович");
        recruiter.setDataBirth("31-12-1999");
        recruiter.setJob("Java Developer");
        recruiter.setStatusJob("recruiter");
        recruiter.setNumber("+998 91 999 99 99");
        recruiter.setRole(Role.RECRUITER);
        recruiter.setPhoto(getPhotoBytes("images/Deniska.jpg"));
        usersRepository.save(recruiter);

        Users devops = new Users();
        devops.setId(5);
        devops.setEmail("emirmus69@gmail.com");
        devops.setPassword("12345678");
        devops.setFio("Джасурова Алёна Магомедовна");
        devops.setDataBirth("22-02-2002");
        devops.setJob("Java Developer");
        devops.setStatusJob("devops");
        devops.setNumber("+998 93 999 99 99");
        devops.setRole(Role.DEVOPS);
        devops.setPhoto(getPhotoBytes("images/Alenka.jpg"));
        usersRepository.save(devops);

        Users teamlead = new Users();
        teamlead.setId(6);
        teamlead.setEmail("emirmus69@gmail.com");
        teamlead.setPassword("12345678");
        teamlead.setFio("Альпенгольтовна Маша Михайловна");
        teamlead.setDataBirth("22-02-1956");
        teamlead.setJob("Java Developer");
        teamlead.setStatusJob("teamlead");
        teamlead.setNumber("+998 90 999 99 99");
        teamlead.setRole(Role.TEAMLEAD);
        teamlead.setPhoto(getPhotoBytes("images/static.jpg"));
        usersRepository.save(teamlead);

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
