package com.kawasaki.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class History extends Activity {
	private TextView textView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		textView =new TextView(this);
		
		setContentView(textView, new LayoutParams(LayoutParams.WRAP_CONTENT, 
		          LayoutParams.WRAP_CONTENT));
		textView.setMovementMethod(ScrollingMovementMethod.getInstance());
		textView.setVerticalScrollBarEnabled(true);
		textView.setText(MainActivity.history);
	}

}
