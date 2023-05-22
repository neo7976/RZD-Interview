package ru.sobinda.RZDInterview.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.sobinda.RZDInterview.dto.scale.ScaleCreateDto;
import ru.sobinda.RZDInterview.dto.WagonPassportDto;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "scale")
public class ScaleEntity {

    /**
     * Список вагонов с атрибутами:
     * Порядковый номер, Номер вагона, Номенклатура груза, Вес груза в вагоне, Вес вагона
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //Порядковый номер
    @Column(name = "serial_number")
    private Integer serialNumber;

    //Номер вагона
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wagon_number")
    private WagonPassportEntity wagonPassport;

    //Список номенклатур товара для одного вагона
    @OneToMany
    @JoinColumn(name = "nomenclature_id")
    private List<DirectoryOfCargoNomenclaturesEntity> nomenclatures;

    //Вес груза в вагоне
    @Column(name = "cargo_weight")
    private BigDecimal cargoWeight;

    //Вес вагона
    @Column(name = "wagon_weight")
    private BigDecimal wagonWeight;

    public static ScaleEntity addScale(ScaleCreateDto scaleCreateDto,
                                       WagonPassportEntity wagonPassportEntity,
                                       List<DirectoryOfCargoNomenclaturesEntity> listDirectory) {
        return ScaleEntity.builder()
                .serialNumber(scaleCreateDto.getSerialNumber())
                .cargoWeight(scaleCreateDto.getCargoWeight())
                .nomenclatures(listDirectory)
                .wagonPassport(wagonPassportEntity)
                .wagonWeight(BigDecimal.valueOf(wagonPassportEntity.getTareWeight()))
                .build();
    }

    public static ScaleEntity updateScaleById(ScaleEntity scaleEntity,
                                              ScaleCreateDto scaleDto,
                                              WagonPassportEntity wagonPassportEntity,
                                              List<DirectoryOfCargoNomenclaturesEntity> listDirectory) {
        return ScaleEntity.builder()
                .id(scaleEntity.getId())
                .serialNumber(scaleDto.getSerialNumber())
                .wagonPassport(wagonPassportEntity)
                .nomenclatures(listDirectory)
                .cargoWeight(scaleDto.getCargoWeight())
                .wagonWeight(BigDecimal.valueOf(wagonPassportEntity.getTareWeight()))
                .build();
    }

    public static WagonPassportEntity getWagonPassportEntity(WagonPassportDto wagonPassportDto) {
        return WagonPassportEntity.addWagonPassport(wagonPassportDto);
    }
}
