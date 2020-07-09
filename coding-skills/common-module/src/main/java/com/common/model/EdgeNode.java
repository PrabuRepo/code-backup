package com.common.model;

public class EdgeNode {
	public int	src;
	public int	dest;
	public int	weight;

	public EdgeNode() {

	}

	public EdgeNode(int s, int d, int w) {
		this.src = s;
		this.dest = d;
		this.weight = w;
	}

}
