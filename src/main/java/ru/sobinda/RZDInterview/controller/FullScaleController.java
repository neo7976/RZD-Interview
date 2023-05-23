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
import ru.sobinda.RZDInterview.dto.fullscale.FullScaleCreateDto;
import ru.sobinda.RZDInterview.dto.fullscale.FullScaleDto;
import ru.sobinda.RZDInterview.service.FullScaleService;

import java.util.List;

@RestController
@RequestMapping("full_scale/")
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Список составов поездов", description = "CRUD FullScale")
public class FullScaleController {

    private final FullScaleService fullScaleService;

    @Operation(summary = "Получить все составы поездов")
    @Secured({"ROLE_ADMIN", "ROLE_USER", "ROLE_GUEST"})
    @GetMapping("all")
    public ResponseEntity<List<FullScaleDto>> getAll() {
        log.info("Запрос на получение всех поездов");
        var result = fullScaleService.getAllFullScale();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("id/{id}")
    @Secured({"ROLE_ADMIN", "ROLE_USER", "ROLE_GUEST"})
    @Operation(summary = "Получить поезд с составами по id")
    public ResponseEntity<FullScaleDto> getFullScaleById(
            @Parameter(description = "Уникальный параметр")
            @PathVariable("id") Integer id) {
        log.info("Запрос на получение состава поезда по {}={}", "id", id);
        var result = fullScaleService.getFullScaleById(id);
        return result.map(
                        fullScaleDto -> new ResponseEntity<>(
                                fullScaleDto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @PostMapping("add")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @Operation(summary = "Добавить новый поезд с составами")
    public ResponseEntity<Void> addFullScale(@RequestBody FullScaleCreateDto fullScaleDto) {
        log.info("Запрос на добавление нового поезда: {}", fullScaleDto.getCompositionNumber());
        if (fullScaleService.addFullScale(fullScaleDto))
            return new ResponseEntity<>(HttpStatus.CREATED);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("id/{id}")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @Operation(summary = "Обновить поезд с составами по id")
    public ResponseEntity<Void> updateFullScaleById(
            @Parameter(description = "Уникальный параметр")
            @PathVariable("id") Integer id,
            @RequestBody FullScaleCreateDto fullScaleCreateDto) {
        log.info("Запрос на изменение поезда с составами по {}: {}", "id", id);
        if (fullScaleService.updateFullScaleById(id, fullScaleCreateDto))
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Operation(summary = "Удалить поезд с составами по id")
    @DeleteMapping("id/{id}")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<Void> deleteFullScaleById(
            @Parameter(description = "Уникальный параметр")
            @PathVariable("id") Integer id) {
        log.info("Запрос на удаление поезда с составами по {}: {}", "id", id);
        if (fullScaleService.deleteFullScaleById(id))
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}
