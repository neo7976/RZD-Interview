package ru.sobinda.RZDInterview.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "station_model")
public class StationEntity {

    /**
     * Станционная модель (Станции, Пути станций)
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "station_name", unique = true)
    private String stationName;

    //Додумать пути станций
    @ElementCollection
    private List<Integer> number;

}
