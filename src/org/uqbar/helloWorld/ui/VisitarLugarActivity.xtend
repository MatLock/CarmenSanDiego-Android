package org.uqbar.helloWorld.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import org.uqbar.helloWorld.services.VisitarLugarService
import restService.ObtenerPistaRequest
import restService.ObtenerPistaResponse
import retrofit.Callback
import retrofit.RestAdapter
import retrofit.RetrofitError
import retrofit.client.Response

class VisitarLugarActivity extends ErrorShower{
	
	String paisActual;
	Button lugar1
	Button lugar2
	Button lugar3
	
	
	override onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState)
		setContentView(R.layout.visitarlugar)
		val intent = getIntent();
		val textView = findViewById(R.id.VISITAR_LUGAR_ESTAS_EN) as TextView
		textView.text="Estas En: " + intent.getStringExtra("PAIS_ACTUAL")
		paisActual = intent.getStringExtra("PAIS_ACTUAL");
        lugar1 = findViewById(R.id.banco) as Button
        lugar2 = findViewById(R.id.biblioteca) as Button
        lugar3 = findViewById(R.id.club) as Button 		
	}
	
	
	def onVisitarBanco(View button){
		visitarGenerico("Banco")
	}
	
	def onVisitarBiblioteca(View button){
		visitarGenerico("Biblioteca")
	}
	
	def onVisitarClub(View button){
		visitarGenerico("Club")
	}
	
	def visitarGenerico(String lugar){
		val restAdapter = new RestAdapter.Builder()
		                .setEndpoint('''«PaisActivity.URL»''' )
                .build();
	   val post = restAdapter.create(VisitarLugarService)
	   val request = new ObtenerPistaRequest(paisActual,lugar);
	   
	   post.visitarLugar(request,new Callback<ObtenerPistaResponse>(){
					
					override failure(RetrofitError arg0) {
						showError("Ha ocurrido un problema de comunicacion con el servidor")
					}
					
					override success(ObtenerPistaResponse response, Response arg1) {
						mostrarDescripcion(response)
					}
	     })
	}
	
	def mostrarDescripcion(ObtenerPistaResponse response){
		val textView = findViewById(R.id.pista) as TextView
		textView.text = response.descripcion
	}
	
	
	 def onVolverAtras(View button){
		finish()
	} 
	
}