package view;

import java.awt.EventQueue;

import com.formdev.flatlaf.FlatLightLaf;


// clase que gestiona las dependecias e inyecciones para trabajar bajo el mismo objecto
// y elemento que haya accedido al sistema

public class app {

	public static void main(String[] args) {
		
		//llamar aqui capas que actuaran como conexion entre la BD y el programa
		
		//llamar aqui los elementos que actuaran como controlles Services (los que manejan la logica de validaciones
		// llaman a las capas y efectuan las operadores crud luego de validar que todo este en orden)
	
		//llamar aqui las vistas  y pasar los elementos a los cuales debe redirigir
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					
					FlatLightLaf.setup();
					 
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


}
