package com.example.learning.DSA.Graph;

import java.util.List;

public class ReconstructItinerary {
    public static void main(String[] args) {

    }


    public int isEulerCircuit(int V, int[][] adj) {
        boolean[] visited= new boolean[V];
        dfs(0,visited,adj);
        for(int i=0;i<V;i++) {
            if(!visited[i] && adj[i].length>0) {
                return 0;
            }
        }

        int[] indegree = new int[V];
        for(int i=0;i<V;i++) {
            for(int j=0;j<adj[i].length;j++) {
                int neigh = adj[i][j];
                indegree[neigh]++;
            }
        }

        int oddIndegreeCount = 0;
        for(int i=0;i<V;i++) {
            if(indegree[i]%2!=0) {
                oddIndegreeCount++;
            }
        }

        if(oddIndegreeCount==0) {
            return 2;
        }

        if(oddIndegreeCount==2) {
            return 1;
        }

        return 0;
    }

    public void dfs(int src,boolean[] visited,int[][] adj) {
        visited[src] = true;
        for(int i=0;i<adj[src].length;i++) {
            int nei = adj[src][i];
            if(!visited[nei]) {
                dfs(nei,visited,adj);
            }
        }
    }

}
