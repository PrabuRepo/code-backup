package com.common.model;

public class Point {
	public int	x;
	public int	y;
	public int	dist;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Point(int x, int y, int dist) {
		this.x = x;
		this.y = y;
		this.dist = dist;
	}
}
