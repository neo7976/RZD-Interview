package ru.sobinda.RZDInterview.dto.fullscale;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Информация о составах поездов")
public class FullScaleCreateDto {

    //Номер состава
    @JsonProperty("composition_number")
    private Integer compositionNumber;

    //Список вагонов, которые принадлежат составу
    @JsonProperty("scale_id")
    private List<Integer> scales;
}
