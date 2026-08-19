package org.example.customes.dto;

import java.time.LocalDate;

public record CustResponseDto(
        Integer id,
        String fullname,
        LocalDate birthDate,
        String phoneNumber,
        String departamentTitle,
        String StatusTitle,
        Integer vacationDays,
        String photoPath
) {
}
