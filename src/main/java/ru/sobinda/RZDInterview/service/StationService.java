package ru.sobinda.RZDInterview.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sobinda.RZDInterview.dto.StationDto;
import ru.sobinda.RZDInterview.dto.WagonPassportDto;
import ru.sobinda.RZDInterview.repository.StationRepository;

import java.util.List;
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
}
