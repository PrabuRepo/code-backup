package com.hubspot.coding.challenge;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Service {

	public void scheduleEvent(List<Partner> partners) {
		List<Country> countries = new ArrayList<>();
		try {
			// Segregate based on country
			Map<String, List<Partner>> countryMap = new HashMap<>();
			for (Partner partener : partners) {
				if (!countryMap.containsKey(partener.getCountry()))
					countryMap.put(partener.getCountry(), new ArrayList<>());
				countryMap.get(partener.getCountry()).add(partener);
			}

			// Check the preferable dates for each country
			for (Entry<String, List<Partner>> entry : countryMap.entrySet()) {
				Map<String, List<String>> dateMap = new HashMap<>();
				// Find Continuous date & corresponding emails
				for (Partner partner : entry.getValue())
					findContinuousDate(partner, dateMap);

				// Populate the Countries
				Country country = populateCountry(entry.getKey(), dateMap);
				countries.add(country);
			}

			// Print the Result
			printResult(countries);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void findContinuousDate(Partner partner, Map<String, List<String>> dateMap) throws ParseException {
		List<String> dates = partner.getAvailableDates();
		if (dates.size() == 0) return;

		for (int i = 1; i < dates.size(); i++) {
			Date prev = new SimpleDateFormat("yyyy-MM-dd").parse(dates.get(i - 1));
			Date curr = new SimpleDateFormat("yyyy-MM-dd").parse(dates.get(i));
			if (curr.compareTo(addOneDays(prev)) == 0) { // Prev = curr -> consecutive days present here
				if (dateMap.get(dates.get(i - 1)) == null) dateMap.put(dates.get(i - 1), new ArrayList<>());
				dateMap.get(dates.get(i - 1)).add(partner.getEmail());
			}
		}
	}

	public Country populateCountry(String name, Map<String, List<String>> dateMap) {
		Country country = new Country();
		country.setName(name);
		if (dateMap.size() == 0) {
			country.setStartDate(null);
			country.setAttendeeCount(0);
			country.setAttendees(new ArrayList<>());
		} else {
			// Find max count
			String maxDate = null;
			int maxSize = 0;
			for (Map.Entry<String, List<String>> entry : dateMap.entrySet()) {
				if (entry.getValue().size() > maxSize) {
					maxDate = entry.getKey();
					maxSize = entry.getValue().size();
				}
			}
			country.setStartDate(maxDate);
			country.setAttendeeCount(maxSize);
			country.setAttendees(dateMap.get(maxDate));
		}
		return country;
	}

	public Date addOneDays(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, 1);
		return c.getTime();
	}

	public void printResult(List<Country> countries) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		String countryList = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(countries);
		System.out.println(countryList);
	}
}