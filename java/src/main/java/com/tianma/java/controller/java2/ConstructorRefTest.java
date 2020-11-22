package com.tianma.java.controller.java2;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 一、构造器引用
 * 
 * 格式：类名 :: new
 * 使用情境：
 * 函数式接口的抽象方法的形参列表与构造器的形参列表一致，同时，抽象方法的返回值即为要创建的对象所属的类。
 *
 * 二、数组引用
 *
 *
 * Created by shkstart
 */
public class ConstructorRefTest {
	//构造器引用
    //Supplier中的T get()

    public void test1(){
    	//lambda表达式
    	Supplier<Employee> sup = () -> new Employee();
    	System.out.println(sup.get());
    	
    	//构造器引用
    	Supplier<Employee> sup1 = Employee :: new;
    	System.out.println(sup1.get());
	}

	//Function中的R apply(T t)

    public void test2(){
    	//lambda表达式
    	Function<Integer,Employee> fun = (id) -> new Employee(id);
    	Employee emp1 = fun.apply(12);
    	System.out.println(emp1);
    	
    	//构造器引用
    	Function<Integer,Employee> fun1 = Employee::new;
    	System.out.println(fun1.apply(13));
    	
    	
	}

	//BiFunction中的R apply(T t,U u)

    public void test3(){
    	BiFunction<Integer,String,Employee> biFun = (id,name) -> new Employee(id,name);
    	Employee emp1 = biFun.apply(12, "Tom");
    	System.out.println(emp1);
    	
    	//构造器引用
    	BiFunction<Integer,String,Employee> biFun1 = Employee::new;
    	Employee emp2 = biFun1.apply(13, "Tom");
    	System.out.println(emp2);
    	
	}

	//数组引用
    //Function中的R apply(T t)

    public void test4(){
    	Function<Integer,String[]> fun = length -> new String[length];
    	String[] arr1 = fun.apply(5);
    	System.out.println(Arrays.toString(arr1));
    	
    	//数组引用
    	Function<Integer,String[]> fun1 = String[] ::new;
    	String[] arr2 = fun1.apply(10);
    	System.out.println(Arrays.toString(arr2));
    	
	}
}
