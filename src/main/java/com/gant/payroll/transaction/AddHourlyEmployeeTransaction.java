package com.gant.payroll.transaction;

import com.gant.payroll.classification.HourlyClassification;
import com.gant.payroll.domain.PaymentClassification;
import com.gant.payroll.domain.PaymentMethod;
import com.gant.payroll.domain.PaymentSchedule;
import com.gant.payroll.method.HoldMethod;
import com.gant.payroll.schedule.WeeklySchedule;

/**
 * 增加小时工
 * 
 * @author jiangyp
 *
 */
public class AddHourlyEmployeeTransaction extends AddEmployeeTransaction {
	private double rate;

	public AddHourlyEmployeeTransaction(String empId, String name, String address, double rate) {
		super(empId, name, address);
		this.rate = rate;
	}

	@Override
	public PaymentClassification getClassification() {
		return new HourlyClassification(rate);
	}

	@Override
	public PaymentSchedule getSchedule() {
		return new WeeklySchedule();
	}

	@Override
	public PaymentMethod getMethod() {
		return new HoldMethod();
	}
}
