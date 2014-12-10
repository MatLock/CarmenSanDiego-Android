package restService

import org.eclipse.xtend.lib.annotations.Accessors

@Accessors
class ObtenerPistaRequest {
	
	public String pais;
	public String lugar;
	
	new(String string, String string2) {
		pais= string;
		lugar = string2;
	}
	
	new(){}
	
	
	
	
	
}