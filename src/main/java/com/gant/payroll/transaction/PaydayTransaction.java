package com.gant.payroll.transaction;

import java.time.LocalDate;
import java.util.List;

import com.gant.payroll.db.PaymentDatabase;
import com.gant.payroll.domain.Employee;
import com.gant.payroll.domain.Paycheck;

public class PaydayTransaction {

	PaymentDatabase paymentDatabase;
	private LocalDate date;

	public PaydayTransaction(LocalDate date) {
		this.date = date;
	}

	public void setPaymentDatabase(PaymentDatabase paymentDatabase) {
		this.paymentDatabase = paymentDatabase;
	}

	public void execute() {
		List<Employee> emps = paymentDatabase.findAllEmp();
		for (Employee emp : emps) {
			if (emp.isPayDay(date)) {
				Paycheck pc = new Paycheck(emp.getPayPeriodStartDate(date), date);
				emp.setPaymentDatabase(paymentDatabase);
				emp.payDay(pc);
			}
		}
	}
}
