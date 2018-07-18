package com.gant.payroll.db.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gant.payroll.affiliation.ServiceChange;
import com.gant.payroll.classification.SalesReceipt;
import com.gant.payroll.classification.TimeCard;
import com.gant.payroll.domain.Affiliation;
import com.gant.payroll.domain.Employee;
import com.gant.payroll.domain.Paycheck;

/**
 * 模式数据库
 * 
 * @author jiangyp
 *
 */
public class Db {

	public Map<String, Employee> id2Emp = new HashMap<>();
	public Map<String, Affiliation> memberId2Affiliation = new HashMap<>();
	public Map<String, List<Employee>> memberId2Emps = new HashMap<>();
	public Map<String, List<TimeCard>> empId2TimeCards = new HashMap<>();
	public Map<String, List<SalesReceipt>> empId2SalesReceipts = new HashMap<>();
	public Map<String, List<Paycheck>> empId2Paycheck = new HashMap<>();
	public Map<String, List<ServiceChange>> memberId2Sc = new HashMap<>();

	private static Db db = new Db();

	private Db() {
	}

	public static Db newInstance() {
		return db;
	}
}
