package com.gant.affiliation;

import java.util.Date;

public class ServiceChange {
	private Date date;
	private double amout;

	public ServiceChange() {
		super();
	}

	public ServiceChange(Date date, double amout) {
		super();
		this.date = date;
		this.amout = amout;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
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
