package com.example.learning.LLDPractice.CommonDesignPatterns.FactoryPattern;

import com.example.learning.LLDPractice.CommonDesignPatterns.BuilderPattern.User;

public class Drive {
    public static void main(String[] args) throws Exception {
//        System.out.println(FactoryPattern.giveObject("Car"));
        User user = new User.BuilderUser()
                .setAge(10)
                .setEmail("s")
                .setName("abhi")
                .build();
        System.out.println(user.toString());
    }
}
