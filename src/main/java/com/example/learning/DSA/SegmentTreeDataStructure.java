package com.example.learning.DSA;

import java.util.Arrays;

public class SegmentTreeDataStructure {
    public static void main(String[] args) {
        int[] arr = {1,4,5,8,0,3,6};
        int[] st = segmentTreeSum(arr);
        System.out.println(Arrays.toString(st));
        //(2,5)
        //(2,6)
        //(1,5)
        //we have got left and we have got right
        //5+8+0+3 = 16
        System.out.println(queryRangeResult(st,0,arr.length-1,0,2,6));
    }

    //[1,4,5,8,0,3,6]

    public static int[] segmentTreeSum(int[] arr) {
        int len = arr.length;
        int[] st = new int[2*len];
        int sum = buildSt(st, arr, 0, 0, len - 1);
        return st;
    }

    public static int buildSt(int[] st, int[] arr, int i, int left,int right) {
        if(left == right) {
            st[i] = arr [right];
            return st[i];
        }

        int mid = (left+right)/2;
        int leftPart = buildSt(st,arr,2*i+1,left,mid);
        int rightPart = buildSt(st,arr,2*i+2,mid+1,right);
        st[i] = leftPart+rightPart;
        return st[i];
    }

    public static int queryRangeResult(
            int[] st,
            int left,
            int right,
            int eleIndex,
            int queryLeft,
            int queryRight) {

        // No overlap
        if (queryRight < left || queryLeft > right) {
            return 0;
        }

        // Complete overlap
        if (queryLeft <= left && right <= queryRight) {
            return st[eleIndex];
        }

        // Partial overlap
        int mid = (left + right) / 2;

        int leftSum = queryRangeResult(
                st,
                left,
                mid,
                2 * eleIndex + 1,
                queryLeft,
                queryRight);

        int rightSum = queryRangeResult(
                st,
                mid + 1,
                right,
                2 * eleIndex + 2,
                queryLeft,
                queryRight);

        return leftSum + rightSum;
    }
}
