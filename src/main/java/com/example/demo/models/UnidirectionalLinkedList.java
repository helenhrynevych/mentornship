package com.example.demo.models;

public class UnidirectionalLinkedList {
	private Node head;

	public int getFirst() {
		return this.head.getData();
	}
	public static UnidirectionalLinkedList insert(UnidirectionalLinkedList list,
	                                              Node node) {
		if (list.head == null) {
			list.head = node;
		} else {
			Node last = list.head;
			while (last.getNext() != null) {
				last = last.getNext();
			}
			last.setNext(node);
		}
		return list;
	}

	public static void printList(UnidirectionalLinkedList list) {
		Node currNode = list.head;
		System.out.print("UnidirectionalLinkedList: ");
		while (currNode != null) {
			System.out.println(currNode.getData() + " ");
			currNode = currNode.getNext();
		}
	}

}
