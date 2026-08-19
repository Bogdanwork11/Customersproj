package org.example.customes.dto;

import java.time.LocalDate;

public record CustRequestDto(
        String fullname,
        String phoneNumber,
        LocalDate birhtDate,
        Integer departamentId,
        Integer statusJobId
) {}
