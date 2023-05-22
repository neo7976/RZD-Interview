package ru.sobinda.RZDInterview.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.sobinda.RZDInterview.dto.WagonPassportDto;
import ru.sobinda.RZDInterview.enums.WagonType;
import ru.sobinda.RZDInterview.service.WagonPassportService;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WagonPassportControllerTest {

    @Mock
    WagonPassportService wagonPassportService;

    @InjectMocks
    WagonPassportController wagonPassportController;
    private final int ID = 1;
    private final int NUMBER = 1111;
    WagonPassportDto wagonPassportDto;


    @BeforeEach
    void setUp() {
        System.out.println("Начало теста:");
        wagonPassportDto = WagonPassportDto.builder()
                .number(NUMBER)
                .type(WagonType.COVERED1)
                .build();
    }

    @AfterEach
    void tearDown() {
        System.out.println("Окончание теста");
    }

    @Test
    void getWagonPassportOk() {
        when(wagonPassportService.getWagonPassport(ID)).thenReturn(Optional.of(wagonPassportDto));
        var result = wagonPassportController.getWagonPassport(ID);
        Assertions.assertEquals(new ResponseEntity<>(wagonPassportDto,HttpStatus.OK), result);
    }
    @Test
    void getWagonPassportBad() {
        when(wagonPassportService.getWagonPassport(ID)).thenReturn(Optional.empty());
        var result = wagonPassportController.getWagonPassport(ID);
        Assertions.assertEquals( new ResponseEntity<>(HttpStatus.BAD_REQUEST), result);
    }


    @Test
    void getWagonPassportByNumberOk() {
        when(wagonPassportService.getWagonPassportByNumber(NUMBER)).thenReturn(Optional.of(wagonPassportDto));
        var result = wagonPassportController.getWagonPassportByNumber(NUMBER);
        Assertions.assertEquals(new ResponseEntity<>(wagonPassportDto,HttpStatus.OK), result);
    }

    @Test
    void getAllWagonPassport() {
    }

    @Test
    void addWagonPassport() {
    }

    @Test
    void updateWagonPassport() {
    }

    @Test
    void deleteWagonPassport() {
    }
}