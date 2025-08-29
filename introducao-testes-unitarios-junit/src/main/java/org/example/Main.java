package org.example;

import org.example.junit5.introdution.Calculator;

public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        Integer sum = calculator.getAdd(1, 2);
        System.out.println(sum);

    }
}