package com.example.learning.DSA.String;

public class Solution {
    public static void main(String[] args) {

    }
    public String processStr(String s) {
        int len = s.length();
        StringBuilder res = new StringBuilder();
        for(int i=0;i<len;i++) {
            char currentChar = s.charAt(i);
            if(currentChar=='*') {
                if(!res.isEmpty()) {
                    res.deleteCharAt(res.length()-1);
                }
             } else if(currentChar=='#') {
                res.append(res);
            } else if(currentChar=='%') {
                res.reverse();
            } else {
                res.append(currentChar);
            }
        }
        return res.toString();
    }
}
