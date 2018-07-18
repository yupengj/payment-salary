package com.gant.payroll.transaction;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.junit.Test;

import com.gant.payroll.affiliation.ServiceChange;
import com.gant.payroll.db.PayrollDatabase;
import com.gant.payroll.db.impl.PaymentDatabaseImpl;
import com.gant.payroll.domain.Employee;

public class ServiceChangeTransactionTest {

	PayrollDatabase payrollDatabase = new PaymentDatabaseImpl();

	@Test
	public void testServiceChange() {
		String memberId = "1";
		String empId = "1";
		AddEmployeeTransaction addEmp = new AddHourlyEmployeeTransaction(empId, "张三", "上海", 10);
		addEmp.execute();
		Employee emp = payrollDatabase.findEmployee(empId);

		AffiliationTransaction affiliationTransaction = new AffiliationTransaction(empId, memberId, 100);
		affiliationTransaction.execute();
		System.out.println(emp);

		payrollDatabase.addMember(memberId, emp);

		ServiceChangeTransaction serviceChangeTransaction = new ServiceChangeTransaction(memberId, LocalDate.of(2018, Month.JULY, 19), 100);
		serviceChangeTransaction.execute();

		serviceChangeTransaction = new ServiceChangeTransaction(memberId, LocalDate.of(2018, Month.JULY, 20), 13);
		serviceChangeTransaction.execute();

		List<ServiceChange> serviceChanges = payrollDatabase.findServiceChange(memberId);
		serviceChanges.forEach(it -> System.out.println(it));

		System.out.println(payrollDatabase.findEmployee(empId));
	}
}
