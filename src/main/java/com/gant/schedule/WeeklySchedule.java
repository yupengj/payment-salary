package com.gant.schedule;

import java.time.LocalDate;
import java.util.Date;

import com.gant.domain.PaymentSchedule;
import com.gant.util.DateUtil;

public class WeeklySchedule implements PaymentSchedule {

	@Override
	public boolean isPayDate(LocalDate date) {
		return DateUtil.isFriday(date);
	}

	@Override
	public LocalDate getPayPeriodStartDate(LocalDate payPeriodEndDate) {
		return DateUtil.add(payPeriodEndDate, -6);
	}

}
