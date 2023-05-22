package ru.sobinda.RZDInterview.dto.fullscale;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.sobinda.RZDInterview.dto.scale.ScaleDto;
import ru.sobinda.RZDInterview.entity.FullScaleEntity;
import ru.sobinda.RZDInterview.entity.ScaleEntity;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
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
}
