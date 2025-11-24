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

CREATE TABLE usuarios (
    idUsuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(120) NOT NULL UNIQUE,
    pin VARCHAR(255) NOT NULL,
    rol VARCHAR(100) NOT NULL
);
