package ru.sobinda.RZDInterview.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sobinda.RZDInterview.dto.ScaleCreateDto;
import ru.sobinda.RZDInterview.dto.ScaleDto;
import ru.sobinda.RZDInterview.entity.DirectoryOfCargoNomenclaturesEntity;
import ru.sobinda.RZDInterview.entity.ScaleEntity;
import ru.sobinda.RZDInterview.entity.WagonPassportEntity;
import ru.sobinda.RZDInterview.exception.InvalidRzdException;
import ru.sobinda.RZDInterview.repository.DirectoryOfCargoNomenclaturesRepository;
import ru.sobinda.RZDInterview.repository.ScaleRepository;
import ru.sobinda.RZDInterview.repository.WagonPassportRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
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

    @Transactional
    public boolean addScale(ScaleCreateDto scaleCreateDto) {
        var wagonId = getWagonPassport(scaleCreateDto);
        var listDirectory = getNomenclaturesEntities(scaleCreateDto);
        if (listDirectory == null)
            return false;
        var id = scaleRepository.save(ScaleEntity.addScale(scaleCreateDto, wagonId, listDirectory)).getId();
        return scaleRepository.existsById(id);
    }

    public Optional<ScaleDto> getScaleById(Integer id) {
        var exist = scaleRepository.existsById(id);
        if (exist) {
            return scaleRepository.findById(id)
                    .map(ScaleDto::addScale);
        }
        return Optional.empty();
    }

    @Transactional
    public boolean updateScaleById(Integer id, ScaleCreateDto updateScaleDto) {
        var item = scaleRepository.existsById(id);
        if (item) {
            var wagonId = getWagonPassport(updateScaleDto);
            var listDirectory = getNomenclaturesEntities(updateScaleDto);
            var result = scaleRepository.findById(id)
                    .map(existed -> ScaleEntity.updateScaleById(
                            existed,
                            updateScaleDto,
                            wagonId,
                            listDirectory)
                    );
            scaleRepository.save(result.get());
        }
        return item;
    }

    private List<DirectoryOfCargoNomenclaturesEntity> getNomenclaturesEntities(ScaleCreateDto scaleCreateDto) {
        var listDirectory = directoryRepository.findAllById(scaleCreateDto.getNomenclatures());
        if (listDirectory.isEmpty()) {
            return null;
        }
        return listDirectory;
    }

    private WagonPassportEntity getWagonPassport(ScaleCreateDto scaleCreateDto) {
        return wagonPassportRepository.findById((scaleCreateDto.getWagonPassportId()))
                .orElseThrow(() -> {
                    throw new InvalidRzdException("Такого вагона не существует");
                });
    }
}
