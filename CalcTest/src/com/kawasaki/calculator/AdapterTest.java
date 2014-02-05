package com.kawasaki.calculator;

import java.math.BigDecimal;

import de.congrace.exp4j.Calculable;
import de.congrace.exp4j.ExpressionBuilder;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;
import android.test.AndroidTestCase;
import android.util.Log;

public class AdapterTest extends AndroidTestCase {

	private Adapter adapter;

	protected void setUp() throws Exception {
		super.setUp();

		adapter = new Adapter();

	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * <pre>
	 * Step1. 3 + 3 + 3 
	 * Step2. clear
	 * Step3. 3
	 * </pre>
	 */
	public void test_calc_add_pattern_1() {
		// Step1
		adapter.setVal(3);
		assertEquals("3", adapter.getString());
		adapter.setOperator(Adapter.PLUS);
		assertEquals("3", adapter.getString());
		adapter.setVal(3);
		assertEquals("3", adapter.getString());
		adapter.setOperator(Adapter.PLUS);
		assertEquals("6", adapter.getString());

		// Step2
		adapter.setClear();
		assertEquals("0", adapter.getString());

		// Step3
		adapter.setVal(3);
		assertEquals("3", adapter.getString());
	}

	/**
	 * 5 x 3 + 3 / 2
	 */
	public void test_calc_4_calc_pattern_1() {
		adapter.setVal(5);
		assertEquals("5", adapter.getString());

		adapter.setOperator(Adapter.MULTIPLY);
		assertEquals("5", adapter.getString());

		adapter.setVal(3);
		assertEquals("3", adapter.getString());

		adapter.setOperator(Adapter.PLUS);
		assertEquals("15", adapter.getString());

		adapter.setVal(3);
		assertEquals("3", adapter.getString());

		adapter.setOperator(Adapter.DIVISION);
		assertEquals("3", adapter.getString());

		adapter.setVal(2);
		assertEquals("2", adapter.getString());

		adapter.equal();
		assertEquals("16.5", adapter.getString());
	}

	/**
	 * 5 / 3 + 3 x 2
	 */
	public void test_calc_4_calc_pattern_2() {
		adapter.setVal(5);
		assertEquals("5", adapter.getString());

		adapter.setOperator(Adapter.DIVISION);
		assertEquals("5", adapter.getString());

		adapter.setVal(3);
		assertEquals("3", adapter.getString());

		adapter.setOperator(Adapter.PLUS);
		assertEquals("1.666667E0", adapter.getString());

		adapter.setVal(3);
		assertEquals("3", adapter.getString());

		adapter.setOperator(Adapter.MULTIPLY);
		assertEquals("3", adapter.getString());

		adapter.setVal(2);
		assertEquals("2", adapter.getString());

		adapter.equal();
		assertEquals("7.666667E0", adapter.getString());
	}

	/**
	 * 5 / 2　x 2 + 3 x 2
	 */
	public void test_calc_4_calc_pattern_3() {
		adapter.setVal(5);
		assertEquals("5", adapter.getString());

		adapter.setOperator(Adapter.DIVISION);
		assertEquals("5", adapter.getString());

		adapter.setVal(2);
		assertEquals("2", adapter.getString());

		adapter.setOperator(Adapter.MULTIPLY);
		assertEquals("2.5", adapter.getString());

		adapter.setVal(2);
		assertEquals("2", adapter.getString());

		adapter.setOperator(Adapter.PLUS);
		assertEquals("5", adapter.getString());

		adapter.setVal(3);
		assertEquals("3", adapter.getString());

		adapter.setOperator(Adapter.MULTIPLY);
		assertEquals("3", adapter.getString());

		adapter.setVal(2);
		assertEquals("2", adapter.getString());

		adapter.equal();
		assertEquals("11", adapter.getString());
	}

	/**
	 * 5 / 2　x 2 - 3 x 2
	 */
	public void test_calc_4_calc_pattern_4() {
		adapter.setVal(5);
		assertEquals("5", adapter.getString());

		adapter.setOperator(Adapter.DIVISION);
		assertEquals("5", adapter.getString());

		adapter.setVal(2);
		assertEquals("2", adapter.getString());

		adapter.setOperator(Adapter.MULTIPLY);
		assertEquals("2.5", adapter.getString());

		adapter.setVal(2);
		assertEquals("2", adapter.getString());

		adapter.setOperator(Adapter.MINUS);
		assertEquals("5", adapter.getString());

		adapter.setVal(3);
		assertEquals("3", adapter.getString());

		adapter.setOperator(Adapter.MULTIPLY);
		assertEquals("-3", adapter.getString());

		adapter.setVal(2);
		assertEquals("2", adapter.getString());

		adapter.equal();
		assertEquals("-1", adapter.getString());
	}

	// 小数点追加
	public void test_arithmetic_point_1() {
		adapter.setVal(3);
		assertEquals("3", adapter.getString());
		adapter.setPoint();
		assertEquals("3.", adapter.getString());
		adapter.setVal(5);
		assertEquals("3.5", adapter.getString());
	}

	// 2重の小数点の無視
	public void test_arithmetic_point_2() {
		adapter.setVal(3);
		assertEquals("3", adapter.getString());
		adapter.setPoint();
		assertEquals("3.", adapter.getString());
		adapter.setPoint();
		assertEquals("3.", adapter.getString());
		adapter.setVal(9);
		assertEquals("3.9", adapter.getString());
	}

	// 00入力
	public void test_set_00_1() {
		adapter.setVal(3);
		assertEquals("3", adapter.getString());
		adapter.set00();
		assertEquals("300", adapter.getString());
	}

	// 何回０だけおしても０しか表示しない
	public void test_set_00_2() {
		adapter.setVal(0);
		assertEquals("0", adapter.getString());
		adapter.set00();
		assertEquals("0", adapter.getString());
	}

	// 0除算でerror表示
	public void test_error_display_1() {
		adapter.setVal(1);
		assertEquals("1", adapter.getString());
		adapter.setOperator(Adapter.DIVISION);
		adapter.setVal(0);
		adapter.equal();
		assertEquals("Error", adapter.getString());
	}

	public void test_() {
		double e = 3 * 10 + 2 - 5 / 2.5;
		try {
			Calculable calc = new ExpressionBuilder("002*3-2").build();
			Log.i("test", "calc = " + Double.toString(calc.calculate()));
		} catch (UnknownFunctionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnparsableExpressionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Log.i("test", "test : " + Double.toString(e));
	}

	// 10桁までは通常表記
	public void test_e10_1() {

		// double val = 1000000000;
		// double val = 1000000;
		// BigDecimal bd = new BigDecimal(val);
		// bd = bd.setScale(0, BigDecimal.ROUND_DOWN);
		// double ret = bd.doubleValue();
		// String str = bd.toEngineeringString();
		// String str2 = bd.toPlainString();
		// Log.i("test","test");
		adapter.setVal(1000000000);
		adapter.equal();
		assertEquals("1000000000", adapter.getString());
	}

	// 10桁を超えるとE表記
	public void test_e10_3() {
		adapter.setVal(1111111111);
		adapter.setOperator(Adapter.MULTIPLY);
		adapter.setVal(10);
		adapter.equal();
		assertEquals("1.111111E10", adapter.getString());
	}


	// 小数点以下の数値いれて、11桁にする

	// 最大桁数設定
	/*
	 * 01-26 09:43:59.041: W/Adapter(20864): Recalculation 数式 : 1*15*3/6 01-26
	 * 09:43:59.041: W/Adapter(20864): Recalculation 計算 : 2.5 01-26
	 * 09:43:59.041: W/Adapter(20864): Recalculation 検算 : 7.5
	 */

}
