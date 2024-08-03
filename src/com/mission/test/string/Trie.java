package com.mission.test.string;

import java.util.HashMap;
import java.util.Map;

// Prefix Tree
public class Trie {

    Node root;

    public Trie() {
        root = new Node();
    }

    public void insert(String word) {
        Node current = root;
        for (char ch : word.toCharArray()) {
            current.children.putIfAbsent(ch, new Node());
            current = current.children.get(ch);
        }
        current.isEnd = true;
    }

    public boolean search(String word) {
        Node current = root;
        for (char ch : word.toCharArray()) {
            current = current.children.get(ch);
            if (current == null) {
                return false;
            }
        }
        return current.isEnd;
    }

    public boolean startsWith(String prefix) {
        Node current = root;
        for (char ch : prefix.toCharArray()) {
            current = current.children.get(ch);
            if (current == null) {
                return false;
            }
        }
        return true;
    }

    static class Node {

        Map<Character, Node> children;
        // Another efficient way to handle this is using an array as it avoids
        // any collision handling required in map implementation
        // char[] children = new char[26]; // considering lowercase chars only
        boolean isEnd;

        Node() {
            children = new HashMap<>();
        }
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println(trie.search("apple"));   // returns true
        System.out.println(trie.search("app"));     // returns false
        System.out.println(trie.startsWith("app")); // returns true
        trie.insert("app");
        System.out.println(trie.search("app"));     // returns true
    }
}
