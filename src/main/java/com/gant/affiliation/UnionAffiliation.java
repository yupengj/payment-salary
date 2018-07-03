package com.gant.affiliation;

import java.util.ArrayList;
import java.util.List;

import com.gant.domain.Affiliation;
import com.gant.model.Paycheck;

public class UnionAffiliation implements Affiliation {
	private long memberId;
	private double weeklyBue;
	private List<ServiceChange> serviceChanges = new ArrayList<>();

	@Override
	public double calculateDeuctions(Paycheck paycheck) {
		return 0;
	}

}
