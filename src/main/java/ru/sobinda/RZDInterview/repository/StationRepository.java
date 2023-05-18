package ru.sobinda.RZDInterview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sobinda.RZDInterview.entity.StationEntity;

public interface StationRepository extends JpaRepository<StationEntity, Integer> {
}
