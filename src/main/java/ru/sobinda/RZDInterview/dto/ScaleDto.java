package ru.sobinda.RZDInterview.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.sobinda.RZDInterview.entity.DirectoryOfCargoNomenclaturesEntity;
import ru.sobinda.RZDInterview.entity.ScaleEntity;
import ru.sobinda.RZDInterview.entity.WagonPassportEntity;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Информация о списках вагонов с атрибутами")
public class ScaleDto {

    @JsonProperty("serial_number")
    private Integer serialNumber;

    @JsonProperty("wagon_number")
    private WagonPassportDto wagonPassport;

    private List<DirectoryOfCargoNomenclaturesEntity> nomenclatures;

    @JsonProperty("cargo_weight")
    private BigDecimal cargoWeight;

    @JsonProperty("wagon_weight")
    private BigDecimal wagonWeight;

    public static ScaleDto addScale(ScaleEntity scale) {
        return ScaleDto.builder()
                .serialNumber(scale.getSerialNumber())
                .wagonPassport(getWagonPassportDto(scale.getWagonPassport()))
                .nomenclatures(scale.getNomenclatures())
                .cargoWeight(scale.getCargoWeight())
                .wagonWeight(scale.getWagonWeight())
                .build();
    }


    public static WagonPassportDto getWagonPassportDto(WagonPassportEntity wagonPassportEntity) {
        return WagonPassportDto.addWagonPassportDto(wagonPassportEntity);
    }
}
