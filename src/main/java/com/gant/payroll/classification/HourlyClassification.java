package com.gant.payroll.classification;

import java.util.ArrayList;
import java.util.List;

import com.gant.payroll.domain.Paycheck;
import com.gant.payroll.domain.PaymentClassification;
import com.gant.payroll.util.DateUtil;

/**
 * 小时工支付策略
 * 
 * @author jiangyp
 *
 */
public class HourlyClassification implements PaymentClassification {

	private double hourlyRate;
	private List<TimeCard> timeCards = new ArrayList<>();

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

	public void addTimeCards(TimeCard timeCard) {
		this.timeCards.add(timeCard);
	}

	private double calculatePayForTimeCard(TimeCard tc) {
		double hours = tc.getHours();
		if (hours > 8) {
			return 8 * hourlyRate + (hours - 8) * hourlyRate * 1.5;
		} else {
			return 8 * hourlyRate;
		}
	}

	@Override
	public String toString() {
		return "HourlyClassification [hourlyRate=" + hourlyRate + ", timeCards=" + timeCards + "]";
	}

}
