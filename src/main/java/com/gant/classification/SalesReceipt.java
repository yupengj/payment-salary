package com.gant.classification;

import java.time.LocalDate;

class SalesReceipt {
	private LocalDate date;
	private double amount;

	public SalesReceipt() {
		super();
	}

	public SalesReceipt(LocalDate date, double amount) {
		super();
		this.date = date;
		this.amount = amount;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "SalesReceipt [date=" + date + ", amount=" + amount + "]";
	}

}
