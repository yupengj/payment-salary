package com.gant.payroll.method;

import com.gant.payroll.domain.Paycheck;
import com.gant.payroll.domain.PaymentMethod;

public class BankMethod implements PaymentMethod {
	private String bank;
	private double account;

	public BankMethod(String bank, double account) {
		super();
		this.bank = bank;
		this.account = account;
	}

	@Override
	public void pay(Paycheck paycheck) {
		System.out.println("向银行卡 " + bank + " 支付" + account + "元");
	}

	@Override
	public String toString() {
		return "BankMethod [bank=" + bank + ", account=" + account + "]";
	}
}
