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
import ru.sobinda.RZDInterview.dto.DirectoryOfCargoNomenclaturesDto;
import ru.sobinda.RZDInterview.service.DirectoryOfCargoNomenclaturesService;

import java.util.List;

@RestController
@RequestMapping("nomenclatures/")
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Номенклатура товаров", description = "CRUD DirectoryOfCargoNomenclatures")
public class DirectoryOfCargoNomenclaturesController {

    private final DirectoryOfCargoNomenclaturesService directoryService;

    @GetMapping("id/{id}")
    @Secured({"ROLE_ADMIN", "ROLE_USER", "ROLE_GUEST"})
    @Operation(summary = "Получить список Номенклатуры товара по id")
    public ResponseEntity<DirectoryOfCargoNomenclaturesDto> getNomenclatureById(
            @Parameter(description = "Уникальный параметр")
            @PathVariable("id") Integer id) {
        log.info("Запрос на получение товара по {}: {}", "id", id);
        var result = directoryService.getNomenclatureById(id);
        return result.map(
                        directory -> new ResponseEntity<>(
                                directory, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @GetMapping("code/{code}")
    @Secured({"ROLE_ADMIN", "ROLE_USER", "ROLE_GUEST"})
    @Operation(summary = "Получить список Номенклатуры товара по коду")
    public ResponseEntity<DirectoryOfCargoNomenclaturesDto> getNomenclatureByCode(
            @Parameter(description = "Код продукта")
            @PathVariable("code") String code) {
        log.info("Запрос на получение товара по {}: {}", "code", code);
        var result = directoryService.getNomenclatureByCode(code);
        return result.map(
                        directory -> new ResponseEntity<>(
                                directory, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @GetMapping("shipping_name/{shipping_name}")
    @Secured({"ROLE_ADMIN", "ROLE_USER", "ROLE_GUEST"})
    @Operation(summary = "Получить список Номенклатуры товара по Имени продукта")
    public ResponseEntity<DirectoryOfCargoNomenclaturesDto> getNomenclatureByName(
            @Parameter(description = "Наименование продукта")
            @PathVariable("shipping_name") String shippingName) {
        log.info("Запрос на получение товара по {}: {}", "shippingName", shippingName);
        var result = directoryService.getNomenclatureByName(shippingName);
        return result.map(
                        directory -> new ResponseEntity<>(
                                directory, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @GetMapping("all")
    @Secured({"ROLE_ADMIN", "ROLE_USER", "ROLE_GUEST"})
    @Operation(summary = "Получить список всех Номенклатуры товаров")
    public ResponseEntity<List<DirectoryOfCargoNomenclaturesDto>> getAllNomenclatures() {
        log.info("Запрос на получение всех позиций");
        var result = directoryService.getAllNomenclatures();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("add")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @Operation(summary = "Добавить новую Номенклатуру товара")
    public ResponseEntity<Void> addNomenclature(@RequestBody DirectoryOfCargoNomenclaturesDto directory) {
        log.info("Запрос на добавление товара: {}", directory.getShippingName());
        if (directoryService.addNomenclature(directory))
            return new ResponseEntity<>(HttpStatus.CREATED);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("id/{id}")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @Operation(summary = "Обновить данные Номенклатуры товара по id")
    public ResponseEntity<Void> updateNomenclatureById(
            @Parameter(description = "Уникальный параметр")
            @PathVariable("id") Integer id,
            @RequestBody DirectoryOfCargoNomenclaturesDto directoryDto) {
        log.info("Запрос на изменение товара по {}: {}", "id", id);
        if (directoryService.updateNomenclatureById(id, directoryDto))
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Operation(summary = "Удалить данные Номенклатуры товара по id")
    @DeleteMapping("id/{id}")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<Void> deleteNomenclatureById(
            @Parameter(description = "Уникальный параметр")
            @PathVariable("id") Integer id) {
        log.info("Запрос на удаление товара по {}: {}", "id", id);
        if (directoryService.deleteNomenclatureById(id))
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}
