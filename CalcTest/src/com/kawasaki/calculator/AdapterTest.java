package com.kawasaki.calculator;

import android.test.AndroidTestCase;

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
		//Step1
		adapter.setVal(3);
		assertEquals("3",adapter.getString());
		adapter.setOperator(Adapter.PLUS);
		assertEquals("3",adapter.getString());
		adapter.setVal(3);
		assertEquals("3",adapter.getString());
		adapter.setOperator(Adapter.PLUS);
		assertEquals("6",adapter.getString());
				
		//Step2
		adapter.setClear();
		assertEquals("0",adapter.getString());
				
		//Step3
		adapter.setVal(3);
		assertEquals("3",adapter.getString());
	}
	
	//小数点追加
	public void test_arithmetic_point_1() {
		adapter.setVal(3);
		assertEquals("3",adapter.getString());
		adapter.setPoint();
		assertEquals("3.",adapter.getString());
		adapter.setVal(5);
		assertEquals("3.5",adapter.getString());	
	}
	
	//2重の小数点の無視
	public void test_arithmetic_point_2() {
		adapter.setVal(3);
		assertEquals("3",adapter.getString());
		adapter.setPoint();
		assertEquals("3.",adapter.getString());
		adapter.setPoint();
		assertEquals("3.",adapter.getString());
		adapter.setVal(9);
		assertEquals("3.9",adapter.getString());
	}
	
	//00入力
	public void test_set_00_1() {
		adapter.setVal(3);
		assertEquals("3",adapter.getString());
		adapter.set00();
		assertEquals("300",adapter.getString());
	}
	
	//何回０だけおしても０しか表示しない
	public void test_set_00_2() {
		adapter.setVal(0);
		assertEquals("0",adapter.getString());
		adapter.set00();
		assertEquals("0",adapter.getString());
	}
	
	//0除算でerror表示
	public void test_error_display_1() {
		adapter.setVal(1);
		assertEquals("1",adapter.getString());
		adapter.setOperator(Adapter.DIVISION);
		adapter.setVal(0);
		adapter.equal();
		assertEquals("Error",adapter.getString());
	}
		
	//最大桁数設定
	

}
