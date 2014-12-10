package org.uqbar.helloWorld.services

import restService.GenerarCasoResponse
import retrofit.Callback
import retrofit.http.GET

interface GenerarCasoService {
	
	@GET("/getCaso")
	def void getCaso(Callback<GenerarCasoResponse> callback);
	
}