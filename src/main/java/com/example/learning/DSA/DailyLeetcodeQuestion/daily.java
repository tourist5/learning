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
        int totalMinute = hour*60+minutes;
        double hourAngleCovered = totalMinute*0.5;
        double minuteAngleCovered = minutes*6;
        return Math.min(Math.abs(hourAngleCovered-minuteAngleCovered),360-Math.abs(hourAngleCovered-minuteAngleCovered));
    }

}
