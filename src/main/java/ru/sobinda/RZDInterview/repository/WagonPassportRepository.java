package ru.sobinda.RZDInterview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sobinda.RZDInterview.entity.WagonPassportEntity;

import java.util.Optional;

public interface WagonPassportRepository extends JpaRepository<WagonPassportEntity, Integer> {


    boolean existsByNumber(int number);

    Optional<WagonPassportEntity> findByNumber(int number);
}
