package com.gant.payroll.transaction;

import com.gant.payroll.classification.CommissionedClassification;
import com.gant.payroll.domain.PaymentClassification;
import com.gant.payroll.domain.PaymentMethod;
import com.gant.payroll.domain.PaymentSchedule;
import com.gant.payroll.method.HoldMethod;
import com.gant.payroll.schedule.BiweeklySchedule;

/**
 * 增加销售员工
 * 
 * @author jiangyp
 *
 */
public class AddCommissionedEmployeeTransaction extends AddEmployeeTransaction {
	private double salary;
	private double rate;

	public AddCommissionedEmployeeTransaction(String empId, String name, String address, double salary, double rate) {
		super(empId, name, address);
		this.salary = salary;
		this.rate = rate;
	}

	@Override
	public PaymentClassification getClassification() {
		return new CommissionedClassification(salary, rate);
	}

	@Override
	public PaymentSchedule getSchedule() {
		return new BiweeklySchedule();
	}

	@Override
	public PaymentMethod getMethod() {
		return new HoldMethod();
	}
}
