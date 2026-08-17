package org.example.customes.repository;

import org.example.customes.entity.Vacations;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacationRp extends JpaRepository<Vacations, Integer> {

}
