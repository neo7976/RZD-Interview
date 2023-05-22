package ru.sobinda.RZDInterview.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import ru.sobinda.RZDInterview.entity.WagonPassportEntity;
import ru.sobinda.RZDInterview.enums.WagonType;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WagonPassportDto that = (WagonPassportDto) o;
        return number == that.number && tareWeight == that.tareWeight && loadCapacity == that.loadCapacity && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, type, tareWeight, loadCapacity);
    }
}
