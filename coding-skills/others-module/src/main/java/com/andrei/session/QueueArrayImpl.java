package com.andrei.session;

import java.util.Arrays;

public class QueueArrayImpl {

	int[] data = new int[10];
	int front =0, rear = -1;
	
	void enqueue(int a) {
		data[++rear] = a;
	}
	
	void dequeue() {
		data[front] = 0;
			front++;
	}
	
	int peek() {
		return data[front];
	}
	
	boolean isEmpty() {
		return (front>rear)? true:false;
	}
	
	public static void main(String[] args) {
		System.out.println("Queue Operations:");
		QueueArrayImpl obj = new QueueArrayImpl();
		System.out.println("Is queue Empty:"+obj.isEmpty());
		obj.enqueue(8);
		obj.dequeue();
		System.out.println("Queue data:"+Arrays.toString(obj.data));
		System.out.println("Is queue Empty:"+obj.isEmpty());
		obj.enqueue(3);
		obj.enqueue(9);
		obj.enqueue(4);
		obj.enqueue(7);
		System.out.println("Queue data:"+Arrays.toString(obj.data));
		System.out.println("Front element:"+obj.peek());
		obj.dequeue();
		obj.dequeue();
		obj.dequeue();
		obj.dequeue();
		System.out.println("Is queue Empty:"+obj.isEmpty());

	}

}
