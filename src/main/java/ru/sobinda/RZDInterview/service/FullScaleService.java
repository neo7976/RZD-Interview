package ru.sobinda.RZDInterview.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.sobinda.RZDInterview.dto.fullscale.FullScaleCreateDto;
import ru.sobinda.RZDInterview.dto.fullscale.FullScaleDto;
import ru.sobinda.RZDInterview.entity.FullScaleEntity;
import ru.sobinda.RZDInterview.entity.ScaleEntity;
import ru.sobinda.RZDInterview.repository.FullScaleRepository;
import ru.sobinda.RZDInterview.repository.ScaleRepository;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class FullScaleService {

    private final FullScaleRepository fullScaleRepository;
    private final ScaleRepository scaleRepository;

    public List<FullScaleDto> getAllFullScale() {
        var list = fullScaleRepository.findAll();
        return list.stream()
                .map(FullScaleDto::addFullScale)
                .collect(Collectors.toList());
    }

    public Optional<FullScaleDto> getFullScaleById(Integer id) {
        var exist = fullScaleRepository.existsById(id);
        if (exist) {
            return fullScaleRepository.findById(id)
                    .map(FullScaleDto::addFullScale);
        }
        return Optional.empty();
    }

    @Transactional
    public boolean addFullScale(FullScaleCreateDto fullScaleCreateDto) {
        var scalesList = getScalesEntities(fullScaleCreateDto);
        var id = fullScaleRepository.save(
                FullScaleEntity.addScale(fullScaleCreateDto,
                scalesList)).getId();
        scaleRepository.updateSerialNumber(id);
        return fullScaleRepository.existsById(id);
    }

    @Transactional
    public boolean updateFullScaleById(Integer id, FullScaleCreateDto fullScaleCreateDto) {
        var item = fullScaleRepository.existsById(id);
        if (item) {
            var scalesList = getScalesEntities(fullScaleCreateDto);
            var result = fullScaleRepository.findById(id)
                    .map(existed -> FullScaleEntity.updateScaleById(
                            existed,
                            fullScaleCreateDto,
                            scalesList)
                    );
            scaleRepository.deleteSerialNumber(id);
            scaleRepository.updateSerialNumber(id);
            fullScaleRepository.save(result.get());
        }
        return item;
    }

    @Transactional
    public boolean deleteFullScaleById(Integer id) {
        var exist = fullScaleRepository.existsById(id);
        scaleRepository.deleteSerialNumber(id);
        if (exist) {
            fullScaleRepository.deleteById(id);
        }
        return exist;
    }

    private List<ScaleEntity> getScalesEntities(FullScaleCreateDto fullScaleCreateDto) {
        var listScales = scaleRepository.findAllById(fullScaleCreateDto.getScales());
        if (listScales.isEmpty()) {
            return Collections.emptyList();
        }
        return listScales;
    }
}
