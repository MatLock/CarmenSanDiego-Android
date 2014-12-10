package restService;

import java.util.List;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.Pure;

@Accessors
@SuppressWarnings("all")
public class GenerarCasoResponse {
  public String descripcion;
  
  public String paisActual;
  
  public List<String> nombresDeVillanos;
  
  public List<String> nombresDePaises;
  
  @Pure
  public String getDescripcion() {
    return this.descripcion;
  }
  
  public void setDescripcion(final String descripcion) {
    this.descripcion = descripcion;
  }
  
  @Pure
  public String getPaisActual() {
    return this.paisActual;
  }
  
  public void setPaisActual(final String paisActual) {
    this.paisActual = paisActual;
  }
  
  @Pure
  public List<String> getNombresDeVillanos() {
    return this.nombresDeVillanos;
  }
  
  public void setNombresDeVillanos(final List<String> nombresDeVillanos) {
    this.nombresDeVillanos = nombresDeVillanos;
  }
  
  @Pure
  public List<String> getNombresDePaises() {
    return this.nombresDePaises;
  }
  
  public void setNombresDePaises(final List<String> nombresDePaises) {
    this.nombresDePaises = nombresDePaises;
  }
}
