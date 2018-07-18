package com.gant.payroll.db;

import java.util.List;

import com.gant.payroll.affiliation.ServiceChange;
import com.gant.payroll.classification.SalesReceipt;
import com.gant.payroll.classification.TimeCard;
import com.gant.payroll.domain.Affiliation;
import com.gant.payroll.domain.Employee;
import com.gant.payroll.domain.Paycheck;

/**
 * 模拟与数据库交互接口
 * 
 * @author jiangyp
 *
 */
public interface PayrollDatabase {

	void addEmployee(String empId, Employee emp);

	Employee findEmployee(String empId);

	List<Employee> findAllEmpByInit();

	List<Employee> findAllEmp();

	void addAffiliation(String memberId, Affiliation affiliation);

	Affiliation findAffiliation(String memberId);

	void addTimeCard(String empId, TimeCard timeCard);

	List<TimeCard> findTimeCards(String empId);

	void addSalesReceipt(String empId, SalesReceipt salesReceipt);

	List<SalesReceipt> findSalesReceipts(String empId);

	void addMember(String memberId, Employee emp);

	List<Employee> findMembers(String memberId);

	void savePaycheck(Paycheck paycheck);

	List<Paycheck> findPaychecks(String empId);

	void addServiceChange(String memberId, ServiceChange serviceChange);

	List<ServiceChange> findServiceChange(String memberId);
}
