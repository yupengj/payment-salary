package com.gant.payroll.schedule;

import java.time.LocalDate;

import com.gant.payroll.domain.PaymentSchedule;
import com.gant.payroll.util.DateUtil;

/**
 * 
 * 隔周周五支付
 * 
 * @author jiangyp
 *
 */
public class BiweeklySchedule implements PaymentSchedule {

	LocalDate firstPayableFriday = LocalDate.of(2017, 7, 6);

	@Override
	public boolean isPayDate(LocalDate date) {
		long interval = DateUtil.getDaysBetween(firstPayableFriday, date);
		return interval % 14 == 0;
	}

	@Override
	public LocalDate getPayPeriodStartDate(LocalDate payPeriodEndDate) {
		return DateUtil.add(payPeriodEndDate, -13);

	}

	@Override
	public String toString() {
		return "BiweeklySchedule [隔周五支付]";
	}

}
