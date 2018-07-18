package com.gant.payroll.transaction;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.junit.Test;

import com.gant.payroll.classification.SalesReceipt;
import com.gant.payroll.db.PayrollDatabase;
import com.gant.payroll.db.impl.PaymentDatabaseImpl;

public class SalesReceiptTransactionTest {

	PayrollDatabase payrollDatabase = new PaymentDatabaseImpl();

	@Test
	public void testAddSalesReceipt() {

		String empId = "3";
		AddEmployeeTransaction addEmp = new AddCommissionedEmployeeTransaction(empId, "王五", "上海", 10, 0.5);
		addEmp.execute();

		System.out.println(payrollDatabase.findEmployee(empId));

		SalesReceiptTransaction salesReceiptTransaction = new SalesReceiptTransaction(empId, LocalDate.of(2018, Month.JULY, 20), 100);
		salesReceiptTransaction.execute();

		salesReceiptTransaction = new SalesReceiptTransaction(empId, LocalDate.of(2018, Month.JULY, 19), 50);
		salesReceiptTransaction.execute();

		List<SalesReceipt> salesReceipts = payrollDatabase.findSalesReceipts(empId);
		salesReceipts.forEach(it -> System.out.println(it));

	}
}
