package com.gant.affiliation;

import java.util.ArrayList;
import java.util.List;

import com.gant.domain.Affiliation;
import com.gant.domain.Paycheck;

public class UnionAffiliation implements Affiliation {
	private long memberId;
	private double weeklyBue;
	private List<ServiceChange> serviceChanges = new ArrayList<>();

	// int fridays = 统计在 paycheck 开始时间和结束时间有多少周五
	// totalDue = fridays * weeklyBue;
	// totalChange = 计算 paycheck 开始时间和结束时间之间的 ServiceChange
	// deduction = totalDue + totalChange
	@Override
	public double calculateDeductions(Paycheck paycheck) {
		return 0;
	}

}
