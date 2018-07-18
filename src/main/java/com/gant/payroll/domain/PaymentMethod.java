package com.gant.payroll.domain;

/**
 * 支付方式
 * 
 * @author jiangyp
 *
 */
public interface PaymentMethod {

	void pay(Paycheck paycheck);
}
