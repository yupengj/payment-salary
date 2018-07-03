package com.gant.schedule;

import java.time.LocalDate;
import java.util.Date;

import com.gant.domain.PaymentSchedule;
import com.gant.util.DateUtil;

public class BiweeklySchedule implements PaymentSchedule {

	LocalDate firstPayableFriday = LocalDate.of(2017, 6, 2);

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
