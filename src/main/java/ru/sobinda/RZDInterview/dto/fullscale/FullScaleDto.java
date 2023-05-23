package ru.sobinda.RZDInterview.dto.fullscale;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import ru.sobinda.RZDInterview.dto.scale.ScaleDto;
import ru.sobinda.RZDInterview.entity.FullScaleEntity;
import ru.sobinda.RZDInterview.entity.ScaleEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Информация о составах поездов")
public class FullScaleDto {
    //Номер состава

    @JsonProperty("composition_number")
    private Integer compositionNumber;
    //Список вагонов, которые принадлежат составу

    @JsonProperty("scale_id")
    private List<ScaleDto> scales;
    public static FullScaleDto addFullScale(FullScaleEntity fullScaleEntity) {
        return FullScaleDto.builder()
                .compositionNumber(fullScaleEntity.getCompositionNumber())
                .scales(getScaleDto(fullScaleEntity.getScales()))
                .build();
    }

    public static List<ScaleDto> getScaleDto(List<ScaleEntity> scaleEntities) {
        List<ScaleDto> dtos = new ArrayList<>();
        for (ScaleEntity scaleEntity : scaleEntities) {
            dtos.add(ScaleDto.addScale(scaleEntity));
        }
        return dtos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FullScaleDto that = (FullScaleDto) o;
        return compositionNumber.equals(that.compositionNumber) && scales.equals(that.scales);
    }

    @Override
    public int hashCode() {
        return Objects.hash(compositionNumber, scales);
    }
}
