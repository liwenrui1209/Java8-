package com.atguigu.java8;

@FunctionalInterface
public interface MyPredicate<T> {
	boolean test(T t);
}