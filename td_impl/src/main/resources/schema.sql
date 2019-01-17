CREATE TABLE IF NOT EXISTS account (
  id BIGINT(19) IDENTITY NOT NULL,
  username VARCHAR(16) NOT NULL,
  email VARCHAR(64) NOT NULL,
  password VARCHAR(64) NOT NULL,
  balance int(16) NOT NULL,
  active VARCHAR(5) NOT NULL,
  CONSTRAINT account_pk_id PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS account_authority (
  account_id BIGINT(19) NOT NULL,
  authority VARCHAR(32) NOT NULL,
  CONSTRAINT account_role_fk_account_id FOREIGN KEY (account_id) REFERENCES account(id)
);

CREATE TABLE IF NOT EXISTS passenger (
  id BIGINT(19) IDENTITY NOT NULL,
  first_name VARCHAR(64) NOT NULL,
  last_name VARCHAR(64) NOT NULL,
  middle_name VARCHAR(64) NOT NULL,
  sex VARCHAR(16) NOT NULL,
  birthday DATE(10) NOT NULL,
  citizenship VARCHAR(64) NOT NULL,
  document_no VARCHAR(10) NOT NULL,
  document_expiry DATE(10) NOT NULL,
  account_id BIGINT(19) NOT NULL,
  CONSTRAINT passenger_pk_id PRIMARY KEY (id),
  CONSTRAINT passenger_fk_account_id FOREIGN KEY (account_id) REFERENCES account(id)
);

CREATE TABLE IF NOT EXISTS airline (
  id BIGINT(19) IDENTITY NOT NULL,
  name VARCHAR(32) NOT NULL,
  CONSTRAINT airline_pk_id PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS plane (
  id BIGINT(19) IDENTITY NOT NULL,
  name VARCHAR(32) NOT NULL,
  seats_quantity INT(4) NOT NULL,
  airline_id BIGINT(19) NOT NULL,
  CONSTRAINT plane_pk_id PRIMARY KEY (id),
  CONSTRAINT plane_fk_airline_id FOREIGN KEY (airline_id) REFERENCES airline(id)
);

CREATE TABLE IF NOT EXISTS country (
  id BIGINT(19) IDENTITY NOT NULL,
  name VARCHAR(32) NOT NULL,
  CONSTRAINT country_pk_id PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS city (
  id BIGINT(19) IDENTITY NOT NULL,
  name VARCHAR(32) NOT NULL,
  country_id BIGINT(19) NOT NULL,
  CONSTRAINT city_pk_id PRIMARY KEY (id),
  CONSTRAINT city_fk_country_id FOREIGN KEY (country_id) REFERENCES country(id)
);

CREATE TABLE IF NOT EXISTS airport (
  id BIGINT(19) IDENTITY NOT NULL,
  name VARCHAR(32) NOT NULL,
  city_id BIGINT(19) NOT NULL,
  CONSTRAINT airport_pk_id PRIMARY KEY (id),
  CONSTRAINT airport_fk_city_id FOREIGN KEY (city_id) REFERENCES city(id)
);

CREATE TABLE IF NOT EXISTS flight (
  id BIGINT(19) IDENTITY NOT NULL,
  flight_no INT(16) NOT NULL,
  CONSTRAINT flight_pk_id PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS ticket (
  id BIGINT(19) IDENTITY NOT NULL,
  seat INT(4) NOT NULL,
  gate VARCHAR(16) NOT NULL,
  price INT(10) NOT NULL,
  departure_date TIMESTAMP(26, 6) NOT NULL,
  arrival_date TIMESTAMP(26, 6) NOT NULL,
  passenger_id BIGINT(19),
  flight_id BIGINT(19),
  airline_id BIGINT(19) NOT NULL,
  departure_airport_id BIGINT(19) NOT NULL,
  arrival_airport_id BIGINT(19) NOT NULL,
  CONSTRAINT ticket_pk_id PRIMARY KEY (id),
  CONSTRAINT ticket_fk_passenger_id FOREIGN KEY (passenger_id) REFERENCES passenger(id),
  CONSTRAINT ticket_fk_flight_id FOREIGN KEY (flight_id) REFERENCES flight(id),
  CONSTRAINT ticket_fk_airline_id FOREIGN KEY (airline_id) REFERENCES airline(id),
  CONSTRAINT ticket_fk_departure_airport_id FOREIGN KEY (departure_airport_id) REFERENCES airport(id),
  CONSTRAINT ticket_fk_arrival_airport_id FOREIGN KEY (arrival_airport_id) REFERENCES airport(id)
);

CREATE TABLE IF NOT EXISTS orders (
  id BIGINT(19) IDENTITY NOT NULL,
  account_id BIGINT(19) NOT NULL,
  ticket_id BIGINT(19) NOT NULL,
  type VARCHAR(16) NOT NULL,
  CONSTRAINT order_pk_id PRIMARY KEY (id),
  CONSTRAINT order_fk_account_id FOREIGN KEY (account_id) REFERENCES account(id),
  CONSTRAINT order_fk_ticket_id FOREIGN KEY (ticket_id) REFERENCES ticket(id)
);

CREATE TABLE IF NOT EXISTS payment (
  id BIGINT(19) IDENTITY NOT NULL,
  account_id BIGINT(19) NOT NULL,
  date DATE(10) NOT NULL,
  time TIME(8) NOT NULL,
  type VARCHAR(16) NOT NULL,
  payment_action VARCHAR(16) NOT NULL,
  value INT(10) NOT NULL,
  CONSTRAINT payment_pk_id PRIMARY KEY (id),
  CONSTRAINT payment_fk_account_id FOREIGN KEY (account_id) REFERENCES account(id)
);
