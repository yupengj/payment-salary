package com.gant.payroll.schedule;

import java.time.LocalDate;

import com.gant.payroll.domain.PaymentSchedule;
import com.gant.payroll.util.DateUtil;

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
