package model;

import java.util.Set;

public class PermissionsProvider {
	
	public static Set<Permiso> getPermissionsProvider(Rol rol){
		
		return switch (rol) {
            case ADMIN -> Set.of(
                Permiso.PRODUCTOS_VER,
                Permiso.VENTAS_VER,
                Permiso.FACTURACION_VER,
                //Permiso.INVENTARIO_VER,
                Permiso.USUARIOS_VER,
                Permiso.SERVICIO_CLIENTE,
            	Permiso.FINANZAS_VER,
                Permiso.REPORTES_VER
            );

            case VENDEDOR -> Set.of(
                Permiso.VENTAS_VER
            );

            case CAJERO -> Set.of(
                Permiso.FACTURACION_VER
            );

            case ENCARGADO_INVENTARIO -> Set.of(
               Permiso.PRODUCTOS_VER
            );
            case SERVICIO_CLIENTE -> Set.of(
       
            		Permiso.SERVICIO_CLIENTE
            );
            case  GERENTE_FINANZAS -> Set.of(
            		Permiso.FINANZAS_VER,
					Permiso.REPORTES_VER
            	); 
        };

	}
	
}
