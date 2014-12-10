package restService

import org.eclipse.xtend.lib.annotations.Accessors
import java.util.List

@Accessors
class GenerarCasoResponse {
	
	public String descripcion;
	public String paisActual;
	public List<String>nombresDeVillanos;
	public List<String>nombresDePaises;
	
	

}