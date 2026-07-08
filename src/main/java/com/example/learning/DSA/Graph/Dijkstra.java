package com.example.learning.DSA.Graph;

import java.util.*;

public class Dijkstra {
    public static void main(String[] args) {
       int  n = 4;
       int[][] flights = {{0,1,100},{1,2,100},{2,0,100},{1,3,600},{2,3,200}};
       int src = 0;
       int dst = 3;
       int k = 1;
        System.out.println(findCheapestPrice(n,flights,src,dst,k));
    }

    public static boolean isValidCell(int currentRow, int currentCol, int row, int col, boolean[][] visited) {
        return currentRow < row && currentRow >= 0 && currentCol >= 0 && currentCol < col && !visited[currentRow][currentCol];
    }

    public int swimInWater(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        pq.offer(new int[]{0, 0, grid[0][0]});
        boolean[][] visited = new boolean[row][col];

        int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        while (!pq.isEmpty()) {
            int[] smallestDist = pq.poll();
            int curRow = smallestDist[0];
            int curCol = smallestDist[1];
            int timeToReachNode = smallestDist[2];

            if (visited[curRow][curCol]) {
                continue;
            }

            visited[curRow][curCol] = true;

            if (curRow == row - 1 && curCol == col - 1) {
                return timeToReachNode;
            }

            for (int[] arr : dir) {
                int neighRow = arr[0] + curRow;
                int neighCol = arr[1] + curCol;

                if (isValidCell(neighRow, neighCol, row, col, visited)) {
                    int curTime = Math.max(timeToReachNode, grid[neighRow][neighCol]);
                    pq.offer(new int[]{neighRow, neighCol, curTime});
                }
            }

        }
        return -1;
    }

    public static int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        List<List<int[]>> adjacencyList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjacencyList.add(new ArrayList<>());
        }
        for (int[] currentFlight : flights) {
            int parent = currentFlight[0];
            int child = currentFlight[1];
            int cost = currentFlight[2];
            adjacencyList.get(parent).add(new int[]{child, cost});
        }

        int[] dist = new int[n];
        Arrays.fill(dist,Integer.MAX_VALUE);

        ArrayDeque<int[]> queue = new ArrayDeque<>();
        queue.addLast(new int[]{src,0,0});

        while (!queue.isEmpty()) {
            int size = queue.size();
            for(int i=0;i<size;i++) {
                int[] removed = queue.removeFirst();
                int nodeRemoved = removed[0];
                int nodeDist = removed[1];
                int stop = removed[2];

                if(stop>k) {
                    continue;
                }

                List<int[]> childs = adjacencyList.get(nodeRemoved);
                for(int[] currentChild: childs) {
                    int currentChildNode = currentChild[0];
                    int currentChildNodeDist = currentChild[1];
                    int distanceFrom0 = nodeDist+currentChildNodeDist;

                    if(distanceFrom0<dist[currentChildNode]) {
                        dist[currentChildNode] = distanceFrom0;
                        queue.addLast(new int[]{currentChildNode,distanceFrom0,stop+1});
                    }
                }

            }
        }

        if(dist[dst]==Integer.MAX_VALUE) {
            return -1;
        }

        return dist[dst];

    }

}


