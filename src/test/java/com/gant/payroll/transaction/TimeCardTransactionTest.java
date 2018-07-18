package com.gant.payroll.transaction;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.junit.Test;

import com.gant.payroll.classification.TimeCard;
import com.gant.payroll.db.PayrollDatabase;
import com.gant.payroll.db.impl.PaymentDatabaseImpl;

public class TimeCardTransactionTest {

	PayrollDatabase payrollDatabase = new PaymentDatabaseImpl();

	@Test
	public void testAddTimeCard() {
		String empId = "1";
		AddEmployeeTransaction addEmp = new AddHourlyEmployeeTransaction(empId, "张三", "上海", 10);
		addEmp.execute();

		System.out.println(payrollDatabase.findEmployee(empId));

		TimeCardTransaction timeCardTransaction = new TimeCardTransaction(empId, LocalDate.of(2018, Month.JULY, 20), 10);
		timeCardTransaction.execute();

		timeCardTransaction = new TimeCardTransaction(empId, LocalDate.of(2018, Month.JULY, 19), 13);
		timeCardTransaction.execute();

		List<TimeCard> timeCards = payrollDatabase.findTimeCards(empId);
		timeCards.forEach(it -> System.out.println(it));

	}
}
