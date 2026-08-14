package org.example.customes.controller;

import org.example.customes.dto.LoginRequest;
import org.example.customes.entity.Users;
import org.example.customes.repository.UsersRp;
import org.example.customes.service.JwtSvc;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController

public class LoginCt {
    private final JwtSvc jwtservice;
    private final UsersRp userRepository;

    public LoginCt(JwtSvc jwtservice, UsersRp userRepository) {
        this.jwtservice = jwtservice;
        this.userRepository = userRepository;
    }

    @PostMapping("/info")
    public String authprofile(@RequestBody LoginRequest request) {

        Users user = userRepository.findByEmail(request.login());
        {
            if (!user.getPassword().equals(request.password())) {
                throw new RuntimeException("Не верный пароль");
            }
            String token = jwtservice.generationToken(user.getEmail(), user.getRole());
            System.out.println("Токен: " + token);

            return jwtservice.generationToken(user.getEmail(), user.getRole());

        }
    }

}
