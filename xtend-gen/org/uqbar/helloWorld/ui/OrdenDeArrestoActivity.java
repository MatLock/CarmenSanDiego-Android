package org.uqbar.helloWorld.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.uqbar.helloWorld.services.EmitirOrdenService;
import org.uqbar.helloWorld.ui.PaisActivity;
import org.uqbar.helloWorld.ui.R;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

@SuppressWarnings("all")
public class OrdenDeArrestoActivity extends Activity {
  private List<String> villanos;
  
  private String ordenParaVillano;
  
  public void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.setContentView(R.layout.ordendearresto);
    final Intent intent = this.getIntent();
    ArrayList<String> _stringArrayListExtra = intent.getStringArrayListExtra("VILLANOS");
    this.villanos = _stringArrayListExtra;
    View _findViewById = this.findViewById(R.id.villanoSeleccionado);
    final TextView textField = ((TextView) _findViewById);
    textField.setText(this.ordenParaVillano);
    View _findViewById_1 = this.findViewById(R.id.OrdenDeArrestoActivity_ESTAS_EN);
    final TextView estasEn = ((TextView) _findViewById_1);
    String _stringExtra = intent.getStringExtra("PAIS_ACTUAL");
    String _plus = ("Estas en:" + _stringExtra);
    estasEn.setText(_plus);
    this.refrescarSpinner();
  }
  
  public void onEmitirOrden(final View button) {
    RestAdapter.Builder _builder = new RestAdapter.Builder();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append(PaisActivity.URL, "");
    RestAdapter.Builder _setEndpoint = _builder.setEndpoint(_builder_1.toString());
    final RestAdapter restAdapter = _setEndpoint.build();
    final EmitirOrdenService request = restAdapter.<EmitirOrdenService>create(EmitirOrdenService.class);
    View _findViewById = this.findViewById(R.id.villanos);
    Object _selectedItem = ((Spinner) _findViewById).getSelectedItem();
    final String villano = ((String) _selectedItem);
    request.emitirOrdenPara(villano, new Callback<String>() {
      public void failure(final RetrofitError e) {
        Log.e(" ", " ", e);
      }
      
      public void success(final String arg0, final Response arg1) {
        View _findViewById = OrdenDeArrestoActivity.this.findViewById(R.id.villanoSeleccionado);
        final TextView textField = ((TextView) _findViewById);
        textField.setText(villano);
        OrdenDeArrestoActivity.this.ordenParaVillano = villano;
      }
    });
  }
  
  public void refrescarSpinner() {
    View _findViewById = this.findViewById(R.id.villanos);
    final Spinner spVillanos = ((Spinner) _findViewById);
    final ArrayAdapter<CharSequence> aVillanos = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_dropdown_item, ((CharSequence[])Conversions.unwrapArray(this.villanos, CharSequence.class)));
    aVillanos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spVillanos.setAdapter(aVillanos);
  }
  
  public void onVolverAtras(final View button) {
    final Intent i = new Intent(this, PaisActivity.class);
    i.putExtra("VILLANO", this.ordenParaVillano);
    this.setResult(Activity.RESULT_OK, i);
    this.finish();
  }
  
  public void onVolverAtrasYVisitarLugar(final View button) {
    final Intent i = new Intent(this, PaisActivity.class);
    i.putExtra("ES_VIAJAR_ACTIVITY", true);
    this.setResult(Activity.RESULT_OK, i);
    this.finish();
  }
}
