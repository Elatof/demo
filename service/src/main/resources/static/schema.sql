DROP TABLE IF EXISTS car_engine;
DROP TABLE IF EXISTS engine;
DROP TABLE IF EXISTS car;
DROP TABLE IF EXISTS car_company;

CREATE TABLE car_company
(
    id      SERIAL PRIMARY KEY,
    name    VARCHAR(32) NOT NULL UNIQUE,
    country VARCHAR(32) NOT NULL,
    city    VARCHAR(32) NOT NULL
);

CREATE TABLE car
(
    id                  SERIAL PRIMARY KEY,
    model_name          VARCHAR(32) NOT NULL,
    price               INTEGER     NOT NULL,
    date_of_manufacture DATE        NOT NULL,
    car_company_id      INTEGER     NOT NULL,

    CONSTRAINT fk_address
        FOREIGN KEY (car_company_id)
            REFERENCES car_company (id)
);

CREATE TABLE engine
(
    id                  SERIAL PRIMARY KEY,
    engine_model        VARCHAR(32) NOT NULL,
    horse_power         INTEGER     NOT NULL,
    number_of_cylinders INTEGER     NOT NULL,
    volume              FLOAT       NOT NULL
);

CREATE TABLE car_engine
(
    id        SERIAL PRIMARY KEY,
    car_id    INTEGER NOT NULL,
    engine_id INTEGER NOT NULL,

    CONSTRAINT fk_car
        FOREIGN KEY (car_id)
            REFERENCES car (id),

    CONSTRAINT fk_engine
        FOREIGN KEY (engine_id)
            REFERENCES engine (id)
);


INSERT INTO car_company(name, country, city)
VALUES ('Volkswagen', 'Germany', 'Volksburg'),
       ('Honda', 'Japan', 'Tokyo'),
       ('Skoda', 'Czech Republic', 'Prague');

INSERT INTO car(model_name, price, date_of_manufacture, car_company_id)
VALUES ('Volkswagen Jetta', 25000, '2017.02.06', 1),
       ('Honda Civic', 17500, '2020.08.25', 2),
       ('Volkswagen Passat', 31000, '2021.04.29', 1),
       ('Skoda Octavia', 21000, '2019.05.21', 3);

INSERT INTO engine(engine_model, horse_power, number_of_cylinders, volume)
VALUES ('1.4 TSI', 150, 4, 1.4),
       ('2.0 TDI', 180, 4, 2.0),
       ('1.5 L15B7', 174, 4, 1.5);

INSERT INTO car_engine(car_id, engine_id)
VALUES (1, 1),
       (4, 1),
       (3, 2),
       (2, 3);
