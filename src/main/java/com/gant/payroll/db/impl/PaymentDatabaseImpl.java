package com.gant.payroll.db.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gant.payroll.affiliation.ServiceChange;
import com.gant.payroll.classification.SalesReceipt;
import com.gant.payroll.classification.TimeCard;
import com.gant.payroll.db.PaymentDatabase;
import com.gant.payroll.domain.Affiliation;
import com.gant.payroll.domain.Employee;
import com.gant.payroll.domain.Paycheck;

public class PaymentDatabaseImpl implements PaymentDatabase {

	Map<String, Employee> id2Emp = new HashMap<>();
	Map<String, Affiliation> memberId2Affiliation = new HashMap<>();
	Map<String, List<Employee>> memberId2Emps = new HashMap<>();
	Map<String, List<TimeCard>> empId2TimeCards = new HashMap<>();
	Map<String, List<SalesReceipt>> empId2SalesReceipts = new HashMap<>();
	Map<String, List<Paycheck>> empId2Paycheck = new HashMap<>();
	Map<String, List<ServiceChange>> memberId2Sc = new HashMap<>();

	@Override
	public void addEmployee(String empId, Employee emp) {
		id2Emp.put(empId, emp);
	}

	@Override
	public Employee findEmployee(String empId) {
		return id2Emp.get(empId);
	}

	@Override
	public List<Employee> findAllEmp() {
		return new ArrayList<>(id2Emp.values());
	}

	@Override
	public void addAffiliation(String memberId, Affiliation affiliation) {
		memberId2Affiliation.put(memberId, affiliation);
	}

	@Override
	public Affiliation findAffiliation(String memberId) {
		return memberId2Affiliation.get(memberId);
	}

	@Override
	public void addMember(String memberId, Employee emp) {
		if (!memberId2Emps.containsKey(memberId)) {
			memberId2Emps.put(memberId, new ArrayList<>());
		}
		memberId2Emps.get(memberId).add(emp);
	}

	@Override
	public List<Employee> findMembers(String memberId) {
		return memberId2Emps.get(memberId);
	}

	@Override
	public void addTimeCard(String empId, TimeCard timeCard) {
		if (!empId2TimeCards.containsKey(empId)) {
			empId2TimeCards.put(empId, new ArrayList<>());
		}
		empId2TimeCards.get(empId).add(timeCard);
	}

	@Override
	public List<TimeCard> findTimeCards(String empId) {
		return empId2TimeCards.get(empId);
	}

	@Override
	public void addSalesReceipt(String empId, SalesReceipt salesReceipt) {
		if (!empId2SalesReceipts.containsKey(empId)) {
			empId2SalesReceipts.put(empId, new ArrayList<>());
		}
		empId2SalesReceipts.get(empId).add(salesReceipt);
	}

	@Override
	public List<SalesReceipt> findSalesReceipts(String empId) {
		return empId2SalesReceipts.get(empId);
	}

	@Override
	public void addPaycheck(String empId, Paycheck paycheck) {
		if (!empId2Paycheck.containsKey(empId)) {
			empId2Paycheck.put(empId, new ArrayList<>());
		}
		empId2Paycheck.get(empId).add(paycheck);
	}

	@Override
	public List<Paycheck> findPaychecks(String empId) {
		return empId2Paycheck.get(empId);
	}

	@Override
	public void addServiceChange(String memberId, ServiceChange serviceChange) {
		if (!memberId2Sc.containsKey(memberId)) {
			memberId2Sc.put(memberId, new ArrayList<>());
		}
		memberId2Sc.get(memberId).add(serviceChange);
	}

	@Override
	public List<ServiceChange> findServiceChange(String memberId) {
		return memberId2Sc.get(memberId);
	}
}
