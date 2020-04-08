package noth.li;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Stream;

import org.junit.Test;

public class TestLambda {

	//原来的匿名内部类
	@Test
	public void test1() {
		Comparator<Integer> com = new Comparator<Integer>(){
			@Override
			public int compare(Integer o1, Integer o2) {
				return Integer.compare(o1, o2);
			}
		};
		
		TreeSet<Integer> ts = new TreeSet<Integer>(com);
	}
	
	//Lambda 表达式
	@Test
	public void test2() {
		Comparator<Integer> com = (x,y) -> Integer.compare(x, y);
		TreeSet<Integer> ts = new TreeSet<Integer>(com);
	}
	
	
	List<Employee> employees = Arrays.asList(
			new Employee("张三", 18, 9999.99),
			new Employee("李四", 20, 8888.88),
			new Employee("王五", 59, 7777.77),
			new Employee("赵六", 24, 6666.66),
			new Employee("田七", 58, 5555.55));
	
	
	//需求:获取当前公司中员工年龄大于35的员工信息
	public List<Employee> filterEmployees(List<Employee> list){
		List<Employee> emps = new ArrayList<Employee>();
		for (Employee emp : list) {
			if(emp.getAge() > 35)
				emps.add(emp);
		}
		return emps;
	}
	
	@Test
	public void Test3() {
		List<Employee> list = filterEmployees(employees);
		for (Employee employee : list) {
			System.out.println(employee);
		}
	}
	
	//需求:获取当前公司中员工工资大于5000的员工信息
	public List<Employee> filterEmployees2(List<Employee> list){
		List<Employee> emps = new ArrayList<Employee>();
		for (Employee emp : list) {
			if(emp.getSalary() >= 5000)
				emps.add(emp);
		}
		return emps;
	}

	
	//优化方式一:策略设计模式
	public List<Employee> filterEmployee(List<Employee> list,MyPredicate<Employee> mp){
		List<Employee> emps = new ArrayList<Employee>();
		for (Employee e : list) {
			if(mp.test(e))
				emps.add(e);
		}
		return emps;
	}
	
	@Test
	public void Test4() {
		List<Employee> list = filterEmployee(employees,new FilterEmployeeByAge());
		for (Employee employee : list) {
			System.out.println(employee);
		}

		System.out.println("=============================");
	
		List<Employee> list2 = filterEmployee(employees,new FilterEmployeeBySalary());
		for (Employee employee : list2) {
			System.out.println(employee);
		}

	}

	
	
	//优化方式二:匿名内部类
	@Test
	public void test5() {
		List<Employee> list = filterEmployee(employees,new MyPredicate<Employee>() {
			@Override
			public boolean test(Employee t) {
				return t.getSalary()<=6000;
			}
		});
		for (Employee employee : list) {
			System.out.println(employee);
		}
	}
	
	
	//优化方式三:Lambda表达式
	@Test
	public void test6() {
		List<Employee> list = filterEmployee(employees,(e) -> e.getSalary()<=7000);
		for (Employee e : list) {
			System.out.println(e);
		}
	}
	
	//优化方式四:Stream API
	@Test
	public void test7() {
		employees.stream()
				.filter((e) -> e.getSalary() <= 8000)
				.limit(2)
				.forEach(System.out::println);
		
		System.out.println("-----------------------------");
		
		employees.stream()
			.map(Employee::getName)
			.forEach(System.out::println);
		
	}
}
