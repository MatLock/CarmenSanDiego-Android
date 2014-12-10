package restService;

import java.util.List;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.Pure;

@Accessors
@SuppressWarnings("all")
public class ViajarResponse {
  public String paisActual;
  
  public List<String> nombresDePaises;
  
  @Pure
  public String getPaisActual() {
    return this.paisActual;
  }
  
  public void setPaisActual(final String paisActual) {
    this.paisActual = paisActual;
  }
  
  @Pure
  public List<String> getNombresDePaises() {
    return this.nombresDePaises;
  }
  
  public void setNombresDePaises(final List<String> nombresDePaises) {
    this.nombresDePaises = nombresDePaises;
  }
}
