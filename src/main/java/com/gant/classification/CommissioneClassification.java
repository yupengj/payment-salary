package com.gant.classification;

import java.util.List;

import com.gant.domain.PaymentClassification;
import com.gant.model.Paycheck;

public class CommissioneClassification implements PaymentClassification {

	private double rate;
	private double salary;
	private List<SalesReceipt> salesReceipt;

	@Override
	public double calculatePay(Paycheck paycheck) {
		return 0;
	}

}
