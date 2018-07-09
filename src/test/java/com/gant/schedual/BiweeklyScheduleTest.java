package com.gant.schedual;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import org.junit.Test;

import com.gant.payroll.schedule.BiweeklySchedule;

public class BiweeklyScheduleTest {

	BiweeklySchedule schedule = new BiweeklySchedule();

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	@Test
	public void testIsPayDate() throws ParseException {
		LocalDate d = LocalDate.of(2017, 6, 30);
		
		System.out.println(schedule.isPayDate(d));
	}

	@Test
	public void testGetPayPeriodStartDate() throws ParseException {
		LocalDate d = LocalDate.of(2017, 6, 30);
		System.out.println(schedule.getPayPeriodStartDate(d));
	}
}
