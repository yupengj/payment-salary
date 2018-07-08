package com.gant.payroll.schedule;

import java.time.LocalDate;

import com.gant.payroll.domain.PaymentSchedule;
import com.gant.payroll.util.DateUtil;

public class BiweeklySchedule implements PaymentSchedule {

	LocalDate firstPayableFriday = LocalDate.of(2017, 7, 20);

	@Override
	public boolean isPayDate(LocalDate date) {
		long interval = DateUtil.getDaysBetween(firstPayableFriday, date);
		return interval % 14 == 0;
	}

	@Override
	public LocalDate getPayPeriodStartDate(LocalDate payPeriodEndDate) {
		return DateUtil.add(payPeriodEndDate, -13);

	}
}