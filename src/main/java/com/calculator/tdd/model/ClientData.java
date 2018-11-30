package com.calculator.tdd.model;

import com.calculator.tdd.enums.Symbol;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ClientData {
    private String number;
    private String delimiter;

    public ClientData(String number) {
        this.number = StringUtils.isEmpty(number)?"0":number;
        this.delimiter = Symbol.DEFAULT_DELIMITER.getCode();
    }

    public String getNumber() {
        return number;
    }

    public String getDelimiter() {
        return String.format("%s|%n",this.delimiter);
    }

    public List<Integer> getNumbers() {
        if(number.contains(Symbol.DEFINED_SYMBOL.getCode())){
            this.delimiter = number.substring(length(Symbol.DEFINED_SYMBOL.getCode()), length(Symbol.DEFINED_SYMBOL.getCode())+1);
            this.number = number.substring(length(Symbol.DEFINED_SYMBOL.getCode())+2);
        }

        return Arrays.asList(getNumber().split(getDelimiter()))
                .stream()
                .map(num -> StringUtils.isEmpty(num) ? 0 : Integer.parseInt(num))
                .collect(Collectors.toList());
    }

    private int length(String code) {
        return code.length();
    }

    public List<Integer> getNegativeNumbers(List<Integer> numbers) {
        return numbers.stream().filter(num -> num < 0).collect(Collectors.toList());
    }
}
