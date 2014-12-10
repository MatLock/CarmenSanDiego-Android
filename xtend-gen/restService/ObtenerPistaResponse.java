package restService;

import java.util.List;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.Pure;

@Accessors
@SuppressWarnings("all")
public class ObtenerPistaResponse {
  public String descripcion;
  
  public List<String> paisesFallidos;
  
  @Pure
  public String getDescripcion() {
    return this.descripcion;
  }
  
  public void setDescripcion(final String descripcion) {
    this.descripcion = descripcion;
  }
  
  @Pure
  public List<String> getPaisesFallidos() {
    return this.paisesFallidos;
  }
  
  public void setPaisesFallidos(final List<String> paisesFallidos) {
    this.paisesFallidos = paisesFallidos;
  }
}
