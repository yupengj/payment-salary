package com.gant.payroll.domain;

import java.time.LocalDate;

/**
 * 支付计划，支付时间
 * 
 * @author jiangyp
 *
 */
public interface PaymentSchedule {

	boolean isPayDate(LocalDate date);

	LocalDate getPayPeriodStartDate(LocalDate date);
}
