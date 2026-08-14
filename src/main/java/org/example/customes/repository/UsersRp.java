package org.example.customes.repository;

import org.example.customes.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRp extends JpaRepository<Users, Integer> {
    Users findByEmail(String email);
}
