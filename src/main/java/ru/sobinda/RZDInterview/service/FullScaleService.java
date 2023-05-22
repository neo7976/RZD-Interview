package ru.sobinda.RZDInterview.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.sobinda.RZDInterview.dto.FullScaleDto;
import ru.sobinda.RZDInterview.dto.ScaleDto;
import ru.sobinda.RZDInterview.repository.FullScaleRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class FullScaleService {

    private final FullScaleRepository fullScaleRepository;

    public List<FullScaleDto> getAllFullScale() {
        var list = fullScaleRepository.findAll();
        return list.stream()
                .map(FullScaleDto::addFullScale)
                .collect(Collectors.toList());
    }
}
