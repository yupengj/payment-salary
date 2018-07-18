package com.gant.payroll.affiliation;

import java.util.ArrayList;
import java.util.List;

import com.gant.payroll.domain.Affiliation;
import com.gant.payroll.domain.Paycheck;
import com.gant.payroll.util.DateUtil;

/**
 * 会费计算实现
 * 
 * @author jiangyp
 *
 */
public class UnionAffiliation implements Affiliation {
	private String memberId;
	private double weeklyBue;
	private List<ServiceChange> serviceChanges = new ArrayList<>();

	public UnionAffiliation(String memberId, double weeklyBue) {
		super();
		this.memberId = memberId;
		this.weeklyBue = weeklyBue;
	}

	// int fridays = 统计在 paycheck 开始时间和结束时间有多少周五
	// totalDue = fridays * weeklyBue;
	// totalChange = 计算 paycheck 开始时间和结束时间之间的 ServiceChange
	// deduction = totalDue + totalChange
	@Override
	public double calculateDeductions(Paycheck paycheck) {
		int fridays = DateUtil.betweenOnFriday(paycheck.getPayPeriodStart(), paycheck.getPayPeriodEnd());
		double totalDue = fridays * weeklyBue;
		double totalChange = 0D;
		for (ServiceChange sc : serviceChanges) {
			if (DateUtil.between(sc.getDate(), paycheck.getPayPeriodStart(), paycheck.getPayPeriodEnd())) {
				totalChange += sc.getAmout();
			}
		}
		return totalDue + totalChange;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public double getWeeklyBue() {
		return weeklyBue;
	}

	public void setWeeklyBue(double weeklyBue) {
		this.weeklyBue = weeklyBue;
	}

	public void addServiceChanges(ServiceChange serviceChange) {
		this.serviceChanges.add(serviceChange);
	}

	@Override
	public String toString() {
		return "UnionAffiliation [memberId=" + memberId + ", weeklyBue=" + weeklyBue + ", serviceChanges=" + serviceChanges + "]";
	}

}
