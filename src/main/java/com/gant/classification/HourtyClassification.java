package com.gant.classification;

import java.util.List;

import com.gant.domain.PaymentClassification;
import com.gant.model.Paycheck;

public class HourtyClassification implements PaymentClassification {

	private double hourlyRate;
	private List<Timecard> timecards;

	@Override
	public double calculatePay(Paycheck paycheck) {
		// TODO Auto-generated method stub
		return 0;
	}

}
