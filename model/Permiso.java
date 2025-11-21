package model;

//enum que maneja los permisos(modules) 
//debe consultar con el enum rol para verificar que rol tiene el usuario y habilitar modulos 
//o bloquear los mismos

public enum Permiso {
	PRODUCTOS_VER,
	VENTAS_VER,
	FACTURACION_VER,
	INVENTARIO_VER,
	USUARIOS_VER
}