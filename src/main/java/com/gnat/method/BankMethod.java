package com.gnat.method;

import com.gant.domain.PaymentMethod;
import com.gant.model.Paycheck;

public class BankMethod implements PaymentMethod {
	private String bank;
	private double account;

	@Override
	public void pay(Paycheck paycheck) {
		// TODO Auto-generated method stub

	}
}
