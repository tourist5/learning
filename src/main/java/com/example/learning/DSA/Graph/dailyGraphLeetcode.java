package com.example.learning.DSA.Graph;

import java.util.*;

public class dailyGraphLeetcode {
    public static void main(String[] args) {
//        int[][] edges = {{0, 1, 5}, {1, 3, 10}, {0, 2, 3}, {2, 3, 4}};
//        boolean[] online = {true, true, true, true};
//        int k = 10;
//        System.out.println(findMaxPathScore(edges, online, k));

//        int n = 4;
//        //  int[][] roads = {{1, 2, 9}, {2, 3, 6}, {2, 4, 5}, {1, 4, 7}};
//        int[][] roads = {{1, 2, 2}, {1, 3, 4}, {3, 4, 7}};
//        System.out.println(minScore(n, roads));
        int n = 4;
        int[] nums = {2,5,6,8};
        int maxDiff = 2;
        int[][] queries = {{0,1},{0,2},{1,3},{2,3}};
        System.out.println(Arrays.toString(pathExistenceQueries(n, nums, maxDiff, queries)));
    }

    public static boolean dijikstr(List<List<int[]>> adjList, boolean[] online, long k, long limit, int numberOfNodes) {
        PriorityQueue<long[]> priorityQueue = new PriorityQueue<>((a, b) -> Long.compare(a[0], b[0]));
        long[] dist = new long[numberOfNodes];
        Arrays.fill(dist, Integer.MAX_VALUE);
        priorityQueue.offer(new long[]{0, 0});
        dist[0] = 0;
        while (!priorityQueue.isEmpty()) {
            long[] pollData = priorityQueue.poll();
            long cost = pollData[0];
            int node = (int) pollData[1];

            if (dist[node] < cost) {
                continue;
            }

            int childNumber = adjList.get(node).size();
            for (int i = 0; i < childNumber; i++) {
                int[] children = adjList.get(node).get(i);
                int childNode = children[0];
                int costChildren = children[1];

                if (!online[childNode]) {
                    continue;
                }

                if (costChildren < limit) {
                    continue;
                }

                long costToReachFromO = cost + costChildren;
                if (dist[childNode] > costToReachFromO) {
                    dist[childNode] = costToReachFromO;
                    priorityQueue.offer(new long[]{costToReachFromO, childNode});
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

        for (int i = 0; i < numberOfNodes; i++) {
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


        while (lowCost <= highCost) {
            int mid = (lowCost + highCost) / 2;

            if (dijikstr(adjList, online, k, mid, numberOfNodes)) {
                res = mid;
                lowCost = mid + 1;
            } else {
                highCost = mid - 1;
            }

        }

        return res;

    }

    //n = 4, roads = [[1,2,2],[1,3,4],[3,4,7]]

    public static int minScore(int n, int[][] roads) {
        List<List<int[]>> adjList = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int[] road : roads) {
            int parent = road[0];
            int child = road[1];
            int weight = road[2];

            adjList.get(parent).add(new int[]{child, weight});
            adjList.get(child).add(new int[]{parent, weight});
        }


        List<Integer> nodesInTheComponent = new ArrayList<>();
        boolean[] visited = new boolean[n + 1];
        dfs(1, adjList, visited, nodesInTheComponent);

        int ans = Integer.MAX_VALUE;

        for (int node : nodesInTheComponent) {
            for (int[] child : adjList.get(node)) {
                int weight = child[1];
                ans = Math.min(ans, weight);
            }
        }

        return ans;

    }

    public static void dfs(int node, List<List<int[]>> adjList, boolean[] visited, List<Integer> nodesInTheComponent) {
        visited[node] = true;
        nodesInTheComponent.add(node);
        for (int[] child : adjList.get(node)) {
            int neigh = child[0];
            if (!visited[neigh]) {
                dfs(neigh, adjList, visited, nodesInTheComponent);
            }
        }
    }

    static class DSU{
        int[] parent;
        int[] size;

        DSU(int n) {
            this.parent = new int[n];
            this.size = new int[n];
            for(int i =0;i<n;i++) {
                parent[i] = i;
            }
            Arrays.fill(size,1);
        }

        int findParent(int node) {
            if(parent[node]==node) {
                return node;
            }
            parent[node]=findParent(parent[node]);
            return parent[node];
        }

        void unionBySize(int node1,int node2) {
            int parentNode1 = findParent(node1);
            int parentNode2 = findParent(node2);

            if(size[parentNode1]>size[parentNode2]) {
                parent[parentNode2]=parentNode1;
                size[parentNode1]+=size[parentNode2];
            } else {
                parent[parentNode1] = parentNode2;
                size[parentNode2]+=size[parentNode1];
            }
        }
    }

    public static boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        DSU dsu = new DSU(n);



        for(int i=1;i<n;i++) {
            //no need to find aal
//            int currentStartValue = nums[i];
//            int left = i;
//            int right = n-1;
//            int upperBound = left;
//
//            while(left<=right) {
//                int mid = (left+right)/2;
//                if(currentStartValue+maxDiff>=nums[mid]) {
//                    upperBound = mid;
//                    left=mid+1;
//                } else {
//                    right=mid-1;
//                }
//            }
//            int parentNode1 = dsu.findParent(i);
//
//            for(int j=i+1;j<=upperBound;j++) {
//                int parentNode2 = dsu.findParent(j);
//                if(parentNode1==parentNode2) {
//                    continue;
//                }
//                dsu.unionBySize(i,j);
//            }

            if(nums[i]<=nums[i-1]+maxDiff) {
                int node1 = i-1;
                int node2 = i;
                if(dsu.findParent(node1)==dsu.findParent(node2)){
                    continue;
                }
                dsu.unionBySize(node1,node2);
            }
        }

        System.out.println("parent->"+ Arrays.toString(dsu.parent));


        int querySize = queries.length;
        boolean[] ans = new boolean[querySize];

        for(int i=0;i<querySize;i++) {
            int node1 = queries[i][0];
            int node2 = queries[i][1];

            ans[i] = dsu.findParent(node1) == dsu.findParent(node2);
        }

        return ans;

    }


}