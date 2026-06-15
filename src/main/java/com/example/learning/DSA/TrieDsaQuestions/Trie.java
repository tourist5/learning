package com.example.learning.DSA.TrieDsaQuestions;

class Trie {
    public static void main(String[] args) {

    }

    public static class TrieDataStructure {
        private TrieDataStructure[] storeTheReferenceOfNextNode;
        private boolean isEndOfSomeString;
        TrieDataStructure() {
            this.storeTheReferenceOfNextNode = new TrieDataStructure[26];
            this.isEndOfSomeString = false;
        }

        public TrieDataStructure[] getStoreTheReferenceOfNextNode() {
            return storeTheReferenceOfNextNode;
        }

        public void setStoreTheReferenceOfNextNode(TrieDataStructure[] storeTheReferenceOfNextNode) {
            this.storeTheReferenceOfNextNode = storeTheReferenceOfNextNode;
        }

        public boolean isEndOfSomeString() {
            return isEndOfSomeString;
        }

        public void setEndOfSomeString(boolean endOfSomeString) {
            isEndOfSomeString = endOfSomeString;
        }
    }

    private TrieDataStructure root;

    public Trie() {
        this.root = new TrieDataStructure();
    }

    public void insert(String word) {
        TrieDataStructure currentNode = this.root;
        int len = word.length();
        int i = 0;
        while (i<len) {
            int index = word.charAt(i)-'a';
            if(currentNode.storeTheReferenceOfNextNode[index]==null) {
                TrieDataStructure nextNode = new TrieDataStructure();
                currentNode.storeTheReferenceOfNextNode[index] = nextNode;
            }
            currentNode = currentNode.storeTheReferenceOfNextNode[index];
            i++;
        }
        currentNode.isEndOfSomeString = true;
    }

    public boolean search(String word) {
        TrieDataStructure currentNode = this.root;
        int len = word.length();
        int i = 0;
        while(i<len) {
            int index = word.charAt(i)-'a';
            if(currentNode.storeTheReferenceOfNextNode[index] == null ){
                return false;
            }
            currentNode = currentNode.storeTheReferenceOfNextNode[index];
            i++;
        }
        return currentNode.isEndOfSomeString;
    }

    public boolean startsWith(String prefix) {
        TrieDataStructure currentNode = this.root;
        int len = prefix.length();
        int i = 0;
        while (i<len) {
            int index = prefix.charAt(i)-'a';
            if(currentNode.storeTheReferenceOfNextNode[index]==null) {
                return false;
            }
            i++;
            currentNode = currentNode.storeTheReferenceOfNextNode[index];
        }
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */