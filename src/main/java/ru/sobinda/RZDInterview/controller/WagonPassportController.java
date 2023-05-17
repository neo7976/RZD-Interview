package ru.sobinda.RZDInterview.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sobinda.RZDInterview.dto.WagonPassportDto;
import ru.sobinda.RZDInterview.service.WagonPassportService;

import java.util.List;

@RestController
@RequestMapping("wagon_passport/")
@RequiredArgsConstructor
@Tag(name = "WagonPassport", description = "CRUD WagonPassport")
public class WagonPassportController {

    private final WagonPassportService wagonPassportService;

    @GetMapping("id/{id}")
    @Operation(summary = "Получить список всех WagonPassport")
    public ResponseEntity<WagonPassportDto> getWagonPassport(
            @Parameter(description = "Уникальный параметр")
            @PathVariable("id") Integer id) {
        var result = wagonPassportService.getWagonPassport(id);
        return result.map(wagonPassportDto -> new ResponseEntity<>(wagonPassportDto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @GetMapping("all")
    public ResponseEntity<List<WagonPassportDto>> getAllWagonPassport() {
        var result = wagonPassportService.getAllWagonPassport();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("add")
    public ResponseEntity<Void> addWagonPassport(@RequestBody WagonPassportDto wagonPassportDto) {
        if (wagonPassportService.addWagonPassport(wagonPassportDto))
            return new ResponseEntity<>(HttpStatus.CREATED);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("id/{id}")
    public ResponseEntity<Void> updateWagonPassport(@PathVariable("id") Integer id, @RequestBody WagonPassportDto wagonPassportDto) {
        if (wagonPassportService.updateWagonPassport(id, wagonPassportDto))
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @DeleteMapping("id/{id}")
    public ResponseEntity<Void> deleteWagonPassport(@PathVariable("id") Integer id) {
        if (wagonPassportService.deleteWagonPassport(id))
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}
