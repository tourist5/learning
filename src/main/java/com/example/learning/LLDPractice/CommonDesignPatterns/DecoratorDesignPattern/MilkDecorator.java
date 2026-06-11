package com.example.learning.LLDPractice.CommonDesignPatterns.DecoratorDesignPattern;

public class MilkDecorator implements Coffee{
    private final Coffee coffee;

    MilkDecorator(Coffee coffee) {
        this.coffee = coffee;
    }

    @Override
    public String description() {
        return coffee.description() + " Milk";
    }

    @Override
    public Integer cost() {
        return coffee.cost() + 20;
    }
}
