package ru.sobinda.RZDInterview.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.sobinda.RZDInterview.dto.WagonPassportDto;
import ru.sobinda.RZDInterview.enums.WagonType;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "wagon_passport")
public class WagonPassportEntity {

    /**
     * •	Паспорт вагонов (Номер, Тип, Вес тары, Грузоподъемность)
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private int number;

    //При необходимости добавить Enums с типом вагона
    @Enumerated(EnumType.STRING)
    private WagonType type;

    @Column(name = "tare_weight")
    private int tareWeight;

    @Column(name = "load_capacity")
    private int loadCapacity;

    public static WagonPassportEntity addWagonPassport(WagonPassportDto wagonPassportDto) {
       return WagonPassportEntity.builder()
                .type(wagonPassportDto.getType())
                .number(wagonPassportDto.getNumber())
                .loadCapacity(wagonPassportDto.getType().getLoadCapacity())
                .tareWeight(wagonPassportDto.getType().getTareWeight())
                .build();
    }
}
