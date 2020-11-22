package com.tianma.java.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Consumer;

/*
 * Lambda表达式的语法使用
 * 1.举例：(o1,o2) -> Integer.compare(o1, o2)
 * 2. 格式： (变量) -> {执行语句}
 *     -> : 箭头操作符 或 lambda操作符
 *     ->左边：lambda形参列表
 *     ->右边：labmda体
 *     
 * 3. 分为六种情况说明
 * 
 *  总结：1.针对于创建接口的匿名实现类的情境，我们可以使用Lambda表达式
 *      2. ->的左边：lambda形参列表的变量类型可以省略。 
 *      				特别的：如果形参列表只有一个变量，此变量所在的一对()也可以省略。
 * 		   ->的右边：labmda体使用一对{}包裹要处理的代码。 
 * 						特别的：如果{}内的执行语句只有一行，则可以省略一对{}
 * 							   如果方法有返回值，且作为lambda体的唯一的执行语句。则此一对{}和return关键字省略
 * 
 * 4.说明：在java中lambda表达式作为接口的实现类的对象。
 * 
 * 5.lambda表达式的使用场景：
 * 	要求1：提供接口的匿名实现类的对象   
 * 	要求2：接口中只能声明唯一的一个抽象方法
 * 
 * 6.如上提到的接口，只能是函数式接口。
 *    什么是函数式接口？  只包含一个抽象方法的接口，称为函数式接口。
 */
public class LambdaTest1 {
	
	//语法格式一：无参，无返回值

	public void test1(){
		
		Runnable r = new Runnable(){

			@Override
			public void run() {
				System.out.println("誓言和谎言的区别是什么？");
			}
			
		};
		
		r.run();
		//*************************
		Runnable r1 = () -> { System.out.println("一个说的当真的，一个是听的当真了"); };
		r1.run();
		
	}
	
	//语法格式二：Lambda 需要一个参数，但是没有返回值。

	public void test2(){
		
		Consumer<String> con = new Consumer<String>(){

			@Override
			public void accept(String t) {
				System.out.println(t);
			}
			
		};
		
		con.accept("今天天气不错！");
		//*************************
		Consumer<String> con1 = (String t) -> {System.out.println(t);};
		con1.accept("明天是清明节");
	}
	
	//语法格式三：数据类型可以省略，因为可由编译器推断得出，称为“类型推断”

	public void test3(){
		Consumer<String> con1 = (String t) -> {System.out.println(t);};
		con1.accept("明天是清明节");
		
		//*************************
		Consumer<String> con2 = (t) -> {System.out.println(t);};
		con2.accept("明天是清明节");
	}
	

	public void test4(){
		int[] arr = {1,2,3};//类型推断
		
		ArrayList<String> list = new ArrayList<>();//类型推断
		
	}
	
	//语法格式四：Lambda 若只需要一个参数时，参数的小括号可以省略

	public void test5(){
		Consumer<String> con1 = (t) -> {System.out.println(t);};
		con1.accept("明天是清明节");
		
		//*************************
		Consumer<String> con2 = t -> {System.out.println(t);};
		con2.accept("明天是清明节");
		
	}
	
	//语法格式五：Lambda 需要两个或以上的参数，多条执行语句，并且可以有返回值

	public void test6(){
		Comparator<Integer> com = new Comparator<Integer>(){

			@Override
			public int compare(Integer o1, Integer o2) {
				System.out.println(o1);
				System.out.println(o2);
				return Integer.compare(o1,o2);
			}
			
			
			
		};
		
		System.out.println(com.compare(12, 2));
		//*************************
		Comparator<Integer> com1 = (o1,o2)->{
			System.out.println(o1);
			System.out.println(o2);
			return Integer.compare(o1,o2);
		};
		
		System.out.println(com1.compare(12, 21));
		
		
	}
	
	//语法格式六：当 Lambda 体只有一条语句时，return 与大括号若有，都可以省略

	public void test7(){
		Comparator<Integer> com1 = (o1,o2)->{return Integer.compare(o1,o2);};
		
		System.out.println(com1.compare(12, 21));
		
		//*************************
		Comparator<Integer> com2 = (o1,o2) -> Integer.compare(o1,o2);
		System.out.println(com2.compare(12, 2));
		
	}
	
}
