package org.uqbar.helloWorld.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import com.google.common.base.Objects;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.uqbar.helloWorld.services.ViajarService;
import org.uqbar.helloWorld.ui.ErrorShower;
import org.uqbar.helloWorld.ui.OrdenDeArrestoActivity;
import org.uqbar.helloWorld.ui.R;
import org.uqbar.helloWorld.ui.VisitarLugarActivity;
import restService.ViajarRequest;
import restService.ViajarResponse;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

@SuppressWarnings("all")
public class PaisActivity extends ErrorShower {
  private Button buscarPistas;
  
  private List<String> villanos;
  
  private List<String> paises;
  
  private String paisActual;
  
  private String villanoSeleccionado = "Nobody";
  
  public static String URL = "http://7.217.103.179:9000";
  
  public static int ES_VILLANO_ACTIVITY = 1;
  
  public void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.setContentView(R.layout.viajar);
    final Intent intent = this.getIntent();
    ArrayList<String> _stringArrayListExtra = intent.getStringArrayListExtra("VILLANOS");
    this.villanos = _stringArrayListExtra;
    ArrayList<String> _stringArrayListExtra_1 = intent.getStringArrayListExtra("PAISES");
    this.paises = _stringArrayListExtra_1;
    String _stringExtra = intent.getStringExtra("PAIS_ACTUAL");
    this.paisActual = _stringExtra;
    View _findViewById = this.findViewById(R.id.buscarPistas);
    this.buscarPistas = ((Button) _findViewById);
    View _findViewById_1 = this.findViewById(R.id.estasEn);
    final TextView textview = ((TextView) _findViewById_1);
    CharSequence _text = textview.getText();
    String _plus = (_text + this.paisActual);
    textview.setText(_plus);
    this.refrescarSpinner();
  }
  
  public void refrescarSpinner() {
    View _findViewById = this.findViewById(R.id.conexiones);
    final Spinner spConexiones = ((Spinner) _findViewById);
    final ArrayAdapter<CharSequence> adConexion = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_dropdown_item, ((CharSequence[])Conversions.unwrapArray(this.paises, CharSequence.class)));
    adConexion.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spConexiones.setAdapter(adConexion);
  }
  
  public void onBuscarPistas(final View button) {
    final Intent intent = new Intent(this, VisitarLugarActivity.class);
    intent.putExtra("PAIS_ACTUAL", this.paisActual);
    this.startActivity(intent);
  }
  
  public void onViajar(final View button) {
    View _findViewById = this.findViewById(R.id.recorridoActual);
    final TextView textView = ((TextView) _findViewById);
    CharSequence _text = textView.getText();
    String _plus = (_text + "->");
    String _plus_1 = (_plus + this.paisActual);
    textView.setText(_plus_1);
    View _findViewById_1 = this.findViewById(R.id.conexiones);
    final Spinner spConexiones = ((Spinner) _findViewById_1);
    Object _selectedItem = spConexiones.getSelectedItem();
    final String pais = ((String) _selectedItem);
    RestAdapter.Builder _builder = new RestAdapter.Builder();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append(PaisActivity.URL, "");
    RestAdapter.Builder _setEndpoint = _builder.setEndpoint(_builder_1.toString());
    final RestAdapter restAdapter = _setEndpoint.build();
    final ViajarService post = restAdapter.<ViajarService>create(ViajarService.class);
    final ViajarRequest request = new ViajarRequest(pais);
    post.viajar(request, new Callback<ViajarResponse>() {
      public void failure(final RetrofitError arg0) {
        PaisActivity.this.showError("Ha ocurrido un problema de comunicación con el servidor o no\n\t\t\t\t\t\t\t\t\tse ha seleccionado un Pais destino");
      }
      
      public void success(final ViajarResponse response, final Response arg1) {
        View _findViewById = PaisActivity.this.findViewById(R.id.estasEn);
        final TextView textView = ((TextView) _findViewById);
        String _paisActual = response.getPaisActual();
        String _plus = ("Estas en:" + _paisActual);
        textView.setText(_plus);
        List<String> _nombresDePaises = response.getNombresDePaises();
        PaisActivity.this.paises = _nombresDePaises;
        String _paisActual_1 = response.getPaisActual();
        PaisActivity.this.paisActual = _paisActual_1;
        PaisActivity.this.refrescarSpinner();
      }
    });
  }
  
  public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
    if ((requestCode == PaisActivity.ES_VILLANO_ACTIVITY)) {
      if ((resultCode == Activity.RESULT_OK)) {
        boolean _booleanExtra = data.getBooleanExtra("ES_VIAJAR_ACTIVITY", false);
        if (_booleanExtra) {
          this.onBuscarPistas(null);
        } else {
          final String villanoTemp = data.getStringExtra("VILLANO");
          boolean _notEquals = (!Objects.equal(villanoTemp, null));
          if (_notEquals) {
            this.villanoSeleccionado = villanoTemp;
          }
        }
      }
    }
  }
  
  public void onGenerarOrden(final View button) {
    final Intent intent = new Intent(this, OrdenDeArrestoActivity.class);
    intent.putExtra("PAIS_ACTUAL", this.paisActual);
    intent.putStringArrayListExtra("VILLANOS", ((ArrayList<String>) this.villanos));
    this.startActivityForResult(intent, PaisActivity.ES_VILLANO_ACTIVITY);
  }
}
