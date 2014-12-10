package org.uqbar.helloWorld.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.uqbar.helloWorld.services.VisitarLugarService;
import org.uqbar.helloWorld.ui.ErrorShower;
import org.uqbar.helloWorld.ui.PaisActivity;
import org.uqbar.helloWorld.ui.R;
import restService.ObtenerPistaRequest;
import restService.ObtenerPistaResponse;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

@SuppressWarnings("all")
public class VisitarLugarActivity extends ErrorShower {
  private String paisActual;
  
  private Button lugar1;
  
  private Button lugar2;
  
  private Button lugar3;
  
  public void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.setContentView(R.layout.visitarlugar);
    final Intent intent = this.getIntent();
    View _findViewById = this.findViewById(R.id.VISITAR_LUGAR_ESTAS_EN);
    final TextView textView = ((TextView) _findViewById);
    String _stringExtra = intent.getStringExtra("PAIS_ACTUAL");
    String _plus = ("Estas En: " + _stringExtra);
    textView.setText(_plus);
    String _stringExtra_1 = intent.getStringExtra("PAIS_ACTUAL");
    this.paisActual = _stringExtra_1;
    View _findViewById_1 = this.findViewById(R.id.banco);
    this.lugar1 = ((Button) _findViewById_1);
    View _findViewById_2 = this.findViewById(R.id.biblioteca);
    this.lugar2 = ((Button) _findViewById_2);
    View _findViewById_3 = this.findViewById(R.id.club);
    this.lugar3 = ((Button) _findViewById_3);
  }
  
  public void onVisitarBanco(final View button) {
    this.visitarGenerico("Banco");
  }
  
  public void onVisitarBiblioteca(final View button) {
    this.visitarGenerico("Biblioteca");
  }
  
  public void onVisitarClub(final View button) {
    this.visitarGenerico("Club");
  }
  
  public void visitarGenerico(final String lugar) {
    RestAdapter.Builder _builder = new RestAdapter.Builder();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append(PaisActivity.URL, "");
    RestAdapter.Builder _setEndpoint = _builder.setEndpoint(_builder_1.toString());
    final RestAdapter restAdapter = _setEndpoint.build();
    final VisitarLugarService post = restAdapter.<VisitarLugarService>create(VisitarLugarService.class);
    final ObtenerPistaRequest request = new ObtenerPistaRequest(this.paisActual, lugar);
    post.visitarLugar(request, new Callback<ObtenerPistaResponse>() {
      public void failure(final RetrofitError arg0) {
        VisitarLugarActivity.this.showError("Ha ocurrido un problema de comunicacion con el servidor");
      }
      
      public void success(final ObtenerPistaResponse response, final Response arg1) {
        VisitarLugarActivity.this.mostrarDescripcion(response);
      }
    });
  }
  
  public void mostrarDescripcion(final ObtenerPistaResponse response) {
    View _findViewById = this.findViewById(R.id.pista);
    final TextView textView = ((TextView) _findViewById);
    textView.setText(response.descripcion);
  }
  
  public void onVolverAtras(final View button) {
    this.finish();
  }
}
