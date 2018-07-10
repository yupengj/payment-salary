package com.gant.payroll.transaction;

import com.gant.payroll.affiliation.UnionAffiliation;
import com.gant.payroll.db.PaymentDatabase;
import com.gant.payroll.domain.Affiliation;
import com.gant.payroll.domain.Employee;

public class AffiliationTransaction {

	PaymentDatabase paymentDatabase;

	private String empId;
	private String memberId;
	private double weeklyBue;

	public AffiliationTransaction(String empId, String memberId, double weeklyBue) {
		this.empId = empId;
		this.memberId = memberId;
		this.weeklyBue = weeklyBue;
	}

	public void execute() {
		Employee emp = paymentDatabase.findEmployee(empId);
		Affiliation af = new UnionAffiliation(memberId, weeklyBue);
		emp.addAffiliations(af);
		paymentDatabase.addAffiliation(memberId, af);
		paymentDatabase.addMember(memberId, emp);
	}

	public void setPaymentDatabase(PaymentDatabase paymentDatabase) {
		this.paymentDatabase = paymentDatabase;
	}
}
