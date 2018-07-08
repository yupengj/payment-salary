package com.gant.payroll.transaction;

import com.gant.payroll.domain.Employee;
import com.gant.payroll.domain.PaymentClassification;
import com.gant.payroll.domain.PaymentMethod;
import com.gant.payroll.domain.PaymentSchedule;
import com.gnat.payroll.method.HoldMethod;

public abstract class AddEmployeeTransaction {
	private String name;
	private String address;
	public AddEmployeeTransaction(String name,String address){
		this.name = name;
		this.address = address;
	}
	public abstract PaymentClassification getClassification();
	public abstract PaymentSchedule getSchedule();
	
	public void execute(){
		PaymentClassification pc = getClassification();
		PaymentSchedule ps = getSchedule();
		PaymentMethod  pm = new HoldMethod();
		Employee  e = new Employee(name, address);
		e.setClassification(pc);
		e.setSchedule(ps);
		e.setPaymentMethod(pm);		
		//保存到数据库, 略
	}
}