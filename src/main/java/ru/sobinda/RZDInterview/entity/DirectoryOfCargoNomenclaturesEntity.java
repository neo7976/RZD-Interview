package ru.sobinda.RZDInterview.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
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
}
