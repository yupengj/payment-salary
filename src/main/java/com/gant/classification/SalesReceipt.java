package com.gant.classification;

import java.util.Date;

class SalesReceipt {
	private Date date;
	private double amount;

	public SalesReceipt() {
		super();
	}

	public SalesReceipt(Date date, double amount) {
		super();
		this.date = date;
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
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
