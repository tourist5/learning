package com.example.learning.LLDPractice.CommonDesignPatterns.DecoratorDesignPattern;

public class Capachino implements Coffee{

    @Override
    public String description() {
        return "Capachino";
    }

    @Override
    public Integer cost() {
        return 130;
    }
}
