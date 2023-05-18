package ru.sobinda.RZDInterview.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.sobinda.RZDInterview.dto.DirectoryOfCargoNomenclaturesDto;
import ru.sobinda.RZDInterview.dto.WagonPassportDto;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "directory_of_cargo_nomenclatures")
public class DirectoryOfCargoNomenclaturesEntity {

    /**
     * Код груза, Наименование груза
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String code;

    @Column(name = "shipping_name",unique = true)
    private String shippingName;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ScaleEntity> scale;

    public static DirectoryOfCargoNomenclaturesEntity addDirectoryOfCargoNomenclatures(
            DirectoryOfCargoNomenclaturesDto directory) {
        return DirectoryOfCargoNomenclaturesEntity.builder()
                .code(directory.getCode())
                .shippingName(directory.getShippingName())
                .build();
    }

    public static DirectoryOfCargoNomenclaturesEntity updateDirectoryOfCargoNomenclatures(
            DirectoryOfCargoNomenclaturesEntity directory,
            DirectoryOfCargoNomenclaturesDto directoryDto) {

        return DirectoryOfCargoNomenclaturesEntity.builder()
                .id(directory.getId())
                .shippingName(directoryDto.getShippingName())
                .code(directoryDto.getCode())
                .build();
    }
}
