package org.example.customes.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record CustCreateDto(
        String fullName,
        String phoneNumber,
        @JsonFormat(pattern = "dd-MM-yyyy")
        LocalDate birthDate,
        Integer departamentId,
        Integer statusJobId
) {}
