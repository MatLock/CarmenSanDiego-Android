package org.uqbar.helloWorld.services

import restService.ObtenerPistaRequest
import restService.ObtenerPistaResponse
import retrofit.Callback
import retrofit.http.Body
import retrofit.http.POST

interface VisitarLugarService {
	
	
	@POST("/visitarLugar")
	def void visitarLugar(@Body ObtenerPistaRequest request ,Callback<ObtenerPistaResponse>callback);
}