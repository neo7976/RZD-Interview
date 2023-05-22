package ru.sobinda.RZDInterview.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import ru.sobinda.RZDInterview.dto.scale.ScaleCreateDto;
import ru.sobinda.RZDInterview.dto.scale.ScaleDto;
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
    @Secured({"ROLE_ADMIN", "ROLE_USER", "ROLE_GUEST"})
    @GetMapping("all")
    public ResponseEntity<List<ScaleDto>> getAll() {
        var result = scaleService.getAllScale();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("id/{id}")
    @Secured({"ROLE_ADMIN", "ROLE_USER", "ROLE_GUEST"})
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
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @Operation(summary = "Добавить новый вагон с атрибутами")
    public ResponseEntity<Void> addScale(@RequestBody ScaleCreateDto scaleCreateDto) {
        log.info("Запрос на добавление нового вагона с атрибутами: {}", scaleCreateDto.getWagonPassportId());
        if (scaleService.addScale(scaleCreateDto))
            return new ResponseEntity<>(HttpStatus.CREATED);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("id/{id}")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @Operation(summary = "Обновить атрибуты вагона по id")
    public ResponseEntity<Void> updateScale(
            @Parameter(description = "Уникальный параметр")
            @PathVariable("id") Integer id,
            @RequestBody ScaleCreateDto updateScaleDto) {
        log.info("Запрос на изменение вагона с атрибутами по {}: {}", "id", id);
        if (scaleService.updateScaleById(id, updateScaleDto))
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Operation(summary = "Удалить атрибуты вагона по id")
    @DeleteMapping("id/{id}")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<Void> deleteScaleById(
            @Parameter(description = "Уникальный параметр")
            @PathVariable("id") Integer id) {
        log.info("Запрос на удаление вагона с атрибутами по {}: {}", "id", id);
        if (scaleService.deleteScaleById(id))
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}
