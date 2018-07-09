package com.gant.payroll.transaction;

import java.util.ArrayList;
import java.util.List;

import com.gant.payroll.db.PaymentDatabase;
import com.gant.payroll.db.impl.PaymentDatabaseImpl;
import com.gant.payroll.domain.Affiliation;
import com.gant.payroll.domain.Employee;
import com.gant.payroll.domain.PaymentClassification;
import com.gant.payroll.domain.PaymentMethod;
import com.gant.payroll.domain.PaymentSchedule;

public abstract class AddEmployeeTransaction {
	PaymentDatabase paymentDatabase = new PaymentDatabaseImpl();

	private String empId;
	private String name;
	private String address;
	private List<Affiliation> affiliations = new ArrayList<>();

	public AddEmployeeTransaction(String empId, String name, String address) {
		this.empId = empId;
		this.name = name;
		this.address = address;
	}

	public abstract PaymentClassification getClassification();

	public abstract PaymentSchedule getSchedule();

	public abstract PaymentMethod getMethod();

	public void addAffiliations(Affiliation affiliation) {
		affiliations.add(affiliation);
	}

	public void execute() {
		Employee e = new Employee(empId, name, address);
		e.setClassification(getClassification());
		e.setSchedule(getSchedule());
		e.setPaymentMethod(getMethod());
		e.setAffiliations(this.affiliations);
		paymentDatabase.addEmployee(empId, e);
	}
}