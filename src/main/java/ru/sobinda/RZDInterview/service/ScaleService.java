package ru.sobinda.RZDInterview.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sobinda.RZDInterview.dto.ScaleAddDto;
import ru.sobinda.RZDInterview.dto.ScaleDto;
import ru.sobinda.RZDInterview.entity.ScaleEntity;
import ru.sobinda.RZDInterview.exception.InvalidRzdException;
import ru.sobinda.RZDInterview.repository.DirectoryOfCargoNomenclaturesRepository;
import ru.sobinda.RZDInterview.repository.ScaleRepository;
import ru.sobinda.RZDInterview.repository.WagonPassportRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScaleService {

    private final ScaleRepository scaleRepository;
    private final DirectoryOfCargoNomenclaturesRepository directoryRepository;
    private final WagonPassportRepository wagonPassportRepository;

    public List<ScaleDto> getAllScale() {
        var list = scaleRepository.findAll();
        return list.stream()
                .map(ScaleDto::addScale)
                .collect(Collectors.toList());
    }

    public boolean addScale(ScaleAddDto scaleAddDto) {
        var wagonId = wagonPassportRepository.findById((scaleAddDto.getWagonPassportId()))
                .orElseThrow(() -> new InvalidRzdException("Такого вагона не существует"));
        var id = scaleRepository.save(ScaleEntity.addScale(scaleAddDto, wagonId)).getId();
        return scaleRepository.existsById(id);
    }
}
