package org.example.junit5.introdution;

public class SimpleCalculator {

    // For showing that a new instance is created for each test
    public SimpleCalculator() {
        System.out.println("New calculator instance created");
    }


    public int add (int numberA, int numberB) {
        return numberA + numberB;

    }

    public float divide (int numberA, int numberB) {
        return (float) numberA / numberB;

    }
}
