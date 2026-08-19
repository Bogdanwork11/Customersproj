package org.example.customes.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.customes.dto.CustCreateDto;
import org.example.customes.dto.CustResponseDto;
import org.example.customes.dto.LoginRequest;
import org.example.customes.service.CustHiberSvc;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/customers")
public class CustomersCt {

    private final CustHiberSvc custHiberSvc;

    public CustomersCt(CustHiberSvc custHiberSvc){
        this.custHiberSvc = custHiberSvc;
    }

    @PostMapping("/create")
    public CustResponseDto createClient(@RequestBody CustCreateDto request){
        return custHiberSvc.create(request);
    }

}
