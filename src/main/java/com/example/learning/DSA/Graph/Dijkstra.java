package com.example.learning.DSA.Graph;

import java.util.PriorityQueue;

public class Dijkstra {
    public static void main(String[] args) {

    }

    public  static boolean isValidCell(int currentRow,int currentCol, int row, int col,boolean[][] visited ) {
        return currentRow < row && currentRow >= 0 && currentCol >= 0 && currentCol < col && !visited[currentRow][currentCol];
    }

    public int swimInWater(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->a[2]-b[2]);
        pq.offer(new int[]{0, 0, grid[0][0]});
        boolean[][] visited = new boolean[row][col];

        int[][] dir = {{1,0},{-1,0},{0,1},{0,-1}};

        while (!pq.isEmpty()) {
            int[] smallestDist = pq.poll();
            int curRow = smallestDist[0];
            int curCol = smallestDist[1];
            int timeToReachNode = smallestDist[2];

            if(visited[curRow][curCol]) {
                continue;
            }

            visited[curRow][curCol] = true;

            if(curRow==row-1&&curCol==col-1) {
                return timeToReachNode;
            }

            for(int[] arr : dir) {
                int neighRow = arr[0]+curRow;
                int neighCol = arr[1] + curCol;

                if(isValidCell(neighRow,neighCol,row,col,visited)) {
                    int curTime = Math.max(timeToReachNode,grid[neighRow][neighCol]);
                    pq.offer(new int[]{neighRow,neighCol,curTime});
                }
            }

        }
        return -1;
    }

}


