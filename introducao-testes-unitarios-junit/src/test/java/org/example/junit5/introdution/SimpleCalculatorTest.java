package org.example.junit5.introdution;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


class SimpleCalculatorTest {

    private SimpleCalculator calculator = new SimpleCalculator();
    private static int contador = 0; // show that the instance is recreated for each test

    @BeforeEach
    public void setup() {
        // In general, this method is used to set up the test environment, for example, initializing objects or setting up mock data
        // Came a database in a initial state before each test
        System.out.println("^^^");
    }

    @AfterEach
    public void tearDown() {
        // In general, this method is used to clean up resources used during the test, for example, closing database
        // connections or deleting temporary files
        System.out.println("vvv");
    }

    @BeforeAll
    public static void setupAll() {
        // In general, this method is used to set up resources that are expensive to create and can be shared across all tests,
        // for example, a database connection or a web server
        System.out.println("BeforeAll method called");
    }

    @AfterAll
    // In general, this method is used to clean up resources that were set up in the @BeforeAll method
    // for example, closing a database connection or stopping a web server
    public static void tearDownAll() {
        System.out.println("AfterAll method called");
    }

    @Test
    @DisplayName("Deve retornar 4 quando somar 2 + 2")
    void twoPlusTwoShouldEqualFour() {
        System.out.println("Test number: " + ++contador);
        // static method import, this way we don't need to use Assertions.assertEquals, what makes the code cleaner and legible
        assertEquals(4, calculator.add(2, 2), "2 + 2 should equal 4");
    }

    @Test
    @DisplayName("Verificando assertivas do JUnit5")
    void assertionsExamples() {
        System.out.println("Test number: " + ++contador);

        assertEquals("Casa", "Casa");
        assertNotEquals("Casa", "casa");
        assertTrue("casa".equalsIgnoreCase("CASA"));
        assertTrue("Casa".endsWith("sa"));
        assertTrue("Casa".startsWith("Ca"));

        List<String> s1 = new ArrayList<>();
        List<String> s2 = new ArrayList<>();
        List<String> s3 = null;

        assertEquals(s1, s2); // same content
        assertSame(s1, s1); // same reference
        //assertEquals(s1, s3); // failed becausse s3 is null and s1 is an empty list
        assertNull(s3);
        assertNotNull(s1);

    }

    // Equivalence Partitioning
    // Equivalence classes are a way of dividing input data into valid and invalid partitions
    // Valid partitions: positive numbers, negative numbers, zero
    // Invalid partitions: non-numeric values (e.g., strings, special characters)
    // Test cases should cover at least one value from each partition
    // For example, testing the addition of two positive numbers, two negative numbers, and zero

    @Test
    @DisplayName("Deve retornar um valor inteiro na divisão")
    void shouldReturnAnIntegerValueWhenDividing() {
        System.out.println("Test number: " + ++contador);

        float result = calculator.divide(4, 2);
        assertEquals(2, result);
    }

    @Test
    @DisplayName("Deve retornar um valor negativo na divisão")
    void shouldReturnAnNegativeValueWhenDividing() {
        System.out.println("Test number: " + ++contador);

        float result = calculator.divide(4, -2);
        assertEquals(-2, result);
    }

    @Test
    @DisplayName("Deve retornar um número decimal na divisão")
    void shouldReturnADecimalValueWhenDividing() {
        System.out.println("Test number: " + ++contador);

        float result = calculator.divide(10, 3);
        assertEquals(3.33, result, 0.01); // verified until 2 decimal places, error margin of 0.01
    }

    @Test
    @DisplayName("Deve lançar exceção na divisão por zero")
        // Verify that dividing by zero throws an ArithmeticException
    void shouldReturnInfinityWhenDividingByZero_JUnit5() {
        ArithmeticException exception = assertThrows(ArithmeticException.class, () -> {
            float result = 10 / 0;

        });
        assertEquals("/ by zero", exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção na divisão por zero")
        // Verify that dividing by zero throws an ArithmeticException
    void shouldReturnInfinityWhenDividingByZero_JUnit4() {
        try {
            float result = 10 / 0;
            Assertions.fail("Deveria ser lançada uma ArithmeticException");
        } catch (ArithmeticException e) {
            assertEquals("/ by zero", e.getMessage());
        }
    }

}