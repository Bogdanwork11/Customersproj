package org.example.customes.repository;

import org.example.customes.entity.StatusJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRp extends JpaRepository<StatusJob, Integer> {
    StatusJob findByTitleSt(String titleSt);
}
