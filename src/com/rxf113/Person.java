package com.rxf113;

/**
 * @author rxf113
 */
@CreatePrint
public class Person {
    private int age;
    private String name;
	public void print() {
        System.out.println(" I am the method created by cusProcessor !!! ");
        System.out.printf("age: %d   name: %s", age, name);
    }
}
