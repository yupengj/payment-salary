package com.gant.payroll.schedule;

import java.time.LocalDate;

import com.gant.payroll.domain.PaymentSchedule;
import com.gant.payroll.util.DateUtil;

/**
 * 没有最后一个工作日支付
 * 
 * @author jiangyp
 *
 */
public class MothlySchedule implements PaymentSchedule {

	@Override
	public boolean isPayDate(LocalDate date) {
		return DateUtil.isLastDayOfMonth(date);
	}

	@Override
	public LocalDate getPayPeriodStartDate(LocalDate payPeriodEndDate) {
		return DateUtil.getFirstDay(payPeriodEndDate);
	}

	@Override
	public String toString() {
		return "MothlySchedule [月底支付]";
	}

}
