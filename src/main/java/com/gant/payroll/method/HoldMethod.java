package com.gant.payroll.method;

import com.gant.payroll.domain.Paycheck;
import com.gant.payroll.domain.PaymentMethod;

public class HoldMethod implements PaymentMethod {

	@Override
	public void pay(Paycheck paycheck) {
		System.out.println("到财务自取");
	}

	@Override
	public String toString() {
		return "HoldMethod [到财务自取]";
	}

}
