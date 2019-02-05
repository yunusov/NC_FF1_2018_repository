INSERT INTO account (username, email, password, balance, active) VALUES
  -- password: 'admin'
  ('admin', 'admin@fly.com', '$2a$08$.OTxetRFa4QXVrgibkt3MelvABbJBQxRGe2SJgvaudIr9S9b/NUWu', 100000, true),
  -- password:  'd'
  ('d', 'dod@gmail.com', '$2a$08$roV0nigJUEakt9DRt4fuzuyV7xb82XJza19nkhePgq9QnVkLS0372', 10000, true);

INSERT INTO account_authority(account_id, authority) VALUES
  (1, 'ADMIN'),
  (2, 'USER');

INSERT INTO AIRLINE(name) VALUES
  ( 'Airlines');

INSERT INTO PLANE(name, seats_quantity, airline_id) VALUES
  ('Boeing 747', 524, (SELECT id FROM airline WHERE name='Airlines') );

INSERT INTO COUNTRY(name) VALUES
  ('Россия');

INSERT INTO CITY(name, country_id) VALUES
  ('Самара', (SELECT id FROM country WHERE name='Россия') ),
  ('Москва', (SELECT id FROM country WHERE name='Россия') );

INSERT INTO AIRPORT(name, city_id) VALUES
  ('Курумоч', (SELECT id FROM city WHERE name='Самара') ),
  ('Домодедово', (SELECT id	FROM city WHERE name='Москва') );

INSERT INTO TICKET(departure_date, arrival_date, gate, price, seat, airline_id,
                   arrival_airport_id, departure_airport_id, flight_id, passenger_id) VALUES
  -- New ticket
  (parsedatetime('28-06-2019 15:47', 'dd-MM-yyyy hh:mm'), parsedatetime('28-06-2019 17:37', 'dd-MM-yyyy hh:mm'), 4, 9300, 3,
  (SELECT id FROM airline WHERE name='Airlines'), (SELECT id FROM airport WHERE name='Курумоч'),
  (SELECT id FROM airport WHERE name='Домодедово'), null, null),
  -- New ticket
  (parsedatetime('07-07-2019 22:13', 'dd-MM-yyyy hh:mm'), parsedatetime('07-07-2019 24:11', 'dd-MM-yyyy hh:mm'), 9, 14400, 71,
  (SELECT id FROM airline WHERE name='Airlines'), (SELECT id FROM airport WHERE name='Домодедово'),
  (SELECT id FROM airport WHERE name='Курумоч') , null, null);






