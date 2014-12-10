package restService;

import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.Pure;

@Accessors
@SuppressWarnings("all")
public class ObtenerPistaRequest {
  public String pais;
  
  public String lugar;
  
  public ObtenerPistaRequest(final String string, final String string2) {
    this.pais = string;
    this.lugar = string2;
  }
  
  public ObtenerPistaRequest() {
  }
  
  @Pure
  public String getPais() {
    return this.pais;
  }
  
  public void setPais(final String pais) {
    this.pais = pais;
  }
  
  @Pure
  public String getLugar() {
    return this.lugar;
  }
  
  public void setLugar(final String lugar) {
    this.lugar = lugar;
  }
}
