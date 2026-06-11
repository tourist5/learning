package com.example.learning.DSA;

import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;

import java.sql.BatchUpdateException;
import java.util.AbstractCollection;
import java.util.HashSet;
import java.util.Set;

public class interview {
    public static void main(String[] args) {
        try {
            int number = 1;
            if(number<0) {
                throw new Exception("Invalid number"+ number);
            }
            int res = factorialRecursion(number);
            System.out.println("Factorial of a number by recursion-> " + number + " is " + res);

            // factorial by iteration
            int iterativeRes = factorial(number);
            System.out.println("Factorial of a number by iteration -> " + number + " is " + iterativeRes);


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
// factorial
// 5 -> 1*2*3*4*5 = 120
// 1 -> 1 =1
    // -> 0=1
    //-2 -> not defined for negative numbers ->

  public static  int factorial(int number) {
            if(number == 0) {
                return 1;
            }
            int res = 1;
            for(int i=1;i<=number;i++) {
                res = res*i;
            }
            return res;
  }
  //o(n)
    //space complexity(o(1))

  public static  int factorialRecursion (int number) {
        if(number == 0 || number == 1) {
            return 1;
        }
//5*fac(4)-> fac(4) -> 4* fac(3) ->
        return factorialRecursion(number-1)*number;
  }

  //space complexity (O(N)
    //time complexity(O(n))

}










