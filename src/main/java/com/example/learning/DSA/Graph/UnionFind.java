package com.example.learning.DSA.Graph;

import java.util.Arrays;

public class UnionFind {
    public static void main(String[] args) {

    }

    static class DSU{
        int[] parent;
        int[] size;

        public  DSU(int n) {
            this.parent = new int[n+1];
            this.size = new int[n+1];
            for(int i=0;i<=n;i++) {
                parent[i] = i;
            }
            Arrays.fill(size,1);
        }

        public int findParent(int node) {
            if(parent[node]==node) {
                return node;
            }

            parent[node] = findParent(parent[node]);
            return parent[node];
        }

        public void union(int node1, int node2) {
            int parentNode1 = findParent(node1);
            int parentNode2 = findParent(node2);

            if(parentNode1!=parentNode2) {
                int size1 = size[parentNode1];
                int size2 = size[parentNode2];

                if(size1>size2) {
                    parent[parentNode2] = parentNode1;
                    size[parentNode1] = size1+size2;

                } else {
                    parent[parentNode1] = parentNode2;
                    size[parentNode2] = size2+size1;
                }
            }
        }
    }

    public static int minScore(int n, int[][] roads) {
        int[] parent = new int[n+1];
        int[] size = new int[n+1];

        DSU dsu = new DSU(n);
        for (int[] road : roads) {
            int node1 = road[0];
            int node2 = road[1];

            if (dsu.findParent(node1) == dsu.findParent(node2)) {
                continue;
            }

            dsu.union(node1, node2);
        }
        int ans = Integer.MAX_VALUE;
        int parent1 = dsu.findParent(1);
        for(int[] road : roads) {
            if(dsu.findParent(road[0])==parent1) {
                ans = Math.min(ans,road[2]);
            }
        }

        return ans;
    }

}
