package ru.sobinda.RZDInterview.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sobinda.RZDInterview.dto.StationDto;
import ru.sobinda.RZDInterview.service.StationService;

import java.util.List;

@RestController
@RequestMapping("station/")
@RequiredArgsConstructor
@Tag(name = "Станционная модель", description = "CRUD Station")
public class StationController {

    private final StationService stationService;


    @Operation(summary = "Получить все станции")
    @GetMapping("all")
    public ResponseEntity<List<StationDto>> getAll(){
        var result = stationService.getAllWagonPassport();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
