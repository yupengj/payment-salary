package com.gant.payroll.domain;

import java.time.LocalDate;

public class Paycheck {
	private LocalDate payPeriodStart;
	private LocalDate payPeriodEnd;
	private double grossPay;
	private double deductions;
	private double netPay;
	private double empId;

	public Paycheck() {
		super();
	}

	public Paycheck(LocalDate payPeriodStart, LocalDate payPeriodEnd, double grossPay, double deductions, double netPay) {
		super();
		this.payPeriodStart = payPeriodStart;
		this.payPeriodEnd = payPeriodEnd;
		this.grossPay = grossPay;
		this.deductions = deductions;
		this.netPay = netPay;
	}

	public LocalDate getPayPeriodStart() {
		return payPeriodStart;
	}

	public void setPayPeriodStart(LocalDate payPeriodStart) {
		this.payPeriodStart = payPeriodStart;
	}

	public LocalDate getPayPeriodEnd() {
		return payPeriodEnd;
	}

	public void setPayPeriodEnd(LocalDate payPeriodEnd) {
		this.payPeriodEnd = payPeriodEnd;
	}

	public double getGrossPay() {
		return grossPay;
	}

	public void setGrossPay(double grossPay) {
		this.grossPay = grossPay;
	}

	public double getDeductions() {
		return deductions;
	}

	public void setDeductions(double deductions) {
		this.deductions = deductions;
	}

	public double getNetPay() {
		return netPay;
	}

	public void setNetPay(double netPay) {
		this.netPay = netPay;
	}

	public double getEmpId() {
		return empId;
	}

	public void setEmpId(double empId) {
		this.empId = empId;
	}

	@Override
	public String toString() {
		return "Paycheck [payPeriodStart=" + payPeriodStart + ", payPeriodEnd=" + payPeriodEnd + ", grossPay=" + grossPay + ", deductions=" + deductions
				+ ", netPay=" + netPay + ", empId=" + empId + "]";
	}

}
