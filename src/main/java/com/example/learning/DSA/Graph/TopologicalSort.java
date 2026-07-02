package com.example.learning.DSA.Graph;

import java.util.*;

public class TopologicalSort {
    public static void main(String[] args) {

    }

    public String foreignDictionary(String[] words) {
        int len = words.length;
        Map<Character, Set<Character>> keyIsParentAndValueIsChild = new HashMap<>();
        Map<Character,Integer> keyIsCharAndValueIsIndegree = new HashMap<>();
        for (String word : words) {
            for (char c : word.toCharArray()) {
                keyIsCharAndValueIsIndegree.putIfAbsent(c, 0);
            }
        }

        for(int i=0;i<len-1;i++) {
            String currentWord = words[i];
            String nextWord = words[i+1];
            if (currentWord.length() > nextWord.length() &&
                    currentWord.startsWith(nextWord)) {
                return "";
            }
            int len2 = Math.min(currentWord.length(), nextWord.length());

            for(int j=0;j<len2;j++) {
                char small =currentWord.charAt(j);
                char big = nextWord.charAt(j);
                if(small!=big) {
                    if(keyIsParentAndValueIsChild.computeIfAbsent(small, k -> new HashSet<>()).add(big))
                    {
                        keyIsCharAndValueIsIndegree.put(big, keyIsCharAndValueIsIndegree.getOrDefault(big, 0) + 1);
                    }
                    break;
                }
            }
        }

        ArrayDeque<Character> queue = new ArrayDeque<>();
        for(Map.Entry<Character,Integer> map:keyIsCharAndValueIsIndegree.entrySet()) {
            Character key = map.getKey();
            Integer value = map.getValue();
            if(value==0) {
                queue.addLast(key);
            }
        }


        StringBuilder ans = new StringBuilder();

        while (!queue.isEmpty()) {
            int size = queue.size();
            for(int i=1;i<=size;i++) {
                Character poll = queue.removeFirst();
                ans.append(poll);
                for(Character character: keyIsParentAndValueIsChild.getOrDefault(poll,Collections.emptySet())) {
                    int value = keyIsCharAndValueIsIndegree.get(character)-1;
                    keyIsCharAndValueIsIndegree.put(character,value);
                    if(value==0) {
                        queue.addFirst(character);
                    }
                }
             }
        }

        if (ans.length() != keyIsCharAndValueIsIndegree.size()) {
            return "";
        }

        return ans.toString();

    }
}
