package com.gant.classification;

import java.util.List;

import com.gant.domain.Paycheck;
import com.gant.domain.PaymentClassification;
import com.gant.util.DateUtil;

public class HourlyClassification implements PaymentClassification {

	private double hourlyRate;
	private List<TimeCard> timeCards;

	public HourlyClassification(double hourlyRate) {
		this.hourlyRate = hourlyRate;
	}

	@Override
	public double calculatePay(Paycheck paycheck) {
		double totalPay = 0;
		for (TimeCard tc : timeCards) {
			if (DateUtil.between(tc.getDate(), paycheck.getPayPeriodStart(), paycheck.getPayPeriodEnd())) {
				totalPay += calculatePayForTimeCard(tc);
			}
		}
		return totalPay;
	}

	public void setTimeCards(List<TimeCard> timeCards) {
		this.timeCards = timeCards;
	}

	private double calculatePayForTimeCard(TimeCard tc) {
		double hours = tc.getHours();
		if (hours > 8) {
			return 8 * hourlyRate + (hours - 8) * hourlyRate * 1.5;
		} else {
			return 8 * hourlyRate;
		}
	}

}
