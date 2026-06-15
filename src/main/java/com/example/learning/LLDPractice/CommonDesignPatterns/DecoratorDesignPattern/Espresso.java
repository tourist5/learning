package com.example.learning.LLDPractice.CommonDesignPatterns.DecoratorDesignPattern;

public class Espresso implements Coffee{

    @Override
    public String description() {
        return "Espresso ";
    }

    @Override
    public Integer cost() {
        return 120;
    }
}
