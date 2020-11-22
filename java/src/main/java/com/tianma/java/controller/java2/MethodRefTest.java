package com.tianma.java.controller.java2;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 方法引用的使用
 * 
 * 1.使用前提：当要传递给Lambda体的操作，已经有实现的方法了，可以使用方法引用！
 * 
 * 
 * 2.在满足条件的情况下，我们可以使用方法引用替换lambda表达式的使用。
 *   方法引用，也是作为函数式接口的实例。即，方法引用也是对象
 *   
 * 3.使用分类
 * 情况一： 对象 :: 实例方法
 * 情况二： 类 :: 静态方法
 * 情况三：类 :: 实例方法 （有难度）
 * 
 * 
 * 4.能否使用方法引用的要求：函数式接口中抽象方法的形参列表和返回值类型 与 可以使用方法引用的方法的形参列表和返回值类型相同。
 *   适合于情况一、情况二。
 *   
 *   
 * Created by shkstart.
 */
public class MethodRefTest {

	// 情况一：对象 :: 实例方法
	//Consumer中的void accept(T t)
	//PrintStream中的void println(T t)

	public void test1() {
		//lambda表达式
		Consumer<String> con1 = t -> System.out.println(t);
		con1.accept("今天天气很晴朗");
		
		//方法引用
		PrintStream ps = System.out;
		Consumer<String> con2 = ps::println;
		con2.accept("今天天气不晴朗");
		
	}
	
	//Supplier中的T get()
	//Employee中的String getName()

	public void test2() {
		
		//lambda表达式
		Employee e = new Employee(1001, "马云", 40, 3200);
		Supplier<String> sup = () -> e.getName();
		System.out.println(sup.get());
		
		//方法引用
		Supplier<String> sup1 = e::getName;
		System.out.println(sup1.get());
		
	}

	// 情况二：类 :: 静态方法
	//Comparator中的int compare(T t1,T t2)
	//Integer中的int compare(T t1,T t2)

	public void test3() {
		//lambda表达式
		Comparator<Integer> com = (t1,t2) -> Integer.compare(t1, t2);
		System.out.println(com.compare(12, 21));
		
		//方法引用
		Comparator<Integer> com1 = Integer:: compare;
		System.out.println(com1.compare(12, 1));
	}
	
	//Function中的R apply(T t)
	//Math中的Long round(Double d)

	public void test4() {
		//lambda表达式
		Function<Double,Long> fun1 = d -> Math.round(d);
		System.out.println(fun1.apply(12.3));
		//方法引用
		Function<Double,Long> fun2 = Math::round;
		System.out.println(fun2.apply(23.2));
	}

	// 情况三：类 :: 实例方法 
	// Comparator中的int comapre(T t1,T t2)
	// String中的int t1.compareTo(t2)

	public void test5() {
		//lambda表达式
		Comparator<String> com1 = (s1,s2) -> s1.compareTo(s2);
		System.out.println(com1.compare("abc", "abd"));
		
		//方法引用
		Comparator<String> com2 = String::compareTo;
		System.out.println(com2.compare("abc", "abd"));
		
	}

	//BiPredicate中的boolean test(T t1, T t2);
	//String中的boolean t1.equals(t2)

	public void test6() {
		//lambda表达式
		BiPredicate<String,String> bi = (s1,s2) -> s1.equals(s2);
		System.out.println(bi.test("abc", "abd"));
		
		//方法引用
		BiPredicate<String,String> bi1 = String :: equals;
		System.out.println(bi1.test("abc", "abd"));
	}
	
	// Function中的R apply(T t)
	// Employee中的String getName();

	public void test7() {
		//lambda表达式
		Employee emp = new Employee(1001, "马云", 40, 3200);
		Function<Employee,String> fun = e -> e.getName();
		System.out.println(fun.apply(emp));
		
		//方法引用
		Function<Employee,String> fun1 = Employee::getName;
		System.out.println(fun1.apply(emp));
		
	}

}
