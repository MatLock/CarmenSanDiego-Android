package org.uqbar.helloWorld.services

import restService.ViajarRequest
import restService.ViajarResponse
import retrofit.http.Body
import retrofit.http.POST
import retrofit.Callback

interface ViajarService {
	
	
	@POST("/viajar")
	def void viajar(@Body ViajarRequest request,Callback<ViajarResponse>response)
}