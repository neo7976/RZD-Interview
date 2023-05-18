package ru.sobinda.RZDInterview.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.sobinda.RZDInterview.entity.DirectoryOfCargoNomenclaturesEntity;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Номенклатура груза")
public class DirectoryOfCargoNomenclaturesDto {

    private String code;

    @JsonProperty("shipping_name")
    private String shippingName;

    public static DirectoryOfCargoNomenclaturesDto addDirectoryOfCargoNomenclatures(
            DirectoryOfCargoNomenclaturesEntity dcn ) {
        return DirectoryOfCargoNomenclaturesDto.builder()
                .code(dcn.getCode())
                .shippingName(dcn.getShippingName())
                .build();
    }
}
