package com.kawasaki.calculator;

import android.util.Log;

public class InputData {
	private final String TAG = InputData.class.getSimpleName();
	String setdata;
	
	public InputData(){
		init();
	}
	
	private void init() {
		setdata = new String("0");
	}
	
	public void set(int i) {
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

	public int getNumberAndClear() {
		int ret = Integer.valueOf(setdata);
		init();
		return ret;
	}
	
	public String getString() {
		Log.i(TAG,"setdata + " + setdata);
		return setdata;
	}

}
