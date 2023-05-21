package ru.sobinda.RZDInterview.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sobinda.RZDInterview.dto.ScaleCreateDto;
import ru.sobinda.RZDInterview.dto.ScaleDto;
import ru.sobinda.RZDInterview.service.ScaleService;

import java.util.List;

@RestController
@RequestMapping("scale/")
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Список вагонов с атрибутами", description = "CRUD Scale")
public class ScaleController {

    private final ScaleService scaleService;

    @Operation(summary = "Получить все Вагоны")
    @GetMapping("all")
    public ResponseEntity<List<ScaleDto>> getAll() {
        var result = scaleService.getAllScale();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("id/{id}")
    @Operation(summary = "Получить вагон с атрибутами по id")
    public ResponseEntity<ScaleDto> getScaleById(
            @Parameter(description = "Уникальный параметр")
            @PathVariable("id") Integer id) {
        var result = scaleService.getScaleById(id);
        return result.map(
                        scaleDto -> new ResponseEntity<>(
                                scaleDto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @PostMapping("add")
    @Operation(summary = "Добавить новый вагон с атрибутами")
    public ResponseEntity<Void> addScale(@RequestBody ScaleCreateDto scaleCreateDto) {
        log.info("Запрос на добавление нового вагона с атрибутами: {}", scaleCreateDto.getWagonPassportId());
        if (scaleService.addScale(scaleCreateDto))
            return new ResponseEntity<>(HttpStatus.CREATED);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("id/{id}")
    @Operation(summary = "Обновить атрибуты вагона по id")
    public ResponseEntity<Void> updateScale(
            @Parameter(description = "Уникальный параметр")
            @PathVariable("id") Integer id,
            @RequestBody ScaleCreateDto updateScaleDto) {
        if (scaleService.updateScaleById(id, updateScaleDto))
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

}
