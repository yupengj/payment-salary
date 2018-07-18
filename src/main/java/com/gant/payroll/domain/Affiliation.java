package com.gant.payroll.domain;

/**
 * 会费计算接口
 * 
 * @author jiangyp
 *
 */
public interface Affiliation {

	double calculateDeductions(Paycheck paycheck);
}
