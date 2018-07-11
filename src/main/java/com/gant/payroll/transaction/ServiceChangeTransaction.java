package com.gant.payroll.transaction;

import java.time.LocalDate;
import java.util.List;

import com.gant.payroll.affiliation.ServiceChange;
import com.gant.payroll.affiliation.UnionAffiliation;
import com.gant.payroll.db.PayrollDatabase;
import com.gant.payroll.domain.Affiliation;
import com.gant.payroll.domain.Employee;

public class ServiceChangeTransaction {

	PayrollDatabase payrollDatabase;

	private String memberId;
	private LocalDate date;
	private double amout;

	public ServiceChangeTransaction(String memberId, LocalDate date, double amout) {
		this.memberId = memberId;
		this.date = date;
		this.amout = amout;
	}

	public void execute() {
		List<Employee> emps = payrollDatabase.findMembers(memberId);
		for (Employee emp : emps) {
			List<Affiliation> affs = emp.getAffiliations();
			for (Affiliation aff : affs) {
				if (aff instanceof UnionAffiliation) {
					UnionAffiliation uaff = (UnionAffiliation) aff;
					ServiceChange sc = new ServiceChange(date, amout);
					uaff.addServiceChanges(sc);
					payrollDatabase.addServiceChange(memberId, sc);
				}
			}
		}
	}

	public void setPaymentDatabase(PayrollDatabase payrollDatabase) {
		this.payrollDatabase = payrollDatabase;
	}

}
