package com.gant.domain;

import com.gant.model.Paycheck;

public interface PaymentMethod {

	void pay(Paycheck paycheck);
}
