package com.gant.payroll.transaction;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.gant.payroll.db.PaymentDatabase;
import com.gant.payroll.db.impl.PaymentDatabaseImpl;
import com.gant.payroll.domain.Paycheck;

public class PaydayTransactionTest {

	PaymentDatabase paymentDatabase = new PaymentDatabaseImpl();

	@Test
	public void testPaydate() {
		// 增加小时工
		String empId1 = "1";
		AddEmployeeTransaction addEmp = new AddHourlyEmployeeTransaction(empId1, "张三", "上海", 10);
		addEmp.setPaymentDatabase(paymentDatabase);
		addEmp.execute();

		// 给小时工增加工时卡
		TimeCardTransaction timeCardTransaction = new TimeCardTransaction(empId1, LocalDate.of(2018, Month.JULY, 18), 100);
		timeCardTransaction.setPaymentDatabase(paymentDatabase);
		timeCardTransaction.execute();

		// 给小时工增加会费
		String memberId1 = "1";
		AffiliationTransaction affiliationTransaction = new AffiliationTransaction(empId1, memberId1, 10);
		affiliationTransaction.setPaymentDatabase(paymentDatabase);
		affiliationTransaction.execute();

		ServiceChangeTransaction serviceChangeTransaction = new ServiceChangeTransaction(memberId1, LocalDate.of(2018, Month.JULY, 18), 20);
		serviceChangeTransaction.setPaymentDatabase(paymentDatabase);
		serviceChangeTransaction.execute();

		// 增加销售
		String empId2 = "2";
		addEmp = new AddCommissionedEmployeeTransaction(empId2, "李四", "上海", 1000, 0.5);
		addEmp.setPaymentDatabase(paymentDatabase);
		addEmp.execute();

		// 增加销售凭条
		SalesReceiptTransaction salesReceiptTransaction = new SalesReceiptTransaction(empId2, LocalDate.of(2018, Month.JULY, 18), 100);
		salesReceiptTransaction.setPaymentDatabase(paymentDatabase);
		salesReceiptTransaction.execute();

		// 增加销售凭条
		salesReceiptTransaction = new SalesReceiptTransaction(empId2, LocalDate.of(2018, Month.JULY, 19), 50);
		salesReceiptTransaction.setPaymentDatabase(paymentDatabase);
		salesReceiptTransaction.execute();

		String empId3 = "3";
		// 增加固定工资员工
		addEmp = new AddSalariedEmployeeTransaction(empId3, "王五", "上海", 1000);
		addEmp.setPaymentDatabase(paymentDatabase);
		addEmp.execute();

		PaydayTransaction paydayTransaction = new PaydayTransaction(LocalDate.of(2018, Month.JULY, 20));
		paydayTransaction.setPaymentDatabase(paymentDatabase);
		paydayTransaction.execute();

		List<Paycheck> paycheckAll = new ArrayList<>();

		List<Paycheck> paychecks1 = paymentDatabase.findPaychecks(empId1);
		if (paychecks1 != null) {
			paycheckAll.addAll(paychecks1);
		}
		List<Paycheck> paychecks2 = paymentDatabase.findPaychecks(empId2);
		if (paychecks2 != null) {
			paycheckAll.addAll(paychecks2);
		}
		List<Paycheck> paychecks3 = paymentDatabase.findPaychecks(empId3);
		if (paychecks3 != null) {
			paycheckAll.addAll(paychecks3);
		}
		paycheckAll.forEach(it -> System.out.println(it));
	}

}
