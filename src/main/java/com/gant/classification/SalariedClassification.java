package com.gant.classification;

import com.gant.domain.Paycheck;
import com.gant.domain.PaymentClassification;

public class SalariedClassification implements PaymentClassification {
	private double salary;

	
	public SalariedClassification(double salary){
		this.salary = salary;
	}
	
	@Override
	public double calculatePay(Paycheck paycheck) {
		return salary;
	}
	
	

}
