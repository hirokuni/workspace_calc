package com.kawasaki.calculator;

import java.math.BigDecimal;

import android.util.Log;

public class calc {
	private static String TAG = calc.class.getSimpleName();

	private BigDecimal memoryB;
	private BigDecimal setB;
	private BigDecimal setTmp;
	
	private BigDecimal setConstMulB1;
	private BigDecimal setConstMulB2;
	private BigDecimal setConstAddB1;
	private BigDecimal setConstAddB2;
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
		setConstMulB1 = new BigDecimal(0);
		setConstMulB2 = new BigDecimal(0);
		setConstAddB1 = new BigDecimal(0);
		setConstAddB2 = new BigDecimal(0);
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
				setConstAddB1 = new BigDecimal(i);
			} else {
				setConstAddB2 = new BigDecimal(i);
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
				setConstMulB1 = new BigDecimal(i);
			} else {
				setConstMulB2 = new BigDecimal(i);
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
		addTmpVal();
	}
	
	/*
	 * Sub
	 */
	public void setOperatorSub() {
		operator = SUB;
		addTmpVal();
	}
	
	public void setOperatorMul() {
		operator = MUL;
	}
	
	public void setOperatorDiv() {
		operator = DIV;
	}
	
	public double equal() {
		isEqual = true;
		
		if (isConstMulMode == true) {
			return setConstMulB1.multiply(setConstMulB2).doubleValue();
		} else if (isConstAddMode == true) {
			return setConstAddB1.add(setConstAddB2).doubleValue();
		} else {
			memoryB = memoryB.add(setTmp);
			memoryB = memoryB.add(setB);
			return memoryB.doubleValue();
		}
	}


	
	
	
	

	

}
