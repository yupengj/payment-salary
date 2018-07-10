package com.gant.payroll.transaction;

import org.junit.Test;

import com.gant.payroll.db.PaymentDatabase;
import com.gant.payroll.db.impl.PaymentDatabaseImpl;
import com.gant.payroll.domain.Employee;

public class AffiliationTransactionTest {

	PaymentDatabase paymentDatabase = new PaymentDatabaseImpl();

	@Test
	public void testAffiliation() {
		AddEmployeeTransaction addEmp = new AddCommissionedEmployeeTransaction("1", "张三", "上海", 10, 0.5);
		addEmp.setPaymentDatabase(paymentDatabase);
		addEmp.execute();
		System.out.println(paymentDatabase.findEmployee("1"));

		addEmp = new AddCommissionedEmployeeTransaction("2", "李四", "上海", 10, 0.3);
		addEmp.setPaymentDatabase(paymentDatabase);
		addEmp.execute();
		System.out.println(paymentDatabase.findEmployee("2"));

		AffiliationTransaction affiliationTransaction = new AffiliationTransaction("1", "1", 100);
		affiliationTransaction.setPaymentDatabase(paymentDatabase);
		affiliationTransaction.execute();

		affiliationTransaction = new AffiliationTransaction("1", "2", 120);
		affiliationTransaction.setPaymentDatabase(paymentDatabase);
		affiliationTransaction.execute();

		Employee emp = paymentDatabase.findEmployee("1");
		System.out.println(emp);
	}
}
