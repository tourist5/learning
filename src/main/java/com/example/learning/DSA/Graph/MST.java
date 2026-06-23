package com.example.learning.DSA.Graph;

import javax.management.MBeanAttributeInfo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MST {
    public static void main(String[] args) {
        int[][] points = {{0,0},{2,2},{3,10},{5,2},{7,0}};
        minCostConnectPoints2(points);
    }

    class DSU {
        int[] parent;
        int[] size;

        DSU(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
            size = new int[n];
            for (int i = 0; i < n; i++) {
                size[i] = 1;
            }
        }

        int findParent(int node) {
            if (parent[node] == node) {
                return parent[node];
            }

            parent[node] = findParent(parent[node]);
            return parent[node];
        }


        boolean unionPossibleIfNotCyclic(int node1, int node2) {
            int findParentNode1 = findParent(node1);
            int findParentNode2 = findParent(node2);

            if (findParentNode1 == findParentNode2) {
                return false;
            }

            if (size[findParentNode1] >= size[findParentNode2]) {
                parent[findParentNode2] = findParentNode1;
                size[findParentNode1] = size[findParentNode1] + size[findParentNode2];
            } else {
                parent[findParentNode1] = findParentNode2;
                size[findParentNode2] += size[findParentNode1];
            }

            return true;
        }

    }

//    [[-1000000,-1000000],[1000000,1000000]]

    //Kruskal algorithm
    public int minCostConnectPoints(int[][] points) {
        int len = points.length;
        List<int[]> edges = new ArrayList<>();
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                int weight = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
                edges.add(new int[]{weight, i, j});
            }
        }
        edges.sort((a, b) -> a[0] - b[0]);
        DSU obj = new DSU(len);
        int ans = 0;
        int count = 0;

        for (int i = 0; i < edges.size(); i++) {
            int[] edgeDetail = edges.get(i);
            if (obj.unionPossibleIfNotCyclic(edgeDetail[1], edgeDetail[2])) {
                ans += edgeDetail[0];
                count++;
            }

            if (count == len - 1) {
                return ans;
            }
        }

        return ans;
    }

    //prim algorithm
    public static int minCostConnectPoints2(int[][] points) {
        int len = points.length;
        int[] mst = new int[len];
        Arrays.fill(mst, Integer.MAX_VALUE);
        mst[0] = 0;
        int totalAns = 0;
        boolean[] visited = new boolean[len];
        for (int i = 0; i < len; i++) {
            int currentNode = -1;
            int miDist = Integer.MAX_VALUE;

            for (int j = 0; j < len; j++) {
                if (!visited[j]) {
                    if (mst[j] < miDist) {
                        miDist = mst[j];
                        currentNode = j;
                    }
                }
            }

            if(currentNode==-1) {
                continue;
            }

            System.out.println(currentNode);

            visited[currentNode] = true;
            totalAns += mst[currentNode];

            for (int j = 0; j < len; j++) {
                if (j != currentNode && !visited[j]) {
                    System.out.println("children "+ j);
                    int dist = Math.abs(points[j][0] - points[currentNode][0]) + Math.abs(points[j][1] - points[currentNode][1]);
                    if (dist < mst[j]) {
                        mst[j] = dist;
                    }
                }
            }
        }
        System.out.println("res "+ totalAns);
        return totalAns;

    }
}
