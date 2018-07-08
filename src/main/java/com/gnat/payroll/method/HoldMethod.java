package com.gnat.payroll.method;

import com.gant.payroll.domain.Paycheck;
import com.gant.payroll.domain.PaymentMethod;

public class HoldMethod implements PaymentMethod {

	@Override
	public void pay(Paycheck paycheck) {
		System.out.println("到财务自取");
	}

}
