-- noinspection SqlNoDataSourceInspectionForFile

-- noinspection SqlDialectInspectionForFile

insert into airports (airport_name, airport_city)
values ('JFK', 'Saopaulo'), ('DTW', 'Riodejaneiro'), ('LAX', 'Santos'), ('LAS', 'Minas');

insert into airlines (airline_name) values ('Delta'), ('JetBlue'), ('Spirit'), ('United');


insert into airplanes (airplane_name, airline_id) values
('D107', 1), ('D235', 1), ('D380', 1), ('D710', 1),
('J130', 2), ('J238', 2), ('J395', 2), ('J725', 2),
('S133', 3), ('S236', 3), ('S390', 3), ('S720', 3),
('U135', 4), ('U238', 4), ('U387', 4), ('U718', 4);

insert into customers (first_name, last_name, email, password) values
('Hamidur', 'Rahman', 'random@email.com', '123'),
('user2', 'user2', 'user2@email.com', 'Osvaldo123+'),
('John', 'Doe', 'john@email.com', 'root123mudar'),
('Jane', 'Doe', 'jane@email.com', 'testando'),
('user1first', 'user1last', 'user1first@email.com', 'senha');

insert into flights (flight_code, date_time_destiny, arrival_airport_id, date_time_source, departure_airport_id , airplane_id, fare, capacity, status)
values ('1FF1', '2020-01-14 15:20:23', 1, '2020-01-14 15:20:23', 3, 1, 45.50, 10, 'ON_TIME');

insert into flights (flight_code, date_time_destiny, arrival_airport_id, date_time_source, departure_airport_id , airplane_id, fare, capacity, status)
values ('1FF2', '2020-01-14 15:20:23', 3, '2020-01-14 15:20:23', 2, 1, 45.50, 10, 'ON_TIME');

insert into flights (flight_code, date_time_destiny, arrival_airport_id, date_time_source, departure_airport_id , airplane_id, fare, capacity, status)
values ('1FF3', '2020-01-14 15:20:23', 1, '2020-01-14 15:20:23', 4, 1, 45.50, 10, 'ON_TIME');

insert into flights (flight_code, date_time_destiny, arrival_airport_id, date_time_source, departure_airport_id , airplane_id, fare, capacity, status)
values ('1FF4', '2020-01-14 15:20:23', 4, '2020-01-14 15:20:23', 2, 1, 45.50, 10, 'ON_TIME');

--

insert into flights (flight_code, date_time_destiny, arrival_airport_id, date_time_source, departure_airport_id , airplane_id, fare, capacity, status)
values ('1FF5', '2020-01-14 15:20:23', 2, '2020-01-14 15:20:23', 1, 1, 45.50, 10, 'ON_TIME');

insert into flights (flight_code, date_time_destiny, arrival_airport_id, date_time_source, departure_airport_id , airplane_id, fare, capacity, status)
values ('1FF6', '2020-01-14 15:20:23', 2, '2020-01-14 15:20:23', 4, 1, 45.50, 10, 'ON_TIME');

insert into flights (flight_code, date_time_destiny, arrival_airport_id, date_time_source, departure_airport_id , airplane_id, fare, capacity, status)
values ('1FF7', '2020-01-14 15:20:23', 3, '2020-01-14 15:20:23', 1, 1, 45.50, 10, 'ON_TIME');

insert into flights (flight_code, date_time_destiny, arrival_airport_id, date_time_source, departure_airport_id , airplane_id, fare, capacity, status)
values ('1FF8', '2020-01-14 15:20:23', 1, '2020-01-14 15:20:23', 2, 1, 45.50, 10, 'ON_TIME');

--

insert into flights (flight_code, date_time_destiny, arrival_airport_id, date_time_source, departure_airport_id , airplane_id, fare, capacity, status)
values ('1FF9', '2020-01-14 15:20:23', 1, '2020-01-14 15:20:23', 3, 1, 45.50, 10, 'ON_TIME');

insert into flights (flight_code, date_time_destiny, arrival_airport_id, date_time_source, departure_airport_id , airplane_id, fare, capacity, status)
values ('1FF10', '2020-01-14 15:20:23', 4, '2020-01-14 15:20:23', 2, 1, 45.50, 10, 'ON_TIME');

insert into flights (flight_code, date_time_destiny, arrival_airport_id, date_time_source, departure_airport_id , airplane_id, fare, capacity, status)
values ('1FF11', '2020-01-14 15:20:23', 1, '2020-01-14 15:20:23', 2, 1, 45.50, 10, 'ON_TIME');

insert into flights (flight_code, date_time_destiny, arrival_airport_id, date_time_source, departure_airport_id , airplane_id, fare, capacity, status)
values ('1FF12', '2020-01-14 15:20:23', 3, '2020-01-14 15:20:23', 2, 1, 45.50, 10, 'ON_TIME');

--

insert into flights (flight_code, date_time_destiny, arrival_airport_id, date_time_source, departure_airport_id , airplane_id, fare, capacity, status)
values ('1FF13', '2020-01-14 15:20:23', 3, '2020-01-14 15:20:23', 1, 1, 45.50, 10, 'ON_TIME');

insert into flights (flight_code, date_time_destiny, arrival_airport_id, date_time_source, departure_airport_id , airplane_id, fare, capacity, status)
values ('1FF14', '2020-01-14 15:20:23', 2, '2020-01-14 15:20:23', 1, 1, 45.50, 10, 'ON_TIME');


insert into flights (flight_code, date_time_destiny, arrival_airport_id, date_time_source, departure_airport_id , airplane_id, fare, capacity, status)
values ('1FF15', '2020-01-14 15:20:23', 4, '2020-01-14 15:20:23', 3, 1, 45.50, 10, 'ON_TIME');

insert into flights (flight_code, date_time_destiny, arrival_airport_id, date_time_source, departure_airport_id , airplane_id, fare, capacity, status)
values ('1FF16', '2020-01-14 15:20:23', 2, '2020-01-14 15:20:23', 1, 1, 45.50, 10, 'ON_TIME');

--

insert into reservations (date_time, status, customer_id, flight_id) values
('2020-01-14 15:20:23', 'ACTIVE', 1, 1),
('2020-01-14 15:20:23', 'ACTIVE', 1, 2),
('2020-01-14 15:20:23', 'CANCELLED', 1, 3),
('2020-01-14 15:20:23', 'CANCELLED', 1, 4),

('2020-01-14 15:20:23', 'ACTIVE', 2, 3),
('2020-01-14 15:20:23', 'ACTIVE', 2, 4),
('2020-01-14 15:20:23', 'CANCELLED', 2, 1),
('2020-01-14 15:20:23', 'CANCELLED', 2, 2),

('2020-01-14 15:20:23', 'ACTIVE', 3, 5),
('2020-01-14 15:20:23', 'ACTIVE', 3, 6),
('2020-01-14 15:20:23', 'CANCELLED', 3, 7),
('2020-01-14 15:20:23', 'CANCELLED', 3, 8),

('2020-01-14 15:20:23', 'ACTIVE', 4, 7),
('2020-01-14 15:20:23', 'ACTIVE', 4, 8),
('2020-01-14 15:20:23', 'CANCELLED', 4, 5),
('2020-01-14 15:20:23', 'CANCELLED', 4, 6);

insert into evaluations (evaluation_comments, evaluation_score, customer_id, flight_id)
    values ('Muito bom mesmo', 9, 1, 1),
           ('Muito bom mesmo', 8, 1, 2),
           ('Muito bom mesmo', 10, 1, 3),
           ('Muito bom mesmo', 1, 1, 4),
           ('Muito bom mesmo', 4, 1, 5),
           ('Muito bom mesmo', 5, 1, 6),
           ('Muito bom mesmo', 7, 1, 7),
           ('Muito bom mesmo', 10, 1, 8),
           ('Muito bom mesmo', 9, 1, 9),
           ('Muito bom mesmo', 1, 1, 10),
           ('Muito bom mesmo', 3, 1, 11),
           ('Muito bom mesmo', 4, 1, 12),
           ('Muito bom mesmo', 3, 1, 13),
           ('Muito bom mesmo', 3, 1, 14),
           ('Muito bom mesmo', 8, 1, 15),
           ('Muito bom mesmo', 10, 1, 16),
           ('Muito bom mesmo', 1, 2, 1),
           ('Muito bom mesmo', 2, 2, 2),
           ('Muito bom mesmo', 4, 2, 3),
           ('Muito bom mesmo', 10, 2, 4),
           ('Muito bom mesmo', 9, 2, 5),
           ('Muito bom mesmo', 5, 2, 6),
           ('Muito bom mesmo', 3, 2, 7),
           ('Muito bom mesmo', 1, 2, 8),
           ('Muito bom mesmo', 1, 2, 9),
           ('Muito bom mesmo', 10, 2, 10),
           ('Muito bom mesmo', 9, 2, 11),
           ('Muito bom mesmo', 8, 2, 12),
           ('Muito bom mesmo', 7, 2, 13),
           ('Muito bom mesmo', 10, 2, 14),
           ('Muito bom mesmo', 2, 2, 15),
           ('Muito bom mesmo', 1, 2, 16),
           ('Muito bom mesmo', 10, 3, 1),
           ('Muito bom mesmo', 8, 3, 2),
           ('Muito bom mesmo', 9, 3, 3),
           ('Muito bom mesmo', 5, 3, 4),
           ('Muito bom mesmo', 1, 3, 5),
           ('Muito bom mesmo', 7, 3, 6),
           ('Muito bom mesmo', 9, 3, 7),
           ('Muito bom mesmo', 10, 3, 8),
           ('Muito bom mesmo', 8, 3, 9),
           ('Muito bom mesmo', 3, 3, 10),
           ('Muito bom mesmo', 2, 3, 11),
           ('Muito bom mesmo', 1, 3, 12),
           ('Muito bom mesmo', 4, 3, 13),
           ('Muito bom mesmo', 3, 3, 14),
           ('Muito bom mesmo', 10, 3, 15),
           ('Muito bom mesmo', 9, 3, 16),
           ('Muito bom mesmo', 1, 4, 1),
           ('Muito bom mesmo', 3, 4, 2),
           ('Muito bom mesmo', 4, 4, 3),
           ('Muito bom mesmo', 7, 4, 4),
           ('Muito bom mesmo', 10, 4, 5),
           ('Muito bom mesmo', 1, 4, 6),
           ('Muito bom mesmo', 2, 4, 7),
           ('Muito bom mesmo', 1, 4, 8),
           ('Muito bom mesmo', 4, 4, 9),
           ('Muito bom mesmo', 10, 4, 10),
           ('Muito bom mesmo', 9, 4, 11),
           ('Muito bom mesmo', 8, 4, 12),
           ('Muito bom mesmo', 7, 4, 13),
           ('Muito bom mesmo', 9, 4, 14),
           ('Muito bom mesmo', 1, 4, 15),
           ('Muito bom mesmo', 3, 4, 16),
           ('Muito bom mesmo', 10, 5, 1),
           ('Muito bom mesmo', 9, 5, 2),
           ('Muito bom mesmo', 5, 5, 3),
           ('Muito bom mesmo', 1, 5, 6),
           ('Muito bom mesmo', 2, 5, 7),
           ('Muito bom mesmo', 1, 5, 13),
           ('Muito bom mesmo', 10, 5, 16);

insert into customers_flights (flight_id, customer_id) values (3, 2);

insert into invoice (customer_id, flight_id, value, method) values
    (1, 15, 75.5, 'Cartão'), (2, 10, 45, 'Cartão'), (1, 15, 75.5, 'Pix'), (3, 11, 75.5, 'Boleto');

