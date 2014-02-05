package com.kawasaki.calculator;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.EmptyStackException;

import de.congrace.exp4j.Calculable;
import de.congrace.exp4j.ExpressionBuilder;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;
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

	StringBuilder sb;

	private final static String DEFUALT_MAX_STRING = "0123456789.";

	private String MaxString = DEFUALT_MAX_STRING;
	private int digitsLimit = MaxString.length() - 1;

	public Adapter() {
		calc_init();
	}

	public void setMaxDigitNumber(int limit) {
		digitsLimit = limit;
		data.setDigitNumberLimit(limit);
	}

	private void calc_init() {
		cal = new calc(digitsLimit);
		display = new String("0");
		data = new InputData();
		setMaxDigitNumber(digitsLimit);
		
		//recalculation
		sb = new StringBuilder();
	}

	public void setVal(int i) {
		data.set(i);
		display = data.getString();

		//recalculation
		sb.append(i);
	}

	public String getString() {
		return display;
	}

	public void setClear() {
		calc_init();
		setDisplay();
	}

	public void setPoint() {
		data.setPoint();
		display = data.getString();

		//recalculation
		if (!sb.toString().contains("."))
			sb.append(".");
	}

	public void set00() {
		data.set00();
		display = data.getString();
		
		//recalculation
		sb.append("00");
	}

	public void clear() {
		calc_init();
	}

	private void setDisplay() {
	
			try {
				
				String ret = prune_data(cal.getMemory());
				display = data.remove_point_0(ret);
				//display = data.remove_point_0(Double.toString(cal.getMemory()));
			} catch (UnknownFunctionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnparsableExpressionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
	}
	
	public void setOperator(int ope) {

		try {
			switch (ope) {
			case PLUS:
				cal.setVal(data.getNumberAndClear());
				cal.setOperatorAdd();
				setDisplay();
				
				//recalculation
				sb.append("+");
				break;
			case MINUS:
				cal.setVal(data.getNumberAndClear());
				cal.setOperatorSub();
				setDisplay();
				
				//recalculation
				sb.append("-");
				break;
			case DIVISION:
				cal.setVal(data.getNumberAndClear());
				cal.setOperatorDiv();
				setDisplay();
				
				//recalculation
				sb.append("/");
				break;
			case MULTIPLY:
				cal.setVal(data.getNumberAndClear());
				cal.setOperatorMul();
				setDisplay();
				
				//recalculation
				sb.append("*");
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

	private String prune_data(double dval) throws UnknownFunctionException, UnparsableExpressionException{
		String ret = new String("0");
		BigDecimal bd = new BigDecimal(dval);
		String plainStr = bd.toPlainString();
		
		if (plainStr.length() <= digitsLimit) {
			ret = plainStr;
		} else {
		  	ret = Double.toString(dval);
		  	if (ret.length() > digitsLimit) {
		  		DecimalFormat df = new DecimalFormat("0.000000E0");
		  		ret = df.format(dval);
		  	}
		  		
		}
		
		return ret;
	}
	
	public void equal() {
		double tmp = 0;
		try {
			String ret;
			
			cal.setVal(data.getNumberAndClear());
			tmp = cal.equal();
			
			/*
			BigDecimal bd = new BigDecimal(tmp);
			String plainStr = bd.toPlainString();
			
			
			if (plainStr.length() <= digitsLimit) {
				ret = plainStr;
			} else {
			  	ret = Double.toString(tmp);
			  	if (ret.length() > digitsLimit) {
			  		DecimalFormat df = new DecimalFormat("0.000000E0");
			  		ret = df.format(tmp);
			  	}
			  		
			}
			*/
			
			ret = prune_data(tmp);
			
			display = data.remove_point_0(ret);
			data.setNumber(tmp);
		}catch (java.lang.ArithmeticException aex) {
			Log.w(TAG, aex);
			display = "Error";
		} catch (UnknownFunctionException e) {
			e.printStackTrace();
			Log.w(TAG, e);
			display = "Error";
		} catch (UnparsableExpressionException e) {
			e.printStackTrace();
			Log.w(TAG, e);
			display = "Error";
		} catch (EmptyStackException ese) {
			ese.printStackTrace();
			Log.w(TAG, ese);
			display = "Error";
		}

		
		//検算
		try {
			Calculable calc = new ExpressionBuilder(sb.toString()).build();
			BigDecimal calcB = new BigDecimal(calc.calculate());
			calcB = calcB.setScale(10,BigDecimal.ROUND_DOWN);
			
			BigDecimal tmpB = new BigDecimal(tmp);
			tmpB = tmpB.setScale(10,BigDecimal.ROUND_DOWN);
			if (tmpB.doubleValue() != calcB.doubleValue()) {
				Log.w(TAG, "検算 数式 : " + sb.toString());
				Log.w(TAG, "計算結果  : " + tmpB.doubleValue());
				Log.w(TAG, "検算結果  : " + calcB.doubleValue());
			}
		} catch (UnknownFunctionException e) {
			e.printStackTrace();
		} catch (UnparsableExpressionException e) {
			e.printStackTrace();
		}catch (java.lang.ArithmeticException aex) {
			aex.printStackTrace();
		}catch(EmptyStackException ese) {
			ese.printStackTrace();
		}catch(IllegalArgumentException iax) {
			iax.printStackTrace();
		}
	}

}
