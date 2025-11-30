CREATE DATABASE supermarket_DB;

USE supermarket_DB;

CREATE TABLE productos ( 
    idProducto INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    precio DOUBLE NOT NULL,
    categoria VARCHAR(50) NOT NULL,	
    cantidad INT NOT NULL,
    unidad VARCHAR(30) NOT NULL,
    activo  tinyint(1) NOT NULL default(1)
);

CREATE TABLE ofertas(
idOferta int primary key auto_increment,
oferta varchar(50) not null,
monto double not null,
dateInicio date not null,
dateFin date not null,
activo tinyint(1) not null default(1),
idProducto int not null,
Foreign key(idProducto) references productos(idProducto)
);

create table proveedores(
idProveedor INT PRIMARY KEY AUTO_INCREMENT,
rncProveedor int not null,
nombre varchar(50)not null,
telefono varchar(50) not null,
correo varchar(50) unique not null,
calle varchar(50) not null,
ciudad varchar(50) not null,
pais varchar(50) not null,
activo tinyint(1) default(1) not null
);

create table detalles_producto(
idDetalleProducto INT PRIMARY KEY AUTO_INCREMENT,
idProducto INT not null,
idProveedor INT NOT NULL,
marca varchar(50) NOT NULL,
origen varchar(50) NOT NULL,
Foreign key(idProducto) references productos(idProducto),
Foreign key(idProveedor) references proveedores(idProveedor)
);


--  si no llega la table con este campo agregado entonces ejecuta esa sentencia por favor.
Alter table detalles_productos
add column fechaAgregado DATE NOT NULL DEFAULT (CURRENT_DATE);

select * from detalles_producto;

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

CREATE TABLE clientes (
    identificacion VARCHAR(20) PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    clasificacion VARCHAR(120) NOT NULL,
    telefono VARCHAR(100) NOT NULL
);
alter table clientes
add column activo   tinyint(1) default(1) not null;

delete  from clientes;
select * from clientes;

create table compra_proveedor(
	idCompra INT PRIMARY KEY AUTO_INCREMENT,
	idProducto INT not null,
	idProveedor INT NOT NULL,
    cantidad INT,
    fecha VARCHAR(15),
    precio DOUBLE NOT NULL,
	Foreign key(idProducto) references productos(idProducto),
	Foreign key(idProveedor) references proveedores(idProveedor)
);

CREATE TABLE cuentas_pagar(
	id INT AUTO_INCREMENT PRIMARY KEY,
    idCompra INT,
    idProveedor INT,
    monto_total double,
    monto_pendiente double,
    estado VARCHAR(20),
    FOREIGN KEY (idCompra) REFERENCES compra_proveedor(idCompra),
    FOREIGN KEY (idProveedor) REFERENCES proveedores(idProveedor)
);

CREATE TABLE cuentas_pagar_pagos(
	idPago INT AUTO_INCREMENT PRIMARY KEY,
    idCuenta INT,
	fecha VARCHAR(15),
    monto double,
    FOREIGN KEY (idCuenta) REFERENCES cuentas_pagar(id)
);


select * from cuentas_cobrar;

CREATE TABLE cuentas_cobrar(
	id INT AUTO_INCREMENT PRIMARY KEY,
    idCliente VARCHAR(20),
    idFactura int,
    monto_total double,
    monto_pendiente double,
    estado VARCHAR(20),
    FOREIGN KEY (idFactura) REFERENCES factura(idFactura),
    FOREIGN KEY (idCliente) REFERENCES clientes(identificacion)
);


CREATE TABLE cuentas_cobrar_pagos(
	idPago INT AUTO_INCREMENT PRIMARY KEY,
    idCuenta INT,
	fecha VARCHAR(15),
    monto double,
    FOREIGN KEY (idCuenta) REFERENCES cuentas_cobrar(id)
);

-- registros de prueba -< usuarios

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

-- Registros de pruebas -> productos

INSERT INTO productos (nombre, precio, categoria, cantidad, unidad, activo) VALUES
('Habichuelas Pintas 2lb', 120.00, 'COMESTIBLES', 8, 'lb', 1),
('Banano Maduro', 12.00, 'VEGETALES_FRUTAS', 15, 'unidad', 1),
('Chuleta de Cerdo 1lb', 180.00, 'CARNES', 4, 'lb', 1),
('Muslos de Pollo 1lb', 95.00, 'POLLO_AVES', 11, 'lb', 1),
('Comino Molido 50g', 40.00, 'CONDIMENTOS', 9, 'unidad', 1),
('Atún en Agua 140g', 78.00, 'ENLATADOS', 6, 'unidad', 1),
('Jamón de Pavo 1lb', 155.00, 'EMBUTIDOS', 5, 'lb', 1),
('Jugo de Naranja 1L', 82.00, 'BEBIDAS', 10, 'litro', 1),
('Yogurt Natural 500ml', 65.00, 'LACTEOS', 7, 'unidad', 1),
('Pan Integral Molde', 90.00, 'PANES', 3, 'unidad', 1);

-- registros de pruebas -> ofertas

INSERT INTO ofertas (oferta, monto, dateInicio, dateFin, activo, idProducto) VALUES
('Descuento Fin de Semana', 15.00, '2025-01-10', '2025-01-12', 1, 1),
('Precio Especial', 20.00, '2025-01-05', '2025-01-20', 1, 2),
('Rebaja por Temporada', 35.00, '2025-02-01', '2025-02-28', 1, 3),
('Oferta Flash', 10.00, '2025-01-15', '2025-01-16', 1, 4),
('Combo Condimentos', 8.00, '2025-03-01', '2025-03-10', 1, 5),
('Ahorro en Enlatados', 12.00, '2025-02-10', '2025-02-18', 1, 6),
('Rebaja Embutidos', 25.00, '2025-01-22', '2025-01-30', 1, 7),
('Oferta Bebidas 2x1', 30.00, '2025-01-28', '2025-02-05', 1, 8),
('Precio Especial Lácteos', 18.00, '2025-02-15', '2025-02-25', 1, 9),
('Descuento Panadería', 6.00, '2025-01-18', '2025-01-31', 1, 10);

-- REGISTROS DE PRUEBAS -> PROVEEDORES

INSERT INTO proveedores 
(rncProveedor, nombre, telefono, correo, calle, ciudad, pais, activo)
VALUES

(101234567, 'Comercial Caribeña', '809-555-1122', 'contacto@caribena.com', 'Av. Independencia 230', 'Santo Domingo', 'República Dominicana', 1),
(102876543, 'Distribuidora Quisqueya', '829-333-9988', 'ventas@quisqueya.do', 'Calle Duarte 58', 'Santiago', 'República Dominicana', 1),
(109887766, 'Proveedora Antillas', '849-444-2211', 'info@antillas.do', 'Av. Hispanoamericana 120', 'La Vega', 'República Dominicana', 1),
(104556677, 'Grupo Suplidora RD', '809-222-7711', 'servicio@suplidorard.do', 'Calle Las Mercedes 14', 'San Cristóbal', 'República Dominicana', 1),
(107123890, 'Mercantil Dominicana', '829-555-7812', 'mercantil@dom.com', 'Av. Libertad 200', 'Puerto Plata', 'República Dominicana', 1),

(205667788, 'Global Foods Ltd', '+1-202-555-0411', 'support@globalfoods.com', '14th Street NW 980', 'Washington', 'Estados Unidos', 1),
(309123456, 'EuroMarket Supply', '+34-911-223344', 'hola@euromarket.es', 'Calle Alcalá 310', 'Madrid', 'España', 1),
(408998877, 'Brasil Distribuidora', '+55-11-5566-7788', 'contato@brasild.com', 'Rua Paulista 500', 'São Paulo', 'Brasil', 1),
(506554433, 'Andes Proveedores SAC', '+51-1-4455667', 'ventas@andes-sac.pe', 'Av. Arequipa 1220', 'Lima', 'Perú', 1),
(607778899, 'Nippon Supply Co', '+81-3-4455-6677', 'service@nipponsupply.jp', 'Shibuya Crossing 22', 'Tokio', 'Japón', 1);

INSERT INTO detalles_producto (idProducto, idProveedor, marca, origen)
VALUES
(1, 1, 'Caribeña Selecta', 'Nacional'),
(2, 2, 'Quisqueya Agro', 'Nacional'),
(3, 3, 'Antillas Prime Pork', 'Nacional'),
(4, 4, 'RD Fresh Poultry', 'Nacional'),
(5, 5, 'Mercantil Especias', 'Nacional'),
(6, 6, 'Global Sea Fresh', 'Importado'),
(7, 8, 'Brasil PavoPro', 'Importado'),
(8, 9, 'Andes Citrus', 'Importado'),
(9, 7, 'EuroLáctea', 'Importado'),
(10, 1, 'Caribeña Bakery', 'Nacional');

INSERT INTO compra_proveedor (idProducto, idProveedor, cantidad, fecha, precio) VALUES
(1,  2, 150, '2025-10-02', 220.00),
(2,  1,  80, '2025-10-03', 350.00),
(3,  7, 200, '2025-10-03',  45.00),
(4,  3, 120, '2025-10-04',  90.00),
(5,  4, 180, '2025-10-05',  60.00),
(6,  5,  70, '2025-10-06', 250.00),
(7,  8, 140, '2025-10-06',  85.00),
(8,  6,  95, '2025-10-07', 120.00),
(9, 10,  60, '2025-10-08', 480.00),
(10, 7, 110, '2025-10-09',  75.00),
(11, 4, 200, '2025-11-01',  55.00),
(12, 9, 130, '2025-11-10', 190.00);

INSERT INTO cuentas_pagar (idCompra, idProveedor, monto_total, monto_pendiente, estado) VALUES
(1,  2, 33000.00, 16500.00, 'Parcial'),
(2,  1, 28000.00,     0.00, 'Pagado'),
(3,  7,  9000.00,  9000.00, 'Pendiente'),
(4,  3, 10800.00,  6800.00, 'Parcial'), 
(5,  4, 10800.00,     0.00, 'Pagado'), 
(6,  5, 17500.00, 17500.00, 'Pendiente'), 
(7,  8, 11900.00,  5900.00, 'Parcial'),  
(8,  6, 11400.00, 11400.00, 'Pendiente'), 
(9, 10, 28800.00,     0.00, 'Pagado'),  
(10, 7,  8250.00,  8250.00, 'Pendiente'),
(11, 4, 11000.00,  3000.00, 'Parcial'),
(12, 9, 24700.00, 24700.00, 'Pendiente');

INSERT INTO cuentas_pagar_pagos (idCuenta, fecha, monto) VALUES
(1, '2025-10-10', 10000.00),
(1, '2025-10-20',  6500.00),
(2, '2025-10-05', 15000.00),
(2, '2025-10-15', 13000.00),
(4, '2025-10-12',  4000.00),
(5, '2025-10-08', 10800.00),
(7, '2025-10-18',  6000.00),
(9, '2025-10-25', 18000.00),
(9, '2025-10-30', 10800.00),
(11, '2025-11-05',  8000.00);

INSERT INTO clientes (identificacion, nombre, clasificacion, telefono) VALUES
('1', 'Juan Pérez', 'Minorista', '809-111-2222'),
('3', 'Empresa XYZ SRL', 'Mayorista', '809-333-4444'),
('5', 'Ana Gómez', 'Minorista', '809-555-6666');

INSERT INTO cuentas_cobrar (idCliente, idFactura, monto_total, monto_pendiente, estado) VALUES
('1', 1,    0.00,    0.00, 'Pagado'),
('3', 2, 2348.00,  348.00, 'Pendiente'),
('1', 3, 1184.00, 1184.00, 'Pendiente'),
('1', 4, 1025.00,    0.00, 'Pagado'),
('1', 5, 1918.00,  918.00, 'Pendiente'),
('1', 6, 1460.00, 1460.00, 'Pendiente'),
('1', 7, 1680.00,    0.00, 'Pagado'),
('1', 8, 1395.00,  395.00, 'Pendiente');

INSERT INTO cuentas_cobrar_pagos (idCuenta, fecha, monto) VALUES
(2, '2025-11-23', 2000.00),
(4, '2025-11-29', 1025.00),
(5, '2025-11-29', 1000.00),
(7, '2025-11-29', 1000.00),
(7, '2025-11-30',  680.00),
(8, '2025-11-30', 1000.00);

