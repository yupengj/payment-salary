package com.gant.payroll.db;

import java.util.List;

import com.gant.payroll.classification.SalesReceipt;
import com.gant.payroll.classification.TimeCard;
import com.gant.payroll.domain.Employee;

public interface PaymentDatabase {

	void addEmployee(String empId, Employee emp);

	Employee findEmployee(String empId);

	List<Employee> findAllEmp();

	void addTimeCard(String empId, TimeCard timeCard);

	List<TimeCard> findTimeCards(String empId);

	void addSalesReceipt(String empId, SalesReceipt salesReceipt);

	List<SalesReceipt> findSalesReceipts(String empId);

	void addMember(String memberId, Employee emp);

	List<Employee> findMembers(String memberId);
}
