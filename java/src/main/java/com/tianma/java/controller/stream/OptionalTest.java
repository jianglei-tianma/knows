package com.tianma.java.controller.stream;

import java.util.Optional;

public class OptionalTest {
	

	public void test1(){
		Optional<Object> optional = Optional.empty();
		if(!optional.isPresent()){
			System.out.println("没有数据");
		}
		//get():如果调用对象包含值，返回该值，否则抛异常
		System.out.println(optional.get());
		
	}
	

	public void test2(){
		
		String str = "hello";
		str = null;
		//of(T t):创建一个 Optional 实例，t必须非空；
		Optional<String> optional = Optional.of(str);
		
		System.out.println(optional.get());
		
	}
	

	public void test3(){
		String str = "hello";
		str = null;
		//ofNullable(T t):将t封装为一个Optional的实例。此时t可能为null的
		Optional<String> optional = Optional.ofNullable(str);
		//orElse(T t1):如果当前对象中的t非空，则此orElse()返回t对象。
		//如果当前对象中的t空值，则使用t1替换，并作为返回值
		String str1 = optional.orElse(new String("shanghai"));
		
		System.out.println(str1.charAt(1));//e
		
	}
}
