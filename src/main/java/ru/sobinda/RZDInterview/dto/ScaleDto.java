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
public class ScaleDto extends ScaleDtoImp {

    @JsonProperty("wagon_number")
    private WagonPassportDto wagonPassport;

    private List<DirectoryOfCargoNomenclaturesEntity> nomenclatures;

    public ScaleDto(int serialNumber,
                    BigDecimal cargoWeight,
                    BigDecimal wagonWeight,
                    WagonPassportDto wagonPassport,
                    List<DirectoryOfCargoNomenclaturesEntity> nomenclatures) {
        super(serialNumber, cargoWeight, wagonWeight);
        this.wagonPassport = wagonPassport;
        this.nomenclatures = nomenclatures;
    }

    public static ScaleDto addScale(ScaleEntity scale) {
//        return ScaleDto.builder()
////                .serialNumber(scale.getSerialNumber())
//                .wagonPassport(getWagonPassportDto(scale.getWagonPassport()))
//                .nomenclatures(scale.getNomenclatures())
////                .cargoWeight(scale.getCargoWeight())
////                .wagonWeight(scale.getWagonWeight())
//                .build();

        return new ScaleDto(
                scale.getSerialNumber(),
                scale.getCargoWeight(),
                scale.getWagonWeight(),
                getWagonPassportDto(scale.getWagonPassport()),
                scale.getNomenclatures());
    }


    public static WagonPassportDto getWagonPassportDto(WagonPassportEntity wagonPassportEntity) {
        return WagonPassportDto.addWagonPassportDto(wagonPassportEntity);
    }
}
