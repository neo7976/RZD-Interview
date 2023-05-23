package ru.sobinda.RZDInterview.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.sobinda.RZDInterview.dto.DirectoryOfCargoNomenclaturesDto;
import ru.sobinda.RZDInterview.entity.DirectoryOfCargoNomenclaturesEntity;
import ru.sobinda.RZDInterview.repository.DirectoryOfCargoNomenclaturesRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class DirectoryOfCargoNomenclaturesService {

    private final DirectoryOfCargoNomenclaturesRepository directoryRepository;

    public List<DirectoryOfCargoNomenclaturesDto> getAllNomenclatures() {
        var list = directoryRepository.findAll();
        log.info("Наличие списка номенклатуры в запросе: {}", !list.isEmpty() );
        return list.stream()
                .map(DirectoryOfCargoNomenclaturesDto::addDirectoryOfCargoNomenclatures)
                .collect(Collectors.toList());
    }

    public Optional<DirectoryOfCargoNomenclaturesDto> getNomenclatureById(Integer id) {
        var exist = directoryRepository.existsById(id);
        log.info("Наличие номенклатуры по {}={} : {}", "id", id, exist);
        if (exist) {
            return directoryRepository.findById(id)
                    .map(DirectoryOfCargoNomenclaturesDto::addDirectoryOfCargoNomenclatures);
        }
        return Optional.empty();
    }

    public Optional<DirectoryOfCargoNomenclaturesDto> getNomenclatureByName(String name) {
        var exist = directoryRepository.existsByShippingNameIgnoreCase(name);
        log.info("Наличие номенклатуры по {}={} : {}", "name", name, exist);
        if (exist) {
            return directoryRepository.findByShippingNameIgnoreCase(name)
                    .map(DirectoryOfCargoNomenclaturesDto::addDirectoryOfCargoNomenclatures);
        }
        return Optional.empty();
    }

    public Optional<DirectoryOfCargoNomenclaturesDto> getNomenclatureByCode(String code) {
        var exist = directoryRepository.existsByCode(code);
        log.info("Наличие номенклатуры по {}={} : {}", "code", code, exist);
        if (exist) {
            return directoryRepository.findByCode(code)
                    .map(DirectoryOfCargoNomenclaturesDto::addDirectoryOfCargoNomenclatures);
        }
        return Optional.empty();
    }

    public boolean addNomenclature(DirectoryOfCargoNomenclaturesDto directory) {
        var id = directoryRepository.save(DirectoryOfCargoNomenclaturesEntity
                .addDirectoryOfCargoNomenclatures(directory)).getId();
        log.info("Сохранение номенклатуры под {}={}", "id", id);
        return directoryRepository.existsById(id);
    }

    public boolean updateNomenclatureById(Integer id, DirectoryOfCargoNomenclaturesDto directoryDto) {
        var item = directoryRepository.existsById(id);
        log.info("Изменение номенклатуры под {}={}", "id", id);
        if (item) {
            var result = directoryRepository.findById(id)
                    .map(
                            existed -> DirectoryOfCargoNomenclaturesEntity
                                    .updateDirectoryOfCargoNomenclatures(existed, directoryDto)
                    );
            directoryRepository.save(result.get());
        }
        return item;
    }

    public boolean deleteNomenclatureById(Integer id) {
        var exist = directoryRepository.existsById(id);
        log.info("Наличие номенклатуры под {}={} для удаления", "id", id);
        if (exist) {
            directoryRepository.deleteById(id);
        }
        return exist;
    }
}
