package org.exercises.linkedlist;

import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

public class LinkedListSolution {

    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        linkedList.insertNode(1);
        linkedList.insertNode(2);
        linkedList.insertNode(3);

        insertNodeAtPosition(linkedList.head, 6, 0);
        hasCycleV1(linkedList.head);
    }

    /**
     * Insert a node at a specific position in a linked list <a href="https://www.hackerrank.com/challenges/insert-a-node-at-a-specific-position-in-a-linked-list/problem">HackerRank Link</a>
     * @param head Head of the linked list
     * @param data Data to insert in the new node
     * @param pos Desired position to insert the node
     * @return Head of the list
     */
    public static Node insertNodeAtPosition(Node head, int data, int pos) {
        Node newNode = new Node(data);

        if (pos == 0) {
            newNode.next = head;
            return newNode;
        }

        Node current = head;
        int position = 0;
        while (current.next != null) {
            if (position + 1 == pos) {
                newNode.next = current.next;
                current.next = newNode;
                break;
            }
            current = current.next;
            position++;
        }

        return head;
    }

    /**
     * Cycle Detection <a href="https://www.hackerrank.com/challenges/detect-whether-a-linked-list-contains-a-cycle/problem">HackerRank Link</a>
     * @param head Head of the linked list
     * @return True if the linked list contains a cycle. False otherwise.
     */
    public static boolean hasCycleV1(Node head) {
        if (head == null || head.next == null) {
            return false;
        }

        Map<Node, Integer> visitedNodes = new HashMap<>();
        Node current = head;
        visitedNodes.put(current, 0);
        int id = 1;
        while(current.next != null) {
            if (visitedNodes.containsKey(current.next)) {
                return true;
            }

            visitedNodes.put(current.next, id);
            current = current.next;
            id++;
        }

        return false;
    }

    /**
     * This is Floyd's Cycle detection algorithm. This is based on a fast and slow node approach.
     */
    public static boolean hasCycleV2(Node head) {
        if (head == null || head.next == null) {
            return false;
        }

        Node fast = head;
        Node slow = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (fast == slow) {
                return true;
            }
        }

        return false;
    }


    @ToString
    public static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
        }
    }


    @ToString
    public static class LinkedList {
        Node head;
        Node tail;

        public void insertNode(int nodeData) {
            Node node = new Node(nodeData);

            if (this.head == null) {
                this.head = node;
            } else {
                this.tail.next = node;
            }

            this.tail = node;
        }
    }

}
