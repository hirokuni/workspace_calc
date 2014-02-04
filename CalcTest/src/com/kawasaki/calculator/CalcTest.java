package com.kawasaki.calculator;

import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;
import android.util.Log;
import junit.framework.TestCase;

public class CalcTest extends TestCase {
	calc cal;

	protected void setUp() throws Exception {
		super.setUp();
		cal = new calc();
		cal.setDigitsLimit("0123456789.".length());
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/*
	 * Add Test
	 */

	// 二つの値が加算される
	public void test_Add_tow_number_2() throws UnknownFunctionException,
			UnparsableExpressionException {
		cal.setVal(5);
		cal.setOperatorAdd();
		cal.setVal(10);
		assertEquals(15.0, cal.equal());
	}

	// ３つの数値の加算。二つの値が加算された後、数字 + = で、前回の　演算子+　でその数値を足す
	public void test_3_number_is_added_to_previous_result_2()
			throws UnknownFunctionException, UnparsableExpressionException {
		cal.setVal(5);
		cal.setOperatorAdd();
		cal.setVal(10);
		cal.setOperatorAdd();
		cal.setVal(10);
		assertEquals(25.0, cal.equal());
	}

	// Clearでmemory、途中の計算結果は消える
	public void test_add_clear() throws UnknownFunctionException,
			UnparsableExpressionException {

	}

	/*
	 * Subtract test
	 */

	// 2つの値が減算される
	public void test_sub_tow_number_2() throws UnknownFunctionException,
			UnparsableExpressionException {
		cal.setVal(5);
		cal.setOperatorSub();
		cal.setVal(10);
		assertEquals(-5.0, cal.equal());
	}

	// ３つの数値の減算。二つの値が減算された後、数字 - = で、前回の　演算子-　でその数値を引く
	public void test_new_number_is_subtracted_with_previous_result()
			throws UnknownFunctionException, UnparsableExpressionException {
		cal.setVal(5);
		cal.setOperatorSub();
		cal.setVal(10);
		cal.setOperatorSub();
		cal.setVal(100);
		assertEquals(-105.0, cal.equal());
	}

	/*
	 * 足し算と引き算
	 */
	public void test_add_and_sub() throws UnknownFunctionException,
			UnparsableExpressionException {
		cal.setVal(5);
		cal.setOperatorAdd();
		cal.setVal(10);
		cal.setOperatorSub();
		cal.setVal(100);
		assertEquals(-85.0, cal.equal());
	}

	/*
	 * かけ算
	 */
	// 2つの値を乗算する
	public void test_mul_tow_number() throws UnknownFunctionException,
			UnparsableExpressionException {
		cal.setVal(5);
		cal.setOperatorMul();
		cal.setVal(10);
		assertEquals(50.0, cal.equal());
	}

	// 3つの値を乗算する
	public void test_mul_three_numbers() throws UnknownFunctionException,
			UnparsableExpressionException {
		cal.setVal(5);
		cal.setOperatorMul();
		cal.setVal(10);
		cal.setOperatorMul();
		cal.setVal(50);
		assertEquals(2500.0, cal.equal());
	}

	// 足し算／引き算／かけ算
	public void test_mul_is_calculated_prior_to_sub()
			throws UnknownFunctionException, UnparsableExpressionException {
		cal.setVal(5);// total 5
		cal.setOperatorAdd();
		cal.setVal(10);
		cal.setOperatorSub();// total 15
		cal.setVal(8);
		cal.setOperatorMul();// -8は先に30をかける
		cal.setVal(30);

		assertEquals(-225.0, cal.equal());
	}

	public void test_mul_is_calculated_prior_to_add()
			throws UnknownFunctionException, UnparsableExpressionException {
		cal.setVal(5);
		cal.setOperatorSub();
		cal.setVal(10);// total 5
		cal.setOperatorAdd();// total 15
		cal.setVal(8);
		cal.setOperatorMul();// 8は先に30をかける
		cal.setVal(30);

		assertEquals(235.0, cal.equal());
	}

	/*
	 * 除算
	 */
	// 2つの値を除算する
	public void test_div_two_numbers() throws UnknownFunctionException,
			UnparsableExpressionException {
		cal.setVal(5);
		cal.setOperatorDiv();
		cal.setVal(10);
		assertEquals(0.5, cal.equal());
	}

	// 3つの値を除算する
	public void test_div_three_numbers() throws UnknownFunctionException,
			UnparsableExpressionException {
		cal.setVal(10);
		cal.setOperatorDiv();
		cal.setVal(2);
		cal.setOperatorDiv();
		cal.setVal(5);
		assertEquals(1.0, cal.equal());
	}

	// 足し算／引き算／除算
	public void test_div_is_calculated_prior_to_sub()
			throws UnknownFunctionException, UnparsableExpressionException {
		cal.setVal(5);// total 5
		cal.setOperatorAdd();
		cal.setVal(10);
		cal.setOperatorSub();// total 15
		cal.setVal(6);
		cal.setOperatorDiv();// -8は先に30をかける
		cal.setVal(3);

		assertEquals(13.0, cal.equal());
	}

	public void test_div_is_calculated_prior_to_add()
			throws UnknownFunctionException, UnparsableExpressionException {
		cal.setVal(5);
		cal.setOperatorSub();
		cal.setVal(10);
		cal.setOperatorAdd();
		cal.setVal(6);
		cal.setOperatorDiv();
		cal.setVal(3);

		assertEquals(-3.0, cal.equal());
	}

	// 足し算／引き算／除算/乗算
	public void test_add_sub_div_mul() throws UnknownFunctionException,
			UnparsableExpressionException {
		cal.setVal(5);
		cal.setOperatorAdd();
		cal.setVal(10);
		cal.setOperatorSub();
		cal.setVal(6);
		cal.setOperatorDiv();
		cal.setVal(3);
		cal.setOperatorMul();
		cal.setVal(4);

		assertEquals(7.0, cal.equal());
	}

	public void test_add_sub_div_mul_2() throws UnknownFunctionException,
			UnparsableExpressionException {
		cal.setVal(5);
		cal.setOperatorAdd();
		cal.setVal(10);
		cal.setOperatorSub();
		cal.setVal(6);
		cal.setOperatorDiv();
		cal.setVal(3);
		cal.setOperatorMul();
		cal.setVal(4);
		cal.setOperatorAdd();
		cal.setVal(3);

		assertEquals(10.0, cal.equal());
	}

	public void test_add_sub_div_mul_3() throws UnknownFunctionException,
			UnparsableExpressionException {
		cal.setVal(5);
		cal.setOperatorAdd();
		cal.setVal(10);
		cal.setOperatorSub();
		cal.setVal(6);
		cal.setOperatorDiv();
		cal.setVal(3);
		cal.setOperatorMul();
		cal.setVal(4);
		cal.setOperatorSub();
		cal.setVal(3);

		assertEquals(4.0, cal.equal());
	}

	public void test_add_sub_div_mul_4() throws UnknownFunctionException,
			UnparsableExpressionException {
		cal.setVal(5);
		cal.setOperatorAdd();
		cal.setVal(10);
		cal.setOperatorSub();
		cal.setVal(6);
		cal.setOperatorDiv();
		cal.setVal(3);
		cal.setOperatorMul();
		cal.setVal(4);
		cal.setOperatorSub();
		cal.setVal(3);
		cal.setOperatorAdd();
		cal.setVal(6);

		assertEquals(10.0, cal.equal());
	}

	public void test_add_sub_div_mul_5() throws UnknownFunctionException,
			UnparsableExpressionException {
		cal.setVal(5);
		cal.setOperatorAdd();
		cal.setVal(10);
		cal.setOperatorSub();
		cal.setVal(6);
		cal.setOperatorDiv();
		cal.setVal(3);
		cal.setOperatorMul();
		cal.setVal(4);
		cal.setOperatorSub();
		cal.setVal(3);
		cal.setOperatorAdd();
		cal.setVal(6);
		cal.setOperatorDiv();
		cal.setVal(5);

		assertEquals(5.2, cal.equal());
	}

	// 定数乗算。後にかけた値がセットされ、移行　？＝　でセットされた値でかけ算していく
	private void set_const_mul_calcs() throws UnknownFunctionException,
			UnparsableExpressionException {
		cal.setVal(5);
		cal.setOperatorMul();
		cal.setVal(10);
		assertEquals(50.0, cal.equal());
		cal.setVal(6);
		assertEquals(60.0, cal.equal());
	}

	public void test_numbers_are_continuously_multipled_with_equal()
			throws UnknownFunctionException, UnparsableExpressionException {
		set_const_mul_calcs();
	}

	// 定数乗算の後に乗算
	public void test_multiply_after_const_multiply_calcs()
			throws UnknownFunctionException, UnparsableExpressionException {
		set_const_mul_calcs();
		cal.setOperatorMul();
		cal.setVal(5);
		assertEquals(300.0, cal.equal());
	}

	// 定数乗算の後に加算
	public void test_add_after_const_multiply_calcs()
			throws UnknownFunctionException, UnparsableExpressionException {
		cal.setVal(5);
		cal.setOperatorMul();
		cal.setVal(10);
		assertEquals(50.0, cal.equal());
		cal.setVal(6);
		assertEquals(60.0, cal.equal());
		cal.setOperatorAdd();
		cal.setVal(5);
		assertEquals(65.0, cal.equal());
		cal.setOperatorAdd();
		cal.setVal(5);
		assertEquals(70.0, cal.equal());
	}

	// 定数乗算の後に減算
	public void test_sub_after_const_multiply_calcs()
			throws UnknownFunctionException, UnparsableExpressionException {
		cal.setVal(5);
		cal.setOperatorMul();
		cal.setVal(10);
		assertEquals(50.0, cal.equal());
		cal.setVal(6);
		assertEquals(60.0, cal.equal());
		cal.setOperatorSub();
		cal.setVal(5);
		assertEquals(55.0, cal.equal());
		cal.setOperatorSub();
		cal.setVal(5);
		assertEquals(50.0, cal.equal());
	}

	// 定数乗算の後に除算
	public void test_div_after_const_multiply_calcs()
			throws UnknownFunctionException, UnparsableExpressionException {
		cal.setVal(5);
		cal.setOperatorMul();
		cal.setVal(10);
		assertEquals(50.0, cal.equal());
		cal.setVal(6);
		assertEquals(60.0, cal.equal());
		cal.setOperatorDiv();
		cal.setVal(5);
		assertEquals(12.0, cal.equal());
		cal.setOperatorDiv();
		cal.setVal(6);
		assertEquals(2.0, cal.equal());
	}

	// 定数加算。後にかけた値がセットされ、以降　？＝　でセットされた値で加算算していく
	public void test_numbers_are_continuously_added_with_equal()
			throws UnknownFunctionException, UnparsableExpressionException {
		cal.setVal(5);
		cal.setOperatorAdd();
		cal.setVal(10);
		assertEquals(15.0, cal.equal());
		cal.setVal(6);
		assertEquals(16.0, cal.equal());
	}

	// 定数加算の後に乗算
	public void test_multiply_after_const_add_calcs()
			throws UnknownFunctionException, UnparsableExpressionException {
		cal.setVal(5);
		cal.setOperatorAdd();
		cal.setVal(10);
		assertEquals(15.0, cal.equal());
		cal.setVal(6);
		assertEquals(16.0, cal.equal());
		cal.setOperatorMul();
		cal.setVal(5);
		assertEquals(80.0, cal.equal());
	}

	// 定数加算の後に加算
	// 5 + 10 = 15
	// 6 = 16
	// + 5 = 21
	public void test_add_after_const_add_calcs()
			throws UnknownFunctionException, UnparsableExpressionException {
		cal.setVal(5);
		cal.setOperatorAdd();
		cal.setVal(10);
		assertEquals(15.0, cal.equal());
		cal.setVal(6);
		assertEquals(16.0, cal.equal());
		cal.setOperatorAdd();
		cal.setVal(5);
		assertEquals(21.0, cal.equal());
	}

	// 定数加算の後に加算,さらに乗算,除算
	// 5 + 10 = 15
	// 6 = 16
	// + 5 = 21
	// * 8 = 168
	// / 5 = 33.6
	public void test_add_mul_div_after_const_add_calcs()
			throws UnknownFunctionException, UnparsableExpressionException {
		cal.setVal(5);
		cal.setOperatorAdd();
		cal.setVal(10);
		assertEquals(15.0, cal.equal());
		cal.setVal(6);
		assertEquals(16.0, cal.equal());
		cal.setOperatorAdd();
		cal.setVal(5);
		assertEquals(21.0, cal.equal());
		cal.setOperatorMul();
		cal.setVal(8);
		assertEquals(168.0, cal.equal());
		cal.setOperatorDiv();
		cal.setVal(5);
		assertEquals(33.6, cal.equal());
	}

	// 定数加算の後に除算
	private void set_div_after_const_add_calcs()
			throws UnknownFunctionException, UnparsableExpressionException {
		cal.setVal(5);
		cal.setOperatorAdd();
		cal.setVal(10);
		assertEquals(15.0, cal.equal());
		cal.setVal(6);
		assertEquals(16.0, cal.equal());
		cal.setOperatorDiv();
		cal.setVal(5);
		assertEquals(3.2, cal.equal());
	}

	public void test_div_after_const_add_calcs()
			throws UnknownFunctionException, UnparsableExpressionException {
		set_div_after_const_add_calcs();
	}

	// 定数加算の後に除算、さらに乗算、加算
	public void test_div_mul_add_after_const_add_calcs()
			throws UnknownFunctionException, UnparsableExpressionException {
		set_div_after_const_add_calcs();
		cal.setOperatorMul();
		cal.setVal(5);
		assertEquals(16.0, cal.equal());
	}

	// 定数加算の後に除算、さらに乗算、加算、さらに定数加算。
	public void test_div_mul_add_const_add_after_const_add_calcs()
			throws UnknownFunctionException, UnparsableExpressionException {
		set_div_after_const_add_calcs();
		cal.setOperatorMul();
		cal.setVal(5);
		assertEquals(16.0, cal.equal());
		cal.setOperatorAdd();
		cal.setVal(10);
		assertEquals(26.0, cal.equal());
		cal.setVal(20);
		assertEquals(30.0, cal.equal());
		cal.setVal(30);
		assertEquals(40.0, cal.equal());
	}

	// 定数除算 ？＝　でセットされた値が割られていく
	private void set_const_div_calcs() throws UnknownFunctionException,
			UnparsableExpressionException {
		cal.setVal(100);
		cal.setOperatorDiv();
		cal.setVal(2);
		assertEquals(50.0, cal.equal());
		cal.setVal(5);
		assertEquals(2.5, cal.equal());
		cal.setVal(10);
		assertEquals(5.0, cal.equal());
	}

	public void test_numbers_are_continuously_divided_with_equal()
			throws UnknownFunctionException, UnparsableExpressionException {
		set_const_div_calcs();
	}

	// 定数除算の後に除算
	public void test_div_after_const_div_calcs()
			throws UnknownFunctionException, UnparsableExpressionException {
		set_const_div_calcs();
		cal.setOperatorDiv();
		cal.setVal(5);
		assertEquals(1.0, cal.equal());
	}

	// 定数除算の後に乗算
	public void test_mul_after_const_div_calcs()
			throws UnknownFunctionException, UnparsableExpressionException {
		set_const_div_calcs();
		cal.setOperatorMul();
		cal.setVal(5);
		assertEquals(25.0, cal.equal());
	}

	// 定数除算の後に加算
	public void test_add_after_const_div_calcs()
			throws UnknownFunctionException, UnparsableExpressionException {
		set_const_div_calcs();
		cal.setOperatorAdd();
		cal.setVal(5.5);
		assertEquals(10.5, cal.equal());
	}

	// 定数除算の後に減算
	public void test_sub_after_const_div_calcs()
			throws UnknownFunctionException, UnparsableExpressionException {
		set_const_div_calcs();
		cal.setOperatorSub();
		cal.setVal(10.5);
		assertEquals(-5.5, cal.equal());
	}

	// 定数減算 ？＝　でセットされた値で引かれていく
	private void set_const_sub_calcs() throws UnknownFunctionException,
			UnparsableExpressionException {
		cal.setVal(100);
		cal.setOperatorSub();
		cal.setVal(2);
		assertEquals(98.0, cal.equal());
		cal.setVal(5);
		assertEquals(3.0, cal.equal());
		cal.setVal(10);
		assertEquals(8.0, cal.equal());
	}

	public void test_numbers_are_continuously_subtracted_with_equal()
			throws UnknownFunctionException, UnparsableExpressionException {
		set_const_sub_calcs();
	}

	// 定数減算の後に除算
	public void test_div_after_const_sub_calcs()
			throws UnknownFunctionException, UnparsableExpressionException {
		set_const_sub_calcs();
		cal.setOperatorDiv();
		cal.setVal(5);
		assertEquals(1.6, cal.equal());
	}

	// 定数減算の後に乗算
	public void test_mul_after_const_sub_calcs()
			throws UnknownFunctionException, UnparsableExpressionException {
		set_const_sub_calcs();
		cal.setOperatorMul();
		cal.setVal(5);
		assertEquals(40.0, cal.equal());
	}

	// 定数減算の後に加算
	public void test_add_after_const_sub_calcs()
			throws UnknownFunctionException, UnparsableExpressionException {
		set_const_sub_calcs();
		cal.setOperatorAdd();
		cal.setVal(5.5);
		assertEquals(13.5, cal.equal());
	}

	// 定数減算の後に減算
	public void test_sub_after_const_sub_calcs()
			throws UnknownFunctionException, UnparsableExpressionException {
		set_const_sub_calcs();
		cal.setOperatorSub();
		cal.setVal(10.5);
		assertEquals(-2.5, cal.equal());
	}

	// 0で割ったらエラー表示
	public void test_return_error_when_div_by_0()
			throws UnknownFunctionException, UnparsableExpressionException {
		cal.setVal(1);
		cal.setOperatorDiv();
		cal.setVal(0);

		try {
			cal.equal();
			fail("no exception happned");
		} catch (java.lang.ArithmeticException aex) {
			// success. nothing to do
			return;
		} catch (Exception ex) {
			fail("ex : " + ex);
		}

		fail("must not reach here!!");
	}

	// = で出た計算結果の後に、数値をInputして、
	// =以外の演算が選択されると、前回の計算結果は破棄され、新しい計算が始まる。(加算)
	public void test_previous_result_is_cleared_with_selecting_new_operation_add_executes_after_equal()
			throws UnknownFunctionException, UnparsableExpressionException {
		cal.setVal(100);
		cal.setOperatorSub();
		cal.setVal(2);
		assertEquals(98.0, cal.equal());
		cal.setVal(5);
		cal.setOperatorAdd();
		cal.setVal(10);
		assertEquals(15.0, cal.equal());
	}

	// = で出た計算結果の後に、数値をInputして、
	// =以外の演算が選択されると、前回の計算結果は破棄され、新しい計算が始まる。(減算)
	public void test_previous_result_is_cleared_with_selecting_new_operation_subtract_executes_after_equal()
			throws UnknownFunctionException, UnparsableExpressionException {
		cal.setVal(100);
		cal.setOperatorSub();
		cal.setVal(2);
		assertEquals(98.0, cal.equal());
		cal.setVal(5);
		cal.setOperatorSub();
		cal.setVal(10);
		assertEquals(-5.0, cal.equal());
	}

	// = で出た計算結果の後に、数値をInputして、
	// =以外の演算が選択されると、前回の計算結果は破棄され、新しい計算が始まる。(乗算)
	public void test_previous_result_is_cleared_with_selecting_new_operation_multiply_executes_after_equal()
			throws UnknownFunctionException, UnparsableExpressionException {
		cal.setVal(100);
		cal.setOperatorSub();
		cal.setVal(2);
		assertEquals(98.0, cal.equal());
		cal.setVal(5);
		cal.setOperatorMul();
		cal.setVal(10);
		assertEquals(50.0, cal.equal());
	}

	// = で出た計算結果の後に、数値をInputして、
	// =以外の演算が選択されると、前回の計算結果は破棄され、新しい計算が始まる。(除算)
	public void test_previous_result_is_cleared_with_selecting_new_operation_division_executes_after_equal()
			throws UnknownFunctionException, UnparsableExpressionException {
		cal.setVal(100);
		cal.setOperatorSub();
		cal.setVal(2);
		assertEquals(98.0, cal.equal());
		cal.setVal(5);
		cal.setOperatorDiv();
		cal.setVal(10);
		assertEquals(0.5, cal.equal());
	}

	/**
	 * <pre>
	 * 例 
	 * 5 + 1 = 6
	 *   + 2 = 8
	 * </pre>
	 */
	public void test_add_number_with_result() throws UnknownFunctionException,
			UnparsableExpressionException {
		cal.setVal(5);
		cal.setOperatorAdd();
		cal.setVal(1);
		assertEquals(6.0, cal.equal());
		cal.setOperatorAdd();
		cal.setVal(2);
		assertEquals(8.0, cal.equal());
	}

	// 8/6, nonterminating decimal expansion, ArithmeticException
	public void test_non_terminating_decimal_exception_is_caught()
			throws UnknownFunctionException, UnparsableExpressionException {
		cal.setVal(8);
		cal.setOperatorDiv();
		cal.setVal(6);
		assertEquals(1.3333333333, cal.equal());
	}

	// overflowにおいては 1e の表記を行う
	public void test_over_flow() throws UnknownFunctionException,
			UnparsableExpressionException {
		cal.setVal(2147483647);
		cal.setOperatorAdd();
		cal.setVal(2147483647);

		Log.i("test_over_flow",
				"over_flow_test : " + Double.toString(cal.equal()));
	}

	/**
	 * <pre>
	 * Step1. 3 + 3 + 3 
	 * Step2. clear
	 * Step3. 3
	 * </pre>
	 */
	public void test_show_number_during_calc_for_add()
			throws UnknownFunctionException, UnparsableExpressionException {
		// Step1
		cal.setVal(3);
		assertEquals(3.0, cal.getMemory());
		cal.setOperatorAdd();
		assertEquals(3.0, cal.getMemory());
		cal.setVal(3);
		try {
			cal.getMemory();
			fail();
		} catch (UnknownFunctionException ufx) {
			// まだ途中計算結果が決定していない
		}

		cal.setOperatorAdd();
		assertEquals(6.0, cal.getMemory());

		// Step2
		cal.clear();
		assertEquals(0.0, cal.getMemory());

		// Step3
		cal.setVal(3);
		assertEquals(3.0, cal.getMemory());

	}

	// 演算子が複数inputされると、最後の演算子をセットする
	public void test_last_operation_is_taken_as_the_proper_one()
			throws UnknownFunctionException, UnparsableExpressionException {
		cal.setVal(1);
		cal.setOperatorDiv();
		cal.setOperatorAdd();
		cal.setVal(5);
		assertEquals(6.0, cal.equal());
	}
	
	// 8* = 64
	public void test_() {
		
	}
}
