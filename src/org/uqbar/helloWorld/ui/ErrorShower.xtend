package org.uqbar.helloWorld.ui

import android.app.Activity
import android.content.Intent

class ErrorShower extends Activity {
	
	
	
	def showError(String s){
		val intent = new Intent(this,ActivityError)
		intent.putExtra("ERROR",s)
		startActivity(intent)
	}
	
	
}