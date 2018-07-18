package com.gant.payroll.transaction;

import org.junit.Test;

import com.gant.payroll.db.PayrollDatabase;
import com.gant.payroll.db.impl.PaymentDatabaseImpl;
import com.gant.payroll.domain.Employee;

public class AffiliationTransactionTest {

	PayrollDatabase payrollDatabase = new PaymentDatabaseImpl();

	@Test
	public void testAffiliation() {
		AddEmployeeTransaction addEmp = new AddCommissionedEmployeeTransaction("1", "张三", "上海", 10, 0.5);
		addEmp.execute();
		System.out.println(payrollDatabase.findEmployee("1"));

		addEmp = new AddCommissionedEmployeeTransaction("2", "李四", "上海", 10, 0.3);
		addEmp.execute();
		System.out.println(payrollDatabase.findEmployee("2"));

		AffiliationTransaction affiliationTransaction = new AffiliationTransaction("1", "1", 100);
		affiliationTransaction.execute();

		affiliationTransaction = new AffiliationTransaction("1", "2", 120);
		affiliationTransaction.execute();

		Employee emp = payrollDatabase.findEmployee("1");
		System.out.println(emp);
	}
}
