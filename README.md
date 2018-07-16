# payment-salary

## 薪水支付系统的初步规格说明
下面是和客户交谈时做的一些记录。
该系统由一个公司雇员数据库以及和雇员相关的数据（比如：工作时间卡）组成。该系统必须为每个雇员支付薪水。系统必须按照规定的方法准时地给雇员支付正确数目的薪水。同时，必须从雇员的薪水中减去各种扣款

- 有些雇员是**钟点工**。会按照他们雇员记录中每小时报酬字段的值对他们进行支付。他们每天会提交**工作时间卡**，其中记录了日期以及工作小时数。如果每天工作超过8小时，那么超过的部分会按照正常报酬的1.5倍进行支付。**每周五对他们进行支付**。

- 有些雇员完全以**月薪**进行支付。**每个月的最后一个工作日对他们进行支付**。在他们的雇员记录中有一个月薪字段。

- 同时，对于一些带薪雇员，会根据他们的**销售**情况，支付他们一定数量的酬金。他们会提交**销售凭条**，其中记录了销售日期和数量。在他们的雇员记录中有一个酬金字段，**每隔一周的周五对他们进行支付**。

- 雇员可以选择**支付方式**。可以选择把支付支票邮寄到他们指定的邮政地址；也可以把支票保存在出纳人员那里随时支取；或者要求将薪水直接存入他们指定的银行账户。

- 一些雇员会计入协会。在他们的雇员记录中有一个每周应付款项目字段。这些应付款项目必须要从他们的新税种扣除。协会有时也会针对单个协会成员征收服务费用。协会每周会提交这些服务费用，服务费用必须用从相应雇员的下一个月的薪水总额中扣除。

- 薪水支付程序**每个工作日运行一次**，并且当天为相应的雇员进行支付。系统会被告知雇员的支付日期，这样它会计算从雇员**上次支付日期到现**在规定的本次支付日期间应支付的数额。

根据需求中我们可以清楚地知道表和字段的样子。可以很容易地设计出一个可用的数据库模型。然后再写一些 DAO Service 。不过在使用这种方法产生的应用程序中，数据库成为了关注的中心。数据库是实现细节，我们先不考虑数据库的模型。先考虑系统中有哪些对象，那些类和类之间的关系。

## 员工类设计
根据前三点需求很容易设计出 Employee 为父类有三个子类，如下图的类结构:

![](https://raw.githubusercontent.com/yupengj/image/master/xszf/emp.png)

**UML类图:**
> * 属性或方法前有 “-” 表示 private
> * 属性或方法前有 “+” 表示 public
> * 属性或方法前有 “#” 表示 protected
> * 空心箭头实线表示继承
> * 用实心菱形表示组合关系。意思是同生同死，只有两个类组合在一起才能使用，缺少一个另一个没有存在的意义。

## 支付方式
> 需求：雇员可以选择支付方式。可以选择把支付支票邮寄到他们指定的邮政地址；也可以把支票保存在出纳人员那里随时支取；或者要求将薪水直接存入他们指定的银行账户。

### 抽象 PaymentMethod 接口
- PaymentMethod 接口有 pay(Double mony) 方法，有三个子类
- MailMethod 邮寄支票方式
- BankMethod 打到银行卡方式
- HoldMethod 到财务自取方式

### 类图
![](https://raw.githubusercontent.com/yupengj/image/master/xszf/emp-zffs.png)

> 空心箭头，虚线表示实现接口


## 需求变化

**钟点工可以改成销售员工，可以把固定带薪员工改成销售员工**

- 看上面的类图，子类是不能相互转化的。
- 在 Employee 中加入一个type字段，代表不同类型的员工，那时 TimeCard 类和 SalseReceipt 类怎么办？都组合到 Employee 类上？可是固定工资员工是没有这两个类的。小时工也没有销售凭条。这样不合理？

我们再仔细阅读需求，可以提取出下面这段话：
> 一些雇员按小时支付，一些雇员按月薪支付，一些雇员按一定数量的酬金支付。

**所以重点是员工的支付策略，而不是员工类型。所以发现变化封装变化继续抽象**

## 支付策略

### 抽象 PaymentClassification 接口
- 把 Employee 的子类化去掉，在 Employee 类中注入一个 PaymentClassification 接口，有三个子类
- HourtyClassification 按小时支付类型
- SalaredClassification 固定薪资支付类型
- CommissioneClassification 按销售凭条支付类型

### 类图 
![](https://raw.githubusercontent.com/yupengj/image/master/xszf/emp-zffs-zfcl.png)

## 薪水计算

现在支付策略，支付方式设计完了，来看一下员工的薪水怎么计算？应该放在那里计算？
放在PaymentClassification的派生类里面是合理的，他们保存了计算薪水所需要的数据，并且每个派生类都知道自己薪水的计算具体方式。

但是我们再看一下需求：
> * 钟点工，根据工时卡，其中记录了日期以及工作小时数。如果每天工作超过8小时，那么超过的部分会按照正常报酬的1.5倍进行支付。**每周五对他们进行支付**
> * 月薪员工，根据月薪进行支付。**每个月的最后一个工作日对他们进行支付**
> * 销售员工，根据凭条，其中记录了销售日期和数量。**每隔一周的周五对他们进行支付**

那么根据上面的需求，写出的代码大概是这样子的

- 固定月薪职工薪水计算
```java
public class CommissionedClassification implements PaymentClassification {

    @Override
	public double calculatePay(Date date) {
		if(date 是否最后一个工作日){
			return 计算薪水
		}else{
		    return 不计算薪水;
		}
	}
}
```
- 小时工薪水计算
```java
public class HourlyClassification implements PaymentClassification {

    @Override
	public double calculatePay(Date date) {
		if(date 是否是周五){
			return 计算薪水
		}else{
		    return 不计算薪水;
		}
	}
}
```
- 销售员工薪水计算
```java
public class SalariedClassification implements PaymentClassification {

    @Override
	public double calculatePay(Date date) {
		if(date 是否为隔周周五){
			return 计算薪水
		}else{
		    return 不计算薪水;
		}
	}
}
```

看这样写有没有什么问题？

难道不会在某天改变策略，支付日期发生变化。假如改为“每周四支付”，“隔三周支付”，"每个月，月末的前一天支付"。
- 把支付薪水时间问题委托给PaymentClassification类，那么该类对于支付薪水时间方面的变化就不是封闭的。当支付时间发生变化时，必须要修改 if 里面的逻辑。这就违反了 OCP（开闭原则，对扩展开发对修改关闭）
- 同时PaymentClassification负责是否为支付日期的判断和支付薪水的计算。这也违反了 SRP（单一职责）


**所以怎么办？ 关键问题是继续对支付时间进行抽象**

## 支付时间抽象

### 抽象 PaymentSchedule 接口
- 接口 PaymentSchedule 定义两个方法，否支付日期的判断，和上一次支付日期，有三个子类
- WeeklySchedule 每周五支付
- BiWeeklySchedule 隔周五支付
- MothlySchedule 按月支付

### 类图 
![](https://raw.githubusercontent.com/yupengj/image/master/xszf/emp-zffs-zfcl-zfjh.png)

抽象出来 PaymentSchedule 之后看一下现在薪水怎么计算：

```java
Employee emp = new Employee();
if(emp.isPayDate(date)){
    emp.calculatePay(date);
}
```

### 分析上面类图

我们来看一下上面的类图：

- 3个接口都是典型的策略模式
- 变化互不影响，系统正交化
- 3个接口9个实现可以随意组合


## 扣除项
> 一些雇员会计入协会。在他们的雇员记录中有一个每周应付款项目字段。这些应付款项目必须要从他们的新税种扣除。协会有时也会针对单个协会成员征收服务费用。协会每周会提交这些服务费用，服务费用必须用从相应雇员的下一个月的薪水总额中扣除。

### 抽象 Affiliation 类
- Affiliation 定义服务费计算，一个员工可以加入多个协会。

### 类图 
![](https://raw.githubusercontent.com/yupengj/image/master/xszf/emp-zffs-zfcl-zfjh-kcx.png) 

## 计算薪水细节问题
- 钟点工：sum（每个时间卡 * 每小时报酬）
    - 对于钟点工，只计算过去一周的时间卡
- 销售：基本薪资 + sum（每个销售凭条的销售额 * rate）
    -  对于销售，只计算过去两周的销售凭条
- 普通员工：固定薪水
- 薪水支付程序每天运行一次，甚至一天运行多次，一定不能产生同一个人多次发薪水的错误。

## 抽象支付细节

### 抽象 Paycheck 类
- 需要把成功运行支付的记录保存下来，保证不会对同一个人重复支付
- 需要有一个时间段（Period）的概念，能标识过去一周、过去两周
- 收集参数

### 类图
![](https://raw.githubusercontent.com/yupengj/image/master/xszf/paycheck.png)

![](https://raw.githubusercontent.com/yupengj/image/master/xszf/emp-zffs-zfcl-zfjh-kcx-pc.png)


## 系统动态模型

- PayrollApplication 动态模型
```seq
Start->PayrollApplication: main
PayrollApplication->PayrollDatabase: findAllEmp
PayrollDatabase->PayrollApplication: list Employee
PayrollApplication->Employee: payDay(date)
Employee->PayrollApplication: For each employee
```

- 动态模型“今天不是发薪日”
```seq
Start->Employee: payDay(date)
Employee->PaymentSchedule: isPayDate(date)
PaymentSchedule->Employee: NO
```
- 动态模型“今天是发薪日”

```seq
Start->Employee: payDay(date)
Employee->PaymentSchedule: isPayDate(date)
PaymentSchedule->Employee: YES
Employee->PaymentClassification: amount = calculatePay(date)
Employee->Affiliation: deductions = calculateDeductions(date)
Employee->PaymentMethod: pay(amount-deductions)
Employee->Employee: savePaycheck
```

## 主要代码

- 计算薪水 PayrollApplication

```java
List<Employee> emps = payrollDatabase.findAllEmp();
for (Employee emp : emps) {
	if (emp.isPayDay(date)) {
		Paycheck pc = new Paycheck(emp.getPayPeriodStartDate(date), date);
		emp.payDay(pc);
		payrollDatabase.savePaycheck(pc);
	}
}
```

- Employee 类
```java
public class Employee {
	private String id;
	private String name;
	private String address;
	private List<Affiliation> affiliations = new ArrayList<>();
	private PaymentClassification classification;
	private PaymentSchedule schedule;
	private PaymentMethod paymentMethod;

	public boolean isPayDay(LocalDate d) {
		return this.schedule.isPayDate(d);
	}

	public LocalDate getPayPeriodStartDate(LocalDate d) {
		return this.schedule.getPayPeriodStartDate(d);
	}

	public void payDay(Paycheck pc) {
		double grossPay = classification.calculatePay(pc);
		double deductions = calculateDeductions(pc);
		double netPay = grossPay - deductions;
		pc.setGrossPay(grossPay);
		pc.setDeductions(deductions);
		pc.setNetPay(netPay);
		pc.setEmpId(id);
		paymentMethod.pay(pc);
	}

	protected double calculateDeductions(Paycheck pc) {
		double deductions = 0.0;
		for (Affiliation affiliation : affiliations) {
			deductions += affiliation.calculateDeductions(pc);
		}
		return deductions;
	}
}
```
### 支付计划 PaymentSchedule
- 实现类 MothlySchedule，每个月最后一个工作日支付
```java
public class MothlySchedule implements PaymentSchedule{

	@Override
	public boolean isPayDate(LocalDate date) {		
		return DateUtil.isLastDayOfMonth(date);
	}

	@Override
	public LocalDate getPayPeriodStartDate(LocalDate payPeriodEndDate) {		
		return DateUtil.getFirstDay(payPeriodEndDate);
	}
}
```
- 实现类 WeeklySchedule， 每周五支付
```java
public class WeeklySchedule implements PaymentSchedule {

	@Override
	public boolean isPayDate(LocalDate date) {
		return DateUtil.isFriday(date);
	}

	@Override
	public LocalDate getPayPeriodStartDate(LocalDate payPeriodEndDate) {
		return DateUtil.add(payPeriodEndDate, -6);
	}
}
```
- 实现类 BiweeklySchedule， 隔周周五支付
``` java
public class BiweeklySchedule implements PaymentSchedule {
	LocalDate firstPayableFriday = LocalDate.of(2017, 7, 6);

	@Override
	public boolean isPayDate(LocalDate date) {
		long interval = DateUtil.getDaysBetween(firstPayableFriday, date);
		return interval % 14 == 0;
	}

	@Override
	public LocalDate getPayPeriodStartDate(LocalDate payPeriodEndDate) {
		return DateUtil.add(payPeriodEndDate, -13);
	}
}
```
### 支付策略 PaymentClassification
-  实现类 HourlyClassification， 小时工薪水计算
```java
public class HourlyClassification implements PaymentClassification {
	private double hourlyRate;
	private List<TimeCard> timeCards = new ArrayList<>();

	public void addTimeCards(TimeCard timeCard) {
		this.timeCards.add(timeCard);
	}

	@Override
	public double calculatePay(Paycheck paycheck) {
		double totalPay = 0;
		for (TimeCard tc : timeCards) {
			if (DateUtil.between(tc.getDate(), 
			    paycheck.getPayPeriodStart(), paycheck.getPayPeriodEnd())) {
				totalPay += calculatePayForTimeCard(tc);
			}
		}
		return totalPay;
	}

	private double calculatePayForTimeCard(TimeCard tc) {
		double hours = tc.getHours();
		if (hours > 8) {
			return 8 * hourlyRate + (hours - 8) * hourlyRate * 1.5;
		} else {
			return 8 * hourlyRate;
		}
	}
}
```
- 实现类 SalariedClassification， 固定月薪员工薪水计算
```java
public class SalariedClassification implements PaymentClassification {
	private double salary;

	public SalariedClassification(double salary) {
		this.salary = salary;
	}

	@Override
	public double calculatePay(Paycheck paycheck) {
		return salary;
	}
}
```
- 实现类 CommissionedClassification， 固定月薪员工薪水计算
```java
public class CommissionedClassification implements PaymentClassification {
	private double rate;
	private double salary;
	private List<SalesReceipt> salesReceipt = new ArrayList<>();

	@Override
	public double calculatePay(Paycheck paycheck) {
		double commission = 0.0;
		for (SalesReceipt sr : salesReceipt) {
			if (DateUtil.between(sr.getDate(), 
			    paycheck.getPayPeriodStart(), paycheck.getPayPeriodEnd())) {
				commission += sr.getAmount() * rate;
			}
		}
		return salary + commission;
	}

	public void addSalesReceipt(SalesReceipt salesReceipt) {
		this.salesReceipt.add(salesReceipt);
	}
}
```
### 支付方式 PaymentMethod
-  实现类 BankMethod， 直接打到银行卡上
```java
public class BankMethod implements PaymentMethod {
	private String bank;
	private double account;

	public BankMethod(String bank, double account) {
		super();
		this.bank = bank;
		this.account = account;
	}

	@Override
	public void pay(Paycheck paycheck) {
		System.out.println("向银行卡 " + bank + " 支付" + account + "元");
	}
}
```

- 实现类 HoldMethod， 财务自取
```java
public class HoldMethod implements PaymentMethod {

	@Override
	public void pay(Paycheck paycheck) {
		System.out.println("到财务自取");
	}
}
```
- 实现类 MailMethod， 支票邮寄到指定地址
```java
public class MailMethod implements PaymentMethod {
	private String address;
	@Override
	public void pay(Paycheck paycheck) {
		System.out.println("向" + address + " 发送支票");
	}
}
```
### 会费计算 Affiliation 
- 实现类 UnionAffiliation
```java
private String memberId;
	private double weeklyBue;
	private List<ServiceChange> serviceChanges = new ArrayList<>();

	// int fridays = 统计在 paycheck 开始时间和结束时间有多少周五
	// totalDue = fridays * weeklyBue;
	// totalChange = 计算 paycheck 开始时间和结束时间之间的 ServiceChange
	// deduction = totalDue + totalChange
	@Override
	public double calculateDeductions(Paycheck paycheck) {
		int fridays = DateUtil.betweenOnFriday(paycheck.getPayPeriodStart(), paycheck.getPayPeriodEnd());
		double totalDue = fridays * weeklyBue;
		double totalChange = 0D;
		for (ServiceChange sc : serviceChanges) {
			if (DateUtil.between(sc.getDate(), paycheck.getPayPeriodStart(), paycheck.getPayPeriodEnd())) {
				totalChange += sc.getAmout();
			}
		}
		return totalDue + totalChange;
	}
}
```

## 目的
一定要掌握常用的设计原则，如单一职责，开闭原则，面向接口编程，优先使用聚合而不是继承。组重要一点，抽象。