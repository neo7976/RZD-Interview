package ru.sobinda.RZDInterview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sobinda.RZDInterview.entity.WagonPassportEntity;

public interface WagonPassportRepository extends JpaRepository<WagonPassportEntity, Integer> {
}
