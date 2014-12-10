package org.uqbar.helloWorld.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.google.common.base.Objects;
import java.util.ArrayList;
import org.uqbar.helloWorld.services.GenerarCasoService;
import org.uqbar.helloWorld.ui.ErrorShower;
import org.uqbar.helloWorld.ui.PaisActivity;
import org.uqbar.helloWorld.ui.R;
import restService.GenerarCasoResponse;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

@SuppressWarnings("all")
public class MainActivity extends ErrorShower {
  private GenerarCasoResponse casoGenerado;
  
  public void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.setContentView(R.layout.activity_main);
  }
  
  public void onGenerarCaso(final View button) {
    RestAdapter.Builder _builder = new RestAdapter.Builder();
    RestAdapter.Builder _setEndpoint = _builder.setEndpoint(PaisActivity.URL);
    final RestAdapter restAdapter = _setEndpoint.build();
    final GenerarCasoService generarCasoService = restAdapter.<GenerarCasoService>create(GenerarCasoService.class);
    generarCasoService.getCaso(new Callback<GenerarCasoResponse>() {
      public void failure(final RetrofitError e) {
        MainActivity.this.showError("Ocurrió un problema de comunicación con el servidor");
      }
      
      public void success(final GenerarCasoResponse response, final Response arg1) {
        View _findViewById = MainActivity.this.findViewById(R.id.descripcion);
        final TextView textField = ((TextView) _findViewById);
        textField.setText(response.descripcion);
        MainActivity.this.casoGenerado = response;
      }
    });
  }
  
  public void onEmpezar(final View view) {
    boolean _notEquals = (!Objects.equal(this.casoGenerado, null));
    if (_notEquals) {
      final Intent intent = new Intent(this, PaisActivity.class);
      intent.putExtra("PAIS_ACTUAL", this.casoGenerado.paisActual);
      intent.putStringArrayListExtra("VILLANOS", ((ArrayList<String>) this.casoGenerado.nombresDeVillanos));
      intent.putStringArrayListExtra("PAISES", ((ArrayList<String>) this.casoGenerado.nombresDePaises));
      this.startActivity(intent);
    } else {
      this.showError("Debe generar un caso primero");
    }
  }
}
