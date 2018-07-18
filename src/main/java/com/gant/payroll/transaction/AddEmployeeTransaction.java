package com.gant.payroll.transaction;

import com.gant.payroll.db.PayrollDatabase;
import com.gant.payroll.db.impl.PaymentDatabaseImpl;
import com.gant.payroll.domain.Employee;
import com.gant.payroll.domain.PaymentClassification;
import com.gant.payroll.domain.PaymentMethod;
import com.gant.payroll.domain.PaymentSchedule;

/**
 * 增加员工
 * 
 * @author jiangyp
 *
 */
public abstract class AddEmployeeTransaction {

	protected PayrollDatabase payrollDatabase = new PaymentDatabaseImpl();

	private String empId;
	private String name;
	private String address;

	public AddEmployeeTransaction(String empId, String name, String address) {
		this.empId = empId;
		this.name = name;
		this.address = address;
	}

	public abstract PaymentClassification getClassification();

	public abstract PaymentSchedule getSchedule();

	public abstract PaymentMethod getMethod();

	public void execute() {
		Employee e = new Employee(empId, name, address);
		e.setClassification(getClassification());
		e.setSchedule(getSchedule());
		e.setPaymentMethod(getMethod());
		payrollDatabase.addEmployee(empId, e);
	}
}