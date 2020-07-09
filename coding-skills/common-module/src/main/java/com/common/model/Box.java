package com.common.model;

public class Box {
	public int	height;
	public int	length;
	public int	width;

	public Box() {
	}

	public Box(int height, int length, int width) {
		this.height = height;
		this.length = length;
		this.width = width;
	}
	/*
		public int compareTo(Box o) {
			int area = o.length * o.width;
			int thisArea = this.length * this.width;
			return area - thisArea;
		}*/
}
