package ru.sobinda.RZDInterview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.sobinda.RZDInterview.entity.ScaleEntity;

public interface ScaleRepository extends JpaRepository<ScaleEntity, Integer> {
    @Modifying
    @Query(value = "update scale set serial_number = null where scale_id =:scale_id;", nativeQuery = true)
    void deleteSerialNumber(@Param("scale_id") Integer scaleId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE scale " +
            "SET serial_number = COALESCE((SELECT MAX(serial_number) FROM scale WHERE scale_id = :scale_id), 0) + CAST(row_number AS int) " +
            "FROM ( " +
            "  SELECT id, ROW_NUMBER() OVER (ORDER BY id) AS row_number " +
            "  FROM scale " +
            "  WHERE scale_id = :scale_id AND serial_number IS NULL " +
            ") AS subquery " +
            "WHERE scale.id = subquery.id", nativeQuery = true)
    void updateSerialNumber(@Param("scale_id") Integer scaleId);
}
