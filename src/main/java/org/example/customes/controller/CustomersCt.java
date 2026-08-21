package org.example.customes.controller;

import java.io.IOException;

import lombok.extern.slf4j.Slf4j;
import org.example.customes.dto.CustCreateDto;
import org.example.customes.dto.CustPatchDto;
import org.example.customes.dto.CustResponseDto;
import org.example.customes.service.CustHiberSvc;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/customers")
public class CustomersCt {

    private final CustHiberSvc custHiberSvc;

    public CustomersCt(CustHiberSvc custHiberSvc) {
        this.custHiberSvc = custHiberSvc;
    }

    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public CustResponseDto createClient(@RequestPart("data") CustCreateDto request, @RequestPart(value = "photo", required = false) MultipartFile photo) throws IOException {
        return custHiberSvc.createInfo(request, photo);
    }

    @GetMapping(value = "/{id}")
    public CustResponseDto requestClient(@PathVariable Integer id) {
        return custHiberSvc.requestInfo(id);
    }

    @PatchMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public CustResponseDto updateClient(@PathVariable Integer id, @RequestPart(value = "data", required = false) CustPatchDto request, @RequestPart(value = "photo", required = false) MultipartFile photo) throws IOException {
        return custHiberSvc.updateInfo(id, request, photo);
    }

    @DeleteMapping(value = "/{id}")
    public CustResponseDto deleteClient(@PathVariable Integer id, CustResponseDto request){
        return custHiberSvc.deleteInfo(id, request);
    }
}
