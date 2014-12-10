package org.uqbar.helloWorld.services;

import org.uqbar.helloWorld.domain.Greeting;
import retrofit.Callback;
import retrofit.http.GET;

@SuppressWarnings("all")
public interface GreetingService {
  @GET("/greeting")
  public abstract void getGreeting(final Callback<Greeting> callback);
}
