package ru.sobinda.RZDInterview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sobinda.RZDInterview.entity.DirectoryOfCargoNomenclaturesEntity;

import java.util.Optional;

public interface DirectoryOfCargoNomenclaturesRepository
        extends JpaRepository<DirectoryOfCargoNomenclaturesEntity, Integer> {

    boolean existsByCode(String code);
    boolean existsByShippingNameIgnoreCase(String shippingName);

    Optional<DirectoryOfCargoNomenclaturesEntity> findByCode(String code);
    Optional<DirectoryOfCargoNomenclaturesEntity> findByShippingNameIgnoreCase(String shippingName);
}
