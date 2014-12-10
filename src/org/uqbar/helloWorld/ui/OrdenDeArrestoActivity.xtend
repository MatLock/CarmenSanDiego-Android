package org.uqbar.helloWorld.ui

import android.app.Activity
import java.util.List
import android.os.Bundle
import android.view.View
import retrofit.Callback
import retrofit.RestAdapter
import retrofit.RetrofitError
import retrofit.client.Response
import org.uqbar.helloWorld.services.EmitirOrdenService
import android.widget.Spinner
import android.widget.ArrayAdapter
import android.widget.TextView
import android.util.Log
import android.content.Intent

class OrdenDeArrestoActivity  extends Activity{
	
	List<String> villanos;
	String ordenParaVillano
	
	override onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState)
		setContentView(R.layout.ordendearresto)
		val intent = getIntent();
		
		villanos = intent.getStringArrayListExtra("VILLANOS");
		val textField = findViewById(R.id.villanoSeleccionado) as TextView
		textField.text= ordenParaVillano
		
		val estasEn = findViewById(R.id.OrdenDeArrestoActivity_ESTAS_EN) as TextView
		estasEn.text = "Estas en:" + intent.getStringExtra("PAIS_ACTUAL")
		
		refrescarSpinner()
	}
	
	
	def onEmitirOrden(View button){
		
		val restAdapter = new RestAdapter
						.Builder()
		                .setEndpoint('''«PaisActivity.URL»''' )
                		.build();
                		
        val request = restAdapter.create(EmitirOrdenService)
        val villano = (findViewById(R.id.villanos) as Spinner).selectedItem as String;
        request.emitirOrdenPara(villano,new Callback<String>(){
									
									override failure(RetrofitError e) {
										Log.e(" "," ",e)
									}
									
									override success(String arg0, Response arg1) {
										val textField = findViewById(R.id.villanoSeleccionado) as TextView
										textField.text = villano
										ordenParaVillano =  villano
									}
        	
        })
        
	}
	
	
	def refrescarSpinner(){
		
		val Spinner spVillanos = findViewById(R.id.villanos) as Spinner;
		val ArrayAdapter<CharSequence> aVillanos = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_dropdown_item, villanos);
		aVillanos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); 
		spVillanos.setAdapter(aVillanos);		
	}
	
	
	def onVolverAtras(View button){
		val i = new Intent(this, PaisActivity);
        i.putExtra("VILLANO", ordenParaVillano);
     	setResult(Activity.RESULT_OK, i );
		finish()
	}
	
	
	def onVolverAtrasYVisitarLugar(View button){
		val i = new Intent(this,PaisActivity)
		i.putExtra("ES_VIAJAR_ACTIVITY",true)
		setResult(Activity.RESULT_OK,i)
		finish()
	}
	
	
}