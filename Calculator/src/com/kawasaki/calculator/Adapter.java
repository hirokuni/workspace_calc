package com.kawasaki.calculator;

import android.util.Log;

public class Adapter {
	private final String TAG = Adapter.class.getSimpleName();

	public final static int PLUS = 1;
	public final static int MINUS = 2;
	public final static int DIVISION = 3;
	public final static int MULTIPLY = 4;

	private String display;
	private InputData data;
	private calc cal;

	private String MaxString = "0123456789.";
	private int digitsLimit = MaxString.length() - 1;

	public Adapter() {
		calc_init();
	}
	
	public void setMaxDigitNumber(int limit) {
		digitsLimit = limit;
		cal.setDigitsLimit(limit);
	}

	private void calc_init() {
		cal = new calc();
		cal.setDigitsLimit(digitsLimit);
		display = new String("0");
		data = new InputData();
	}

	public void setVal(int i) {
		data.set(i);
		display = data.getString();
	}

	public String getString() {
		return display;
	}

	public void setClear() {
		calc_init();
		display = data.remove_point_0(Double.toString(cal.getMemory()));
	}

	public void setPoint() {
		data.setPoint();
		display = data.getString();
	}

	public void set00() {
		data.set00();
		display = data.getString();
	}

	public void clear() {
		calc_init();
	}
	
	public void setOperator(int ope) {

		try {
			switch (ope) {
			case PLUS:
				cal.setVal(data.getNumberAndClear());
				cal.setOperatorAdd();
				display = data.remove_point_0(Double.toString(cal.getMemory()));
				break;
			case MINUS:
				cal.setVal(data.getNumberAndClear());
				cal.setOperatorSub();
				display = data.remove_point_0(Double.toString(cal.getMemory()));
				break;
			case DIVISION:
				cal.setVal(data.getNumberAndClear());
				cal.setOperatorDiv();
				display = data.remove_point_0(Double.toString(cal.getMemory()));
				break;
			case MULTIPLY:
				cal.setVal(data.getNumberAndClear());
				cal.setOperatorMul();
				display = data.remove_point_0(Double.toString(cal.getMemory()));
				break;
			default:
				Log.w(TAG, "unknown operator");
				display = "Error";
			}
		} catch (java.lang.ArithmeticException aex) {
			Log.w(TAG, aex);
			display = "Error";
		}

	}

	public void equal() {
		try {
			cal.setVal(data.getNumberAndClear());
			double tmp = cal.equal();
			display = data.remove_point_0(Double.toString(tmp));
			data.setNumber(tmp);
		} catch (java.lang.ArithmeticException aex) {
			Log.w(TAG, aex);
			display = "Error";
		}

	}

}
