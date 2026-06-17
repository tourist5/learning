package com.example.learning.DSA.String;

public class Solution {
    public static void main(String[] args) {

    }

    public String processStr(String s) {
        int len = s.length();
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < len; i++) {
            char currentChar = s.charAt(i);
            if (currentChar == '*') {
                if (!res.isEmpty()) {
                    res.deleteCharAt(res.length() - 1);
                }
            } else if (currentChar == '#') {
                res.append(res);
            } else if (currentChar == '%') {
                res.reverse();
            } else {
                res.append(currentChar);
            }
        }
        return res.toString();
    }

    public char processStrPart2(String s, long k) {
        int length = s.length();
        long[] len = new long[length];
        len[0] = 'a'<=s.charAt(0) && s.charAt(0) <='z'?1:0;
        for(int i=1;i<length;i++) {
            long prevLen = len[i-1];
            char c = s.charAt(i);
            if(c=='#') {
                len[i] = prevLen*2;
            } else if(c=='%') {
                len[i] = prevLen;
            } else if(c=='*') {
                len[i] = Math.max(prevLen-1,0);
            } else {
                len[i] = prevLen+1;
            }
        }


        if(k<0 || len[length-1]==0 || k>=len[length-1]) {
            return '.';
        }

        for(int i=length-1;i>=0;i--) {
            char currentCharacter = s.charAt(i);
            long currentLengthOfResultedString = len[i];
            if('a'<=currentCharacter && currentCharacter<='z' && currentLengthOfResultedString-1==k) {
                return currentCharacter;
            } else if(currentCharacter=='#') {
                long prevLength = currentLengthOfResultedString/2;
                if(k>=prevLength) {
                    k= k-prevLength;
                }
            } else if(currentCharacter=='%') {
                k=currentLengthOfResultedString-1-k;
            }
        }

        return '.';

    }

}
