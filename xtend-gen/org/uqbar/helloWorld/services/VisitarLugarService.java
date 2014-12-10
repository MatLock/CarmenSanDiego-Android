package org.uqbar.helloWorld.services;

import restService.ObtenerPistaRequest;
import restService.ObtenerPistaResponse;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

@SuppressWarnings("all")
public interface VisitarLugarService {
  @POST("/visitarLugar")
  public abstract void visitarLugar(@Body final ObtenerPistaRequest request, final Callback<ObtenerPistaResponse> callback);
}
