package com.gant.payroll.transaction;

import java.time.LocalDate;

import com.gant.payroll.classification.HourlyClassification;
import com.gant.payroll.classification.TimeCard;
import com.gant.payroll.db.PaymentDatabase;
import com.gant.payroll.db.impl.PaymentDatabaseImpl;
import com.gant.payroll.domain.Employee;
import com.gant.payroll.domain.PaymentClassification;

public class TimeCardTransaction {

	PaymentDatabase paymentDatabase = new PaymentDatabaseImpl();

	private String empId;
	private LocalDate date;
	private double hours;;

	public TimeCardTransaction(String empId, LocalDate date, double hours) {
		this.empId = empId;
		this.date = date;
		this.hours = hours;
	}

	public void execute() {
		Employee emp = paymentDatabase.findEmployee(empId);
		if (emp == null) {
			throw new RuntimeException("没有员工");
		}
		PaymentClassification pc = emp.getClassification();
		if (pc instanceof HourlyClassification) {
			HourlyClassification hc = (HourlyClassification) pc;
			hc.addTimeCards(new TimeCard(date, hours));
		}
	}
}