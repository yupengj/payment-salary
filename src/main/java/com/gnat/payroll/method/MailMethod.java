package com.gnat.payroll.method;

import com.gant.payroll.domain.Paycheck;
import com.gant.payroll.domain.PaymentMethod;

public class MailMethod implements PaymentMethod {

	private String address;

	public MailMethod(String address) {
		super();
		this.address = address;
	}

	@Override
	public void pay(Paycheck paycheck) {
		System.out.println("向" + address + " 发送支票");
	}

	@Override
	public String toString() {
		return "MailMethod [address=" + address + "]";
	}

}
