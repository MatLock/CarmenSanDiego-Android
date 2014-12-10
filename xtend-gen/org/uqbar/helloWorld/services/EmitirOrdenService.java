package org.uqbar.helloWorld.services;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

@SuppressWarnings("all")
public interface EmitirOrdenService {
  @GET("/emitirOrdenPara/{nombreVillano}")
  public abstract void emitirOrdenPara(@Path("nombreVillano") final String nombreVillano, final Callback<String> callback);
}
