package ru.sobinda.RZDInterview.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.sobinda.RZDInterview.entity.WagonPassportEntity;
import ru.sobinda.RZDInterview.enums.WagonType;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Информация о паспорте вагона")
public class WagonPassportDto {

    private int number;
    private WagonType type;
    private int tareWeight;
    private int loadCapacity;


    public static WagonPassportDto addWagonPassportDto(WagonPassportEntity wagonPassportEntity) {
        return WagonPassportDto.builder()
                .type(wagonPassportEntity.getType())
                .number(wagonPassportEntity.getNumber())
                .loadCapacity(wagonPassportEntity.getLoadCapacity())
                .tareWeight(wagonPassportEntity.getType().getTareWeight())
                .build();
    }
}
