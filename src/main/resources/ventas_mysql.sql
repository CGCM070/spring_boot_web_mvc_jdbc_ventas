DROP DATABASE IF EXISTS ventas;
 CREATE DATABASE ventas CHARACTER SET utf8mb4;
 USE ventas;

CREATE TABLE cliente (
                         id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
                         nombre VARCHAR(100) NOT NULL,
                         apellido1 VARCHAR(100) NOT NULL,
                         apellido2 VARCHAR(100),
                         ciudad VARCHAR(100),
                         categoría INT UNSIGNED
);

CREATE TABLE comercial (
                           id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
                           nombre VARCHAR(100) NOT NULL,
                           apellido1 VARCHAR(100) NOT NULL,
                           apellido2 VARCHAR(100),
                           comisión FLOAT
);

CREATE TABLE pedido (
                        id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
                        total DOUBLE NOT NULL,
                        fecha DATE,
                        id_cliente INT UNSIGNED NOT NULL,
                        id_comercial INT UNSIGNED NOT NULL,
                        FOREIGN KEY (id_cliente) REFERENCES cliente(id),
                        FOREIGN KEY (id_comercial) REFERENCES comercial(id)
);

INSERT INTO cliente VALUES(1, 'Aarón', 'Rivero', 'Gómez', 'Almería', 100);
INSERT INTO cliente VALUES(2, 'Adela', 'Salas', 'Díaz', 'Granada', 200);
INSERT INTO cliente VALUES(3, 'Adolfo', 'Rubio', 'Flores', 'Sevilla', NULL);
INSERT INTO cliente VALUES(4, 'Adrián', 'Suárez', NULL, 'Jaén', 300);
INSERT INTO cliente VALUES(5, 'Marcos', 'Loyola', 'Méndez', 'Almería', 200);
INSERT INTO cliente VALUES(6, 'María', 'Santana', 'Moreno', 'Cádiz', 100);
INSERT INTO cliente VALUES(7, 'Pilar', 'Ruiz', NULL, 'Sevilla', 300);
INSERT INTO cliente VALUES(8, 'Pepe', 'Ruiz', 'Santana', 'Huelva', 200);
INSERT INTO cliente VALUES(9, 'Guillermo', 'López', 'Gómez', 'Granada', 225);
INSERT INTO cliente VALUES(10, 'Daniel', 'Santana', 'Loyola', 'Sevilla', 125);

INSERT INTO comercial VALUES(1, 'Daniel', 'Sáez', 'Vega', 0.15);
INSERT INTO comercial VALUES(2, 'Juan', 'Gómez', 'López', 0.13);
INSERT INTO comercial VALUES(3, 'Diego','Flores', 'Salas', 0.11);
INSERT INTO comercial VALUES(4, 'Marta','Herrera', 'Gil', 0.14);
INSERT INTO comercial VALUES(5, 'Antonio','Carretero', 'Ortega', 0.12);
INSERT INTO comercial VALUES(6, 'Manuel','Domínguez', 'Hernández', 0.13);
INSERT INTO comercial VALUES(7, 'Antonio','Vega', 'Hernández', 0.11);
INSERT INTO comercial VALUES(8, 'Alfredo','Ruiz', 'Flores', 0.05);

INSERT INTO pedido VALUES(1, 150.5, '2017-10-05', 5, 2);
INSERT INTO pedido VALUES(2, 270.65, '2016-09-10', 1, 5);
INSERT INTO pedido VALUES(3, 65.26, '2017-10-05', 2, 1);
INSERT INTO pedido VALUES(4, 110.5, '2016-08-17', 8, 3);
INSERT INTO pedido VALUES(5, 948.5, '2017-09-10', 5, 2);
INSERT INTO pedido VALUES(6, 2400.6, '2016-07-27', 7, 1);
INSERT INTO pedido VALUES(7, 5760, '2015-09-10', 2, 1);
INSERT INTO pedido VALUES(8, 1983.43, '2017-10-10', 4, 6);
INSERT INTO pedido VALUES(9, 2480.4, '2016-10-10', 8, 3);
INSERT INTO pedido VALUES(10, 250.45, '2015-06-27', 8, 2);
INSERT INTO pedido VALUES(11, 75.29, '2016-08-17', 3, 7);
INSERT INTO pedido VALUES(12, 3045.6, '2017-04-25', 2, 1);
INSERT INTO pedido VALUES(13, 545.75, '2019-01-25', 6, 1);
INSERT INTO pedido VALUES(14, 145.82, '2017-02-02', 6, 1);
INSERT INTO pedido VALUES(15, 370.85, '2019-03-11', 1, 5);
INSERT INTO pedido VALUES(16, 2389.23, '2019-03-11', 1, 5);



-- Cliente dentro del último trimestre
INSERT INTO cliente VALUES(11, 'Carlos', 'Pérez', 'López', 'Madrid', 150);
INSERT INTO pedido VALUES(17, 1450.75, '2025-01-15', 11, 1);  -- Pedido en el último trimestre
INSERT INTO pedido VALUES(18, 2360.45, '2025-01-20', 11, 2);  -- Pedido en el último trimestre

-- Cliente dentro del último semestre
INSERT INTO cliente VALUES(12, 'Elena', 'González', 'Jiménez', 'Madrid', 175);
INSERT INTO pedido VALUES(19, 3200.90, '2024-08-10', 12, 3);  -- Pedido en el último semestre
INSERT INTO pedido VALUES(20, 1450.35, '2024-12-05', 12, 1);  -- Pedido en el último semestre

-- Cliente dentro del último año
INSERT INTO cliente VALUES(13, 'Luis', 'Moreno', 'Cruz', 'Barcelona', 200);
INSERT INTO pedido VALUES(21, 980.15, '2024-03-22', 13, 4);  -- Pedido en el último año
INSERT INTO pedido VALUES(22, 4100.50, '2024-11-01', 13, 5);  -- Pedido en el último año

-- Cliente dentro del último lustro
INSERT INTO cliente VALUES(14, 'Sara', 'Martínez', 'Sánchez', 'Valencia', 250);
INSERT INTO pedido VALUES(23, 1575.25, '2020-05-15', 14, 6);  -- Pedido en el último lustro
INSERT INTO pedido VALUES(24, 2870.35, '2023-10-30', 14, 7);  -- Pedido en el último lustro

-- Cliente Aarón Rivero, 5 pedidos en el último trimestre (octubre-diciembre 2024)
INSERT INTO pedido VALUES(25, 1230.50, '2024-10-05', 1, 2);  -- Pedido en el último trimestre
INSERT INTO pedido VALUES(26, 2560.75, '2024-11-15', 1, 5);  -- Pedido en el último trimestre
INSERT INTO pedido VALUES(27, 945.40, '2024-12-02', 1, 5);   -- Pedido en el último trimestre
INSERT INTO pedido VALUES(28, 4500.60, '2024-11-20', 1, 2);  -- Pedido en el último trimestre
INSERT INTO pedido VALUES(29, 1930.80, '2024-12-22', 1, 5);  -- Pedido en el último trimestre

-- Cliente Adela Salas, 2 pedidos en el último semestre (julio-diciembre 2024)
INSERT INTO pedido VALUES(30, 1800.40, '2024-09-15', 2, 1);  -- Pedido en el último semestre
INSERT INTO pedido VALUES(31, 3800.30, '2024-11-25', 2, 5);  -- Pedido en el último semestre

-- Cliente Adolfo Rubio, no se agregan pedidos para el último año
-- (Este cliente no tiene pedidos en el último año)

-- Cliente Adrián Suárez, 3 pedidos en el último lustro (2020-2024)
INSERT INTO pedido VALUES(32, 765.50, '2020-02-10', 4, 6);  -- Pedido en el último lustro
INSERT INTO pedido VALUES(33, 1550.90, '2022-11-05', 4, 5);  -- Pedido en el último lustro
INSERT INTO pedido VALUES(34, 2100.30, '2023-06-15', 4, 2);  -- Pedido en el último lustro

-- Cliente Marcos Loyola, 5 pedidos en el último trimestre (octubre-diciembre 2024)
INSERT INTO pedido VALUES(35, 2100.50, '2024-10-01', 5, 1);  -- Pedido en el último trimestre
INSERT INTO pedido VALUES(36, 3500.25, '2024-11-10', 5, 1);  -- Pedido en el último trimestre
INSERT INTO pedido VALUES(37, 800.75, '2024-10-23', 5, 2);   -- Pedido en el último trimestre
INSERT INTO pedido VALUES(38, 2450.60, '2024-12-18', 5, 2);  -- Pedido en el último trimestre
INSERT INTO pedido VALUES(39, 1750.85, '2024-11-30', 5, 1);  -- Pedido en el último trimestre

-- Cliente María Santana, 2 pedidos en el último lustro (2020-2024)
INSERT INTO pedido VALUES(40, 490.40, '2022-08-20', 6, 1);   -- Pedido en el último lustro
INSERT INTO pedido VALUES(41, 3800.30, '2023-12-15', 6, 2);  -- Pedido en el último lustro

-- Pedidos en el último semestre de 2024 (julio-diciembre)
INSERT INTO pedido VALUES(42, 1750.50, '2024-09-20', 7, 1);  -- Pedido en el último semestre
INSERT INTO pedido VALUES(43, 980.45, '2024-12-10', 7, 3);   -- Pedido en el último semestre

-- Sin pedidos en el último año (2024)
-- No se agrega ningún pedido

-- Pedidos en el último lustro (2020-2025)
INSERT INTO pedido VALUES(44, 2395.30, '2023-03-05', 7, 1);  -- Pedido en el último lustro
INSERT INTO pedido VALUES(45, 3100.25, '2022-06-25', 7, 3);  -- Pedido en el último lustro

-- Pedido en el último trimestre de 2024 (octubre-diciembre)
INSERT INTO pedido VALUES(46, 1900.75, '2024-10-25', 8, 2);  -- Pedido en el último trimestre

-- Pedidos en el último semestre de 2024 (julio-diciembre)
INSERT INTO pedido VALUES(47, 3200.85, '2024-07-15', 8, 1);  -- Pedido en el último semestre
INSERT INTO pedido VALUES(48, 4500.00, '2024-11-02', 8, 3);  -- Pedido en el último semestre

-- Pedido en el último año (2024)
INSERT INTO pedido VALUES(49, 2900.50, '2024-05-30', 8, 2);  -- Pedido en el último año

-- Pedidos en el último lustro (2020-2025)
INSERT INTO pedido VALUES(50, 1625.40, '2021-08-20', 8, 4);  -- Pedido en el último lustro
INSERT INTO pedido VALUES(51, 2000.15, '2022-09-10', 8, 5);  -- Pedido en el último lustro