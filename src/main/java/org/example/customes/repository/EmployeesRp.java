package org.example.customes.repository;

import org.example.customes.entity.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeesRp extends JpaRepository<Employees, Integer> {

}
