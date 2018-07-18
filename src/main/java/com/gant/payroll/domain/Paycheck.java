package com.gant.payroll.domain;

import java.time.LocalDate;

/**
 * 支付记录
 * 
 * @author jiangyp
 *
 */
public class Paycheck {
	private LocalDate payPeriodStart;
	private LocalDate payPeriodEnd;
	private double grossPay;
	private double deductions;
	private double netPay;
	private String empId;

	public Paycheck(LocalDate payPeriodStart, LocalDate payPeriodEnd) {
		super();
		this.payPeriodStart = payPeriodStart;
		this.payPeriodEnd = payPeriodEnd;
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

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	@Override
	public String toString() {
		return "Paycheck [payPeriodStart=" + payPeriodStart + ", payPeriodEnd=" + payPeriodEnd + ", grossPay=" + grossPay + ", deductions=" + deductions
				+ ", netPay=" + netPay + ", empId=" + empId + "]";
	}

}
