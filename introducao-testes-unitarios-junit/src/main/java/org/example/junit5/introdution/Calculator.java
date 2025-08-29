package org.example.junit5.introdution;

public class Calculator {
    int firstNumber;
    int secondNumber;
    int sum;

    public Calculator() {
        // No needed content
    }

    public int getFirstNumber() {
        return firstNumber;
    }

    public void setFirstNumber(int firstNumber) {
        this.firstNumber = firstNumber;
    }

    public int getSecondNumber() {
        return secondNumber;
    }

    public void setSecondNumber(int secondNumber) {
        this.secondNumber = secondNumber;
    }

    public int getAdd(int firstNumber, int secondNumber) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
        sum = firstNumber + secondNumber;
        return sum;
    }
}
