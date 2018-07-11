package com.gant.payroll.transaction;

import com.gant.payroll.affiliation.UnionAffiliation;
import com.gant.payroll.db.PayrollDatabase;
import com.gant.payroll.domain.Affiliation;
import com.gant.payroll.domain.Employee;

public class AffiliationTransaction {

	PayrollDatabase payrollDatabase;

	private String empId;
	private String memberId;
	private double weeklyBue;

	public AffiliationTransaction(String empId, String memberId, double weeklyBue) {
		this.empId = empId;
		this.memberId = memberId;
		this.weeklyBue = weeklyBue;
	}

	public void execute() {
		Employee emp = payrollDatabase.findEmployee(empId);
		Affiliation af = new UnionAffiliation(memberId, weeklyBue);
		emp.addAffiliations(af);
		payrollDatabase.addAffiliation(memberId, af);
		payrollDatabase.addMember(memberId, emp);
	}

	public void setPaymentDatabase(PayrollDatabase payrollDatabase) {
		this.payrollDatabase = payrollDatabase;
	}
}
