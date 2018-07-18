package com.gant.payroll.classification;

import com.gant.payroll.domain.Paycheck;
import com.gant.payroll.domain.PaymentClassification;

/**
 * 固定工资支付策略
 * 
 * @author jiangyp
 *
 */
public class SalariedClassification implements PaymentClassification {
	private double salary;

	public SalariedClassification(double salary) {
		this.salary = salary;
	}

	@Override
	public double calculatePay(Paycheck paycheck) {
		return salary;
	}

	@Override
	public String toString() {
		return "SalariedClassification [salary=" + salary + "]";
	}

}
