package com.calculator.tdd;

import com.calculator.tdd.exceptions.CalculatorException;
import com.calculator.tdd.service.Calculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StringCalculatorTest {

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @Test
    public void empty_number() throws CalculatorException {
        addShouldBe(0, "");
    }

    @Test
    public void one_number_is_1() throws CalculatorException {
        addShouldBe(1, "1");
    }

    @Test
    public void one_number_is_2() throws CalculatorException {
        addShouldBe(2, "2");
    }

    @Test
    public void two_number_is_1_2() throws CalculatorException {
        addShouldBe(3, "1,2");
    }

    @Test
    public void more_than_two_number_is_1_2_3_4_5() throws CalculatorException {
        addShouldBe(15, "1,2,3,4,5");
    }

    @Test
    public void handle_new_line_between_numbers() throws CalculatorException {
        addShouldBe(6, "1\n2,3");
    }

    @Test
    public void support_different_delimiters() throws CalculatorException {
        addShouldBe(3, "//;\n1;2");
    }

    @Test
    public void one_nagative_number_throws_exception(){
        Exception exception = assertThrows(Exception.class, () -> calculator.add("-1"));
        assertEquals("negatives not allowed [-1]", exception.getMessage());
    }

    @Test
    public void more_than_one_nagative_number_throws_exception(){
        Exception exception = assertThrows(CalculatorException.class, () -> calculator.add("1\n-1,-2"));
        assertEquals("negatives not allowed [-1, -2]", exception.getMessage());
    }

    private void addShouldBe(int expected, String number) throws CalculatorException {
        assertEquals(expected, calculator.add(number));
    }
}
