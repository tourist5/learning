package com.example.learning.DSA.SegmentTreeDsa;

import java.math.BigInteger;
import java.util.Arrays;

public class dailySegmentTreeLeetcode {
    public static void main(String[] args) {

    }

    public int[] sumAndMultiply(String s, int[][] queries) {
        int n = s.length();
        int MOD = 1000000007;

        int[] prefixSum = new int[n];
        int[] nonZeroCount = new int[n];

        long[] prefixValue = new long[n];
        long[] pow10 = new long[n + 1];

        // powers of 10
        pow10[0] = 1;
        for (int i = 1; i <= n; i++) {
            pow10[i] = (pow10[i - 1] * 10) % MOD;
        }

        for (int i = 0; i < n; i++) {

            char ch = s.charAt(i);

            if (i > 0) {
                prefixSum[i] = prefixSum[i - 1];
                nonZeroCount[i] = nonZeroCount[i - 1];
                prefixValue[i] = prefixValue[i - 1];
            }

            if (ch != '0') {

                int digit = ch - '0';

                prefixSum[i] += digit;
                nonZeroCount[i]++;

                if (i == 0) {
                    prefixValue[i] = digit;
                } else {
                    prefixValue[i] = (prefixValue[i - 1] * 10 + digit) % MOD;
                }
            }
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {

            int L = queries[i][0];
            int R = queries[i][1];

            long sum = prefixSum[R];
            if (L > 0)
                sum -= prefixSum[L - 1];

            if (sum == 0) {
                ans[i] = 0;
                continue;
            }

            long number = prefixValue[R];

            if (L > 0) {

                int cnt = nonZeroCount[R] - nonZeroCount[L - 1];

                number = (number
                        - (prefixValue[L - 1] * pow10[cnt]) % MOD
                        + MOD) % MOD;
            }

            ans[i] = (int) ((number * (sum % MOD)) % MOD);
        }

        return ans;
    }

}