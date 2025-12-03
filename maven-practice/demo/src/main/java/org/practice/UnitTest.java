package org.practice;

import org.junit.jupiter.api.Test;

public class UnitTest {
    private final Calculator calculator = new Calculator();

    @Test
    void addition() {
        assertEquals(2, calculator.add(1, 1));
    }

    public static void main(String[] args) {
        UnitTest example = new UnitTest();
        example.addition();
    }
}
