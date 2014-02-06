package com.kawasaki.calculator;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.EmptyStackException;

import de.congrace.exp4j.Calculable;
import de.congrace.exp4j.ExpressionBuilder;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;

import android.util.Log;

public class calc {
	private static String TAG = calc.class.getSimpleName();

	private StringBuilder mathematical_fomula;
	private double memory_d;

	private String lastOperator;
	private String lastNumber;
	private String constCalcNum;
	private String lastResultNumber;
	private static final String PLUS = "+";
	private static final String SUB = "-";
	private static final String DIV = "/";
	private static final String MUL = "*";

	private static StringBuilder history;
	
	public void setScale(int scale){
	}

	public calc(int scale) {
		if (history == null)
			history = new StringBuilder();
		clear();
	}

	public void clear() {
		Log.i(TAG, "clear()");
		mathematical_fomula = new StringBuilder();
		memory_d = 0;
		
		lastOperator = new String();
		lastNumber = new String();
		constCalcNum = new String();
		lastResultNumber = new String();
		
	}

	public void setVal(double i) {
		mathematical_fomula.append(i);
		lastNumber = Double.toString(i);
		Log.i(TAG, "Set : " + i);
		return;
	}

	private void setMathematicalFomula(String operator) {

		if (mathematical_fomula.length() > 0)
			if ((mathematical_fomula.charAt(mathematical_fomula.length() - 1) == '+')
					|| (mathematical_fomula
							.charAt(mathematical_fomula.length() - 1) == '-')
					|| (mathematical_fomula
							.charAt(mathematical_fomula.length() - 1) == '*')
					|| (mathematical_fomula
							.charAt(mathematical_fomula.length() - 1) == '/'))

			{
				mathematical_fomula
						.deleteCharAt(mathematical_fomula.length() - 1);
			}

		if (mathematical_fomula.length() > 0)
			mathematical_fomula.append(operator);
		else
			mathematical_fomula.append(lastResultNumber + operator);
	}

	public void setOperatorAdd() {
		setMathematicalFomula(PLUS);
		lastOperator = PLUS;
	}

	public void setOperatorSub() {
		setMathematicalFomula(SUB);
		lastOperator = SUB;
	}

	public void setOperatorMul() {
		setMathematicalFomula(MUL);
		lastOperator = MUL;
	}

	public void setOperatorDiv() {
		setMathematicalFomula(DIV);
		lastOperator = DIV;
	}

	public void resetHistory() {
		history = new StringBuilder();
	}
	
	
	
	public String getHistory() {
		return history.toString();
	}
	
	private double executeCalc(String str) throws UnknownFunctionException,
			UnparsableExpressionException, IllegalNumber {
		

		Calculable calc = new ExpressionBuilder(str).build();
		
		double ret = 0;
		try {
			ret = calc.calculate();
		}catch(IllegalArgumentException iax) {
			throw new IllegalNumber(iax.toString());
		}
		
		
		
		Log.i(TAG, "数式 : " + str.toString());
		Log.i(TAG, "結果 : " + ret);
		
		history.append("数式 : " + str);
		history.append("\n");
		history.append("結果 : " + ret);
		history.append("\n");
		
		return ret;

	}

	public double equal() throws UnknownFunctionException,
			UnparsableExpressionException, IllegalNumber {

		
		String tmp = mathematical_fomula.toString();

		if (!tmp.contains(PLUS) && !tmp.contains(DIV) && !tmp.contains(MUL)
				&& !tmp.contains(SUB)) {
			// 定数計算（定数加算・減算・乗算・除算）
			mathematical_fomula.append(lastOperator);
			mathematical_fomula.append(constCalcNum);
		} else {
			// 通常計算
			char lastIndexChar = mathematical_fomula.charAt(mathematical_fomula.length() - 1);
			if (lastIndexChar == '-' || lastIndexChar == '+' || lastIndexChar == '*' || lastIndexChar == '/')
			{
				mathematical_fomula.insert(mathematical_fomula.length(), lastNumber);
			}
			constCalcNum = lastNumber;
		}

		
		
		memory_d = executeCalc(mathematical_fomula.toString());
		mathematical_fomula = new StringBuilder();
		lastResultNumber = Double.toString(memory_d);

		return memory_d;
	}

	// if the calculation can't return current result, then
	// UnknownFunctionExcetion will be returned.
	public double getMemory() throws UnknownFunctionException, IllegalNumber {
		double ret = 0.0;
		String str = mathematical_fomula.toString();

		if (str.length() < 1)
			return ret;

		if ((str.charAt(str.length() - 1) == '+')
				|| (str.charAt(str.length() - 1) == '-')) {
			str = str.substring(0, str.length() - 1);
			try {
				ret = executeCalc(str);
			} catch (UnknownFunctionException e) {
				e.printStackTrace();
			} catch (UnparsableExpressionException e) {
				e.printStackTrace();
			} catch (EmptyStackException esex) {
				esex.printStackTrace();
				str = str.substring(0, str.length() - 1);
			}
		} else if ((str.charAt(str.length() - 1) == '*')
				|| (str.charAt(str.length() - 1) == '/')) {

			for (int i = str.length() - 2; i > 0; i--) {
				if ((str.charAt(i) == '+') || (str.charAt(i) == '-')) {
					try {
						ret = executeCalc(str.substring(i, str.length() - 1));
						return ret;
					} catch (UnparsableExpressionException e) {
						e.printStackTrace();
						throw new UnknownFunctionException(str);
					}
				}
			}

			try {
				ret = executeCalc(str.substring(0, str.length() - 1));
			} catch (UnparsableExpressionException e) {
				e.printStackTrace();
				throw new UnknownFunctionException(str);
			}

		} else {

			if (!str.contains(PLUS) && !str.contains(DIV) && !str.contains(MUL)
					&& !str.contains(SUB))
				try {
					ret = executeCalc(str);
				} catch (UnparsableExpressionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			else
				throw new UnknownFunctionException(str);
		}

		return ret;
	}
	

}
