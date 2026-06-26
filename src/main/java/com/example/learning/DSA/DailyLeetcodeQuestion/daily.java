package com.example.learning.DSA.DailyLeetcodeQuestion;

import java.util.*;

public class daily {
    public static void main(String[] args) {
        int[][] edges = {
                {1, 2},
                {1, 3},
                {3, 4},
                {3, 5}
        };
        int ans = assignEdgeWeights(edges);
        int mod = 1000000007;
        System.out.println(pow(2, ans - 1, mod));
    }

    public static int assignEdgeWeights(int[][] edges) {
        Map<Integer, List<Integer>> keyIsNodeAndValueIsChildren = new HashMap<>();
        for (int[] edge : edges) {
            keyIsNodeAndValueIsChildren.computeIfAbsent(edge[0], list -> new ArrayList<>()).add(edge[1]);
            keyIsNodeAndValueIsChildren.computeIfAbsent(edge[1], list -> new ArrayList<>()).add(edge[0]);
        }
        System.out.println(keyIsNodeAndValueIsChildren);
        HashSet<Integer> visited = new HashSet<>();
        int finalRes = dfs(keyIsNodeAndValueIsChildren, visited, 1);
        System.out.println(finalRes);
        return finalRes;
    }

    public static int dfs(Map<Integer, List<Integer>> keyIsNodeAndValueIsChildren, HashSet<Integer> visited, int currentEle) {
        visited.add(currentEle);

        int maxDepth = 0;

        List<Integer> childs = keyIsNodeAndValueIsChildren.get(currentEle);
        for (Integer child : childs) {
            if (!visited.contains(child)) {
                int currentRes = 1 + dfs(keyIsNodeAndValueIsChildren, visited, child);
                maxDepth = Math.max(maxDepth, currentRes);
            }
        }
        return maxDepth;
    }

    static int pow(int x, int pow, int mod) {
        int res = 1;

        while (pow > 0) {
            if ((pow & 1) == 1) {
                res = (res * x) % mod;
            }

            x = (x * x) % mod;
            pow >>= 1;
        }

        return res;
    }

    public double angleClock(int hour, int minutes) {
        int totalMinute = hour * 60 + minutes;
        double hourAngleCovered = totalMinute * 0.5;
        double minuteAngleCovered = minutes * 6;
        return Math.min(Math.abs(hourAngleCovered - minuteAngleCovered), 360 - Math.abs(hourAngleCovered - minuteAngleCovered));
    }

    public int maxNumberOfBalloons(String text) {
        int len = text.length();
        int[] frequency = new int[26];
        for (int i = 0; i < len; i++) {
            char c = text.charAt(i);
            if (c == 'a' || c == 'b' || c == 'l' || c == 'n' || c == 'o') {
                int index = text.charAt(i) - 'a';
                frequency[index]++;
            }
        }
        int mi = Integer.MAX_VALUE;
        for (int i = 0; i < 26; i++) {
            char c = (char) (i + 'a');
            if (c == 'a' || c == 'b' || c == 'l' || c == 'n' || c == 'o') {
                int fr;
                if (c == 'l' || c == 'o') {
                    fr = frequency[i] / 2;
                } else {
                    fr = frequency[i];
                }
                mi = Math.min(mi, fr);
            }
        }

        return mi;

    }

    public int maxIceCream(int[] costs, int coins) {
        Arrays.sort(costs);
        if (coins < costs[0]) {
            return 0;
        }
        long preSum = 0;
        for (int i = 0; i < costs.length; i++) {
            preSum += costs[i];
            if (preSum > coins) {
                return i;
            }
        }
        return costs.length;
    }

    public long countMajoritySubarrays(int[] nums, int target) {
        int len = nums.length;
        for(int i=0;i<len;i++) {
            if(nums[i]==target) {
                nums[i]=1;
            } else {
                nums[i] = -1;
            }
        }

        int[] prefix = new int[len];
        prefix[0] = nums[0];

        for(int i=1;i<len;i++) {
            prefix[i] = prefix[i-1]+nums[i];
        }
        int shift = len;
        int[] frequency = new int[2*len+1];
        frequency[shift] = 1;
        long ans = 0;
        long valid = 0;
        int last = 0;

        for(int i=0;i<len;i++) {
            if(prefix[i]>last) {
                valid = valid+frequency[last+shift];
            } else {
                valid = valid-frequency[prefix[i]+shift];
            }
            ans+=valid;
            frequency[prefix[i]+shift]++;
            last = prefix[i];
        }

        return ans;
    }

}
