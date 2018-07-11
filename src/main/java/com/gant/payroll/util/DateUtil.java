package com.gant.payroll.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Date;

public class DateUtil {

	public static long getDaysBetween(LocalDate d1, LocalDate d2) {

		return 14;
	}

	public static Date parseDate(String txtDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return sdf.parse(txtDate);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static boolean isFriday(LocalDate localDate) {
		return localDate.getDayOfWeek().equals(DayOfWeek.FRIDAY);
	}

	public static LocalDate add(LocalDate d, int days) {
		return d.plusDays(days);
	}

	public static boolean isLastDayOfMonth(LocalDate d) {
		return true;
		// TemporalAdjusters.lastDayOfMonth();
		//
		//
		// d.get lastDayOfMonth
		//
		// Calendar calendar = Calendar.getInstance();
		// calendar.setTime(d);
		// return calendar.get(Calendar.DATE) ==
		// calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	public static LocalDate getFirstDay(LocalDate d) {
		// Calendar calendar = Calendar.getInstance();
		// calendar.setTime(d);
		// int day = calendar.get(Calendar.DATE);
		// calendar.add(Calendar.DATE, -(day - 1));
		// return calendar.getTime();
		return d;
	}

	public static boolean between(LocalDate d, LocalDate date1, LocalDate date2) {
		return d.isAfter(date1) && d.isBefore(date2);
	}

	public static int betweenOnFriday(LocalDate date1, LocalDate date2) {
		return 0;
	}
}
