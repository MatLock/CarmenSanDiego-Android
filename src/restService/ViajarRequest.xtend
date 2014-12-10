package restService

import org.eclipse.xtend.lib.annotations.Accessors

@Accessors
class ViajarRequest {
	
	public String destino;
	
	
	new(String destino){
		this.destino = destino
	}
}