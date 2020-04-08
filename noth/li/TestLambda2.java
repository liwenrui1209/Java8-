package noth.li;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import org.junit.Test;

/*
 * 一、Lambda表达式的基础语法:
 * 		Java8中引入了一个新的操作符"->" 该操作符称为箭头操作符或者Lambda操作符
 * 			箭头操作符将Lambda表达式拆分成两部分：
 * 				左侧：Lambda表达式的参数列表
 * 				右侧：Lambda表达式中所需要执行的功能,即Lambda体
 * 
 *  	语法格式一：无参数，无返回值 
 *  		() -> System.out.println("Hello Lambda!");
 *  
 *  	语法格式二：有一个参数，无返回值
 *  		(x) -> System.out.println(x);
 *  
 *  	语法格式三：若只有一个参数，小括号可以省略不写 
 *  		x -> System.out.println(x);
 *  
 *  	语法格式四：有两个或两个以上的参数，有返回值，并且Lambda体中有多条语句
 *  		Comparator<Integer> com = (x,y) -> {
				System.out.println("函数式接口");
				return Integer.compare(x, y);
			};
			
		语法格式五：有两个或两个以上的参数，有返回值，并且Lambda体中只有一条语句，return和大括号都可以省略不写
				Comparator<Integer> com1 = (x,y) -> Integer.compare(x, y);
				
		语法格式六：Lambda的参数列表的数据类型可以省略不写，因为JVM的编译器可以通过上下文推断出数据类型,即“类型推断”
			Comparator<Integer> com2 = (Integer x,Integer y) -> Integer.compare(x, y);

	左右遇一括号省
	左侧推断类型省
	能省则省
	
	
	二、Lambda 表达式需要"函数式接口"的支持
		函数式接口：接口中只有一个抽象方法的接口，称为函数式接口。
			可以使用@FunctionalInterface修饰，可以检查是否是函数式接口
			@FunctionalInterface修饰的接口必须只有一个抽象方法
 */
public class TestLambda2 {
	
	@Test
	public void test1() {
		int num = 0; //jdk 1.7以前必须是final jdk1.8 默认给你加上了final
		
		Runnable r =new Runnable() {
			@Override
			public void run() {
				System.out.println("Hello Lambda!" + num);
			}
		};
		r.run();
		
		System.out.println("=========================================");
		
		Runnable r1 = () -> System.out.println("Hello world!"+num);
		r1.run();
	}
	
	@Test
	public void test2() {
		Consumer<String> con = (x) -> System.out.println(x);
		con.accept("余子微！");
		
		Consumer<String> con1 = x -> System.out.println(x);
		con1.accept("余子微我的青春!");
	}
	
	@Test
	public void test3() {
		Comparator<Integer> com = (x,y) -> {
			System.out.println("函数式接口");
			return Integer.compare(x, y);
		};
		Comparator<Integer> com1 = (x,y) -> Integer.compare(x, y);
		Comparator<Integer> com2 = (Integer x,Integer y) -> Integer.compare(x, y);
	}
	
	@Test
	public void test4() {
//		String[] strs;
//		strs= {"a","b"};
		
		List<String> list = new ArrayList<>();
		
		show(new HashMap<>());
	}

	public void show(Map<String,Integer> map) {
		
	}
	
	//需求：对一个数进行运算
	@Test
	public void test5() {
		System.out.println(operation(12,x -> 1+x));
		System.out.println(operation(12,x -> {x+=9;return x-2;}));
		
		System.out.println(operation(100,x -> x*x));
		System.out.println(operation(200,y -> y+200));
		
	}
	
	public Integer operation(Integer num,MyFunction<Integer> mf) {
		return mf.getValue(num);
	}
}
