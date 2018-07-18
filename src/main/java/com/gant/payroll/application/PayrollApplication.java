package com.gant.payroll.application;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import com.gant.payroll.db.PayrollDatabase;
import com.gant.payroll.db.impl.PaymentDatabaseImpl;
import com.gant.payroll.domain.Employee;
import com.gant.payroll.domain.Paycheck;

/**
 * 支付程序入口
 * 
 * @author jiangyp
 *
 */
public class PayrollApplication {

	static PayrollDatabase payrollDatabase = new PaymentDatabaseImpl();

	public static void main(String[] args) {
		LocalDate date = LocalDate.of(2018, Month.JULY, 20);
		
		List<Employee> emps = payrollDatabase.findAllEmpByInit();
		
		for (Employee emp : emps) {
			if (emp.isPayDay(date)) {
				Paycheck pc = new Paycheck(emp.getPayPeriodStartDate(date), date);
				emp.payDay(pc);
				payrollDatabase.savePaycheck(pc);
			}
		}
		
		plintPaycheck(emps);
	}

	protected static void plintPaycheck(List<Employee> emps) {
		for (Employee emp : emps) {
			System.out.println();
			System.out.println(emp);
			List<Paycheck> paychecks = payrollDatabase.findPaychecks(emp.getId());
			if (paychecks != null) {
				paychecks.forEach(it -> System.out.println(it));
			}
		}
	}
}
