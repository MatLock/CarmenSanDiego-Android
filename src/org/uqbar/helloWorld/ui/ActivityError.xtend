package org.uqbar.helloWorld.ui

import android.app.Activity
import android.os.Bundle
import android.widget.TextView

class ActivityError extends Activity{
	
	
	
	override onCreate(Bundle savedInstanceState){
		
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_error)
		
		val textView = findViewById(R.id.error) as TextView
		textView.text = getIntent().getStringExtra("ERROR");
		
	}
	
}