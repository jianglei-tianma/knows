package com.tianma.java.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/*
 * 函数式接口的使用：
 * 
 * 4个核心的函数式接口
 * 
 * 消费型：Consumer<T>   	void accept(T t)
 * 供给型：Supplier<T>   	T get()
 * 函数型：Function<T,R> 	R apply(T t)
 * 断定型：Predicate<T>    boolean test(T t)
 * 
 * 
 */
public class LambdaTest2 {

	public static void main(String[] args) {


		test1();
	}

	public static void test1(){
		List<Integer> list = Arrays.asList(1,2,3);
		
		//旧的写法
		list.forEach(new Consumer<Integer>(){

			@Override
			public void accept(Integer t) {
				System.out.println(t);
			}
			
		});
		
		
		//新的写法
		list.forEach(t -> System.out.println(t));

		ArrayList<Object> list1 = new ArrayList<>();
		list1.add("1");
		list1.add("2");
		list1.add("3");
		list1.forEach(t -> System.out.println(t));

	}
	

	public void testHappyTime(){
		
		
		happyTime(500,new Consumer<Double>(){

			@Override
			public void accept(Double t) {
				System.out.println("今天学习太累了，晚上去天上人家泡澡,花了" + t);
			}
			
		});
		
		//*******************
		happyTime(500,t -> System.out.println("今天学习太累了，晚上去天上人家泡澡,花了" + t));


	}
	
	
	public void happyTime(double money,Consumer<Double> con){
		con.accept(money);
	}
	

	public void testFilterList(){
		List<String> list = Arrays.asList("北京","南京","东京","西京","天津","普京");
		List<String> filterList = filterList(list,str -> str.contains("京"));
		for(String s : filterList){
			System.out.println(s);
		}
	}
	
	public List<String> filterList(List<String> list,Predicate<String> p){
		
		ArrayList<String> filterList = new ArrayList<>();
		
		for(String s : list){
			if(p.test(s)){
				filterList.add(s);
			}
		}
		
		return filterList;
	}
	
}
