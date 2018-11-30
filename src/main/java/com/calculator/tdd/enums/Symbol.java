package com.calculator.tdd.enums;

public enum Symbol {
    DEFAULT_DELIMITER(","),
    DEFINED_SYMBOL("//");

    private String code;

    private Symbol(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
