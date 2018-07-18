package com.gant.payroll.transaction;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.junit.Test;

import com.gant.payroll.db.PayrollDatabase;
import com.gant.payroll.db.impl.PaymentDatabaseImpl;
import com.gant.payroll.domain.Employee;
import com.gant.payroll.domain.Paycheck;

public class PaydayTransactionTest {

	PayrollDatabase payrollDatabase = new PaymentDatabaseImpl();

	@Test
	public void testPaydate() {
		PaydayTransaction paydayTransaction = new PaydayTransaction(LocalDate.of(2018, Month.JULY, 20));
		paydayTransaction.execute();

		List<Employee> emps = payrollDatabase.findAllEmp();
		for (Employee emp : emps) {
			System.out.println();
			System.out.println(emp);
			List<Paycheck> paychecks = payrollDatabase.findPaychecks(emp.getId());
			if (paychecks != null) {
				paychecks.forEach(it -> System.out.println(it));
			}
		}
	}

}
