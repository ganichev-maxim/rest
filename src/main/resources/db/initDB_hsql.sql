DROP TABLE person IF EXISTS;

CREATE TABLE person
(
  id               BIGINT                  PRIMARY KEY,
  name             VARCHAR(255)            NOT NULL,
  birth_date       TIMESTAMP               NOT NULL
);