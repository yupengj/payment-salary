<<<<<<< HEAD
package com.gant.payroll.method;

import com.gant.payroll.domain.Paycheck;
import com.gant.payroll.domain.PaymentMethod;

/**
 * 邮寄支票支付方式
 * 
 * @author jiangyp
 *
 */
public class MailMethod implements PaymentMethod {

	private String address;

	public MailMethod(String address) {
		super();
		this.address = address;
	}

	@Override
	public void pay(Paycheck paycheck) {
		// "向" + address + " 发送支票"
	}

	@Override
	public String toString() {
		return "MailMethod [address=" + address + "]";
	}
}
=======
package com.gant.payroll.method;

import com.gant.payroll.domain.Paycheck;
import com.gant.payroll.domain.PaymentMethod;

/**
 * 邮寄支票支付方式
 * 
 * @author jiangyp
 *
 */
public class MailMethod implements PaymentMethod {

	private String address;

	public MailMethod(String address) {
		super();
		this.address = address;
	}

	@Override
	public void pay(Paycheck paycheck) {
		// "向" + address + " 发送支票"
	}

	@Override
	public String toString() {
		return "MailMethod [address=" + address + "]";
	}

}
>>>>>>> 802d09c32f20b6c1274439ddc7598351fa878c4b
