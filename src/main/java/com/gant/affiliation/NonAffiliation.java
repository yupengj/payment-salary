package com.gant.affiliation;

import com.gant.domain.Affiliation;
import com.gant.model.Paycheck;

public class NonAffiliation implements Affiliation {

	@Override
	public double calculateDeuctions(Paycheck paycheck) {
		return 0;
	}

}
