package com.gant.payroll.method;

import com.gant.payroll.domain.Paycheck;
import com.gant.payroll.domain.PaymentMethod;

/**
 * 财务自取支付方式
 * 
 * @author jiangyp
 *
 */
public class HoldMethod implements PaymentMethod {

	@Override
	public void pay(Paycheck paycheck) {
		// 到财务自取
	}

	@Override
	public String toString() {
		return "HoldMethod [到财务自取]";
	}
}
