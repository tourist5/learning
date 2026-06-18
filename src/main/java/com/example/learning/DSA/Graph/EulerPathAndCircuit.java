package com.example.learning.DSA.Graph;

import java.util.*;

public class EulerPathAndCircuit {
    public static void main(String[] args) {

    }


    public int isEulerCircuit(int V, int[][] adj) {
        boolean[] visited = new boolean[V];
        dfs(0, visited, adj);
        for (int i = 0; i < V; i++) {
            if (!visited[i] && adj[i].length > 0) {
                return 0;
            }
        }

        int[] indegree = new int[V];
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < adj[i].length; j++) {
                int neigh = adj[i][j];
                indegree[neigh]++;
            }
        }

        int oddIndegreeCount = 0;
        for (int i = 0; i < V; i++) {
            if (indegree[i] % 2 != 0) {
                oddIndegreeCount++;
            }
        }

        if (oddIndegreeCount == 0) {
            return 2;
        }

        if (oddIndegreeCount == 2) {
            return 1;
        }

        return 0;
    }

    public void dfs(int src, boolean[] visited, int[][] adj) {
        visited[src] = true;
        for (int i = 0; i < adj[src].length; i++) {
            int nei = adj[src][i];
            if (!visited[nei]) {
                dfs(nei, visited, adj);
            }
        }
    }

    class Solution {

        public int[][] validArrangement(int[][] pairs) {
            Map<Integer, List<Integer>> keyIsNodeAndValueAreChilds = new HashMap<>();
            Map<Integer, Integer> keyIsNodeAndValueIsIndegreeCount = new HashMap<>();
            Map<Integer, Integer> keyIsNodeAndValueIsOutdegreeCount = new HashMap<>();

            for (int[] pair : pairs) {
                int parent = pair[0];
                int child = pair[1];

                keyIsNodeAndValueAreChilds
                        .computeIfAbsent(parent, list -> new ArrayList<>())
                        .add(child);

                keyIsNodeAndValueIsIndegreeCount.put(
                        child,
                        keyIsNodeAndValueIsIndegreeCount.getOrDefault(child, 0) + 1);

                keyIsNodeAndValueIsOutdegreeCount.put(
                        parent,
                        keyIsNodeAndValueIsOutdegreeCount.getOrDefault(parent, 0) + 1);
            }

            int startingNode = pairs[0][0];

            for (Map.Entry<Integer, Integer> map : keyIsNodeAndValueIsOutdegreeCount.entrySet()) {
                int node = map.getKey();
                int outdegree = map.getValue();
                int indegree = keyIsNodeAndValueIsIndegreeCount.getOrDefault(node, 0);

                if (outdegree - indegree == 1) {
                    startingNode = node;
                    break;
                }
            }

            List<Integer> res = new ArrayList<>();
            dfs(startingNode, keyIsNodeAndValueAreChilds, res);

            Collections.reverse(res);

            int[][] ans = new int[res.size() - 1][2];

            for (int i = 0; i < res.size() - 1; i++) {
                ans[i][0] = res.get(i);
                ans[i][1] = res.get(i + 1);
            }

            return ans;
        }

        public void dfs(Integer node,
                        Map<Integer, List<Integer>> keyIsNodeAndValueAreChilds,
                        List<Integer> res) {

            List<Integer> children = keyIsNodeAndValueAreChilds.get(node);

            while (children != null && !children.isEmpty()) {
                int child = children.removeLast(); // consume edge
                dfs(child, keyIsNodeAndValueAreChilds, res);
            }

            res.add(node);
        }
    }

}
