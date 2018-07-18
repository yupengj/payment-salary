package com.gant.payroll.transaction;

import java.time.LocalDate;

import com.gant.payroll.classification.CommissionedClassification;
import com.gant.payroll.classification.SalesReceipt;
import com.gant.payroll.db.PayrollDatabase;
import com.gant.payroll.db.impl.PaymentDatabaseImpl;
import com.gant.payroll.domain.Employee;
import com.gant.payroll.domain.PaymentClassification;

/**
 * 增加销售凭条
 * 
 * @author jiangyp
 *
 */
public class SalesReceiptTransaction {

	protected PayrollDatabase payrollDatabase = new PaymentDatabaseImpl();

	private String empId;
	private LocalDate date;
	private double amount;

	public SalesReceiptTransaction(String empId, LocalDate date, double amount) {
		this.empId = empId;
		this.date = date;
		this.amount = amount;
	}

	public void execute() {
		Employee emp = payrollDatabase.findEmployee(empId);
		if (emp == null) {
			throw new RuntimeException("没有员工");
		}
		PaymentClassification pc = emp.getClassification();
		if (pc instanceof CommissionedClassification) {
			CommissionedClassification cc = (CommissionedClassification) pc;
			SalesReceipt sr = new SalesReceipt(date, amount);
			cc.addSalesReceipt(sr);
			payrollDatabase.addSalesReceipt(empId, sr);
		}
	}
}