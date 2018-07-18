package com.gant.payroll.transaction;

import com.gant.payroll.affiliation.UnionAffiliation;
import com.gant.payroll.db.PayrollDatabase;
import com.gant.payroll.db.impl.PaymentDatabaseImpl;
import com.gant.payroll.domain.Affiliation;
import com.gant.payroll.domain.Employee;

/**
 * 增加扣除项
 * 
 * @author jiangyp
 *
 */
public class AffiliationTransaction {

	protected PayrollDatabase payrollDatabase = new PaymentDatabaseImpl();

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
}
