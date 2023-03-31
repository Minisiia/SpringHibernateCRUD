CREATE TABLE person
(
    id    int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name  VARCHAR(30),
    age   INT,
    email VARCHAR(30)
);

INSERT INTO person (name, age, email)
values ('Ana', 12, 'a@gmail.com'),
       ('Taras', 23, 'rar@gmail.com'),
       ('Grigoriy', 33, 'grigoriy@gmail.com'),
       ('Paraska', 77, 'babka@gmail.com');