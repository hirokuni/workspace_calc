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
	private boolean isConstDivMode;
	private boolean isConstSubMode;
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
		reset_all_const_calc();
		isEqual = false;
		setTmp = new BigDecimal(0);
		setConstValB1 = new BigDecimal(0);
		setConstValB2 = new BigDecimal(0);
	}

	public void setVal(double i) {

		switch (operator) {
		case NON:
			memoryB = new BigDecimal(i);
			reset_all_const_calc();
			break;
		case ADD:
			setB = new BigDecimal(i);
			if (isEqual == true) {
				isConstAddMode = true;
			}

			if (isConstAddMode == false) {
				setConstValB1 = new BigDecimal(i);
			} else {
				setConstValB2 = new BigDecimal(i);
			}
			break;
		case SUB:
			setB = new BigDecimal(-i);

			if (isEqual == true) {
				isConstSubMode = true;
			}

			if (isConstSubMode == false) {
				setConstValB1 = new BigDecimal(i);
			} else {
				setConstValB2 = new BigDecimal(i);
			}
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

			if (isEqual == true) {
				isConstDivMode = true;
			}

			if (isConstDivMode == false) {
				setConstValB1 = new BigDecimal(i);
			} else {
				setConstValB2 = new BigDecimal(i);
			}

			break;
		default:
			Log.w(TAG, "illegal operator");
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

	private void reset_all_const_calc(){
		isConstMulMode = false;
		isConstAddMode = false;
		isConstDivMode = false;
		isConstSubMode = false;
		isEqual = false;
	}
	
	/*
	 * Add
	 */
	public void setOperatorAdd() {
		operator = ADD;
		isEqual = false;
		reset_all_const_calc();
		addTmpVal();
	}

	/*
	 * Sub
	 */
	public void setOperatorSub() {
		operator = SUB;
		isEqual = false;
		reset_all_const_calc();
		addTmpVal();
	}

	public void setOperatorMul() {
		operator = MUL;
		isEqual = false;
		reset_all_const_calc();
	}

	public void setOperatorDiv() {
		operator = DIV;
		isEqual = false;
		reset_all_const_calc();
	}

	public double equal() {
		isEqual = true;

		if (isConstMulMode == true) {
			memoryB = setConstValB2.multiply(setConstValB1);
			isConstMulMode = false;
			setB = new BigDecimal(0);
			setTmp = new BigDecimal(0);
			return setConstValB2.multiply(setConstValB1).doubleValue();
		} else if (isConstAddMode == true) {
			memoryB = setConstValB2.add(setConstValB1);
			isConstAddMode = false;
			setB = new BigDecimal(0);
			setTmp = new BigDecimal(0);
			return setConstValB2.add(setConstValB1).doubleValue();
		} else if (isConstDivMode == true) {
			memoryB = setConstValB2.divide(setConstValB1);
			isConstDivMode = false;
			setB = new BigDecimal(0);
			setTmp = new BigDecimal(0);
			return setConstValB2.divide(setConstValB1).doubleValue();
		} else if (isConstSubMode == true) {
			memoryB = setConstValB2.subtract(setConstValB1);
			isConstDivMode = false;
			setB = new BigDecimal(0);
			setTmp = new BigDecimal(0);
			return setConstValB2.subtract(setConstValB1).doubleValue();
		} else {
			memoryB = memoryB.add(setTmp);
			memoryB = memoryB.add(setB);
			setB = new BigDecimal(0);
			setTmp = new BigDecimal(0);
			return memoryB.doubleValue();
		}
	}

}
