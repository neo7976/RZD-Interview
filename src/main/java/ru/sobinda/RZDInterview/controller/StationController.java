package ru.sobinda.RZDInterview.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import ru.sobinda.RZDInterview.dto.StationDto;
import ru.sobinda.RZDInterview.service.StationService;

import java.util.List;

@RestController
@RequestMapping("station/")
@RequiredArgsConstructor
@Tag(name = "Станционная модель", description = "CRUD Station")
public class StationController {

    private final StationService stationService;

    @GetMapping("id/{id}")
    @Secured({"ROLE_ADMIN", "ROLE_USER", "ROLE_GUEST"})
    @Operation(summary = "Получить список Станций по id")
    public ResponseEntity<StationDto> getStationById(
            @Parameter(description = "Уникальный параметр")
            @PathVariable("id") Integer id) {
        var result = stationService.getStationById(id);
        return result.map(
                        stationDto -> new ResponseEntity<>(
                                stationDto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @Operation(summary = "Получить все станции")
    @Secured({"ROLE_ADMIN", "ROLE_USER", "ROLE_GUEST"})
    @GetMapping("all")
    public ResponseEntity<List<StationDto>> getAllStations() {
        var result = stationService.getAllStations();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("add")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @Operation(summary = "Добавить новую Станцию")
    public ResponseEntity<Void> addStation(@RequestBody StationDto stationDto) {
        if (stationService.addStation(stationDto))
            return new ResponseEntity<>(HttpStatus.CREATED);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("id/{id}")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @Operation(summary = "Обновить данные Станции по id")
    public ResponseEntity<Void> updateStation(
            @Parameter(description = "Уникальный параметр")
            @PathVariable("id") Integer id,
            @RequestBody StationDto stationDto) {
        if (stationService.updateStation(id, stationDto))
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Operation(summary = "Удалить данные Станции по id")
    @DeleteMapping("id/{id}")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<Void> deleteStation(
            @Parameter(description = "Уникальный параметр")
            @PathVariable("id") Integer id) {
        if (stationService.deleteStation(id))
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}
