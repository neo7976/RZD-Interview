package ru.sobinda.RZDInterview.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import ru.sobinda.RZDInterview.dto.WagonPassportDto;
import ru.sobinda.RZDInterview.service.WagonPassportService;

import java.util.List;

@RestController
@RequestMapping("wagon_passport/")
@RequiredArgsConstructor
@Tag(name = "Паспорт вагонов", description = "CRUD WagonPassport")
public class WagonPassportController {

    private final WagonPassportService wagonPassportService;

    @GetMapping("id/{id}")
    @Secured({"ROLE_ADMIN", "ROLE_USER", "ROLE_GUEST"})
    @Operation(summary = "Получить список WagonPassport по id")
    public ResponseEntity<WagonPassportDto> getWagonPassport(
            @Parameter(description = "Уникальный параметр")
            @PathVariable("id") Integer id) {
        var result = wagonPassportService.getWagonPassport(id);
        return result.map(
                        wagonPassportDto -> new ResponseEntity<>(
                                wagonPassportDto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @GetMapping("number/{number}")
    @Secured({"ROLE_ADMIN", "ROLE_USER", "ROLE_GUEST"})
    @Operation(summary = "Получить список WagonPassport по номеру")
    public ResponseEntity<WagonPassportDto> getWagonPassportByNumber(
            @Parameter(description = "Номер вагона")
            @PathVariable("number") int number) {
        var result = wagonPassportService.getWagonPassportByNumber(number);
        return result.map(
                        wagonPassportDto -> new ResponseEntity<>(
                                wagonPassportDto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER", "ROLE_GUEST"})
    @GetMapping("all")
    @Operation(summary = "Получить список всех WagonPassport")
    public ResponseEntity<List<WagonPassportDto>> getAllWagonPassport() {
        var result = wagonPassportService.getAllWagonPassport();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("add")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @Operation(summary = "Добавить новый WagonPassport")
    public ResponseEntity<Void> addWagonPassport(@RequestBody WagonPassportDto wagonPassportDto) {
        if (wagonPassportService.addWagonPassport(wagonPassportDto))
            return new ResponseEntity<>(HttpStatus.CREATED);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("id/{id}")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @Operation(summary = "Обновить данные WagonPassport по id")
    public ResponseEntity<Void> updateWagonPassport(
            @Parameter(description = "Уникальный параметр")
            @PathVariable("id") Integer id,
            @RequestBody WagonPassportDto wagonPassportDto) {
        if (wagonPassportService.updateWagonPassport(id, wagonPassportDto))
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Operation(summary = "Удалить данные WagonPassport по id")
    @DeleteMapping("id/{id}")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<Void> deleteWagonPassport(
            @Parameter(description = "Уникальный параметр")
            @PathVariable("id") Integer id) {
        if (wagonPassportService.deleteWagonPassport(id))
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}
