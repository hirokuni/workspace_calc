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
}
