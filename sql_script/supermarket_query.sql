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

-- INSERT INTO productos (nombre, precio, categoria, cantidad, unidad, activo, idProveedor) VALUES
-- ('Habichuelas Pintas 2lb', 120.00, 'COMESTIBLES', 8, 'lb', 1, 1),
-- ('Banano Maduro', 12.00, 'VEGETALES_FRUTAS', 15, 'unidad', 1, 2),
-- ('Chuleta de Cerdo 1lb', 180.00, 'CARNES', 4, 'lb', 1, 3),
-- ('Muslos de Pollo 1lb', 95.00, 'POLLO_AVES', 11, 'lb', 1, 4),
-- ('Comino Molido 50g', 40.00, 'CONDIMENTOS', 9, 'unidad', 1, 5),
-- ('Atún en Agua 140g', 78.00, 'ENLATADOS', 6, 'unidad', 1, 6),
-- ('Jamón de Pavo 1lb', 155.00, 'EMBUTIDOS', 5, 'lb', 1, 7),
-- ('Jugo de Naranja 1L', 82.00, 'BEBIDAS', 10, 'litro', 1, 8),
-- ('Yogurt Natural 500ml', 65.00, 'LACTEOS', 7, 'unidad', 1, 9),
-- ('Pan Integral Molde', 90.00, 'PANES', 3, 'unidad', 1, 10);


-- registros de pruebas -> ofertas

-- INSERT INTO ofertas (oferta, monto, dateInicio, dateFin, activo, idProducto) VALUES
-- ('Descuento Fin de Semana', 15.00, '2025-01-10', '2025-01-12', 1, 1),
-- ('Precio Especial', 20.00, '2025-01-05', '2025-01-20', 1, 2),
-- ('Rebaja por Temporada', 35.00, '2025-02-01', '2025-02-28', 1, 3),
-- ('Oferta Flash', 10.00, '2025-01-15', '2025-01-16', 1, 4),
-- ('Combo Condimentos', 8.00, '2025-03-01', '2025-03-10', 1, 5),
-- ('Ahorro en Enlatados', 12.00, '2025-02-10', '2025-02-18', 1, 6),
-- ('Rebaja Embutidos', 25.00, '2025-01-22', '2025-01-30', 1, 7),
-- ('Oferta Bebidas 2x1', 30.00, '2025-01-28', '2025-02-05', 1, 8),
-- ('Precio Especial Lácteos', 18.00, '2025-02-15', '2025-02-25', 1, 9),
-- ('Descuento Panadería', 6.00, '2025-01-18', '2025-01-31', 1, 10);


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
