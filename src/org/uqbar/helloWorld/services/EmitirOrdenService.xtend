package org.uqbar.helloWorld.services

import retrofit.http.Path
import retrofit.http.GET
import retrofit.Callback

interface EmitirOrdenService {
	
	@GET("/emitirOrdenPara/{nombreVillano}")
	def void emitirOrdenPara(@Path("nombreVillano")String nombreVillano,Callback<String>callback);
	
}