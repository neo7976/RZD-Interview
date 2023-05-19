package ru.sobinda.RZDInterview.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sobinda.RZDInterview.dto.FullScaleDto;
import ru.sobinda.RZDInterview.service.FullScaleService;

import java.util.List;

@RestController
@RequestMapping("full_scale/")
@RequiredArgsConstructor
@Tag(name = "Список составов поездов", description = "CRUD FullScale")
public class FullScaleController {

    private final FullScaleService fullScaleService;

    @Operation(summary = "Получить все составы поездов")
    @GetMapping("all")
    public ResponseEntity<List<FullScaleDto>> getAll() {
        var result = fullScaleService.getAllFullScale();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
