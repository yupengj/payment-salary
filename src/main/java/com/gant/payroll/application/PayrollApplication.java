package com.gant.payroll.application;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import com.gant.payroll.db.PayrollDatabase;
import com.gant.payroll.db.impl.PaymentDatabaseImpl;
import com.gant.payroll.domain.Employee;
import com.gant.payroll.domain.Paycheck;
import com.gant.payroll.transaction.AddCommissionedEmployeeTransaction;
import com.gant.payroll.transaction.AddEmployeeTransaction;
import com.gant.payroll.transaction.AddHourlyEmployeeTransaction;
import com.gant.payroll.transaction.AddSalariedEmployeeTransaction;
import com.gant.payroll.transaction.AffiliationTransaction;
import com.gant.payroll.transaction.SalesReceiptTransaction;
import com.gant.payroll.transaction.ServiceChangeTransaction;
import com.gant.payroll.transaction.TimeCardTransaction;

public class PayrollApplication {

	static PayrollDatabase payrollDatabase = new PaymentDatabaseImpl();

	public static void main(String[] args) {
		// 模拟数据
		buildEmp();

		LocalDate date = LocalDate.of(2018, Month.JULY, 20);
		List<Employee> emps = payrollDatabase.findAllEmp();
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
			System.out.println(emp);
			List<Paycheck> paychecks = payrollDatabase.findPaychecks(emp.getId());
			if (paychecks != null) {
				paychecks.forEach(it -> System.out.println(it));
			}
		}
	}

	protected static void buildEmp() {
		// 增加小时工
		String empId1 = "1";
		AddEmployeeTransaction addEmp = new AddHourlyEmployeeTransaction(empId1, "张三", "上海", 10);
		addEmp.setPaymentDatabase(payrollDatabase);
		addEmp.execute();

		// 给小时工增加工时卡
		TimeCardTransaction timeCardTransaction = new TimeCardTransaction(empId1, LocalDate.of(2018, Month.JULY, 18), 100);
		timeCardTransaction.setPaymentDatabase(payrollDatabase);
		timeCardTransaction.execute();

		// 给小时工增加会费
		String memberId1 = "1";
		AffiliationTransaction affiliationTransaction = new AffiliationTransaction(empId1, memberId1, 10);
		affiliationTransaction.setPaymentDatabase(payrollDatabase);
		affiliationTransaction.execute();

		ServiceChangeTransaction serviceChangeTransaction = new ServiceChangeTransaction(memberId1, LocalDate.of(2018, Month.JULY, 18), 20);
		serviceChangeTransaction.setPaymentDatabase(payrollDatabase);
		serviceChangeTransaction.execute();

		// 增加销售
		String empId2 = "2";
		addEmp = new AddCommissionedEmployeeTransaction(empId2, "李四", "上海", 1000, 0.5);
		addEmp.setPaymentDatabase(payrollDatabase);
		addEmp.execute();

		// 增加销售凭条
		SalesReceiptTransaction salesReceiptTransaction = new SalesReceiptTransaction(empId2, LocalDate.of(2018, Month.JULY, 18), 100);
		salesReceiptTransaction.setPaymentDatabase(payrollDatabase);
		salesReceiptTransaction.execute();

		// 增加销售凭条
		salesReceiptTransaction = new SalesReceiptTransaction(empId2, LocalDate.of(2018, Month.JULY, 19), 50);
		salesReceiptTransaction.setPaymentDatabase(payrollDatabase);
		salesReceiptTransaction.execute();

		String empId3 = "3";
		// 增加固定工资员工
		addEmp = new AddSalariedEmployeeTransaction(empId3, "王五", "上海", 1000);
		addEmp.setPaymentDatabase(payrollDatabase);
		addEmp.execute();
	}
}
