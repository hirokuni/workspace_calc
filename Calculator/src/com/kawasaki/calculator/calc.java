package com.kawasaki.calculator;

import java.math.BigDecimal;

import android.util.Log;

public class calc {
	private static String TAG = calc.class.getSimpleName();

	private BigDecimal memory;
	private BigDecimal tmp_memory_1;
	private BigDecimal tmp_memory_2;
	private BigDecimal set_memory;

	private BigDecimal const_val_1;
	private BigDecimal const_val_2;
	private boolean isConstMulMode;
	private boolean isConstAddMode;
	private boolean isConstDivMode;
	private boolean isConstSubMode;
	private boolean isEqual;
	private boolean isSet;

	private int operator;
	static final int NON = 0;
	static final int ADD = 1;
	static final int SUB = 2;
	static final int DIV = 3;
	static final int MUL = 4;

	static int digitNumLimit;

	public calc() {
		clear();
	}

	public void clear() {
		operator = NON;
		memory = new BigDecimal(0);
		tmp_memory_1 = new BigDecimal(0);
		reset_all_const_calc();
		isEqual = false;
		isSet = false;
		tmp_memory_2 = new BigDecimal(0);
		const_val_1 = new BigDecimal(0);
		const_val_2 = new BigDecimal(0);
		set_memory = new BigDecimal(0);
		digitNumLimit = 10;// default;
	}

	// when arithmeticEception happens, clear() must be called.
	public void setVal(double i) {
		set_memory = new BigDecimal(i);
		isSet = true;

		switch (operator) {
		case NON:
			Log.d(TAG, "setVal NON : " + i);
			memory = new BigDecimal(i);
			reset_all_const_calc();
			return;
		case ADD:
			Log.d(TAG, "setVal Add : " + i);
			tmp_memory_1 = new BigDecimal(i);
			if (isEqual == true) {
				isConstAddMode = true;
			}
			break;
		case SUB:
			Log.d(TAG, "setVal Sub : " + i);
			tmp_memory_1 = new BigDecimal(-i);

			if (isEqual == true) {
				isConstSubMode = true;
			}
			break;
		case MUL:
			Log.d(TAG, "setVal Mul : " + i);

			if (tmp_memory_2.doubleValue() != 0.0) {
				tmp_memory_2 = tmp_memory_2.multiply(new BigDecimal(i));
			} else if (tmp_memory_1.doubleValue() != 0.0) {
				tmp_memory_2 = tmp_memory_1.multiply(new BigDecimal(i));
				tmp_memory_1 = new BigDecimal(0);
			} else {
				memory = memory.multiply(new BigDecimal(i));
			}

			if (isEqual == true) {
				isConstMulMode = true;
			}
			break;
		case DIV:
			Log.d(TAG, "setVal Div : " + i);

			/*
			 * NOTE : 銀行の計算において、小数点以下は切り捨てている場合が多いようだ （利息計算で切り上げる場合、違法の危険性がある）
			 * TODO 四捨五入、切り捨てを計算上、いつどちらを設定するかをClearにする
			 */
			if (tmp_memory_2.doubleValue() != 0.0) {
				tmp_memory_2 = tmp_memory_2.divide(new BigDecimal(i),
						digitNumLimit, BigDecimal.ROUND_DOWN);
			} else if (tmp_memory_1.doubleValue() != 0.0) {
				tmp_memory_2 = tmp_memory_1.divide(new BigDecimal(i),
						digitNumLimit, BigDecimal.ROUND_DOWN);
				tmp_memory_1 = new BigDecimal(0);
			} else {
				memory = memory.divide(new BigDecimal(i), digitNumLimit,
						BigDecimal.ROUND_DOWN);
			}

			if (isEqual == true) {
				isConstDivMode = true;
			}
			break;
		default:
			Log.w(TAG, "illegal operator");
			return;
		}

		boolean isConstMode = isConstAddMode | isConstMulMode | isConstDivMode
				| isConstSubMode;

		if (isConstMode == false) {
			const_val_1 = new BigDecimal(i);
		} else {
			const_val_2 = new BigDecimal(i);
		}

		return;
	}

	private void addTmpVal() {
		memory = memory.add(tmp_memory_1);
		tmp_memory_1 = new BigDecimal(0);

		memory = memory.add(tmp_memory_2);
		tmp_memory_2 = new BigDecimal(0);
	}

	private void reset_all_const_calc() {
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

		if (isEqual && isSet) {
			BigDecimal tmp = set_memory;
			clear();
			tmp_memory_1 = tmp;
		}

		operator = ADD;
		addTmpVal();
		reset_all_const_calc();
	}

	/*
	 * Sub
	 */
	public void setOperatorSub() {
		if (isEqual && isSet) {
			BigDecimal tmp = set_memory;
			clear();
			tmp_memory_1 = tmp;
		}

		operator = SUB;
		addTmpVal();
		reset_all_const_calc();
	}

	public void setOperatorMul() {
		if (isEqual && isSet) {
			BigDecimal tmp = set_memory;
			clear();
			tmp_memory_1 = tmp;
		}

		operator = MUL;
		reset_all_const_calc();
	}

	public void setOperatorDiv() {
		if (isEqual && isSet) {
			BigDecimal tmp = set_memory;
			clear();
			tmp_memory_1 = tmp;
		}

		operator = DIV;
		reset_all_const_calc();
	}

	public double equal() {
		isEqual = true;

		if (isConstMulMode == true) {
			memory = const_val_2.multiply(const_val_1);
			isConstMulMode = false;
		} else if (isConstAddMode == true) {
			memory = const_val_2.add(const_val_1);
			isConstAddMode = false;
		} else if (isConstDivMode == true) {
			memory = const_val_2.divide(const_val_1, digitNumLimit,
					BigDecimal.ROUND_DOWN);
			isConstDivMode = false;
		} else if (isConstSubMode == true) {
			memory = const_val_2.subtract(const_val_1);
			isConstDivMode = false;
		} else {
			memory = memory.add(tmp_memory_2);
			memory = memory.add(tmp_memory_1);
		}

		tmp_memory_1 = new BigDecimal(0);
		tmp_memory_2 = new BigDecimal(0);
		set_memory = new BigDecimal(0);
		isSet = false;

		Log.d(TAG, "equal : " + memory.doubleValue());

		return memory.doubleValue();
	}

	public void setDigitsLimit(int limitNum) {
		digitNumLimit = limitNum;

	}

}
