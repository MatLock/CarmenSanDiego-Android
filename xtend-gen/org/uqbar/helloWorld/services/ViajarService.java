package org.uqbar.helloWorld.services;

import restService.ViajarRequest;
import restService.ViajarResponse;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

@SuppressWarnings("all")
public interface ViajarService {
  @POST("/viajar")
  public abstract void viajar(@Body final ViajarRequest request, final Callback<ViajarResponse> response);
}
