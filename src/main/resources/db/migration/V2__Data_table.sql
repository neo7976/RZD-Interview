INSERT INTO station_model (station_name)
VALUES ('Ярославский вокзал'),
       ('Казанский вокзал'),
       ('Ленинградский вокзал'),
       ('Киевский вокзал'),
       ('Павелецкий вокзал');
INSERT INTO station_entity_number (station_entity_id, number)
VALUES (1,1),
       (1,2),
       (1,3),
       (1,4),
       (2,1),
       (2,2),
       (2,3),
       (2,4),
       (3,1),
       (3,2),
       (3,3),
       (3,4),
       (4,1),
       (4,2),
       (4,3),
       (4,4),
       (5,1),
       (5,2),
       (5,3),
       (5,4);
INSERT INTO wagon_passport (LOAD_CAPACITY, NUMBER, TARE_WEIGHT, TYPE)
VALUES (94000,1111,31000, 'SEMI_WAGON1'),
       (69000,2222,22000, 'SEMI_WAGON2'),
       (66000,3333,23000, 'COVERED1'),
       (68000,4444,22800, 'COVERED2'),
       (50000,5555,35000, 'COVERED3');
