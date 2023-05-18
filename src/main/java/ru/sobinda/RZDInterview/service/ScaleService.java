package ru.sobinda.RZDInterview.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sobinda.RZDInterview.repository.ScaleRepository;

@Service
@RequiredArgsConstructor
public class ScaleService {

    private final ScaleRepository scaleRepository;
}
