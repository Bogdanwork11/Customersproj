package org.example.customes.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.customes.dto.CustCreateDto;
import org.example.customes.dto.CustPatchDto;
import org.example.customes.dto.CustResponseDto;
import org.example.customes.service.CustHiberSvc;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/customers")
public class CustomersCt {

    private final CustHiberSvc custHiberSvc;

    public CustomersCt(CustHiberSvc custHiberSvc) {
        this.custHiberSvc = custHiberSvc;
    }

    @PostMapping("/create")
    public CustResponseDto createClient(@RequestBody CustCreateDto request) {
        return custHiberSvc.createInfo(request);
    }

    @GetMapping(value = "/{id}")
    public CustResponseDto requestClient(@PathVariable Integer id) {
        return custHiberSvc.requestInfo(id);
    }

    @PatchMapping(value = "/{id}")
    public CustPatchDto updateClient(@PathVariable Integer id, @RequestBody CustPatchDto request) {
        return custHiberSvc.updateInfo(id, request);
    }
}
