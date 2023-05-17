package ru.sobinda.RZDInterview.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum WagonType {
    SEMI_WAGON1("6-осный цельнометаллический полувагон", 94_000, 31_000),
    SEMI_WAGON2("4-осный цельнометаллический полувагон, модель 12-1000", 69_000, 22_000),
    COVERED1("4-осный крытый вагон (с металлической торцовой стеной), модель 11-066", 66_000, 23_000),
    COVERED2("4-осный крытый цельнометаллический вагон", 68_000, 22_880),
    COVERED3("4-осный крытый вагон, модель 11-274", 50_000, 35_000);


    private final String nameType;
    private final int loadCapacity;
    private final int tareWeight;
}
