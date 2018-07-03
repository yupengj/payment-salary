package com.gant.schedule;

import java.time.LocalDate;
import java.util.Date;

import com.gant.domain.PaymentSchedule;
import com.gant.util.DateUtil;

public class MothlySchedule implements PaymentSchedule{

	@Override
	public boolean isPayDate(LocalDate date) {		
		return DateUtil.isLastDayOfMonth(date);
	}

	@Override
	public LocalDate getPayPeriodStartDate(LocalDate payPeriodEndDate) {		
		return DateUtil.getFirstDay(payPeriodEndDate);
	}

}
