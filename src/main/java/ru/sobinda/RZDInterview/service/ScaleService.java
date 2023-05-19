package ru.sobinda.RZDInterview.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sobinda.RZDInterview.dto.ScaleDto;
import ru.sobinda.RZDInterview.dto.ScaleDtoImp;
import ru.sobinda.RZDInterview.entity.ScaleEntity;
import ru.sobinda.RZDInterview.repository.DirectoryOfCargoNomenclaturesRepository;
import ru.sobinda.RZDInterview.repository.ScaleRepository;
import ru.sobinda.RZDInterview.repository.WagonPassportRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScaleService {

    private final ScaleRepository scaleRepository;
    private final DirectoryOfCargoNomenclaturesRepository directory;
    private final WagonPassportRepository wagonPassportService;

    public List<ScaleDtoImp> getAllScale() {
        var list = scaleRepository.findAll();
        return list.stream()
                .map(ScaleDto::addScale)
                .collect(Collectors.toList());
    }

    public boolean addScale(ScaleDto scaleDto) {
        var id = scaleRepository.save(ScaleEntity.addScale(scaleDto)).getId();
        return scaleRepository.existsById(id);
    }
}
