package com.gant.payroll.db.impl;

import java.util.ArrayList;
import java.util.List;

import com.gant.payroll.affiliation.ServiceChange;
import com.gant.payroll.classification.SalesReceipt;
import com.gant.payroll.classification.TimeCard;
import com.gant.payroll.db.PayrollDatabase;
import com.gant.payroll.db.PayrollFactory;
import com.gant.payroll.domain.Affiliation;
import com.gant.payroll.domain.Employee;
import com.gant.payroll.domain.Paycheck;

public class PaymentDatabaseImpl implements PayrollDatabase {

	protected PayrollFactory payrollFactory = new PayrollFactoryImpl();
	protected Db db = Db.newInstance();

	@Override
	public void addEmployee(String empId, Employee emp) {
		db.id2Emp.put(empId, emp);
	}

	@Override
	public Employee findEmployee(String empId) {
		return db.id2Emp.get(empId);
	}

	/**
	 * 模拟从数据库查询员工数据，然后构建成内存对象
	 */
	@Override
	public List<Employee> findAllEmpByInit() {
		payrollFactory.createEmployee();
		return findAllEmp();
	}

	/**
	 * 直接查询内存中的员工对象
	 */
	@Override
	public List<Employee> findAllEmp() {
		return new ArrayList<>(db.id2Emp.values());
	}

	@Override
	public void addAffiliation(String memberId, Affiliation affiliation) {
		db.memberId2Affiliation.put(memberId, affiliation);
	}

	@Override
	public Affiliation findAffiliation(String memberId) {
		return db.memberId2Affiliation.get(memberId);
	}

	@Override
	public void addMember(String memberId, Employee emp) {
		if (!db.memberId2Emps.containsKey(memberId)) {
			db.memberId2Emps.put(memberId, new ArrayList<>());
		}
		db.memberId2Emps.get(memberId).add(emp);
	}

	@Override
	public List<Employee> findMembers(String memberId) {
		return db.memberId2Emps.get(memberId);
	}

	@Override
	public void addTimeCard(String empId, TimeCard timeCard) {
		if (!db.empId2TimeCards.containsKey(empId)) {
			db.empId2TimeCards.put(empId, new ArrayList<>());
		}
		db.empId2TimeCards.get(empId).add(timeCard);
	}

	@Override
	public List<TimeCard> findTimeCards(String empId) {
		return db.empId2TimeCards.get(empId);
	}

	@Override
	public void addSalesReceipt(String empId, SalesReceipt salesReceipt) {
		if (!db.empId2SalesReceipts.containsKey(empId)) {
			db.empId2SalesReceipts.put(empId, new ArrayList<>());
		}
		db.empId2SalesReceipts.get(empId).add(salesReceipt);
	}

	@Override
	public List<SalesReceipt> findSalesReceipts(String empId) {
		return db.empId2SalesReceipts.get(empId);
	}

	@Override
	public void savePaycheck(Paycheck paycheck) {
		if (!db.empId2Paycheck.containsKey(paycheck.getEmpId())) {
			db.empId2Paycheck.put(paycheck.getEmpId(), new ArrayList<>());
		}
		db.empId2Paycheck.get(paycheck.getEmpId()).add(paycheck);
	}

	@Override
	public List<Paycheck> findPaychecks(String empId) {
		return db.empId2Paycheck.get(empId);
	}

	@Override
	public void addServiceChange(String memberId, ServiceChange serviceChange) {
		if (!db.memberId2Sc.containsKey(memberId)) {
			db.memberId2Sc.put(memberId, new ArrayList<>());
		}
		db.memberId2Sc.get(memberId).add(serviceChange);
	}

	@Override
	public List<ServiceChange> findServiceChange(String memberId) {
		return db.memberId2Sc.get(memberId);
	}
}
