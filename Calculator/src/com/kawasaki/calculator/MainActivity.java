package com.kawasaki.calculator;

import com.kawasaki.calculator.R.id;

import android.os.Bundle;
import android.app.Activity;
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
	private calc cal;
	private InputData data;

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

		calc_init();
		return;

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);

		return true;
	}

	@Override
	public void onClick(View v) {
		int button_id = v.getId();
		String displaied_number;

		//input data
		switch (button_id) {
		case id.Button0:
			data.set(0);
			break;
		case id.Button1:
			data.set(1);
			break;
		case id.Button2:
			data.set(2);
			break;
		case id.Button3:
			data.set(3);
			break;
		case id.Button4:
			data.set(4);
			break;
		case id.Button5:
			data.set(5);
			break;
		case id.Button6:
			data.set(6);
			break;
		case id.Button7:
			data.set(7);
			break;
		case id.Button8:
			data.set(8);
			break;
		case id.Button9:
			data.set(9);
			break;
		case id.Button00:
			// cal.setVal(0);
			break;
		default:
			// nothing to do
		}
		
		displaied_number = data.getString();
		
		//operation
		switch(button_id){
		case id.ButtonPlus:
			displaied_number = data.getString();
			cal.setVal(data.getNumberAndClear());
			cal.setOperatorAdd();
			break;
		case id.ButtonMinus:
			displaied_number = data.getString();
			cal.setVal(data.getNumberAndClear());
			cal.setOperatorSub();
			break;
		case id.ButtonDiv:
			displaied_number = data.getString();
			cal.setVal(data.getNumberAndClear());
			cal.setOperatorDiv();
			break;
		case id.ButtonX:
			displaied_number = data.getString();
			cal.setVal(data.getNumberAndClear());
			cal.setOperatorMul();
			break;
		case id.ButtonClear:
			calc_init();
			break;
		default:
			//nothing to do
		}

		//display result
		if (button_id == id.ButtonEqual) {
			cal.setVal(data.getNumberAndClear());
			double tmp = cal.equal();
			result.setText(Double.toString(tmp));
			data.setNumber(tmp);
		} else
			result.setText(displaied_number);
	}

	private void calc_init() {
		cal = new calc();
		result.setText("0");
		data = new InputData();
	}
}
