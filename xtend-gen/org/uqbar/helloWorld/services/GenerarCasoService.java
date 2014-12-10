package org.uqbar.helloWorld.services;

import restService.GenerarCasoResponse;
import retrofit.Callback;
import retrofit.http.GET;

@SuppressWarnings("all")
public interface GenerarCasoService {
  @GET("/getCaso")
  public abstract void getCaso(final Callback<GenerarCasoResponse> callback);
}
