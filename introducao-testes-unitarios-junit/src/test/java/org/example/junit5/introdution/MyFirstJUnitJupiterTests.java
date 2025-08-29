package org.example.junit5.introdution;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyFirstJUnitJupiterTests {

    private final Calculator calculator = new Calculator();
    // Indica que é uma classe de teste
    @Test
   void addition() {
        assertEquals(2, calculator.getAdd(1, 1));
    }
}
