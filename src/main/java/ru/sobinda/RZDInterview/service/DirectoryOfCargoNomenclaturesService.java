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
        return list.stream()
                .map(DirectoryOfCargoNomenclaturesDto::addDirectoryOfCargoNomenclatures)
                .collect(Collectors.toList());
    }

    public Optional<DirectoryOfCargoNomenclaturesDto> getNomenclatureById(Integer id) {
        var exist = directoryRepository.existsById(id);
        if (exist) {
            return directoryRepository.findById(id)
                    .map(DirectoryOfCargoNomenclaturesDto::addDirectoryOfCargoNomenclatures);
        }
        return Optional.empty();
    }

    public Optional<DirectoryOfCargoNomenclaturesDto> getNomenclatureByName(String name) {
        var exist = directoryRepository.existsByShippingNameIgnoreCase(name);
        if (exist) {
            return directoryRepository.findByShippingNameIgnoreCase(name)
                    .map(DirectoryOfCargoNomenclaturesDto::addDirectoryOfCargoNomenclatures);
        }
        return Optional.empty();
    }

    public Optional<DirectoryOfCargoNomenclaturesDto> getNomenclatureByCode(String code) {
        var exist = directoryRepository.existsByCode(code);
        if (exist) {
            return directoryRepository.findByCode(code)
                    .map(DirectoryOfCargoNomenclaturesDto::addDirectoryOfCargoNomenclatures);
        }
        return Optional.empty();
    }

    public boolean addNomenclature(DirectoryOfCargoNomenclaturesDto directory) {
        var id = directoryRepository.save(DirectoryOfCargoNomenclaturesEntity
                .addDirectoryOfCargoNomenclatures(directory)).getId();
        return directoryRepository.existsById(id);
    }

    public boolean updateNomenclatureById(Integer id, DirectoryOfCargoNomenclaturesDto directoryDto) {
        var item = directoryRepository.existsById(id);
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
        if (exist) {
            directoryRepository.deleteById(id);
        }
        return exist;
    }
}
