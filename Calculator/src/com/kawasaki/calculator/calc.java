package com.kawasaki.calculator;

import java.math.BigDecimal;

import android.util.Log;

public class calc {
	private static String TAG = calc.class.getSimpleName();

	private BigDecimal memoryB;
	private BigDecimal setB;
	private BigDecimal setTmp;
	
	private BigDecimal setConstValB1;
	private BigDecimal setConstValB2;
	private boolean isConstMulMode;
	private boolean isConstAddMode;
	private boolean isEqual;
	
	private int operator;
	static final int NON = 0;
	static final int ADD = 1;
	static final int SUB = 2;
	static final int DIV = 3;
	static final int MUL = 4;
	
	public calc() {
		clear();
	}
	
	public void clear() {
		operator = NON;
		memoryB = new BigDecimal(0);
		setB = new BigDecimal(0);
		isConstMulMode = false;
		isConstAddMode = false;
		isEqual = false;
		setTmp = new BigDecimal(0);
		setConstValB1 = new BigDecimal(0);
		setConstValB2 = new BigDecimal(0);
		}

	public void setVal(double i) {
		
		switch(operator) {
		case NON:
			memoryB = new BigDecimal(i);
			isEqual = false;
			isConstMulMode = false;
			isConstAddMode = false;
			break;
		case ADD:
			setB = new BigDecimal(i);
			if (isEqual == true){
				isConstAddMode = true;
			}
			
			if (isConstAddMode == false) {
				setConstValB1 = new BigDecimal(i);
			} else {
				setConstValB2 = new BigDecimal(i);
			}
			
			
			isConstMulMode = false;
			isEqual = false;
			break;
		case SUB:
			setB = new BigDecimal(-i);
			isEqual = false;
			isConstMulMode = false;
			isConstAddMode = false;
			break;
		case MUL:
			if (setTmp.doubleValue() != 0.0) {
				setTmp = setTmp.multiply(new BigDecimal(i));
			} else if (setB.doubleValue() != 0.0) {
				BigDecimal tmp = setB.multiply(new BigDecimal(i));
				setTmp = tmp;
				setB = new BigDecimal(0);
			} else {
				memoryB = memoryB.multiply(new BigDecimal(i));
			}
			
			if (isEqual == true) {
				isConstMulMode = true;
			}
			
			if (isConstMulMode == false) {
				setConstValB1 = new BigDecimal(i);
			} else {
				setConstValB2 = new BigDecimal(i);
			}
			isConstAddMode = false;
			break;
		case DIV:
			if (setTmp.doubleValue() != 0.0) {
				setTmp = setTmp.divide(new BigDecimal(i));
			} else if (setB.doubleValue() != 0.0) {
				BigDecimal tmp = setB.divide(new BigDecimal(i));
				setTmp = tmp;
				setB = new BigDecimal(0);
			} else {
				memoryB = memoryB.divide(new BigDecimal(i));
			}
			isEqual = false;
			isConstMulMode = false;
			isConstAddMode = false;
			break;
		default:
			Log.w(TAG,"illegal operator");
			break;
		}
		
		return;
	}
	
	private void addTmpVal() {
		memoryB = memoryB.add(setB);
		setB = new BigDecimal(0);
		
		memoryB = memoryB.add(setTmp);
		setTmp = new BigDecimal(0);
	}
	
	/*
	 * Add
	 */
	public void setOperatorAdd() {
		operator = ADD;	
		isEqual = false;
		addTmpVal();
	}
	
	/*
	 * Sub
	 */
	public void setOperatorSub() {
		operator = SUB;
		isEqual = false;
		addTmpVal();
	}
	
	public void setOperatorMul() {
		operator = MUL;
		isEqual = false;
	}
	
	public void setOperatorDiv() {
		operator = DIV;
		isEqual = false;
	}
	
	public double equal() {
		isEqual = true;
		
		if (isConstMulMode == true) {
			memoryB = setConstValB1.multiply(setConstValB2);
			isConstMulMode = false;
			setB = new BigDecimal(0);
			setTmp = new BigDecimal(0);
			return setConstValB1.multiply(setConstValB2).doubleValue();
		} else if (isConstAddMode == true) {
			memoryB = setConstValB1.add(setConstValB2);
			isConstAddMode = false;
			setB = new BigDecimal(0);
			setTmp = new BigDecimal(0);
			return setConstValB1.add(setConstValB2).doubleValue();
		} else {
			memoryB = memoryB.add(setTmp);
			memoryB = memoryB.add(setB);
			setB = new BigDecimal(0);
			setTmp = new BigDecimal(0);
			return memoryB.doubleValue();
		}
	}


	
	
	
	

	

}
