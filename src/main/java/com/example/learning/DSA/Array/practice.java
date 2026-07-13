package com.example.learning.DSA.Array;

import java.util.*;

public class practice {
    public static void main(String[] args) {
//        int[] nums = {1, 1, 1, 2, 2, 3};
//        System.out.println(longestConsecutive(nums));
////        System.out.println(topKFrequent(nums,));
        sequentialDigits(10,100000);
    }

    public static int longestConsecutive(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        }
        if (len == 1) {
            return 1;
        }
        Arrays.sort(nums);
        int ans = 1;
        int i = 0;
        while (i < len) {
            int nextDifferentValue = nums[i] + 1;
            int currentAns = 1;
            int j = i + 1;
            while (j < len) {
                if (nums[j] == nums[j - 1]) {
                    j++;
                    continue;
                }
                if (nums[j] == nextDifferentValue) {
                    j++;
                    currentAns++;
                    nextDifferentValue = nextDifferentValue + 1;
                } else {
                    break;
                }
            }
            ans = Math.max(ans, currentAns);
            i = j;
        }

        return ans;
    }

    public static int longestConsecutive2(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return 1;
        }
        Set<Integer> set = new HashSet<>();
        for (int i : nums) {
            set.add(i);
        }
        int ans = 1;
        for (int i : nums) {
            if (!set.contains(i - 1)) {

                int currentLong = 1;
                int nextEle = i + 1;

                while (set.contains(nextEle)) {
                    currentLong++;
                    nextEle++;
                }

                ans = Math.max(ans, currentLong);
            }
        }

        return ans;
    }

//    public static int[] topKFrequent(int[] nums, int k) {
//        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Integer::compare);
//        for (int val : nums) {
//            int size = priorityQueue.size();
//            if (size < k) {
//                priorityQueue.offer(val);
//                continue;
//            }
//
//            Integer peek = priorityQueue.peek();
//            if(peek>=val) {
//                continue;
//            }
//            priorityQueue.poll();
//            priorityQueue.offer(val);
//
//        }
//
//        int[] ans = new int[k];
//        int i=0;
//
//        while (!priorityQueue.isEmpty()) {
//            Integer poll = priorityQueue.poll();
//            ans[i] = poll;
//            i++;
//        }
//
//        reverse(ans);
//        return ans;
//
//
//    }
//
//    public static void reverse(int[] arr) {
//        int left = 0;
//        int right = arr.length-1;
//
//        while(left<=right) {
//            int temp = arr[left];
//            arr[left]=arr[right];
//            arr[right]=temp;
//            left++;
//            right--;
//        }
//    }

    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        List<Integer>[] buckets = new ArrayList[nums.length + 1];
        for (int i = 0; i <= nums.length; i++) {
            buckets[i] = new ArrayList<>();
        }
        for (Map.Entry<Integer, Integer> currentEle : map.entrySet()) {
            Integer key = currentEle.getKey();
            int value = currentEle.getValue();
            buckets[value].add(key);
        }
        int cnt = 0;
        int[] ans = new int[k];
        for (int i = nums.length; i >= 1; i--) {
            List<Integer> val = buckets[i];
            if (cnt == k) {
                break;
            }
            for (int num : val) {
                ans[cnt] = num;
                cnt++;
            }
        }

        return ans;

    }

    public static List<Integer> sequentialDigits(int low, int high) {
        int cnt = 8;
        int digit = 2;

        List<Integer> tempRes = new ArrayList<>();
        while (cnt>0) {
            int start = 1;
            while(start<=cnt) {
                int nextDigit = start;
                int number = nextDigit;
                for(int i=2;i<=digit;i++) {
                    nextDigit = nextDigit+1;
                    number = number*10+nextDigit;
                }
                tempRes.add(number);
                start++;
            }

            digit++;
            cnt--;
        }

        System.out.println(tempRes);

        List<Integer> ans = new ArrayList<>();

        for(int i=0;i<tempRes.size();i++) {
            int val = tempRes.get(i);
            if(val>high) {
                break;
            }
            if(low<=val) {
                ans.add(val);
            }
        }

        return ans;

    }

}
