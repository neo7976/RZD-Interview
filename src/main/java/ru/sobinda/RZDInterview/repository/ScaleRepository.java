package ru.sobinda.RZDInterview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sobinda.RZDInterview.entity.ScaleEntity;

public interface ScaleRepository extends JpaRepository<ScaleEntity, Integer> {
}
