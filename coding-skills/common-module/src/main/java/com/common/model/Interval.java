package com.common.model;

public class Interval {
	public int	start;
	public int	end;
	public int	order;

	public Interval(int s, int e) {
		this.start = s;
		this.end = e;
	}

	public Interval(int s, int e, int o) {
		this.start = s;
		this.end = e;
		this.order = o;
	}
}
