package ru.sobinda.RZDInterview.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.sobinda.RZDInterview.dto.fullscale.FullScaleCreateDto;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
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

    public static FullScaleEntity addScale(FullScaleCreateDto fullScaleCreateDto,
                                           List<ScaleEntity> listScales) {
        return FullScaleEntity.builder()
                .compositionNumber(fullScaleCreateDto.getCompositionNumber())
                .scales(listScales)
                .build();
    }

    public static FullScaleEntity updateScaleById(FullScaleEntity fullScaleEntity,
                                                  FullScaleCreateDto fullScaleCreateDto,
                                                  List<ScaleEntity> listScales) {
        return FullScaleEntity.builder()
                .id(fullScaleEntity.getId())
                .compositionNumber(fullScaleCreateDto.getCompositionNumber())
                .scales(listScales)
                .build();
    }
}
