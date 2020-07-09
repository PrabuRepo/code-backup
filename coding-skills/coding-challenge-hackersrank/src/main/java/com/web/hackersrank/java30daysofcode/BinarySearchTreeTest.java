package com.web.hackersrank.java30daysofcode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Node {
	int data;
	Node left, right;

	public Node(int x) {
		this.data = x;
		left = right = null;
	}
}

public class BinarySearchTreeTest {

	public static void main(String[] arge) {
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		Node root = null;
		while (t-- > 0) {
			root = insert(in.nextInt(), root);
		}
		System.out.println("\nIn Order Traversal:");
		inOrderTraversal(root);
		System.out.println("Pre Order Traversal:");
		preOrderTraversal(root);
		System.out.println("\nPost Order Traversal:");
		postOrderTraversal(root);
		System.out.println("\nLevel Order Traaversal:");
		levelOrderTraversal(root);
		System.out.println("\nHeight of a tree:"+getHeight(root));
	}

	public static Node insert(int n, Node root) {
		Node newNode = new Node(n);
		if (root == null)
			return newNode;

		Node temp = root;
		while (temp != null) {
			if (n < temp.data) {
				if (temp.left == null) {
					temp.left = newNode;
					break;
				} else
					temp = temp.left;
			}
			if (n >= temp.data) {
				if (temp.right == null) {
					temp.right = newNode;
					break;
				} else
					temp = temp.right;
			}
		}
		return root;
	}
	//Recursive Approach
	public static Node insertNode(Node root,int data){
        if(root==null){
            return new Node(data);
        }
        else{
            Node cur;
            if(data<=root.data){
                cur=insertNode(root.left,data);
                root.left=cur;
            }
            else{
                cur=insertNode(root.right,data);
                root.right=cur;
            }
            return root;
        }
    }

	public int delete(int n, Node root) {
		return 0;
	}

	public int find() {
		return 0;
	}

	public static void inOrderTraversal(Node root) {
		if (root != null) {
			inOrderTraversal(root.left);
			System.out.print(root.data + ",");
			inOrderTraversal(root.right);
		}
	}

	public static void preOrderTraversal(Node root) {
		if (root != null) {
			System.out.print(root.data + ",");
			preOrderTraversal(root.left);
			preOrderTraversal(root.right);
		}

	}

	public static void postOrderTraversal(Node root) {
		if (root != null) {
			postOrderTraversal(root.left);
			postOrderTraversal(root.right);
			System.out.print(root.data + ",");
		}
	}
	
	public static void levelOrderTraversal(Node n) {
		if(n!=null) {
			Queue<Node> queue = new LinkedList<>();
			queue.add(n);
			while(!queue.isEmpty()) {
				Node temp = queue.remove();
				System.out.print(temp.data+" ");
				if(temp.left!=null)
					queue.add(temp.left);
				if(temp.right !=null)
					queue.add(temp.right);
			}
			
		}
	}
	
	
	public static int getHeight(Node n) {
		if(n!=null) {
			int left = getHeight(n.left);
			int right = getHeight(n.right);
			return 1+(left>right?left:right);
		}else {
			return -1;
		}
	}

	public int depth(Node n) {
		return -1;
	}
}
