package com.gant.payroll.transaction;

import java.time.LocalDate;
import java.util.List;

import com.gant.payroll.db.PayrollDatabase;
import com.gant.payroll.db.impl.PaymentDatabaseImpl;
import com.gant.payroll.domain.Employee;
import com.gant.payroll.domain.Paycheck;

/**
 * 支付薪资
 * 
 * @author jiangyp
 *
 */
public class PaydayTransaction {

	protected PayrollDatabase payrollDatabase = new PaymentDatabaseImpl();
	private LocalDate date;

	public PaydayTransaction(LocalDate date) {
		this.date = date;
	}

	public void execute() {
		List<Employee> emps = payrollDatabase.findAllEmpByInit();
		for (Employee emp : emps) {
			if (emp.isPayDay(date)) {
				Paycheck pc = new Paycheck(emp.getPayPeriodStartDate(date), date);
				emp.payDay(pc);
				payrollDatabase.savePaycheck(pc);
			}
		}
	}
}
