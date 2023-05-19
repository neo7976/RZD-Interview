package ru.sobinda.RZDInterview.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class ScaleDtoImp {

    @JsonProperty("serial_number")
    private int serialNumber;

    @JsonProperty("cargo_weight")
    private BigDecimal cargoWeight;

    @JsonProperty("wagon_weight")
    private BigDecimal wagonWeight;
}
