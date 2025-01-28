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





-- Datos de tabla cliente
INSERT INTO cliente VALUES(11, 'Aarón', 'Rivero', 'Gómez', 'Almería', 100);
INSERT INTO cliente VALUES(12, 'Adela', 'Salas', 'Díaz', 'Granada', 200);
INSERT INTO cliente VALUES(13, 'Adolfo', 'Rubio', 'Flores', 'Sevilla', NULL);

-- Datos de tabla comercial
INSERT INTO comercial VALUES(14, 'Daniel', 'Sáez', 'Vega', 0.15);
INSERT INTO comercial VALUES(15, 'Juan', 'Gómez', 'López', 0.13);
INSERT INTO comercial VALUES(16, 'Diego', 'Flores', 'Salas', 0.11);

-- Datos de tabla pedido
INSERT INTO pedido VALUES(17, 150.5, '2017-10-05', 11, 14); -- Aarón tiene pedido con Daniel
INSERT INTO pedido VALUES(18, 270.65, '2016-09-10', 11, 15); -- Aarón tiene pedido con Juan
INSERT INTO pedido VALUES(19, 65.26, '2017-10-05', 11, 16); -- Aarón tiene pedido con Diego
INSERT INTO pedido VALUES(20, 110.5, '2016-08-17', 12, 14); -- Adela tiene pedido con Daniel
INSERT INTO pedido VALUES(21, 948.5, '2017-09-10', 12, 15); -- Adela tiene pedido con Juan
INSERT INTO pedido VALUES(22, 2400.6, '2016-07-27', 12, 16); -- Adela tiene pedido con Diego
INSERT INTO pedido VALUES(23, 5760, '2015-09-10', 13, 14); -- Adolfo tiene pedido con Daniel
INSERT INTO pedido VALUES(24, 1983.43, '2017-10-10', 13, 15); -- Adolfo tiene pedido con Juan
INSERT INTO pedido VALUES(25, 2480.4, '2016-10-10', 13, 16); -- Adolfo tiene pedido con Diego


