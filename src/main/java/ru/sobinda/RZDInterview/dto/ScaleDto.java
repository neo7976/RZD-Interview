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

@NoArgsConstructor
@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Информация о списках вагонов с атрибутами")
public class ScaleDto extends ScaleDtoImp {

    @JsonProperty("wagon_number")
    private WagonPassportDto wagonPassport;

    private List<DirectoryOfCargoNomenclaturesEntity> nomenclatures;

    public ScaleDto(
            WagonPassportDto wagonPassport,
            List<DirectoryOfCargoNomenclaturesEntity> nomenclatures
    ) {
        this.wagonPassport = wagonPassport;
        this.nomenclatures = nomenclatures;
    }

    public static ScaleDtoImp addScale(ScaleEntity scale) {
        return new ScaleDto(
                getWagonPassportDto(scale.getWagonPassport()),
                scale.getNomenclatures()
        )
                .setSerialNumber(scale.getSerialNumber())
                .setCargoWeight(scale.getCargoWeight())
                .setWagonWeight(scale.getWagonWeight());
    }

    public static WagonPassportDto getWagonPassportDto(WagonPassportEntity wagonPassportEntity) {
        return WagonPassportDto.addWagonPassportDto(wagonPassportEntity);
    }
}
