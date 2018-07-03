package com.gant.classification;

import java.util.List;

import com.gant.domain.Paycheck;
import com.gant.domain.PaymentClassification;
import com.gant.util.DateUtil;

public class CommissionedClassification implements PaymentClassification {

	private double rate;
	private double salary;
	private List<SalesReceipt> salesReceipt;

	public CommissionedClassification(double salary, double rate) {
		this.salary = salary;
		this.rate = rate;
	}

	@Override
	public double calculatePay(Paycheck paycheck) {
		double commission = 0.0;
		for (SalesReceipt sr : salesReceipt) {
			if (DateUtil.between(sr.getDate(), paycheck.getPayPeriodStart(), paycheck.getPayPeriodEnd())) {
				commission += sr.getAmount() * rate;
			}
		}
		return salary + commission;
	}
}
