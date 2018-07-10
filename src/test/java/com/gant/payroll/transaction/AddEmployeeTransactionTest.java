package com.gant.payroll.transaction;

import org.junit.Test;

import com.gant.payroll.db.PaymentDatabase;
import com.gant.payroll.db.impl.PaymentDatabaseImpl;
import com.gant.payroll.domain.Employee;

public class AddEmployeeTransactionTest {

	PaymentDatabase paymentDatabase = new PaymentDatabaseImpl();

	@Test
	public void testAddHourlyEmp() {
		String empId = "1";
		AddEmployeeTransaction addEmp = new AddHourlyEmployeeTransaction(empId, "张三", "上海", 10);
		addEmp.setPaymentDatabase(paymentDatabase);
		addEmp.execute();

		Employee emp = paymentDatabase.findEmployee(empId);
		System.out.println(emp);
	}

	@Test
	public void testAddCommissionedEmp() {
		String empId = "2";
		AddEmployeeTransaction addEmp = new AddCommissionedEmployeeTransaction(empId, "李四", "上海", 10, 10);
		addEmp.setPaymentDatabase(paymentDatabase);
		addEmp.execute();

		Employee emp = paymentDatabase.findEmployee(empId);
		System.out.println(emp);
	}

	@Test
	public void testAddSalariedEmp() {
		String empId = "3";
		AddEmployeeTransaction addEmp = new AddSalariedEmployeeTransaction(empId, "王五", "上海", 10);
		addEmp.setPaymentDatabase(paymentDatabase);
		addEmp.execute();

		Employee emp = paymentDatabase.findEmployee(empId);
		System.out.println(emp);
	}
}
