package view;
import view.Auth.Login;
public class App {
	public void start() {
		var context = new AplicationContext();
		var login = new Login(context);
		login.setVisible(true);
	}
	public static void main(String[] args) {
		new App().start();
	}	
}

// nota de cambios realizados:

/*
 *  esta funcionando el apartado de admin, creacion de usuarios, eliminar, editar y buscar
 *  
 *  de productos funciona buscar, editar y eliminar(que en realidad es desactivar), todavia crear no  funciona puesto que acordamos
 *  que para crear un producto debe exisitr un proveedor, ofertas no esta funcionando porque no tengo donde enviar esos datos de momento
 *  es decir el dao  ni controller del mismo
 *  
 *colocare otro campo que es -> activo en la clase productos para poder eliminar productos o mejor dicho desativarlo si el stock llega  a 0
puesto que intente usar el metodo de eliminar pero por las relaciones con la tabla factura_producto me dio error por las FK Y PK
ademas de que si lo eliminamos totalmente  no podremos acceder a informacion de facturas que se hayan generado con ese producto 
asi que mejor desde mi punto de vista lo desactivamos, si tienen otra idea  me hacen saber y la aplicamos 

para esto tuve que altera la BD pero si no estan de acuerdo basta con restaurarla tengo una copia con los datos que estaba usando para probar me notifican 
si la requieren
 *  
 * seguire trabajando con los forms faltantes como de factura y agregare el modulo de servicio al cliente para registrar clientes,
 * el apartado de asistente de ventas lo deje de solo lectura solo permite filtra (de momento 
 * no muestra nada porque aun no hay una tabla de ventas en la bd)
 *  
 * en ese modulo colocare los btn para los eventos de reporte que estan creando.


este es uno de los usuarios admin por si quieren probar el sistema no tenga que estar entrando a la BD:
cmendez@empresa.com
1234

 :D
*/