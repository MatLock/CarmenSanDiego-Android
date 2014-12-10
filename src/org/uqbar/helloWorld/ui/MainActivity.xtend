package org.uqbar.helloWorld.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import java.util.ArrayList
import org.uqbar.helloWorld.services.GenerarCasoService
import restService.GenerarCasoResponse
import retrofit.Callback
import retrofit.RestAdapter
import retrofit.RetrofitError
import retrofit.client.Response

class MainActivity extends ErrorShower {

	GenerarCasoResponse casoGenerado;
	
	override onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
	}
	
	def onGenerarCaso(View button){
		val restAdapter = new RestAdapter.Builder()
					.setEndpoint(PaisActivity.URL)
					.build();
		
		val GenerarCasoService generarCasoService = restAdapter.create(GenerarCasoService)
		
		generarCasoService.getCaso(new Callback<GenerarCasoResponse>(){
			
			override failure(RetrofitError e) {
				showError("Ocurrió un problema de comunicación con el servidor")
			}
			override success(GenerarCasoResponse response, Response arg1) {
				val textField = findViewById(R.id.descripcion) as TextView
				textField.text = response.descripcion;
				casoGenerado = response
			}
		})
	}
	
	def onEmpezar(View view){
		if(casoGenerado != null){
			val intent = new Intent(this,PaisActivity);
			intent.putExtra("PAIS_ACTUAL",casoGenerado.paisActual);
			intent.putStringArrayListExtra("VILLANOS",casoGenerado.nombresDeVillanos  as ArrayList<String>);
			intent.putStringArrayListExtra("PAISES",casoGenerado.nombresDePaises as ArrayList<String>);
			startActivity(intent);
    	}else{
    		showError("Debe generar un caso primero")
    	}
	
	}

}
