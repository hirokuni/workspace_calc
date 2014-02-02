package com.kawasaki.calculator;

import android.util.Log;

public class InputData {
	private final String TAG = InputData.class.getSimpleName();
	String setdata;
	boolean isSetNumber;

	public InputData() {
		init();
	}

	private void init() {
		setdata = new String("0");
		isSetNumber = false;
	}

	public void set(int i) {

		if (isSetNumber) {
			setdata = Integer.toString(i);
			isSetNumber = false;
			return;
		}

		if (i == 0) {
			if (setdata.equalsIgnoreCase("0"))
				return;
		}

		if (setdata.equalsIgnoreCase("0")) {
			setdata = Integer.toString(i);
			return;
		}

		setdata += Integer.toString(i);
	}

	/**
	 * Set number forcibly
	 */
	public void setNumber(double num) {
		setdata = Double.toString(num);
		isSetNumber = true;
	}

	public double getNumberAndClear() {
		double ret = Double.valueOf(setdata);
		init();
		return ret;
	}

	public String getString() {
		Log.i(TAG, "setdata + " + setdata);
		return setdata;
	}

	public void setPoint(String point) {

		if (!setdata.contains("."))
			setdata += point;

	}

}
