package com.example.learning.LLDPractice.CommonDesignPatterns.DecoratorDesignPattern;

public class HoneyDecorator implements Coffee{
    private final Coffee coffee;

    HoneyDecorator(Coffee coffee) {
        this.coffee = coffee;
    }

    @Override
    public String description() {
        return coffee.description() + " Honey";
    }

    @Override
    public Integer cost() {
        return coffee.cost() + 30;
    }
}
