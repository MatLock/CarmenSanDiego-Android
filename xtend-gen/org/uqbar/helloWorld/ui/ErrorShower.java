package org.uqbar.helloWorld.ui;

import android.app.Activity;
import android.content.Intent;
import org.uqbar.helloWorld.ui.ActivityError;

@SuppressWarnings("all")
public class ErrorShower extends Activity {
  public void showError(final String s) {
    final Intent intent = new Intent(this, ActivityError.class);
    intent.putExtra("ERROR", s);
    this.startActivity(intent);
  }
}
