package com.example.learning.java;

import java.util.HashSet;
import java.util.Set;

public class BehaviourCheck {
    public static void main(String[] args) {
        Set<Integer> set = new HashSet<>();
        int[] nums = {1,2,3,4,5,5,5,5,5};
        for(int i:nums) {
            set.add(i);
        }
        System.out.println("Set is: " + set);

    }
}
