update scale
set serial_number = null
where scale_id = 1;

UPDATE scale
SET serial_number = COALESCE((SELECT MAX(serial_number) FROM scale WHERE scale_id = :scale_id), 0) + row_number
FROM (
         SELECT id, ROW_NUMBER() OVER (ORDER BY id) AS row_number
         FROM scale
         WHERE scale_id = :scale_id AND serial_number IS NULL
     ) AS subquery
WHERE scale.id = subquery.id;


