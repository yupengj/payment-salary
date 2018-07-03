package com.gant.util;

import org.junit.Test;

public class DateUtilTest {

	@Test
	public void testIsLastDayOfMonth() {
		System.out.println(DateUtil.isLastDayOfMonth(DateUtil.parseDate("2017-6-29")));
	}

	@Test
	public void testGetFirstDay() {
		System.out.println(DateUtil.getFirstDay(DateUtil.parseDate("2017-6-30")));
	}
}
