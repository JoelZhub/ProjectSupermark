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


Select * from productos;
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

select * from usuarios;
INSERT INTO usuarios (nombre, email, pin, rol) VALUES
('Carlos Méndez', 'cmendez@empresa.com', '1234', 'ADMIN'),
('Ana Rodríguez', 'arodriguez@empresa.com', '9876', 'VENDEDOR'),
('Luis Herrera', 'lherrera@empresa.com', '4567', 'CAJERO'),
('María Gómez', 'mgomez@empresa.com', '3219', 'ENCARGADO_INVENTARIO'),
('Pedro Santana', 'psantana@empresa.com', '8899', 'VENDEDOR'),
('Julia Martínez', 'jmartinez@empresa.com', '7744', 'CAJERO'),
('Roberto Díaz', 'rdiaz@empresa.com', '6633', 'ADMIN'),
('Elena Torres', 'etorres@empresa.com', '5522', 'ENCARGADO_INVENTARIO'),
('Samuel Castro', 'scastro@empresa.com', '9911', 'VENDEDOR'),
('Daniela Ruiz', 'druiz@empresa.com', '1100', 'CAJERO');
