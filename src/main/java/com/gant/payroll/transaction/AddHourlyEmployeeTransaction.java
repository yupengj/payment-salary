package com.gant.payroll.transaction;

import com.gant.payroll.classification.HourlyClassification;
import com.gant.payroll.domain.PaymentClassification;
import com.gant.payroll.domain.PaymentSchedule;
import com.gant.payroll.schedule.WeeklySchedule;

public class AddHourlyEmployeeTransaction extends AddEmployeeTransaction {
	private double rate;

	AddHourlyEmployeeTransaction(String name, String address, double hourlyRate) {
		super(name, address);
		this.rate = hourlyRate;
	}

	@Override
	public PaymentClassification getClassification() {
		return new HourlyClassification(rate);
	}

	@Override
	public PaymentSchedule getSchedule() {

		return new WeeklySchedule();
	}
}
