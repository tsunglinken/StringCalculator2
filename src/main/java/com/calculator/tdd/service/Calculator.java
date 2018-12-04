package com.calculator.tdd.service;

import com.calculator.tdd.exceptions.CalculatorException;
import com.calculator.tdd.model.ClientData;
import java.util.List;

public class Calculator {
    public int add(String number) throws CalculatorException {
        ClientData clientData = new ClientData(number);
        List<Integer> numbers = clientData.getNumbers();
        List<Integer> negativeNumbers = clientData.getNegativeNumbers(numbers);
        if (!negativeNumbers.isEmpty()) {
            throw new CalculatorException(String.format("negatives not allowed %s", negativeNumbers.toString()));
        }
        return numbers.stream().mapToInt(num -> num).sum();
    }
}
