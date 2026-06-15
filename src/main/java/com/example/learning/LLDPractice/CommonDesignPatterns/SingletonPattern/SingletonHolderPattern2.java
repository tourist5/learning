package com.example.learning.LLDPractice.CommonDesignPatterns.SingletonPattern;

public class SingletonHolderPattern2 {
    private SingletonHolderPattern2() {

    }
    private static final SingletonHolderPattern2 instance = new SingletonHolderPattern2();

    public static SingletonHolderPattern2 getInstance() {
        return instance;
    }

    public static void  print() {
        System.out.println("ddkd");;
    }

    public static void main(String[] args) {
        SingletonHolderPattern2 s ;
        SingletonHolderPattern2.print();
    }
}


