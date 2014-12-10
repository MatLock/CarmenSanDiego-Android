package restService;

import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.Pure;

@Accessors
@SuppressWarnings("all")
public class ViajarRequest {
  public String destino;
  
  public ViajarRequest(final String destino) {
    this.destino = destino;
  }
  
  @Pure
  public String getDestino() {
    return this.destino;
  }
  
  public void setDestino(final String destino) {
    this.destino = destino;
  }
}
