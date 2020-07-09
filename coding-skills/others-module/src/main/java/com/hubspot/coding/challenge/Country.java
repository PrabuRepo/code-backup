
package com.hubspot.coding.challenge;

import java.io.Serializable;
import java.util.List;

public class Country implements Serializable {

	private Integer				attendeeCount;
	private List<String>		attendees			= null;
	private String				name;
	private String				startDate;
	private final static long	serialVersionUID	= 4304379748386708871L;

	public Integer getAttendeeCount() {
		return attendeeCount;
	}

	public void setAttendeeCount(Integer attendeeCount) {
		this.attendeeCount = attendeeCount;
	}

	public List<String> getAttendees() {
		return attendees;
	}

	public void setAttendees(List<String> attendees) {
		this.attendees = attendees;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

}
