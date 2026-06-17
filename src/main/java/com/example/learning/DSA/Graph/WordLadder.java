package com.example.learning.DSA.Graph;

import org.springframework.util.CollectionUtils;

import java.util.*;

public class WordLadder {
    public static void main(String[] args) {
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = List.of("hot", "dot", "dog", "lot", "log", "cog");
        System.out.println(ladderLength(beginWord, endWord, wordList));
    }


    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        int lenWord = beginWord.length();
        int wordSize = wordList.size();

        if (!wordList.contains(endWord)) {
            return 0;
        }

        Map<String, List<String>> graph = new HashMap<>();

        for (int i = -1; i < wordSize; i++) {
            String element;

            if (i == -1) {
                element = beginWord;
            } else {
                element = wordList.get(i);
            }

            for (int j = 0; j < wordSize; j++) {
                if (i == j) {
                    continue;
                }

                String comparing = wordList.get(j);

                int diff = 0;
                for (int k = 0; k < lenWord; k++) {
                    if (element.charAt(k) != comparing.charAt(k)) {
                        diff++;
                    }
                }

                if (diff == 1) {
                    graph.computeIfAbsent(element,
                            x -> new ArrayList<>()).add(comparing);
                }
            }
        }

        // BFS
        ArrayDeque<String> bfsQueue = new ArrayDeque<>();
        bfsQueue.addLast(beginWord);

        Set<String> visited = new HashSet<>();
        visited.add(beginWord);

        int level = 1;

        while (!bfsQueue.isEmpty()) {
            int size = bfsQueue.size();

            for (int i = 0; i < size; i++) {
                String current = bfsQueue.removeFirst();

                if (current.equals(endWord)) {
                    return level;
                }

                List<String> neighbours = graph.get(current);

                if (neighbours != null) {
                    for (String neighbour : neighbours) {
                        if (!visited.contains(neighbour)) {
                            visited.add(neighbour);
                            bfsQueue.addLast(neighbour);
                        }
                    }
                }
            }

            level++;
        }

        return 0;
    }

}
