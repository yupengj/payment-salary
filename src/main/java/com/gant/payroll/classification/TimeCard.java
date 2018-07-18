package com.gant.payroll.classification;

import java.time.LocalDate;

/**
 * 时间卡片
 * 
 * @author jiangyp
 *
 */
public class TimeCard {
	private LocalDate date;
	private double hours;

	public TimeCard(LocalDate date, double hours) {
		super();
		this.date = date;
		this.hours = hours;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
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
