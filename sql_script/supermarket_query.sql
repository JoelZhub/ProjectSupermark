CREATE DATABASE supermarket_DB;

USE supermarket_DB;

CREATE TABLE productos (
    idProducto INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    precio DOUBLE NOT NULL,
    categoria VARCHAR(50) NOT NULL,
    cantidad INT NOT NULL,
    unidad VARCHAR(30) NOT NULL
);

CREATE TABLE factura(
    idFactura INT PRIMARY KEY AUTO_INCREMENT,
    fecha VARCHAR(20),
    total DOUBLE,
    metodoPago VARCHAR(50),
    clienteId INT null,
    empleadoId INT
);

CREATE TABLE factura_producto (
    idFactura int,
    idProducto int,
    cantidad INT,
    PRIMARY KEY (idFactura, idProducto),
    FOREIGN KEY (idFactura) REFERENCES factura(idFactura),
    FOREIGN KEY (idProducto) REFERENCES productos(idProducto)
);

/*
registros de pruebas
*/

Drop table productos;

INSERT INTO productos (nombre, precio, categoria, cantidad, unidad) VALUES
('Arroz Selecto 10lb', 320.00, 'COMESTIBLES', 150, 'lb'),
('Manzana Roja', 35.00, 'VEGETALES_FRUTAS', 80, 'unidad'),
('Pechuga de Pollo', 185.00, 'POLLO_AVES', 60, 'lb'),
('Carne Molida Res', 260.00, 'CARNES', 50, 'lb'),
('Leche Entera 1L', 75.00, 'LACTEOS', 120, 'litro'),
('Pan de Agua', 10.00, 'PANES', 200, 'unidad'),
('Detergente Líquido', 230.00, 'LIMPIEZA', 70, 'botella'),
('Shampoo Herbal', 180.00, 'HIGIENE', 90, 'botella'),
('Habichuelas Rojas Lata', 55.00, 'ENLATADOS', 140, 'lata'),
('Refresco Cola 2L', 85.00, 'BEBIDAS', 100, 'botella');


INSERT INTO factura (fecha, total, metodoPago, clienteId, empleadoId) VALUES
('2025-11-20', 0, 'EFECTIVO', 1, 101),
('2025-11-20', 0, 'TARJETA', 2, 101),
('2025-11-21', 0, 'EFECTIVO', 3, 102),
('2025-11-21', 0, 'TRANSFERENCIA', 4, 102),
('2025-11-22', 0, 'EFECTIVO', 5, 103),
('2025-11-22', 0, 'TARJETA', 6, 103),
('2025-11-22', 0, 'EFECTIVO', 7, 101),
('2025-11-23', 0, 'TARJETA', 8, 101),
('2025-11-23', 0, 'EFECTIVO', 9, 102),
('2025-11-23', 0, 'TRANSFERENCIA', 10, 103);

INSERT INTO factura_producto (idFactura, idProducto, cantidad) VALUES
(1, 1, 2),
(1, 6, 5),
(2, 3, 1),
(3, 2, 4),
(4, 10, 2),
(5, 4, 1),
(6, 5, 3),
(7, 9, 2),
(8, 7, 1),
(9, 8, 2),
(10, 1, 1);

