package com.tianma.java.controller.stream;

import com.tianma.java.controller.java2.Employee;
import com.tianma.java.controller.java2.EmployeeData;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
 * Stream的终止操作
 * 
 * 
 */
public class StreamAPITest2 {
	//1-匹配与查找

	public void test1(){
		List<Employee> employees = EmployeeData.getEmployees();
	
	//  allMatch(Predicate p)——检查是否匹配所有元素。
		//练习：是否所有的员工的年龄都大于18
		boolean allMatch = employees.stream().allMatch(e -> e.getAge() > 18);
		System.out.println(allMatch);
	//	anyMatch(Predicate p)——检查是否至少匹配一个元素。
		//练习：是否存在员工的工资大于 10000
		boolean anyMatch = employees.stream().anyMatch(e -> e.getSalary() > 10000);
		System.out.println(anyMatch);
		
	//	noneMatch(Predicate p)——检查是否没有匹配的元素。
		//练习：是否存在员工姓“雷”
		boolean noneMatch = employees.stream().noneMatch(e -> e.getName().contains("雷"));
		System.out.println(noneMatch);
		
	//	findFirst——返回第一个元素
		Optional<Employee> emp = employees.stream().findFirst();
		System.out.println(emp.get());
	//	findAny——返回当前流中的任意元素
		Optional<Employee> emp1 = employees.parallelStream().findAny();
		System.out.println(emp1.get());
	//	count——返回流中元素的总个数
		long count = employees.stream().count();
		System.out.println(count);
	//	max(Comparator c)——返回流中最大值
		Optional<Employee> optional = employees.stream().max((e1,e2) -> Double.compare(e1.getSalary(), e2.getSalary()));
		System.out.println(optional.get());
	//	练习：返回最高的工资：
	//	min(Comparator c)——返回流中最小值
	//	练习：返回最低工资的员工
	//	forEach(Consumer c)——内部迭代
	}
	
	//2-归约

	public void test2(){
//		reduce(T identity, BinaryOperator)——可以将流中元素反复结合起来，得到一个值。返回 T  
//		练习1：计算1-10的自然数的和
		Integer[] arr = new Integer[]{1,2,3,4,5,6,7,8,9,10};
		Optional<Integer> optional = Arrays.stream(arr).reduce((num1,num2) -> num1 + num2);
		System.out.println(optional);
//		reduce(BinaryOperator) ——可以将流中元素反复结合起来，得到一个值。返回 Optional<T>
//		练习2：计算公司所有员工工资的总和
		List<Employee> employees = EmployeeData.getEmployees();
		Stream<Double> stream = employees.stream().map(e -> e.getSalary());
		Optional<Double> optional2 = stream.reduce((s1,s2) -> s1 + s2);
		System.out.println(optional2.get());
	}
	//3-收集

	public void test3(){
//		collect(Collector c)——将流转换为其他形式。接收一个 Collector接口的实现，用于给Stream中元素做汇总的方法
//		练习1：查找工资大于6000的员工，结果返回为一个List或Set
		
		List<Employee> employees = EmployeeData.getEmployees();
		List<Employee> list = employees.stream().filter(e -> e.getSalary() > 6000).collect(Collectors.toList());
		
		System.out.println();
		list.forEach(System.out::println);
	}
	

}
