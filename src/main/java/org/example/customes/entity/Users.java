package org.example.customes.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.customes.role.Role;

import jakarta.persistence.Lob; //для хибернейта чтобы позволял больше памяти брать на фото от 255 юайт

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "Email", nullable = false)
    private String email;

    @Column(name = "Password", nullable = false)
    private String password;

    @Column(name = "FIO", nullable = false)
    private String fio;

    @Column(name = "DataBirth", nullable = false)
    private String dataBirth;

    @Column(name = "Job", nullable = false)
    private String job;

    @Column(name = "Number", nullable = false)
    private String number;

    @Lob
    @Column(name = "Photo", nullable = false, columnDefinition = "LONGBLOB") // для MySQL/PostgreSQL
    private byte[] photo;

    @Column(name = "StatusJob", nullable = false)
    private String statusJob;

    @Enumerated(EnumType.STRING)
    @Column(name = "Role", nullable = false)
    private Role role;





}
