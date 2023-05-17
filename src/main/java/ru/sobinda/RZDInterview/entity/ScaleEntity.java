package ru.sobinda.RZDInterview.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "scale")
public class ScaleEntity {

    /**
     * Список вагонов с атрибутами:
     * Порядковый номер, Номер вагона, Номенклатура груза, Вес груза в вагоне, Вес вагона)
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "serial_number")
    private Integer serialNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wagon_number")
    private WagonPassportEntity wagonPassport;

    @ManyToMany(mappedBy = "scale", fetch = FetchType.EAGER)
    private List<DirectoryOfCargoNomenclaturesEntity> nomenclatures;

    @Column(name = "cargo_weight")
    private BigDecimal cargoWeight;

    @Column(name = "wagon_weight")
    private BigDecimal wagonWeight;
}
