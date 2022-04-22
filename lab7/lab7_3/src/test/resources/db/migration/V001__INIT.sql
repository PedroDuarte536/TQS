CREATE TABLE books (
  id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  name VARCHAR(50) NOT NULL
);

INSERT INTO books ("name") 
VALUES 
('Book1'),
('Book2'),
('Book3'), 
('Book4'),
('Book5');