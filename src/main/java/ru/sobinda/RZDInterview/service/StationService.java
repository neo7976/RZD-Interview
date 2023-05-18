package ru.sobinda.RZDInterview.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sobinda.RZDInterview.dto.StationDto;
import ru.sobinda.RZDInterview.entity.StationEntity;
import ru.sobinda.RZDInterview.repository.StationRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StationService {

    private final StationRepository stationRepository;

    public List<StationDto> getAllWagonPassport() {
        var list = stationRepository.findAll();
        return list.stream()
                .map(StationDto::addStationDto)
                .collect(Collectors.toList());
    }

    public Optional<StationDto> getStation(Integer id) {
        var exist = stationRepository.existsById(id);
        if (exist) {
            return stationRepository.findById(id)
                    .map(StationDto::addStationDto);
        }
        return Optional.empty();
    }

    public boolean addStation(StationDto stationDto) {
        var id = stationRepository.save(StationEntity.addStation(stationDto)).getId();
        return stationRepository.existsById(id);
    }

    public boolean updateStation(Integer id, StationDto stationDto) {
        var item = stationRepository.existsById(id);
        if (item) {
            var result = stationRepository.findById(id)
                    .map(existed -> StationEntity.updateStation(existed, stationDto));
            stationRepository.save(result.get());
        }
        return item;
    }

    public boolean deleteStation(Integer id) {
        var exist = stationRepository.existsById(id);
        if (exist) {
            stationRepository.deleteById(id);
        }
        return exist;
    }
}
