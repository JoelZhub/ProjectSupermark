package model;

import java.util.Set;

//clase que gestiona los permisos segun el rol -> recibe el rol del enum que tenga el usuarios  asignador 
//previamente registrado - >

//nota esta es clase esta creada para poder habilitar modulos con el UsersContext -> si ya esta creada una clase que gestionar 
//los permisos favor ir al UsersContext hacer el cambio en el constructor y llamar a la clase que ya estaba previamente creada
// en caso de requerir esta

public class PermissionsProvider {
	
	public static Set<Permiso> getPermissionsProvider(/*Rol rol*/){
		
		// en caso de decidir usar esta clase combinada con el enum 
		//cambien los valores de los casos por los del enum de Rol
		/*
		 * 
		return switch(rol)

				Case ADMIN  -> Set.of(
						Permiso.PRODUCTOS_VER,
						Permiso.VENTAS_VER,
						Permiso.FACTURACION_VER,
//						Permiso.INVENTARIO_VER,
						Permiso.USUARIOS_VER
						
				);
				
			 Case VENDEDOR -> Set.of(
					Permiso.VENTAS_VER,
			 );
			 
			 Case CAJERO -> Set.of(
					Permiso.FACTURACION_VER,
			 );
			 
			 Case ENCARGADOINVENTARIO  -> Set.of(
						Permiso.PRODUCTOS_VER,
			
				);
			 
			 
			 
		*/
		
		return null;
	}
	
}
