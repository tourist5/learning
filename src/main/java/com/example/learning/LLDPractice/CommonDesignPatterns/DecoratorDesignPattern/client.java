package com.example.learning.LLDPractice.CommonDesignPatterns.DecoratorDesignPattern;

public class client {
    public static void main(String[] args) {
        Coffee coffee = new Espresso();
        System.out.println(coffee.cost());
        System.out.println(coffee.description());

        Coffee coffee1 = new Capachino();
        System.out.println(coffee1.description());
        System.out.println(coffee1.cost());

        Coffee capachinoWithMilk = new MilkDecorator(new Capachino());
        Coffee capachinoWithMilkAndHoney = new HoneyDecorator(new MilkDecorator(new Capachino()));
        System.out.println(capachinoWithMilkAndHoney.description());
        System.out.println(capachinoWithMilkAndHoney.cost());

        System.out.println(capachinoWithMilk.description());
        System.out.println(capachinoWithMilk.cost());

        Coffee espressoWithMilk = new MilkDecorator(new Espresso());
        System.out.println(espressoWithMilk.description());
        System.out.println(espressoWithMilk.cost());

        Coffee espressoWithMilkAndHoney = new HoneyDecorator(new MilkDecorator(new Espresso()));
        System.out.println(espressoWithMilkAndHoney.description());
        System.out.println(espressoWithMilkAndHoney.cost());
    }
}
