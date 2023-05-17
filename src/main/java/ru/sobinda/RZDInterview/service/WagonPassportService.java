package ru.sobinda.RZDInterview.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.sobinda.RZDInterview.dto.WagonPassportDto;
import ru.sobinda.RZDInterview.entity.WagonPassportEntity;
import ru.sobinda.RZDInterview.repository.WagonPassportRepository;

@Service
@RequiredArgsConstructor
@Log4j2

public class WagonPassportService {

    private final WagonPassportRepository wagonPassportRepository;

    public boolean addWagonPassport(WagonPassportDto wagonPassportDto) {
        var id = wagonPassportRepository.save(WagonPassportEntity.addWagonPassport(wagonPassportDto)).getId();
        return wagonPassportRepository.existsById(id);
    }
}
