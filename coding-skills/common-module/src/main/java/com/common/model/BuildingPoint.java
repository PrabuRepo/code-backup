package com.common.model;

public class BuildingPoint implements Comparable<BuildingPoint> {

	public int		x;
	public int		y;			// height value
	public boolean	isStart;	// To find the start & end point of x-axis value

	@Override
	public int compareTo(BuildingPoint ob) {
		/* Sorting should be done based on x value(Increasing Order Sorting). If more than one 'x' element has the same value, then should use below 
		 * 3 tie breaker cases:
		 *   i.  Both are starting point or One start point and another one is end point -> Max y should be first(Decreasing Order Sorting)
		 *   ii. Both are ending point(isStart = false for both)  -> Min y should be first(Increasing Order Sorting)
		 */
		if (this.x != ob.x) // Normal Case: For different x values(Increasing Order Sorting)
			return this.x - ob.x;

		return ((!this.isStart && !ob.isStart) ? this.y - ob.y : ob.y - this.y);
	}

}
