package com.hubspot.coding.challenge;

public class Key {
	public String	startingDate;
	public String	countryName;

	public Key(String stDate, String name) {
		this.startingDate = stDate;
		this.countryName = name;
	}

	@Override
	public int hashCode() {
		String date = startingDate == null ? "null" : startingDate.toString();
		return countryName.hashCode() + date.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		Key ob = (Key) obj;
		String currDate = this.startingDate == null ? "null" : this.startingDate.toString();
		String obDate = startingDate == null ? "null" : startingDate.toString();
		return ob.countryName.equals(this.countryName) && obDate.compareTo(currDate) == 0;
	}
}
