package com.example.learning.DSA.Graph;

public class dfs {
    public static void main(String[] args) {
        int[][] grid = {{0,1,2,3,4},{24,23,22,21,5},{12,13,14,15,16},{11,17,18,19,20},{10,9,8,7,6}};
        System.out.println(swimInWater(grid));
    }

    public static int swimInWater(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        boolean[][] visited = new boolean[row][col];
        int time = grid[0][0];
        return dfs(grid,time,visited,0,0,row,col);
    }



    public  static boolean isValidCell(int currentRow,int currentCol, int row, int col,boolean[][] visited ) {
        return currentRow < row && currentRow >= 0 && currentCol >= 0 && currentCol < col && !visited[currentRow][currentCol];
    }

    public static int dfs(int[][] grid, int time, boolean[][] visited,int currentRow,int currentCol, int row, int col) {
        visited[currentRow][currentCol]  = true;


        if(currentRow==row-1 && currentCol==col-1) {
            visited[currentRow][currentCol] = false;
            int ele = grid[currentRow][currentCol];
            return Math.max(ele, time);
        }

        int op1=Integer.MAX_VALUE,op2 =Integer.MAX_VALUE,op3=Integer.MAX_VALUE,op4=Integer.MAX_VALUE;

         if(isValidCell(currentRow+1,currentCol,row,col,visited)) {
             int curEle = grid[currentRow+1][currentCol];
             op1 = dfs(grid, Math.max(time,curEle), visited, currentRow + 1, currentCol, row, col);
         }
        if(isValidCell(currentRow-1,currentCol,row,col,visited)) {
            int curEle = grid[currentRow-1][currentCol];
            op2 = dfs(grid, Math.max(time,curEle), visited, currentRow - 1, currentCol, row, col);
        }
        if(isValidCell(currentRow,currentCol+1,row,col,visited)) {
            int curEle = grid[currentRow][currentCol+1];
            op3 = dfs(grid, Math.max(time,curEle), visited, currentRow, currentCol + 1, row, col);
        }
        if(isValidCell(currentRow,currentCol-1,row,col,visited)) {
            int curEle = grid[currentRow][currentCol-1];
            op4 = dfs(grid, Math.max(time,curEle), visited, currentRow, currentCol - 1, row, col);
        }

        visited[currentRow][currentCol] = false;
        int min = Math.min(op1, Math.min(op2, Math.min(op3, op4)));
      //  System.out.println(min);
        return min;

    }



}
