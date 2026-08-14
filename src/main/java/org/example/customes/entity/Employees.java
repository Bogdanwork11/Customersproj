package org.example.customes.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Employees {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users userId;

    @Column(name = "full_name", nullable = false)
    private String fullName;


    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "phone", nullable = false)
    private String phoneNumber;

    @Lob
    @Column(name = "Photo", nullable = false, columnDefinition = "MEDIUMBLOB") //BLOB расшифроввывается как binaryLargeObject
    private byte[] photo;


    @ManyToOne
    @JoinColumn(name = "departament_id", nullable = false)
    private Departament departamentId;

    @ManyToOne
    @JoinColumn(name = "status_job", nullable = false)
    private StatusJob statusJob;

    @ManyToOne
    @JoinColumn(name = "vacation_id", nullable = false)
    private Vacations vacationId;




}
