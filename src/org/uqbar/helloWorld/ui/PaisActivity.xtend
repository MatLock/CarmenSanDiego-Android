package org.uqbar.helloWorld.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import java.util.ArrayList
import java.util.List
import org.uqbar.helloWorld.services.ViajarService
import restService.ViajarRequest
import restService.ViajarResponse
import retrofit.Callback
import retrofit.RestAdapter
import retrofit.RetrofitError
import retrofit.client.Response
import android.app.Activity

class PaisActivity extends ErrorShower{
	
	Button buscarPistas;
    List<String>villanos;
    List<String>paises;
    String paisActual;
    String villanoSeleccionado = "Nobody";
    
	public static String URL = "http://7.217.103.179:9000"
	public static int ES_VILLANO_ACTIVITY = 1
	
	override onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState)
		setContentView(R.layout.viajar)
		val intent = getIntent();
		
		villanos = intent.getStringArrayListExtra("VILLANOS");
		paises = intent.getStringArrayListExtra("PAISES");
		paisActual = intent.getStringExtra("PAIS_ACTUAL");
        buscarPistas = findViewById(R.id.buscarPistas) as Button 	
        
        val TextView textview = findViewById(R.id.estasEn) as TextView
        textview.text = textview.text + paisActual 
        
        refrescarSpinner()

	}
	
	def refrescarSpinner(){
		
		val Spinner spConexiones = findViewById(R.id.conexiones) as Spinner;
		val ArrayAdapter<CharSequence> adConexion = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_dropdown_item, paises);
		adConexion.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); 
		spConexiones.setAdapter(adConexion);		
	}
	
	def onBuscarPistas(View button){
		val intent = new Intent(this,VisitarLugarActivity);
		intent.putExtra("PAIS_ACTUAL",this.paisActual);
		startActivity(intent);
	}
	
	def onViajar(View button){
		val textView = findViewById(R.id.recorridoActual) as TextView
		textView.text = textView.text + "->" + paisActual
		
		val Spinner spConexiones = findViewById(R.id.conexiones) as Spinner;
		val pais = spConexiones.selectedItem as String
		
		
		val restAdapter = new RestAdapter.Builder()
		                .setEndpoint('''«PaisActivity.URL»''' )
                		.build();
	   val post = restAdapter.create(ViajarService)
	   val request = new ViajarRequest(pais);
	   
	   post.viajar(request,new Callback<ViajarResponse>(){
					
					override failure(RetrofitError arg0) {
						showError("Ha ocurrido un problema de comunicación con el servidor o no
									se ha seleccionado un Pais destino")
					}
					
					override success(ViajarResponse response, Response arg1) {
						val textView = findViewById(R.id.estasEn) as TextView
						textView.text ="Estas en:" + response.getPaisActual
						paises = response.getNombresDePaises
						paisActual = response.getPaisActual
						refrescarSpinner()
					}
					
					
	     })
	}
	
	
	override onActivityResult(int requestCode, int resultCode, Intent data) {
     	if ( requestCode == ES_VILLANO_ACTIVITY ){
        	  if ( resultCode == Activity.RESULT_OK ){
        	  	 if(data.getBooleanExtra("ES_VIAJAR_ACTIVITY",false)){
        	  	 	onBuscarPistas(null) // le pongo null para no pasarle una view, el metodo no hace nada con la view por parametro
        	  	 }else{
            	   val villanoTemp = data.getStringExtra("VILLANO")
    	        	   if(villanoTemp != null) 
        	    	       villanoSeleccionado =villanoTemp 
          			  }
        	  	 }
     	}
     
    }
	
	
	def onGenerarOrden(View button){
		val intent = new Intent(this,OrdenDeArrestoActivity);
		intent.putExtra("PAIS_ACTUAL",paisActual)
		intent.putStringArrayListExtra("VILLANOS",villanos  as ArrayList<String>);
		startActivityForResult(intent,ES_VILLANO_ACTIVITY);
	}
	
	
	

}