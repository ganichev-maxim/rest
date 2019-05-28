DROP TABLE person IF EXISTS;
DROP TABLE car IF EXISTS;

CREATE TABLE person
(
  id               BIGINT                  PRIMARY KEY,
  name             VARCHAR(2000)            NOT NULL,
  birth_date       TIMESTAMP               NOT NULL
);


CREATE TABLE car
(
    id               BIGINT                  PRIMARY KEY,
    model            VARCHAR(2000)           NOT NULL,
    horse_power      INTEGER                 NOT NULL
);