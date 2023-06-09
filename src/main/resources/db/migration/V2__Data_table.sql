INSERT INTO station_model (station_name)
VALUES ('Ярославский вокзал'),
       ('Казанский вокзал'),
       ('Ленинградский вокзал'),
       ('Киевский вокзал'),
       ('Павелецкий вокзал');
INSERT INTO station_entity_number (station_entity_id, number)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (1, 4),
       (2, 1),
       (2, 2),
       (2, 3),
       (2, 4),
       (3, 1),
       (3, 2),
       (3, 3),
       (3, 4),
       (4, 1),
       (4, 2),
       (4, 3),
       (4, 4),
       (5, 1),
       (5, 2),
       (5, 3),
       (5, 4);
INSERT INTO wagon_passport (LOAD_CAPACITY, NUMBER, TARE_WEIGHT, TYPE)
VALUES (94000, 1111, 31000, 'SEMI_WAGON1'),
       (69000, 2222, 22000, 'SEMI_WAGON2'),
       (66000, 3333, 23000, 'COVERED1'),
       (68000, 4444, 22800, 'COVERED2'),
       (50000, 5555, 35000, 'COVERED3');

insert into scale (cargo_weight, wagon_weight, wagon_number)
values (5885, 35000, 5),
       (5885, 35000, 5),
       (5885, 22800, 4),
       (5885, 22800, 4),
       (5885, 23000, 3),
       (5885, 23000, 3);



INSERT INTO directory_of_cargo_nomenclatures(code, shipping_name, nomenclature_id)
VALUES ('495151', 'Товар 1', 1),
       ('495152', 'Товар 2', 1),
       ('495153', 'Товар 3', 1),
       ('495154', 'Товар 4', 1),
       ('495155', 'Товар 5', 1),
       ('495156', 'Товар 6', 2),
       ('495157', 'Товар 7', 2),
       ('495158', 'Товар 8', 2),
       ('495159', 'Товар 9', 3),
       ('495160', 'Товар 10', 4),
       ('495161', 'Товар 11', 4),
       ('495162', 'Товар 12', 5),
       ('495163', 'Товар 13', 5),
       ('495164', 'Товар 14', 6),
       ('495165', 'Товар 15', 6),
       ('495166', 'Товар 16', 6),
       ('495167', 'Товар 17', 6);

INSERT INTO full_scale(composition_number)
values (585496);

update scale
SET scale_id=1
where id < 4;

update scale
set serial_number=1
where id = 1;

update scale
set serial_number=2
where id = 2;

update scale
set serial_number=3
where id = 3;

