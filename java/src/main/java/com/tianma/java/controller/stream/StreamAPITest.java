package com.tianma.java.controller.stream;

import com.tianma.java.controller.java2.Employee;
import com.tianma.java.controller.java2.EmployeeData;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/*
 * Stream API
 * 1.
 * Stream API：面向cpu的，对内存中集合、数组中的数据进行运算
 * 
 * 集合、数组：面向内存的，对多个数据进行存储
 * 
 * 2.Stream API的特点：
 * ①Stream 自己不会存储元素。
 * ②Stream 不会改变源对象。相反，他们会返回一个持有结果的新Stream。
 * ③Stream 操作是延迟执行的。这意味着他们会等到需要结果的时候才执行。
 * 
 * 3.Stream的执行过程：
 * 第一步：创建 Stream
 * 第二步：一系列的中间操作
 * 第三步：终止操作
 * 
 * 注意：一旦执行终止操作，就执行中间操作链，并产生结果。之后，不会再被使用
 * 
 * 
 * 如下的代码测试Stream API的实例化
 */
public class StreamAPITest {
	public static void main(String[] args) {

		test1();
	}
	//调用集合接口的stream()
	public static void test1(){
		
		List<Employee> list = EmployeeData.getEmployees();
		Stream<Employee> stream = list.stream();
		System.out.println(stream);
		
		List<Integer> list1 = Arrays.asList(1,2,3);
		Stream<Integer> stream1 = list1.stream();
		
	}
	//调用数组工具类Arrays的stream()
	public void test2(){
		
		int[] arr = new int[]{1,2,3,4,5};
		IntStream stream = Arrays.stream(arr);
		
		String[] arr1 = new String[]{"AA","BB","CC"};
		Stream<String> stream1 = Arrays.stream(arr1);
		
	}
	//通过Stream的of(T ... t)
	public void test3(){
		
		Stream<Integer> stream = Stream.of(1,2,3,4);
		
		Employee emp1 = new Employee(1001, "马化腾", 34, 6000.38);
		Employee emp2 = new Employee(1005, "李彦宏", 65, 5555.32);
		Stream<Employee> stream1 = Stream.of(emp1,emp2);
		
	}
	
	//创建无限流

	public void test4(){
//		迭代
//		public static<T> Stream<T> iterate(final T seed, final UnaryOperator<T> f) 
//		Stream.iterate(1,x -> x + 1).forEach(x -> System.out.println(x));
		Stream.iterate(1,x -> x + 1).limit(10).forEach(System.out::println);
		
		
//		生成
//		public static<T> Stream<T> generate(Supplier<T> s) 
		Stream.generate(Math :: random).limit(5).forEach(System.out::println);
		
		
	}
}
