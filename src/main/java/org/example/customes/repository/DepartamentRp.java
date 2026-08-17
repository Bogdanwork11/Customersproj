package org.example.customes.repository;

import org.example.customes.entity.Departament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartamentRp extends JpaRepository<Departament, Integer>{

Departament findByTitleDp(String titleDp);
}


