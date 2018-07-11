package com.gant.payroll.transaction;

import java.time.LocalDate;
import java.util.List;

import com.gant.payroll.db.PayrollDatabase;
import com.gant.payroll.domain.Employee;
import com.gant.payroll.domain.Paycheck;

public class PaydayTransaction {

	PayrollDatabase payrollDatabase;
	private LocalDate date;

	public PaydayTransaction(LocalDate date) {
		this.date = date;
	}

	public void setPaymentDatabase(PayrollDatabase payrollDatabase) {
		this.payrollDatabase = payrollDatabase;
	}

	public void execute() {
		List<Employee> emps = payrollDatabase.findAllEmp();
		for (Employee emp : emps) {
			if (emp.isPayDay(date)) {
				Paycheck pc = new Paycheck(emp.getPayPeriodStartDate(date), date);
				emp.payDay(pc);
				payrollDatabase.savePaycheck(pc);
			}
		}
	}
}
