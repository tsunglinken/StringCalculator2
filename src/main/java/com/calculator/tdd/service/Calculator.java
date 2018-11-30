package com.calculator.tdd.service;

import com.calculator.tdd.exceptions.CalculatorException;
import com.calculator.tdd.model.ClientData;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

public class Calculator {
    public int add(String number) throws CalculatorException {
        ClientData clientData = new ClientData(number);
        List<Integer> numbers = clientData.getNumbers();
        List<Integer> negativeNumbers = clientData.getNegativeNumbers(numbers);
        if (negativeNumbers.size() > 0) {
            throw new CalculatorException(String.format("negatives not allowed %s", negativeNumbers.toString()));
        }
        return numbers.stream().mapToInt(num -> num).sum();
    }



}
