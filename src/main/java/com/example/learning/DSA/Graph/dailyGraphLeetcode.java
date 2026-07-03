package com.example.learning.DSA.Graph;

import java.util.*;

public class dailyGraphLeetcode {
    public static void main(String[] args) {
        int[][] edges = {{0,1,5},{1,3,10},{0,2,3},{2,3,4}};
        boolean[] online = {true,true,true,true};
        int k = 10;
        System.out.println(findMaxPathScore(edges,online,k));
    }

    public static boolean dijikstr( List<List<int[]>> adjList, boolean[] online,long k, long limit, int numberOfNodes) {
        PriorityQueue<long[]> priorityQueue = new PriorityQueue<>((a,b)->Long.compare(a[0],b[0]));
        long[] dist = new long[numberOfNodes];
        Arrays.fill(dist,Integer.MAX_VALUE);
        priorityQueue.offer(new long[]{0, 0});
        dist[0] = 0;
        while (!priorityQueue.isEmpty()) {
            long[] pollData = priorityQueue.poll();
            long cost = pollData[0];
            int node = (int) pollData[1];

            if(dist[node]<cost) {
                continue;
            }

            int childNumber = adjList.get(node).size();
            for(int i = 0;i<childNumber;i++) {
                int[] children = adjList.get(node).get(i);
                int childNode = children[0];
                int costChildren = children[1];

                if(!online[childNode]) {
                    continue;
                }

                if(costChildren<limit) {
                    continue;
                }

                long costToReachFromO = cost+costChildren;
                if(dist[childNode]>costToReachFromO) {
                    dist[childNode]  = costToReachFromO;
                    priorityQueue.offer(new long[]{costToReachFromO,childNode});
                }
            }

        }

        return dist[numberOfNodes - 1] <= k;
    }

    public static int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        int numberOfNodes = online.length;

        int lowCost = Integer.MAX_VALUE;
        int highCost = Integer.MIN_VALUE;

        List<List<int[]>> adjList = new ArrayList<>();

        for(int i=0;i<numberOfNodes;i++) {
            adjList.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int parentNode = edge[0];
            int childNode = edge[1];
            int cost = edge[2];
            adjList.get(parentNode).add(new int[]{childNode, cost});

            lowCost = Math.min(lowCost, cost);
            highCost = Math.max(highCost, cost);
        }

        System.out.println("adjlist " + adjList);
        System.out.println("lowcost " + lowCost);
        System.out.println("highcost " + highCost);

        int res = -1;
        //binary search


        while (lowCost<=highCost) {
            int mid = (lowCost+highCost)/2;

            if(dijikstr(adjList,online,k,mid,numberOfNodes)) {
                res = mid;
                lowCost = mid+1;
            } else {
                highCost = mid-1;
            }

        }

        return res;

    }

}
