package com.gant.classification;

import java.util.Date;

class Timecard {
	private Date date;
	private double hours;

	public Timecard() {
		super();
	}

	public Timecard(Date date, double hours) {
		super();
		this.date = date;
		this.hours = hours;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getHours() {
		return hours;
	}

	public void setHours(double hours) {
		this.hours = hours;
	}

	@Override
	public String toString() {
		return "Timecard [date=" + date + ", hours=" + hours + "]";
	}

}
