package ru.sobinda.RZDInterview.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sobinda.RZDInterview.dto.ScaleAddDto;
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

    @PostMapping("add")
    @Operation(summary = "Добавить новый вагон с атрибутами")
    public ResponseEntity<Void> addScale(@RequestBody ScaleAddDto scaleAddDto) {
        log.info("Запрос на добавление нового вагона с атрибутами: {}", scaleAddDto.getWagonPassportId());
        if (scaleService.addScale(scaleAddDto))
            return new ResponseEntity<>(HttpStatus.CREATED);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
