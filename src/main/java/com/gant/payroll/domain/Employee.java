package com.gant.payroll.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * 员工类
 * 
 * @author jiangyp
 *
 */
public class Employee {

	private String id;
	private String name;
	private String address;
	private List<Affiliation> affiliations = new ArrayList<>();

	private PaymentClassification classification;
	private PaymentSchedule schedule;
	private PaymentMethod paymentMethod;

	public Employee(String id, String name, String address) {
		this.id = id;
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
		pc.setEmpId(id);
		paymentMethod.pay(pc);
	}

	protected double calculateDeductions(Paycheck pc) {
		double deductions = 0.0;
		for (Affiliation affiliation : affiliations) {
			deductions += affiliation.calculateDeductions(pc);
		}
		return deductions;
	}

	public PaymentClassification getClassification() {
		return classification;
	}

	public void setClassification(PaymentClassification classification) {
		this.classification = classification;
	}

	public PaymentSchedule getSchedule() {
		return schedule;
	}

	public void setSchedule(PaymentSchedule schedule) {
		this.schedule = schedule;
	}

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public List<Affiliation> getAffiliations() {
		return affiliations;
	}

	public void addAffiliations(Affiliation affiliations) {
		this.affiliations.add(affiliations);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", address=" + address + ", affiliations=" + affiliations + ", classification=" + classification
				+ ", schedule=" + schedule + ", paymentMethod=" + paymentMethod + "]";
	}

}
