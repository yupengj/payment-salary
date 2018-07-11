package com.gant.payroll.transaction;

import com.gant.payroll.classification.SalariedClassification;
import com.gant.payroll.domain.PaymentClassification;
import com.gant.payroll.domain.PaymentMethod;
import com.gant.payroll.domain.PaymentSchedule;
import com.gant.payroll.method.HoldMethod;
import com.gant.payroll.schedule.MothlySchedule;

public class AddSalariedEmployeeTransaction extends AddEmployeeTransaction {
	private double salary;

	public AddSalariedEmployeeTransaction(String empId, String name, String address, double salary) {
		super(empId, name, address);
		this.salary = salary;
	}

	@Override
	public PaymentClassification getClassification() {
		return new SalariedClassification(salary);
	}

	@Override
	public PaymentSchedule getSchedule() {
		return new MothlySchedule();
	}

	@Override
	public PaymentMethod getMethod() {
		return new HoldMethod();
	}

}
