package com.example.learning.DSA;

import java.util.*;

public class dsa {
    public static void main(String[] args) {
        System.out.println(totalWaviness(4848, 4848));
    }

    public boolean canReach(String s, int minJump, int maxJump) {
        int len = s.length();
        if (s.charAt(len - 1) == '1') {
            return false;
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        int maxReach = 0;
        while (!queue.isEmpty()) {
            Integer frontElem = queue.remove();
            if (frontElem == len - 1) {
                return true;
            }
            int startInd = Math.max(frontElem + minJump, maxReach);
            int endInd = Math.min(frontElem + maxJump, len - 1);
            maxReach = endInd + 1;
            if (startInd <= len - 1) {
                for (int i = startInd; i <= endInd; i++) {
                    if (s.charAt(i) == '0') {
                        queue.add(i);
                    }
                }
            }

        }
        return false;
    }

    public boolean canReach2(String s, int minJump, int maxJump) {
        int len = s.length();
        if (s.charAt(len - 1) == '1') {
            return false;
        }
        int[] dp = new int[len];
        dp[0] = 1;
        int count = 0;
        for (int i = 1; i < len; i++) {
            int closestAddedIndex = i - minJump;
            int farthestRemovedIndex = i - maxJump - 1;
            if (closestAddedIndex >= 0 && s.charAt(closestAddedIndex) == '0') {
                count = count + dp[closestAddedIndex];
            }
            if (farthestRemovedIndex >= 0 && s.charAt(farthestRemovedIndex) == '0') {
                count = count - dp[farthestRemovedIndex];
            }

            if (count >= 0 && s.charAt(i) == '0') {
                dp[i] = count;
            }
        }
        return dp[len - 1] > 0;
    }

    public int numberOfSpecialChars(String word) {
        int len = word.length();
        boolean[] freqSmall = new boolean[26];
        boolean[] freqBig = new boolean[26];
        for (int i = 0; i < len; i++) {
            char curr = word.charAt(i);
            if ('a' <= curr && curr <= 'z') {
                freqSmall[curr - 'a'] = true;
            } else {
                freqBig[curr - 'A'] = true;
            }
        }
        int count = 0;
        for (int i = 0; i < 26; i++) {
            if (freqSmall[i] && freqBig[i]) {
                count++;
            }
        }

        return count;

    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, Set<Integer>> parentIsKeyAndValueAreChild = new HashMap<>();
        int[] indexIsCourseAndValueIsNumberOfCoursesItIsDependent = new int[numCourses];
        for (int[] prerequisite : prerequisites) {
            int parent = prerequisite[1];
            int child = prerequisite[0];
            parentIsKeyAndValueAreChild.computeIfAbsent(parent, set -> new HashSet<>()).add(child);
            indexIsCourseAndValueIsNumberOfCoursesItIsDependent[child]++;
        }

        Queue<Integer> bffQueue = new ArrayDeque<>();
        int[] res = new int[numCourses];
        int resIndex = 0;
        for (int i = 0; i < indexIsCourseAndValueIsNumberOfCoursesItIsDependent.length; i++) {
            if (indexIsCourseAndValueIsNumberOfCoursesItIsDependent[i] == 0) {
                bffQueue.offer(i);
            }
        }

        while (!bffQueue.isEmpty()) {
            int queueSize = bffQueue.size();
            for (int i = 1; i <= queueSize; i++) {
                Integer courseCompleted = bffQueue.poll();
                res[resIndex] = courseCompleted;
                resIndex++;
                if (parentIsKeyAndValueAreChild.containsKey(courseCompleted)) {
                    Set<Integer> childCourses = parentIsKeyAndValueAreChild.get(courseCompleted);
                    for (Integer childCourse : childCourses) {
                        indexIsCourseAndValueIsNumberOfCoursesItIsDependent[childCourse]--;
                        if (indexIsCourseAndValueIsNumberOfCoursesItIsDependent[childCourse] == 0) {
                            bffQueue.offer(childCourse);
                        }
                    }
                }
            }
        }

        for (int i = 0; i < numCourses; i++) {
            if (indexIsCourseAndValueIsNumberOfCoursesItIsDependent[i] != 0) {
                return new int[0];
            }
        }

        return res;

    }

    boolean hasCycle(boolean[] visited, int currentNode, Map<Integer, Set<Integer>> keyIsParentAndValueIsChild, int parent) {
        visited[currentNode] = true;
        if (keyIsParentAndValueIsChild.containsKey(currentNode)) {
            Set<Integer> integerChildConnected = keyIsParentAndValueIsChild.get(currentNode);
            for (Integer currentChildNode : integerChildConnected) {
                if (!visited[currentChildNode]) {
                    boolean isCycle = hasCycle(visited, currentChildNode, keyIsParentAndValueIsChild, currentNode);
                    if (isCycle) {
                        return true;
                    }
                } else if (currentChildNode != parent) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean validTree(int n, int[][] edges) {
        int sizeEdge = edges.length;
        if (sizeEdge != n - 1) {
            return false;
        }
        Map<Integer, Set<Integer>> keyIsParentAndValueIsChild = new HashMap<>();
        for (int[] edge : edges) {
            int firstEle = edge[0];
            int secondEle = edge[1];
            keyIsParentAndValueIsChild.computeIfAbsent(firstEle, set -> new HashSet<>()).add(secondEle);
            keyIsParentAndValueIsChild.computeIfAbsent(secondEle, set -> new HashSet<>()).add(firstEle);
        }
        boolean[] visited = new boolean[n];
        if (hasCycle(visited, 0, keyIsParentAndValueIsChild, -1)) {
            return false;
        }
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                return false;
            }
        }
        return true;
    }

    void dfs(Map<Integer, List<Integer>> keyIsParentAndValuesAreChilds, boolean[] visited, int currentIndex) {
        visited[currentIndex] = true;
        if (keyIsParentAndValuesAreChilds.containsKey(currentIndex)) {
            List<Integer> childs = keyIsParentAndValuesAreChilds.get(currentIndex);
            for (Integer child : childs) {
                if (!visited[child]) {
                    dfs(keyIsParentAndValuesAreChilds, visited, child);
                }
            }
        }
    }

    public int countComponents(int n, int[][] edges) {
        Map<Integer, List<Integer>> keyIsParentAndValuesAreChilds = new HashMap<>();
        for (int[] edge : edges) {
            int firstNode = edge[0];
            int secondNode = edge[1];
            keyIsParentAndValuesAreChilds.computeIfAbsent(firstNode, list -> new ArrayList<>()).add(secondNode);
            keyIsParentAndValuesAreChilds.computeIfAbsent(secondNode, list -> new ArrayList<>()).add(firstNode);
        }

        boolean[] visited = new boolean[n];
        int count = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                count++;
                dfs(keyIsParentAndValuesAreChilds, visited, i);
            }
        }

        return count;
    }


    private int findUltimateParent(int ele, int[] parent) {
        if (parent[ele] == ele) {
            return ele;
        }
        return parent[ele] = findUltimateParent(parent[ele], parent);
    }

    private void unionBySize(int firstNode, int secondNode, int[] parent, int[] size) {
        int firstNodeUltimateParent = findUltimateParent(firstNode, parent);
        int secondNodeUltimateParent = findUltimateParent(secondNode, parent);
        if (firstNodeUltimateParent != secondNodeUltimateParent) {
            int sizeFirstNodeUltimateParent = size[firstNodeUltimateParent];
            int sizeSecondNodeUltimateParent = size[secondNodeUltimateParent];
            if (sizeFirstNodeUltimateParent >= sizeSecondNodeUltimateParent) {
                parent[secondNodeUltimateParent] = firstNodeUltimateParent;
                size[firstNodeUltimateParent] = sizeFirstNodeUltimateParent + sizeSecondNodeUltimateParent;
            } else {
                parent[firstNodeUltimateParent] = secondNodeUltimateParent;
                size[sizeSecondNodeUltimateParent] = sizeSecondNodeUltimateParent + sizeFirstNodeUltimateParent;
            }
        }
    }

    public int countComponentsByUnionBySize(int n, int[][] edges) {
        int[] ultimateParent = new int[n];
        int[] size = new int[n];
        Arrays.fill(size, 1);
        for (int i = 0; i < n; i++) {
            ultimateParent[i] = i;
        }
        for (int i = 0; i < edges.length; i++) {
            int firstNode = edges[i][0];
            int secondNode = edges[i][1];
            unionBySize(firstNode, secondNode, ultimateParent, size);
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            if (ultimateParent[i] == i) {
                count++;
            }
        }

        return count;
    }

    int findUltimateParentSecond(int ele, int[] parent) {
        if (parent[ele] == ele) {
            return ele;
        }
        return parent[ele] = findUltimateParentSecond(parent[ele], parent);
    }

    boolean unionBySizeCyclic(int firstNode, int secondNode, int[] parent, int[] size) {
        int parentFirstNode = findUltimateParentSecond(firstNode, parent);
        int parentSecondNode = findUltimateParentSecond(secondNode, parent);
        if (parentFirstNode == parentSecondNode) {
            return true;
        }
        if (size[parentFirstNode] >= size[parentSecondNode]) {
            parent[parentSecondNode] = parentFirstNode;
            size[parentFirstNode] += size[parentSecondNode];
        } else {
            parent[parentFirstNode] = parentSecondNode;
            size[parentSecondNode] += size[parentFirstNode];
        }
        return false;
    }

    public int[] findRedundantConnection(int[][] edges) {
        int len = edges.length;
        int[] ultimateParent = new int[len + 1];
        for (int i = 1; i <= len; i++) {
            ultimateParent[i] = i;
        }
        int[] size = new int[len + 1];
        Arrays.fill(size, 1);
        for (int[] edge : edges) {
            boolean b = unionBySizeCyclic(edge[0], edge[1], ultimateParent, size);
            if (b) {
                return edge;
            }
        }
        return null;

    }


    class Solution {

        class TrieNode {
            TrieNode[] children = new TrieNode[26];
            int bestIndex = -1;
            int bestLength = Integer.MAX_VALUE;
        }

        TrieNode root = new TrieNode();

        private void update(TrieNode node, int index, int len) {
            if (len < node.bestLength ||
                    (len == node.bestLength && index < node.bestIndex)) {
                node.bestLength = len;
                node.bestIndex = index;
            }
        }

        private void insert(String word, int index) {
            TrieNode node = root;
            int len = word.length();

            update(node, index, len);

            for (int i = len - 1; i >= 0; i--) {
                int ch = word.charAt(i) - 'a';

                if (node.children[ch] == null) {
                    node.children[ch] = new TrieNode();
                }

                node = node.children[ch];
                update(node, index, len);
            }
        }

        private int search(String word) {
            TrieNode node = root;

            for (int i = word.length() - 1; i >= 0; i--) {
                int ch = word.charAt(i) - 'a';

                if (node.children[ch] == null) {
                    break;
                }

                node = node.children[ch];
            }

            return node.bestIndex;
        }

        public int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {

            for (int i = 0; i < wordsContainer.length; i++) {
                insert(wordsContainer[i], i);
            }

            int[] ans = new int[wordsQuery.length];

            for (int i = 0; i < wordsQuery.length; i++) {
                ans[i] = search(wordsQuery[i]);
            }

            return ans;
        }
    }

    public int minElement(int[] nums) {
        int res = Integer.MAX_VALUE;
        for (int num : nums) {
            int curEle = num;
            int curRes = 0;
            while (curEle > 0) {
                int digit = curEle % 10;
                curRes += digit;
                curEle = curEle / 10;
            }
            res = Math.min(res, curRes);
        }
        return res;

    }

    public int earliestFinishTime(int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration) {
        int minL = Integer.MAX_VALUE;
        int minW = Integer.MAX_VALUE;
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < landDuration.length; i++) {
            int ele = landDuration[i];
            minL = Math.min(ele + landStartTime[i], minL);
        }
        for (int i = 0; i < waterDuration.length; i++) {
            minW = Math.min(minW, waterStartTime[i] + waterDuration[i]);
            res = Math.min(res, minL + waterDuration[i] + Math.max(minL, waterStartTime[i]));
        }

        for (int i = 0; i < landDuration.length; i++) {
            res = Math.min(res, minW + landDuration[i] + Math.max(minW, landStartTime[i]));
        }

        return res;
    }

    public void subsetsWithDupRecur(int[] nums, List<Integer> currentRes, List<List<Integer>> resList, int idx, int len) {
        if (idx == len) {
            return;
        }

        for (int j = idx; j < len; j++) {
            if (j != idx && nums[j - 1] == nums[j]) {
                continue;
            }
            currentRes.add(nums[j]);
            resList.add(new ArrayList<>(currentRes));
            subsetsWithDupRecur(nums, currentRes, resList, j + 1, len);
            currentRes.remove(currentRes.size() - 1);
        }

    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        int len = nums.length;
        List<Integer> currentRes = new ArrayList<>();
        List<List<Integer>> resList = new ArrayList<>();
        Arrays.sort(nums);
        subsetsWithDupRecur(nums, currentRes, resList, 0, len);
        resList.add(new ArrayList<>());
        return resList;
    }

    public int earliestFinishTime2(int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration) {
        int earliestLandTaskFinish = Integer.MAX_VALUE;
        int earliestWaterTaskFinish = Integer.MAX_VALUE;
        for (int i = 0; i < landStartTime.length; i++) {
            int currentStartTime = landStartTime[i];
            int currentDuration = landDuration[i];
            int endingTime = currentStartTime + currentDuration;
            earliestLandTaskFinish = Math.min(earliestLandTaskFinish, endingTime);
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < waterStartTime.length; i++) {
            int currentStartTime = waterStartTime[i];
            int currentDuration = waterDuration[i];
            int endingTime = currentStartTime + currentDuration;
            earliestWaterTaskFinish = Math.min(earliestWaterTaskFinish, endingTime);
            res = Math.min(Math.max(earliestLandTaskFinish, waterStartTime[i]) + waterDuration[i], res);
        }
        for (int i = 0; i < landStartTime.length; i++) {
            res = Math.min(Math.max(earliestWaterTaskFinish, landStartTime[i]) + landDuration[i], res);
        }
        return res;

    }

    //4848
    //8
    //484
    //4

    public static int totalWaviness(int num1, int num2) {
        int res = 0;
        for (int i = num1; i <= num2; i++) {
            int j = i;
            int prev = -1;
            int next = -1;
            boolean last = true;
            while (j > 0) {
                int currentEle = j % 10;
                j = j / 10;
                if (j > 0) {
                    next = j % 10;
                }
                if (j > 0 && !last) {
                    if ((currentEle > prev && currentEle > next) || (currentEle < prev && currentEle < next)) {
                        res++;
                        System.out.println(res);
                    }
                }
                last = false;
                prev = currentEle;
                System.out.println(prev + currentEle + next);
            }
        }
        return res;
    }

    public int[] leftRightDifference(int[] nums) {
        int len = nums.length;
        int[] leftSum = new int[len];
        int[] rightSum = new int[len];
        int[] res = new int[len];
        for (int i = 1; i < len; i++) {
            leftSum[i] = leftSum[i - 1] + nums[i - 1];
        }
        for (int i = len - 2; i >= 0; i--) {
            rightSum[i] = rightSum[i + 1] + nums[i + 1];
            res[i] = Math.abs(rightSum[i] - leftSum[i]);
        }
        res[len - 1] = leftSum[len - 1];
        return res;
    }


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public class PairOfNodeAndIsRoot {
        private TreeNode node;
        private Boolean isRoot;

        PairOfNodeAndIsRoot(TreeNode node, Boolean isRoot) {
            this.node = node;
            this.isRoot = isRoot;
        }

        public TreeNode getNode() {
            return node;
        }

        public void setNode(TreeNode node) {
            this.node = node;
        }

        public Boolean getRoot() {
            return isRoot;
        }

        public void setRoot(Boolean root) {
            isRoot = root;
        }
    }


    public TreeNode createBinaryTree(int[][] descriptions) {
        Map<Integer, PairOfNodeAndIsRoot> keyIsNodeDataAndValueIsNodeReference = new HashMap<>();
        for (int[] description : descriptions) {
            int getParent = description[0];
            int getChild = description[1];
            int isLeft = description[2];
            if (!keyIsNodeDataAndValueIsNodeReference.containsKey(getParent)) {
                TreeNode treeNode = new TreeNode(getParent);
                PairOfNodeAndIsRoot pairOfNodeAndIsRoot = new PairOfNodeAndIsRoot(treeNode, Boolean.TRUE);
                keyIsNodeDataAndValueIsNodeReference.put(getParent, pairOfNodeAndIsRoot);
            }

            if (!keyIsNodeDataAndValueIsNodeReference.containsKey(getChild)) {
                TreeNode treeNode = new TreeNode(getChild);
                PairOfNodeAndIsRoot pairOfNodeAndIsRoot = new PairOfNodeAndIsRoot(treeNode, Boolean.FALSE);
                keyIsNodeDataAndValueIsNodeReference.put(getChild, pairOfNodeAndIsRoot);
            }

            PairOfNodeAndIsRoot pairOfNodeAndIsRoot = keyIsNodeDataAndValueIsNodeReference.get(getParent);
            TreeNode node = pairOfNodeAndIsRoot.getNode();

            PairOfNodeAndIsRoot childNodePair = keyIsNodeDataAndValueIsNodeReference.get(getChild);
            TreeNode childNode = childNodePair.node;

            if (isLeft == 1) {
                node.left = childNode;
            } else {
                node.right = childNode;
            }

            childNodePair.isRoot = Boolean.FALSE;

        }

        for (Map.Entry<Integer, PairOfNodeAndIsRoot> entry : keyIsNodeDataAndValueIsNodeReference.entrySet()) {
            PairOfNodeAndIsRoot value = entry.getValue();
            if (value.isRoot) {
                return value.node;
            }
        }

        return null;
    }

    public int[] pivotArray(int[] nums, int pivot) {
        int len = nums.length;
        int leftPointer = 0;
        int rightPointer = len - 1;
        int[] res = new int[len];
        int i = 0;
        int j = len - 1;
        while (leftPointer <= len - 1) {
            if (nums[leftPointer] < pivot) {
                res[i] = nums[leftPointer];
                i++;
            }
            if (nums[rightPointer] > pivot) {
                res[j] = nums[rightPointer];
                j--;
            }

            leftPointer++;
            rightPointer--;
        }

        while (i <= j) {
            res[i] = pivot;
            i++;
        }
        return res;
    }

    public long maxTotalValue(int[] nums, int k) {
        int mi = Integer.MAX_VALUE;
        int ma = Integer.MIN_VALUE;
        for (int num : nums) {
            mi = Math.min(num, mi);
            ma = Math.max(num, ma);
        }
        return (long) (ma - mi) *k;
    }
}

