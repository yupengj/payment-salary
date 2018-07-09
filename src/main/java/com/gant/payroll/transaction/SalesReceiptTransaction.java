package com.gant.payroll.transaction;

import java.time.LocalDate;

import com.gant.payroll.classification.CommissionedClassification;
import com.gant.payroll.classification.SalesReceipt;
import com.gant.payroll.db.PaymentDatabase;
import com.gant.payroll.db.impl.PaymentDatabaseImpl;
import com.gant.payroll.domain.Employee;
import com.gant.payroll.domain.PaymentClassification;

public class SalesReceiptTransaction {

	PaymentDatabase paymentDatabase = new PaymentDatabaseImpl();

	private String empId;
	private LocalDate date;
	private double amount;

	public SalesReceiptTransaction(String empId, LocalDate date, double amount) {
		this.empId = empId;
		this.date = date;
		this.amount = amount;
	}

	public void execute() {
		Employee emp = paymentDatabase.findEmployee(empId);
		if (emp == null) {
			throw new RuntimeException("没有员工");
		}
		PaymentClassification pc = emp.getClassification();
		if (pc instanceof CommissionedClassification) {
			CommissionedClassification cc = (CommissionedClassification) pc;
			cc.addSalesReceipt(new SalesReceipt(date, amount));
		}
	}
}