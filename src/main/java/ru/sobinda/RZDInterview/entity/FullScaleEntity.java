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
@Table(name = "full_scale")
public class FullScaleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //Номер состава
    @Column(name = "composition_number", unique = true)
    private Integer compositionNumber;

    //Список вагонов, которые принадлежат составу
    @OneToMany
    @JoinColumn(name = "scale_id")
    private List<ScaleEntity> scales;
}
