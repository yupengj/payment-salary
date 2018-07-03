package com.gant.schedule;

import java.util.Date;

import com.gant.domain.PaymentSchedule;

public class MothlySchedule implements PaymentSchedule{

	@Override
	public boolean isPayDate(Date date) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Date getPayPeriodStartDate(Date date) {
		// TODO Auto-generated method stub
		return null;
	}

}
