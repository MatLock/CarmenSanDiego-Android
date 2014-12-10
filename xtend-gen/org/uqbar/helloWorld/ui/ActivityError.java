package org.uqbar.helloWorld.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import org.uqbar.helloWorld.ui.R;

@SuppressWarnings("all")
public class ActivityError extends Activity {
  public void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.setContentView(R.layout.activity_error);
    View _findViewById = this.findViewById(R.id.error);
    final TextView textView = ((TextView) _findViewById);
    Intent _intent = this.getIntent();
    String _stringExtra = _intent.getStringExtra("ERROR");
    textView.setText(_stringExtra);
  }
}
