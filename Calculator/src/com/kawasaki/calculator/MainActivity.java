package com.kawasaki.calculator;

import com.kawasaki.calculator.R.id;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {

	private final String TAG = MainActivity.class.getSimpleName();
	int button_ids[] = { R.id.Button0, R.id.Button1, R.id.Button2,
			R.id.Button3, R.id.Button4, R.id.Button5, R.id.Button6,
			R.id.Button7, R.id.Button8, R.id.Button9, R.id.Button00,
			R.id.ButtonEqual, R.id.ButtonPlus, R.id.ButtonMinus, R.id.ButtonX,
			R.id.ButtonDiv, R.id.ButtonPoint, R.id.ButtonClear };
	private Button buttons[];
	private TextView result;
	private Adapter adapter;
	private String MaxString = "0123456789.";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		buttons = new Button[button_ids.length];

		for (int i = 0; i < button_ids.length; i++) {
			Log.i(TAG, "button id list index : " + i);
			buttons[i] = (Button) findViewById(button_ids[i]);
			buttons[i].setOnClickListener(this);
		}

		result = (TextView) findViewById(id.display);

		adapter = new Adapter();
		

		

		return;

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.main, menu);

		return true;
	}

	@Override
	public void onClick(View v) {
		int button_id = v.getId();

		// input data
		switch (button_id) {
		case id.Button0:
			adapter.setVal(0);
			break;
		case id.Button1:
			adapter.setVal(1);
			break;
		case id.Button2:
			adapter.setVal(2);
			break;
		case id.Button3:
			adapter.setVal(3);
			break;
		case id.Button4:
			adapter.setVal(4);
			break;
		case id.Button5:
			adapter.setVal(5);
			break;
		case id.Button6:
			adapter.setVal(6);
			break;
		case id.Button7:
			adapter.setVal(7);
			break;
		case id.Button8:
			adapter.setVal(8);
			break;
		case id.Button9:
			adapter.setVal(9);
			break;
		case id.ButtonPoint:
			adapter.setPoint();
			break;
		case id.Button00:
			adapter.set00();
			break;
		case id.ButtonPlus:
			adapter.setOperator(Adapter.PLUS);
			break;
		case id.ButtonMinus:
			adapter.setOperator(Adapter.MINUS);
			break;
		case id.ButtonDiv:
			adapter.setOperator(Adapter.DIVISION);
			break;
		case id.ButtonX:
			adapter.setOperator(Adapter.MULTIPLY);
			break;
		case id.ButtonClear:
			adapter.clear();
			break;
		case id.ButtonEqual:
			adapter.equal();
			break;
		default:
			Log.w(TAG,"unknown button id");
		}

		result.setText(adapter.getString());

	}

	protected static final int MAX_FONT_SIZE = 500;
	protected static final int MIN_FONT_SIZE = 10;

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		// setMaxTextSize(MaxString, result);
		result.setBackgroundColor(Color.BLACK);
		
		result.setBackgroundColor(Color.WHITE);
		result.setTextColor(Color.BLACK);
		
		setMaxTextSize(MaxString, result);
	}

	/*
	 * Sample : http://mesubuta.blog.jp/archives/2323960.html
	 */
	private void setMaxTextSize(String str, TextView tv) {
		/*
		 * 1ピクセル＝1dip/scaledDensity
		 * http://labs.techfirm.co.jp/android/m_yamada/1668
		 */
		DisplayMetrics mmetrics = new DisplayMetrics();
		Paint p = new Paint();

		getWindowManager().getDefaultDisplay().getMetrics(mmetrics);
		final float density = mmetrics.scaledDensity;
		for (int i = MAX_FONT_SIZE; i > MIN_FONT_SIZE; i = i - 2) {
			p.setTextSize(i);
			float width = tv.getWidth() / density; // Dip単位に変換します
			
			Log.i(TAG, "Text View width : " + tv.getWidth());
			
			if (width != 0){
				Log.i(TAG,"width : " + width);
			}
			
			if ((width >= p.measureText(str))) {
				tv.setText(str);
				int size = (int) (i - (3 * density));
				tv.setTextSize(size);
				tv.setText("0");
				break;
			}
		}
	}
}
