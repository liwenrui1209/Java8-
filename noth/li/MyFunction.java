package com.atguigu.java8;

@FunctionalInterface
public interface MyFunction<T> {
	Integer getValue(Integer num);
}