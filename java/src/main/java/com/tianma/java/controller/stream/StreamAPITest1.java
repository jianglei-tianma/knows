package com.tianma.java.controller.stream;

import com.tianma.java.controller.java2.Employee;
import com.tianma.java.controller.java2.EmployeeData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/*
 * 测试Stream API的中间操作
 * 
 * 
 */
public class StreamAPITest1 {
	
	//1-筛选与切片

	public void test1(){
		List<Employee> employees = EmployeeData.getEmployees();
//		filter(Predicate p)——接收 Lambda ， 从流中排除某些元素。
		Stream<Employee> stream = employees.stream();
		stream.filter(e -> e.getSalary() > 6000).forEach(System.out::println);
		System.out.println();
		
//		limit(n)——截断流，使其元素不超过给定数量。
		
		employees.stream().limit(3).forEach(System.out::println);
		
		System.out.println();
//		skip(n) —— 跳过元素，返回一个扔掉了前 n 个元素的流。若流中元素不足 n 个，则返回一个空流。与 limit(n) 互补
		employees.stream().skip(3).forEach(System.out::println);
//		
		System.out.println();
//		distinct()——筛选，通过流所生成元素的 hashCode() 和 equals() 去除重复元素
		
		employees.add(new Employee(1009, "扎克伯格", 35, 2500.32));
		employees.add(new Employee(1009, "扎克伯格", 35, 2500.32));
		employees.add(new Employee(1009, "扎克伯格", 35, 2500.32));
		employees.add(new Employee(1009, "扎克伯格", 35, 2500.32));
		employees.add(new Employee(1009, "扎克伯格", 35, 2500.32));
		
		employees.stream().distinct().forEach(System.out::println);
		System.out.println();
		//小结：
		//1.得到Stream的实例以后，可以执行多个中间操作
		Stream<Employee> stream1 = employees.stream();
		stream1.filter(e -> e.getAge() > 30).limit(2).forEach(System.out::println);
		System.out.println();
		//2.一旦终止操作执行，Stream不能再进行其他操作了  --->推论：终止操作只能有一个
		stream1.limit(1).forEach(System.out::println);
	}
	
	//2-映射

	public void test2(){
		List<Employee> employees = EmployeeData.getEmployees();
//		map(Function f)——接收一个函数作为参数，将元素转换成其他形式或提取信息，该函数会被应用到每个元素上，并将其映射成一个新的元素。
//		练习：获取员工姓名长度大于3的员工。
		employees.stream().filter(e -> e.getName().length() > 3).forEach(System.out::println);
		
//		练习：获取员工姓名长度大于3的员工的姓名。
		Stream<Employee> stream = employees.stream();
		Stream<String> stream1 = stream.map(e -> e.getName());
		
		stream1.filter(name -> name.length() > 3).forEach(System.out::println);
		
		//拓展：获取员工工资大于6000的员工的工资
		employees.stream().map(e -> e.getSalary()).filter(salary -> salary > 6000).forEach(System.out::println);
		
		//***************对比map和flatMap********************************
		List<String> list = Arrays.asList("aa","bb","cc");
		Stream<Stream<Character>> stream2 = list.stream().map(StreamAPITest1::getChars);
		stream2.forEach(s -> {
			s.forEach(System.out::println);
		});
		System.out.println();
//		flatMap(Function f)——接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流。
		Stream<Character> stream3 = list.stream().flatMap(StreamAPITest1::getChars);
		stream3.forEach(System.out::println);
	}
	
	//将字符串中的字符存放到集合中，并返回集合的Stream
	public static Stream<Character> getChars(String str){//str:abc
		ArrayList<Character> list = new ArrayList<>();
	
		for(char c : str.toCharArray()){
			list.add(c);
		}
		
		return list.stream();
	}
	

	public void test3(){
		
		List list1 = new ArrayList();
		list1.add(1);
		list1.add(2);
		list1.add(3);
		
		List list2 = new ArrayList();
		list2.add(4);
		list2.add(5);
		list2.add(6);
		
		list1.add(list2);
//		list1.addAll(list2);
		System.out.println(list1);
		
	}
	
	//3-排序

	public void test4(){
		Integer[] arr = new Integer[]{32,43,523,65,72,3,7,7868,34,34};
//		sorted()——自然排序
		Stream<Integer> stream = Arrays.stream(arr);
		stream.sorted().forEach(System.out::println);
		
		System.out.println(Arrays.toString(arr));
		//因为Employee没有实现Comparable接口，所有执行失败
//		List<Employee> employees = EmployeeData.getEmployees();
//		employees.stream().sorted().forEach(System.out::println);
		
//		sorted(Comparator com)——定制排序
		System.out.println();
		List<Employee> employees = EmployeeData.getEmployees();
		employees.stream().sorted((e1,e2) -> Double.compare(e1.getSalary(), e2.getSalary())).forEach(System.out::println);

	}
	
}
