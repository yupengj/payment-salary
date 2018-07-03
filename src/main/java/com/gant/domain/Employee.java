package com.gant.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Employee {
	private String id;
	private String name;
	private String address;
	private List<Affiliation> affiliations = new ArrayList<>();

	private PaymentClassification classification;
	private PaymentSchedule schedule;
	private PaymentMethod paymentMethod;

	public Employee(String name, String address) {
		this.name = name;
		this.address = address;
	}

	public boolean isPayDay(LocalDate d) {
		return this.schedule.isPayDate(d);
	}

	public LocalDate getPayPeriodStartDate(LocalDate d) {
		return this.schedule.getPayPeriodStartDate(d);
	}

	public void payDay(Paycheck pc) {
		double grossPay = classification.calculatePay(pc);
		double deductions = calculateDeductions(pc);
		double netPay = grossPay - deductions;
		pc.setGrossPay(grossPay);
		pc.setDeductions(deductions);
		pc.setNetPay(netPay);
		paymentMethod.pay(pc);
	}

	protected double calculateDeductions(Paycheck pc) {
		double deductions = 0.0;
		for (Affiliation affiliation : affiliations) {
			deductions += affiliation.calculateDeductions(pc);
		}
		return deductions;
	}

	public void setClassification(PaymentClassification classification) {
		this.classification = classification;
	}

	public void setSchedule(PaymentSchedule schedule) {
		this.schedule = schedule;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
}
