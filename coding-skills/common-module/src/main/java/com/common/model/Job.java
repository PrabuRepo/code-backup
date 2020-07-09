package com.common.model;

public class Job {
	// Below Attributes for Job Sequence Problem
	// Job id
	public char id;
	// Deadline of job
	public int deadLine;
	// Profit if job is over before or on deadline
	public int profit;

	// Below Attributes for Activity Selection Problem
	public int startTime;

	public int endTime;

	public Job(int s, int e) {
		this.startTime = s;
		this.endTime = e;
	}

	public Job(char id, int d, int p) {
		this.id = id;
		this.deadLine = d;
		this.profit = p;
	}
}
