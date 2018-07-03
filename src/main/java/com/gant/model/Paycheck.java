package com.gant.model;

import java.util.Date;

public class Paycheck {
	private Date payPeriodStart;
	private Date payPeriodEnd;
	private double grossPay;
	private double deductions;
	private double netPay;

	public Paycheck() {
		super();
	}

	public Paycheck(Date payPeriodStart, Date payPeriodEnd, double grossPay, double deductions, double netPay) {
		super();
		this.payPeriodStart = payPeriodStart;
		this.payPeriodEnd = payPeriodEnd;
		this.grossPay = grossPay;
		this.deductions = deductions;
		this.netPay = netPay;
	}

	public Date getPayPeriodStart() {
		return payPeriodStart;
	}

	public void setPayPeriodStart(Date payPeriodStart) {
		this.payPeriodStart = payPeriodStart;
	}

	public Date getPayPeriodEnd() {
		return payPeriodEnd;
	}

	public void setPayPeriodEnd(Date payPeriodEnd) {
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

	@Override
	public String toString() {
		return "Paycheck [payPeriodStart=" + payPeriodStart + ", payPeriodEnd=" + payPeriodEnd + ", grossPay=" + grossPay + ", deductions=" + deductions
				+ ", netPay=" + netPay + "]";
	}

}
