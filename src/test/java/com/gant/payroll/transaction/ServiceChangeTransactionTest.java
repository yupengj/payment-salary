package com.gant.payroll.transaction;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.junit.Test;

import com.gant.payroll.affiliation.ServiceChange;
import com.gant.payroll.db.PaymentDatabase;
import com.gant.payroll.db.impl.PaymentDatabaseImpl;
import com.gant.payroll.domain.Employee;

public class ServiceChangeTransactionTest {

	PaymentDatabase paymentDatabase = new PaymentDatabaseImpl();

	@Test
	public void testServiceChange() {
		String memberId = "1";
		String empId = "1";
		AddEmployeeTransaction addEmp = new AddHourlyEmployeeTransaction(empId, "张三", "上海", 10);
		addEmp.setPaymentDatabase(paymentDatabase);
		addEmp.execute();
		Employee emp = paymentDatabase.findEmployee(empId);

		AffiliationTransaction affiliationTransaction = new AffiliationTransaction(empId, memberId, 100);
		affiliationTransaction.setPaymentDatabase(paymentDatabase);
		affiliationTransaction.execute();
		System.out.println(emp);

		paymentDatabase.addMember(memberId, emp);

		ServiceChangeTransaction serviceChangeTransaction = new ServiceChangeTransaction(memberId, LocalDate.of(2018, Month.JULY, 19), 100);
		serviceChangeTransaction.setPaymentDatabase(paymentDatabase);
		serviceChangeTransaction.execute();

		serviceChangeTransaction = new ServiceChangeTransaction(memberId, LocalDate.of(2018, Month.JULY, 20), 13);
		serviceChangeTransaction.setPaymentDatabase(paymentDatabase);
		serviceChangeTransaction.execute();

		List<ServiceChange> serviceChanges = paymentDatabase.findServiceChange(memberId);
		serviceChanges.forEach(it -> System.out.println(it));

		System.out.println(paymentDatabase.findEmployee(empId));
	}
}
