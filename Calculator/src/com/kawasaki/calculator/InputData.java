package com.kawasaki.calculator;

import android.util.Log;

public class InputData {
	private final String TAG = InputData.class.getSimpleName();
	private String setdata;
	private boolean isSetNumber;
	private int digitLimitNumber;

	public InputData() {
		init();
	}

	
	
	private void init() {
		setdata = new String("0");
		isSetNumber = false;
		digitLimitNumber = 10;
	}

	public void set(int i) {
		if (setdata.length() > digitLimitNumber)
			return;
		
		if (setdata.contains(".")) {
			if (setdata.length() >= digitLimitNumber + 1)
				return;
		} else {
			if (setdata.length() >= digitLimitNumber)
				return;
		}

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
		Log.i(TAG, "getString# setdata  " + setdata);

		setdata = remove_point_0(setdata);
		
		return setdata;
	}
	
	public String remove_point_0(String str) {
		if (str.endsWith(".0")) {
			str = str.substring(0, str.length() - 2);
		}
		return str;
	}

	public void setPoint() {
		String point = ".";
		if (!setdata.contains("."))
			setdata += point;

	}

	public void set00() {
		StringBuilder sb = new StringBuilder();
		sb.append(setdata);

		if (!setdata.equalsIgnoreCase("0")) {
			int len = 0;
			if (setdata.contains("."))
				len -= 1;

			for (int i = 0; i < 2; i++) {
				{
					len += setdata.length() + "0".length();

					if (len <= digitLimitNumber)
						sb.append("0");
				}
			}
		}

		setdata = sb.toString();
		Log.i(TAG, "setdata : " + setdata);
	}

	public void setDigitNumberLimit(int i) {
		digitLimitNumber = i;
	}

}
