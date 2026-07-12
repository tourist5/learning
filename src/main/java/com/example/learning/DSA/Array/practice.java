package com.example.learning.DSA.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class practice {
    public static void main(String[] args) {
        int[] nums = {0,3,7,2,5,8,4,6,0,1};
        System.out.println(longestConsecutive(nums));
    }

    public static int longestConsecutive(int[] nums) {
        int len = nums.length;
        if(len==0) {
            return 0;
        }
        if(len==1) {
            return 1;
        }
        Arrays.sort(nums);
        int ans = 1;
        int i=0;
        while(i<len) {
            int nextDifferentValue = nums[i]+1;
            int currentAns = 1;
            int j=i+1;
            while(j<len) {
                if (nums[j] == nums[j - 1]) {
                    j++;
                    continue;
                }
                if(nums[j]==nextDifferentValue) {
                    j++;
                    currentAns++;
                    nextDifferentValue = nextDifferentValue+1;
                } else {
                    break;
                }
            }
            ans = Math.max(ans,currentAns);
            i=j;
        }

        return ans;
    }

}
