package com.gant.domain;

import java.util.Date;

public interface PaymentSchedule {

	boolean isPayDate(Date date);

	Date getPayPeriodStartDate(Date date);
}
