DELETE FROM car;
DELETE FROM person;

INSERT INTO person (id, name, birth_date) VALUES
  (2, 'user', '2001-05-30'),
  (3, 'user2', '1999-04-30'),
  (4, 'user4', '1999-04-30');

INSERT INTO car (id, model, horse_power, owner_id) VALUES
    (2, 'BMW-X3', 400, 2),
    (3, 'BMW-X6', 500, 3);