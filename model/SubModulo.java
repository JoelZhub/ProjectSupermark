package model;

//este enum lo cree para manejar los forms de proveedores y ofertas del modulo inventario

//puesto que en inventario se manejan tres elementos diferentes -> productos -> proveedores y ofertas
//entonces para no ligar lo que es la logica de negocio y la muestra de formularios tengo un form factory
//que se encarga de llamar los elementos  que necesito(formularios) segun la operacion 
public enum SubModulo {
	PRODUCTO,
	PROVEEDOR,
	OFERTA,
	CUENTA_COBRAR,
	CUENTA_PAGAR,
	PAGO_CXC,
	PAGO_CXP,

}
