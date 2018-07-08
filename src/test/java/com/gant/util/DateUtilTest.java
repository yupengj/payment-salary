package com.gant.util;

import java.time.LocalDate;

import org.junit.Test;

import com.gant.payroll.util.DateUtil;

public class DateUtilTest {

	@Test
	public void testIsLastDayOfMonth() {
		LocalDate d = LocalDate.of(2017, 6, 29);
		System.out.println(DateUtil.isLastDayOfMonth(d));
	}

	@Test
	public void testGetFirstDay() {
		LocalDate d = LocalDate.of(2017, 6, 30);
		System.out.println(DateUtil.getFirstDay(d));
	}
}
