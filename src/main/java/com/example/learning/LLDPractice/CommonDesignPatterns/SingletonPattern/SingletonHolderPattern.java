package com.example.learning.LLDPractice.CommonDesignPatterns.SingletonPattern;

public class SingletonHolderPattern {
    private SingletonHolderPattern() {

    }
    private static final int instance2 = 10;

    public static class SingletonInitializeVariable {
        private static final SingletonHolderPattern instance = new SingletonHolderPattern();
    }
    public static SingletonHolderPattern getInstance() {
        return SingletonInitializeVariable.instance;
    }

    public static void  print() {
        System.out.println("ddkd");
    }

    static {
        System.out.println("static");

    }


    public static void main(String[] args) {
        SingletonHolderPattern s ;
        SingletonHolderPattern.print();
    }
}


