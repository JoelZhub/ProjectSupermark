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
    FOREIGN KEY (idFactura) REFERENCES facturas(idFactura),
    FOREIGN KEY (idProducto) REFERENCES productos(idProducto)
);
