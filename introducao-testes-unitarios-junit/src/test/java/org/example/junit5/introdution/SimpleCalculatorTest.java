package org.example.junit5.introdution;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;


class SimpleCalculatorTest {

    @Test
    @DisplayName("Deve retornar 4 quando somar 2 + 2")
     void twoPlusTwoShouldEqualFour() {
        var calculator = new SimpleCalculator();
        // static method import, this way we don't need to use Assertions.assertEquals, what makes the code cleaner and legible
        assertEquals(4, calculator.add(2, 2), "2 + 2 should equal 4");
    }

    @Test
    @DisplayName("Verificando assertivas do JUnit5")
    void assertionsExamples() {
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
        assertEquals(s1, s3); // failed becausse s3 is null and s1 is an empty list
        assertNull(s3);
        assertNotNull(s1);


    }
}