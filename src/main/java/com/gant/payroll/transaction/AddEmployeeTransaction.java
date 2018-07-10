package com.gant.payroll.transaction;

import com.gant.payroll.db.PaymentDatabase;
import com.gant.payroll.domain.Employee;
import com.gant.payroll.domain.PaymentClassification;
import com.gant.payroll.domain.PaymentMethod;
import com.gant.payroll.domain.PaymentSchedule;

public abstract class AddEmployeeTransaction {
	PaymentDatabase paymentDatabase;

	private String empId;
	private String name;
	private String address;

	public AddEmployeeTransaction(String empId, String name, String address) {
		this.empId = empId;
		this.name = name;
		this.address = address;
	}

	public void setPaymentDatabase(PaymentDatabase paymentDatabase) {
		this.paymentDatabase = paymentDatabase;
	}

	public abstract PaymentClassification getClassification();

	public abstract PaymentSchedule getSchedule();

	public abstract PaymentMethod getMethod();

	public void execute() {
		Employee e = new Employee(empId, name, address);
		e.setClassification(getClassification());
		e.setSchedule(getSchedule());
		e.setPaymentMethod(getMethod());
		paymentDatabase.addEmployee(empId, e);
	}
}