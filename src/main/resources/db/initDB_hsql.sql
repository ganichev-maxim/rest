DROP TABLE car IF EXISTS;
DROP TABLE person IF EXISTS;

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
    horse_power      INTEGER                 NOT NULL,
    owner_id         BIGINT                  NOT NULL,
    FOREIGN KEY (owner_id) REFERENCES person (id) ON DELETE CASCADE
);