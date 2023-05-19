package ru.sobinda.RZDInterview.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Информация для добавления списка вагонов с атрибутами")
public class ScaleAddDto extends ScaleDtoImp{

    @JsonProperty("wagon_passport_id")
    private int wagonPassportId;

    private List<Integer> nomenclatures;

    public ScaleAddDto(int serialNumber, BigDecimal cargoWeight, BigDecimal wagonWeight) {
        super(serialNumber, cargoWeight, wagonWeight);
    }
   }
