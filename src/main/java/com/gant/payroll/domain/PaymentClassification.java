package com.gant.payroll.domain;

/**
 * 支付策略
 * 
 * @author jiangyp
 *
 */
public interface PaymentClassification {

	double calculatePay(Paycheck paycheck);
}
