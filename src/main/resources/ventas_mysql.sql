DROP DATABASE IF EXISTS ventas;
 CREATE DATABASE ventas CHARACTER SET utf8mb4;
 USE ventas;

CREATE TABLE cliente (
                         id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
                         nombre VARCHAR(100) NOT NULL,
                         apellido1 VARCHAR(100) NOT NULL,
                         apellido2 VARCHAR(100),
                         ciudad VARCHAR(100),
                         categoría INT UNSIGNED,
                         email VARCHAR(100)
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

INSERT INTO cliente VALUES(1, 'Aarón', 'Rivero', 'Gómez', 'Almería', 100, 'aaron.rivero@gmail.com');
INSERT INTO cliente VALUES(2, 'Adela', 'Salas', 'Díaz', 'Granada', 200, 'adela.salas@yahoo.com');
INSERT INTO cliente VALUES(3, 'Adolfo', 'Rubio', 'Flores', 'Sevilla', NULL, 'adolfo.rubio@outlook.com');
INSERT INTO cliente VALUES(4, 'Adrián', 'Suárez', NULL, 'Jaén', 300, 'adrian.suarez@gmail.com');
INSERT INTO cliente VALUES(5, 'Marcos', 'Loyola', 'Méndez', 'Almería', 200, 'marcos.loyola@empresa.com');
INSERT INTO cliente VALUES(6, 'María', 'Santana', 'Moreno', 'Cádiz', 100, 'maria.santana@hotmail.com');
INSERT INTO cliente VALUES(7, 'Pilar', 'Ruiz', NULL, 'Sevilla', 300, 'pilar.ruiz@gmail.com');
INSERT INTO cliente VALUES(8, 'Pepe', 'Ruiz', 'Santana', 'Huelva', 200, 'pepe.ruiz@correo.es');
INSERT INTO cliente VALUES(9, 'Guillermo', 'López', 'Gómez', 'Granada', 225, 'guillermo.lopez@empresa.com');
INSERT INTO cliente VALUES(10, 'Daniel', 'Santana', 'Loyola', 'Sevilla', 125, 'daniel.santana@gmail.com');

INSERT INTO cliente VALUES(11, 'Carlos', 'Pérez', 'López', 'Madrid', 150, 'carlos.perez@outlook.com');
INSERT INTO cliente VALUES(12, 'Elena', 'González', 'Jiménez', 'Madrid', 175, 'elena.gonzalez@yahoo.com');
INSERT INTO cliente VALUES(13, 'Luis', 'Moreno', 'Cruz', 'Barcelona', 200, 'luis.moreno@empresa.com');
INSERT INTO cliente VALUES(14, 'Sara', 'Martínez', 'Sánchez', 'Valencia', 250, 'sara.martinez@hotmail.com');

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