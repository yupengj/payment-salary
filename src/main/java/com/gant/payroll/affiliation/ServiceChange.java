package com.gant.payroll.affiliation;

import java.time.LocalDate;

public class ServiceChange {
	private LocalDate date;
	private double amout;

	public ServiceChange() {
		super();
	}

	public ServiceChange(LocalDate date, double amout) {
		super();
		this.date = date;
		this.amout = amout;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public double getAmout() {
		return amout;
	}

	public void setAmout(double amout) {
		this.amout = amout;
	}

	@Override
	public String toString() {
		return "ServiceChange [date=" + date + ", amout=" + amout + "]";
	}

}
