
USE library;

-- Tabla de Usuarios
CREATE TABLE users (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       full_name VARCHAR(255) NOT NULL,
                       email VARCHAR(150) UNIQUE NOT NULL,
                       phone_number VARCHAR(20),
                       home_address VARCHAR(255),
                       state BOOLEAN DEFAULT TRUE
);


-- Tabla de Autores
CREATE TABLE authors (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(255) NOT NULL
);

-- Tabla de Géneros
CREATE TABLE genres (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(100) NOT NULL,
                        description TEXT
);

-- Tabla de Libros
CREATE TABLE books (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       genre_id INT,
                       author_id INT,
                       publish_date DATE,
                       price INT,
                       available BOOLEAN DEFAULT TRUE,
                       CONSTRAINT fk_genre FOREIGN KEY (genre_id) REFERENCES genres(id),
                       CONSTRAINT fk_author FOREIGN KEY (author_id) REFERENCES authors(id)
);

-- Tabla de Préstamos
CREATE TABLE loans (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       book_id INT NOT NULL,
                       user_id INT NOT NULL,
                       date_start DATE NOT NULL,
                       date_end DATE,
                       state ENUM('PENDING','ACTIVE','RETURNED','LATE') NOT NULL DEFAULT 'ACTIVE',
                       CONSTRAINT fk_book FOREIGN KEY (book_id) REFERENCES books(id),
                       CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id)
);



-- ======================
-- AUTORES
-- ======================
INSERT INTO authors (id, name) VALUES
                                   (1, "Hernesto Perez"),
                                   (2, "Gabriel García Márquez"),
                                   (3, "Isabel Allende"),
                                   (4, "Stephen King"),
                                   (5, "J.K. Rowling"),
                                   (6, "George R.R. Martin");

-- ======================
-- GÉNEROS
-- ======================
INSERT INTO genres (id, name, description) VALUES
                                               (1, "terror", "Libros de miedo"),
                                               (2, "realismo mágico", "Novelas con elementos mágicos dentro de contextos realistas"),
                                               (3, "fantasía", "Mundos mágicos y criaturas ficticias"),
                                               (4, "ciencia ficción", "Historias futuristas o tecnológicas"),
                                               (5, "romance", "Historias centradas en el amor y relaciones"),
                                               (6, "suspenso", "Novelas de misterio e intriga");

-- ======================
-- USUARIOS
-- ======================
INSERT INTO users (id, full_name, email, phone_number, home_address, state) VALUES
                                                                                (1, "Juan Romero", "mateoromero0910@gmail.com", "3212714034", "cll 131c #126 - 72", TRUE),
                                                                                (2, "Diana López", "diana.lopez@example.com", "3112233445", "Calle 45 #12-30", TRUE),
                                                                                (3, "Carlos Ramírez", "carlos.ramirez@example.com", "3001122334", "Carrera 10 #50-20", TRUE),
                                                                                (4, "Ana Torres", "ana.torres@example.com", "3129876543", "Calle 100 #25-60", TRUE),
                                                                                (5, "Pedro González", "pedro.gonzalez@example.com", "3205566778", "Av. Las Américas #15-45", TRUE),
                                                                                (6, "Diana Barrante", "dianabarrante17@gmail.com", "3208416934", "cll 137 bis # 103 - 22", TRUE);

-- ======================
-- LIBROS
-- ======================
INSERT INTO books (id, name, genre_id, author_id, publish_date, price, available) VALUES
                                                                                      (1, "El libro maldito", 1, 1, "2020-05-01", 45000, TRUE),
                                                                                      (2, "Cien años de soledad", 2, 2, "1967-06-05", 60000, TRUE),
                                                                                      (3, "La casa de los espíritus", 2, 3, "1982-09-15", 55000, TRUE),
                                                                                      (4, "It", 1, 4, "1986-09-15", 70000, TRUE),
                                                                                      (5, "Harry Potter y la piedra filosofal", 3, 5, "1997-06-26", 50000, TRUE),
                                                                                      (6, "Juego de tronos", 3, 6, "1996-08-06", 80000, TRUE),
                                                                                      (7, "El amor en los tiempos del cólera", 5, 2, "1985-03-15", 65000, TRUE),
                                                                                      (8, "El resplandor", 6, 4, "1977-01-28", 75000, TRUE),
                                                                                      (9, "Memorias de una geisha", 5, 3, "1997-09-27", 47000, TRUE),
                                                                                      (10, "Fuego y sangre", 3, 6, "2018-11-20", 90000, TRUE);

-- ======================
-- PRÉSTAMOS (ejemplo inicial)
-- ======================
INSERT INTO loans (id, book_id, user_id, date_start, date_end, state) VALUES
                                                                          (1, 2, 2, "2025-09-25", "2025-10-02", "ACTIVE"),
                                                                          (2, 4, 3, "2025-09-20", "2025-09-27", "RETURNED"),
                                                                          (3, 6, 4, "2025-09-22", NULL, "ACTIVE");

