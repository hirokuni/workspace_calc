package com.kawasaki.calculator;

import java.math.BigDecimal;

import de.congrace.exp4j.Calculable;
import de.congrace.exp4j.ExpressionBuilder;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;
import android.test.AndroidTestCase;

public class InputTest extends AndroidTestCase {
	InputData data;

	protected void setUp() throws Exception {
		super.setUp();
		data = new InputData();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/* 整数のインプット */
	public void test_intput_string_double_digits_and_get_the_number() {
		data.set(5);
		data.set(6);

		assertEquals("56", data.getString());
		assertEquals(56.0, data.getNumberAndClear());
	}

	/* 二桁以上の数値インプットにおいて、0が最初にinputされたらカウントしない */
	public void test_0_is_not_added() {
		data.set(0);
		assertEquals(0.0, data.getNumberAndClear());
		assertEquals("0", data.getString());

		data.set(0);
		assertEquals(0.0, data.getNumberAndClear());
		assertEquals("0", data.getString());
	}

	/* 数値を取得すると、保存していた数値を０にリセットする */
	public void test_data_is_cleared_when_get_number() {
		data.set(5);
		assertEquals("5", data.getString());
		assertEquals(5.0, data.getNumberAndClear());
		assertEquals("0", data.getString());
		assertEquals(0.0, data.getNumberAndClear());
	}

	public void test_tmp_data_set() {
		data.set(5);
		data.set(1);
		data.setNumber(6.0);
		assertEquals(6.0, data.getNumberAndClear());
	}

	/**
	 * 
	 */
	public void test_setNumber_is_cleared_with_set() {
		data.set(5);
		data.set(1);
		assertEquals(51.0, data.getNumberAndClear());
		data.setNumber(51.0);
		assertEquals(51.0, data.getNumberAndClear());
		data.set(3);
		assertEquals(3.0, data.getNumberAndClear());
	}

	/* 小数点のinput */
	public void test_input_arithmetic_point() {
		data.set(1);
		data.setPoint();
		data.set(2);
		assertEquals(1.2, data.getNumberAndClear());
	}

	// if already point is contained, nothing is set
	public void test_nothing_is_set_when_alread_point_is_contained() {
		data.set(1);
		data.setPoint();
		data.set(2);
		data.setPoint();
		data.set(1);
		assertEquals(1.21, data.getNumberAndClear());
	}

	// 小数点以下に０を連続してinputできる
	public void test_0_can_be_added_continuously_after_the_decimal_point() {
		data.set(1);
		data.setPoint();
		data.set(0);
		data.set(0);
		data.set(1);
		assertEquals(1.001, data.getNumberAndClear());
	}

	public void test_0_can_be_added_continuously_after_the_decimal_point_with_all_0() {
		data.set(0);
		data.setPoint();
		data.set(0);
		data.set(0);
		data.set(0);
		assertEquals(0.000, data.getNumberAndClear());
	}

	// 何も数値を入力していない状態で、小数点から入力を開始すると、0.xxの表記になる
	public void test_input_arithmetic_point_with_no_input() {
		data.setPoint();
		data.set(2);
		assertEquals(0.2, data.getNumberAndClear());
	}

	// 0の数値を入力した後、小数点から入力を開始すると、0.xxの表記になる
	public void test_input_arithmetic_point_with_0() {
		data.set(0);
		data.setPoint();
		data.set(2);
		assertEquals(0.2, data.getNumberAndClear());
	}

	// 00の入力
	public void test_00_is_added_when_non_0_value_was_set() {
		data.set(1);
		data.set00();
		assertEquals(100.0, data.getNumberAndClear());
	}
	
	public void test_00_is_not_added_when_only_0_is_set() {
		data.set(0);
		data.set00();
		assertEquals(0.0, data.getNumberAndClear());
	}
	
	// 00は小数点の時、00は入力される
	public void test_00_is_added_after_the_arithmetic_point() {
		data.set(0);
		data.setPoint();
		data.set00();
		data.set(1);
		assertEquals(0.001, data.getNumberAndClear());
	}
	
	// max 2桁がinput されたときは、それ以上のINPUTは無視される
	public void test_max_input_digit_number_is_2() {
		data.setDigitNumberLimit(2);
		
		data.set(1);
		data.set(2);
		data.set(3);
		
		assertEquals(12.0, data.getNumberAndClear());
	}
	
	// max 4桁がinput されたときは、それ以上のINPUTは無視される
	public void test_max_input_digit_number_is_4() {
		data.setDigitNumberLimit(4);
		
		data.set(1);
		data.set(2);
		data.set(3);
		data.set(4);
		data.set(5);
		
		assertEquals("1234", data.getString());
		assertEquals(1234.0, data.getNumberAndClear());
		
	}
	
	// max 4桁がinput されたときは、それ以上のINPUTは無視される (Pointは関係ない)
	public void test_max_input_digit_number_is_4_with_arithmetic_point() {
		data.setDigitNumberLimit(4);
		
		data.set(1);
		data.set(2);
		data.setPoint();
		data.set(3);
		data.set(4);
		data.set(5);
		
		assertEquals("12.34", data.getString());
		assertEquals(12.34, data.getNumberAndClear());
		
	}
	
	// 桁数のLimitは5。4桁inputされた状態で00を入力した場合、0は一つしかINPUTされない
	public void test_max_input_digit_number_is_5_and_one_0_is_inputted_with_00() {
		data.setDigitNumberLimit(4);
		
		data.set(1);
		data.set(2);
		data.setPoint();
		data.set(3);
		data.set00();
		
		assertEquals("12.30", data.getString());
	}
	
	//0.001入力
	public void test_point_input_1() throws UnknownFunctionException, UnparsableExpressionException {
		data.set(0);
		data.setPoint();
		data.set(0);
		data.set(1);
		assertEquals("0.01", data.getString());
	}
	
	//小数点以下が0のみの場合、小数点以下の0は表示されない
}
