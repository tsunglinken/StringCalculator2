package com.calculator.tdd.model;

import com.calculator.tdd.enums.Symbol;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
        return String.format("%s|%n", this.delimiter);
    }

    public List<Integer> getNumbers() {
        if(number.contains(Symbol.DEFINED_SYMBOL.getCode())){
            customDelimiterSyntaxAndNumber();
        }

        return Arrays.asList(getNumber().split(getDelimiter()))
                .stream()
                .map(num -> StringUtils.isEmpty(num) ? 0 : Integer.parseInt(num))
                .filter(num -> num <= 1000)
                .collect(Collectors.toList());
    }

    private void customDelimiterSyntaxAndNumber() {
        Matcher matcher = regexMatches("//(.*)\\s(.*)", this.number);
        this.delimiter = matcher.group(1).replaceAll("(\\]\\[)", "\\|");
        this.number = matcher.group(2);
    }

    private Matcher regexMatches(String regex, String number) {
        Matcher m = Pattern.compile(regex).matcher(number);
        m.matches();
        return m;
    }

    public List<Integer> getNegativeNumbers(List<Integer> numbers) {
        return numbers.stream().filter(num -> num < 0).collect(Collectors.toList());
    }
}
