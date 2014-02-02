package com.kawasaki.calculator;

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

	/* 00インプット */

	/* 小数点のinput */
	public void test_input_arithmetic_point() {
		data.set(1);
		data.setPoint(".");
		data.set(2);
		assertEquals(1.2,data.getNumberAndClear());
	}
	
	//if already point is contained, nothing is set
	public void test_nothing_is_set_when_alread_point_is_contained() {
		data.set(1);
		data.setPoint(".");
		data.set(2);
		data.setPoint(".");
		data.set(1);
		assertEquals(1.21,data.getNumberAndClear());
	}
	
	//小数点以下に０を連続してinputできる
	public void test_0_can_be_added_continuously_after_the_decimal_point() {
		data.set(1);
		data.setPoint(".");
		data.set(0);
		data.set(0);
		data.set(1);
		assertEquals(1.001,data.getNumberAndClear());
	}
	
	public void test_0_can_be_added_continuously_after_the_decimal_point_with_all_0() {
		data.set(0);
		data.setPoint(".");
		data.set(0);
		data.set(0);
		data.set(0);
		assertEquals(0.000,data.getNumberAndClear());
	}
	
	//何も数値を入力していない状態で、小数点から入力を開始すると、0.xxの表記になる
	public void test_input_arithmetic_point_with_no_input() {
		data.setPoint(".");
		data.set(2);
		assertEquals(0.2,data.getNumberAndClear());
	}
	
	//0の数値を入力した後、小数点から入力を開始すると、0.xxの表記になる
	public void test_input_arithmetic_point_with_0() {
		data.set(0);
		data.setPoint(".");
		data.set(2);
		assertEquals(0.2,data.getNumberAndClear());
	}
	
}
