package ru.sobinda.RZDInterview.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sobinda.RZDInterview.dto.ScaleDto;
import ru.sobinda.RZDInterview.repository.ScaleRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScaleService {

    private final ScaleRepository scaleRepository;

    public List<ScaleDto> getAllScale() {
        var list = scaleRepository.findAll();
        return list.stream()
                .map(ScaleDto::addScale)
                .collect(Collectors.toList());
    }
}
