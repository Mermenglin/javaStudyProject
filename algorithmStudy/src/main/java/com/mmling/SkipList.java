package com.mmling;


import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Random;

/**
 * 跳跃表
 *
 * @auther Meimengling
 * @date 2020-8-4 11:53
 */
public class SkipList<K extends Comparable<K>, V> {

    @Getter
    @Setter
    static final class Node<K extends Comparable<K>, V> {
        private K key;
        private V value;
        private Node<K, V> up, down, pre, next;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", value=" + value +
                    ", up=" + (up == null ? "null" : up.hashCode()) +
                    ", down=" + (down == null ? "null" : down.hashCode()) +
                    ", pre=" + (pre == null ? "null" : pre.hashCode()) +
                    ", next=" + (next == null ? "null" : next.hashCode()) +
                    '}';
        }
    }

    private Node<K, V> head;

    private Integer level = 0;

    private Integer length = 0;

    private Random random = new Random(System.currentTimeMillis());

    public SkipList() {
        createNewLevel();
    }

    public void put(K key, V value) {
        if (key == null || value == null) {
            return;
        }
        Node<K, V> newNode = new Node<>(key, value);
        insertNode(newNode);
    }

    private void insertNode(Node<K, V> newNode) {
        Node<K, V> curNode = findNode(newNode.getKey());
        if (curNode.getKey() == null) {
            insertNode(curNode, newNode);
        } else if (curNode.getKey().compareTo(newNode.getKey()) == 0) {
            curNode.setValue(newNode.getValue());
            return;
        } else {
            insertNode(curNode, newNode);
        }

        int currentLevel = 1;
        Node<K, V> oldTop = newNode;
        while (random.nextInt(100) < 35) {
            Node<K, V> newTop = new Node<>(newNode.getKey(), null);
            if (currentLevel >= level) {
                createNewLevel();
            }
            while (curNode.getPre() != null && curNode.getUp() == null) {
                curNode = curNode.getPre();
            }
            if (curNode.getUp() == null) {
                continue;
            }
            curNode = curNode.getUp();
            Node<K, V> curNodeNext = curNode.getNext();

            curNode.setNext(newTop);
            newTop.setNext(curNodeNext);
            newTop.setPre(curNode);
//            curNodeNext.setPre(newTop);

            newTop.setDown(oldTop);
            oldTop.setUp(newTop);

            oldTop = newTop;
            currentLevel++;
        }
    }

    private void insertNode(Node<K, V> curNode, Node<K, V> newNode) {
        Node<K, V> curNodeNext = curNode.getNext();
        newNode.setNext(curNodeNext);
        if (curNodeNext != null) {
            curNodeNext.setPre(newNode);
        }
        curNode.setNext(newNode);
        newNode.setPre(curNode);

        this.length++;
    }

    public V get(K key) {
        Node<K, V> node = findNode(key);
        if (node.getKey().compareTo(key) == 0) {
            return node.getValue();
        }
        return null;
    }

    /**
     * @param key
     * @return
     */
    private Node<K, V> findNode(K key) {
        Node<K, V> curNode = this.head;
        for (; ; ) {
            while (curNode.getNext() != null && curNode.getNext().getKey().compareTo(key) <= 0) {
                curNode = curNode.getNext();
            }
            if (curNode.getDown() != null) {
                curNode = curNode.getDown();
            } else {
                break;
            }
        }
        return curNode;
    }

    private void createNewLevel() {
        Node<K, V> newHead = new Node<>(null, null);
        if (this.head == null) {
            this.head = newHead;
            this.level++;
            return;
        }

        this.head.setUp(newHead);
        newHead.setDown(this.head);
        this.head = newHead;
        this.level++;
    }

    public void print() {
        Node<K, V> curI = this.head;
        String[][] strings = new String[level][length + 1];
        for (String[] string : strings) {
            Arrays.fill(string, "0");
        }
        while (curI.getDown() != null) {
            curI = curI.getDown();
        }
        System.out.println("level:" + level + "_" + "length:" + length);
        int i = 0;
        while (curI != null) {
            Node<K, V> curJ = curI;
            int j = level - 1;
            while (curJ != null) {
                strings[j][i] = String.valueOf(curJ.getKey());
                if (curJ.getUp() == null) {
                    break;
                }
                curJ = curJ.getUp();
                j--;
            }
            if (curI.getNext() == null) {
                break;
            }
            curI = curI.getNext();
            i++;
        }
        for (String[] string : strings) {
            System.out.println(Arrays.toString(string));
        }
    }

    public static void main(String[] args) {
        SkipList<Integer, String> skipList = new SkipList<>();

        skipList.put(2, "a");
        skipList.put(1, "B");
        skipList.put(3, "C");
        skipList.put(2, "A");

        skipList.print();

        System.out.println(skipList.get(2));
    }

}
