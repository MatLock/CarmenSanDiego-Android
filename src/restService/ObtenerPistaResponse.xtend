package restService

import java.util.List
import org.eclipse.xtend.lib.annotations.Accessors

@Accessors
class ObtenerPistaResponse {
	
	
	public String descripcion;
	public List<String>paisesFallidos;
}