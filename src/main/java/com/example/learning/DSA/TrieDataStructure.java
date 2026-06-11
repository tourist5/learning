package com.example.learning.DSA;

import java.util.Arrays;
import java.util.PriorityQueue;

public class TrieDataStructure {
    static class TrieNode {
        TrieNode[] nextCharacterNode;
        boolean isEnd;
        int count;

        TrieNode() {
            this.nextCharacterNode = new TrieNode[26];
            this.isEnd = false;
            this.count=1;
        }
    }

    private static TrieNode root = new TrieNode();

    public static void insert(String input) {
        TrieNode node = root;
        for(int i=0;i<input.length();i++) {
            int index = input.charAt(i)-'a';
            if(node.nextCharacterNode[index] == null ) {
                node.nextCharacterNode[index] = new TrieNode();
            }
            node = node.nextCharacterNode[index];
        }
        node.isEnd=true;
    }

    public static boolean search(String input) {
        TrieNode node = root;
        for(int i=0;i<input.length();i++) {
            int index = input.charAt(i)-'a';
            if(node.nextCharacterNode[index]==null) {
                return false;
            }
            node = node.nextCharacterNode[index];
        }
        return node.isEnd;
    }

    public static boolean isPrefixThere (String prefix) {
        TrieNode node = root;
        for(int i=0;i<prefix.length();i++) {
            int index = prefix.charAt(i)-'a';
            if(node.nextCharacterNode[index]==null) {
                return false;
            }
            node = node.nextCharacterNode[index];
        }
        return true;
    }

    public static void main(String[] args) {
        String input1 = "apple";
        insert(input1);
        String input2 = "app";
        insert(input2);
        String input3 = "bat";
        insert(input3);
        String input4 = "ball";
        insert(input4);
        String input5 = "abhishek";
        insert(input5);
        System.out.println(search("app"));
        System.out.println(search("abhi"));
        System.out.println(isPrefixThere("abhi"));

    }


    class TrieNode2 {
        TrieNode2[] children = new TrieNode2[26];
        int countIsEnd;
        int countPrefixIndex;
    }

    public class Trie {
        private TrieNode2 rootNode;

        public Trie() {
            rootNode = new TrieNode2();
        }

        public void insert(String word) {
            // Write your code here.
            TrieNode2 node = rootNode;
            for(int i=0;i<word.length();i++) {
                int index = word.charAt(i)-'a';
                if(node.children[index]==null) {
                    node.children[index] = new TrieNode2();
                }
                node = node.children[index];
                node.countPrefixIndex ++;
            }
            node.countIsEnd++;
        }

        public int countWordsEqualTo(String word) {
            // Write your code here.
            TrieNode2 node = rootNode;
            for(int i=0;i<word.length();i++) {
                int index = word.charAt(i)-'a';
                if(node.children[index]==null) {
                    return 0;
                }
                node = node.children[index];
            }
            return node.countIsEnd;
        }

        public int countWordsStartingWith(String word) {
            // Write your code here.
            TrieNode2 node = rootNode;
            for(int i=0;i<word.length();i++) {
                int index = word.charAt(i)-'a';
                if(node.children[index]==null) {
                    return 0;
                }
                node = node.children[index];
            }
            return node.countPrefixIndex;
        }

        private void dfs(String word , int index,int len,TrieNode2 node) {
            if(index == len) {
                return;
            }
            int currentNodeIndex = word.charAt(index)-'a';
            TrieNode2 currentNode = node.children[currentNodeIndex];
            dfs(word,index+1,len,currentNode);
            currentNode.countPrefixIndex--;
            currentNode.countIsEnd--;
            if(currentNode.countPrefixIndex==0) {
                node.children[currentNodeIndex]=null;
            }
        }

        public void erase(String word) {
            // Write your code here.
            TrieNode2 node = rootNode;
            int len = word.length();
            dfs(word,0,len,node);
        }

        public static void main(String[] args) {

        }



    }




}
