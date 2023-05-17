package ru.sobinda.RZDInterview.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.sobinda.RZDInterview.entity.StationEntity;
import ru.sobinda.RZDInterview.entity.WagonPassportEntity;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Информация о станции")
public class StationDto {

    @JsonProperty("station_name")
    private String stationName;

    private List<Integer> number;

    public static StationDto addStationDto(StationEntity stationEntity) {
        return StationDto.builder()
                .stationName(stationEntity.getStationName())
                .number(stationEntity.getNumber())
                .build();
    }
}
