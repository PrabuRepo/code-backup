package hubspot.coding.challenge;

import java.util.LinkedHashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class EventPlanner {

	public static void main(String[] args) {
		LinkedHashMap<String, LinkedHashMap<String, Partners>> countryMap = new LinkedHashMap<String, LinkedHashMap<String, Partners>>();
		JSONParser parser = new JSONParser();
		try {

			String jsonResponse = getRequest();

			JSONObject jsonObj = (JSONObject) parser.parse(jsonResponse);

			// Loop through the partner objects
			JSONArray partnerArray = (JSONArray) jsonObj.get("partners");
			for (int i = 0; i < partnerArray.size(); i++) {
				JSONObject partnerObject = (JSONObject) partnerArray.get(i);
				String country = (String) partnerObject.get("country");
				country = country.trim();
				String email = (String) partnerObject.get("email");
				JSONArray dateArray = (JSONArray) partnerObject.get("availableDates");

				// Make an entry into the hashmap for each country so that we will account for
				// countries with 0 attendees in the result
				if (countryMap.get(country) == null) {
					LinkedHashMap<String, Partners> dateMap = new LinkedHashMap<String, Partners>();
					countryMap.put(country, dateMap);
				}

				// Loop through the date array
				for (int j = 0; j < dateArray.size() - 1; j++) {
					// The below fn call will validate if the person can attend the event for the
					// two consecutive days
					boolean isAvailableBothDays = EventPlannerUtil.isNextDay((String) dateArray.get(j),
							(String) dateArray.get(j + 1));
					if (isAvailableBothDays) {
						// Populate the dateMap with Date as key and Partner as object. Increment count
						// in the Partner object every time a person can attend the event on this date.
						if (countryMap.get(country) != null) {
							LinkedHashMap<String, Partners> dateMap = countryMap.get(country);
							if (dateMap.containsKey((String) dateArray.get(j))) {
								int count = dateMap.get((String) dateArray.get(j)).getCount();
								count++;
								dateMap.get((String) dateArray.get(j)).setCount(count);
								dateMap.get((String) dateArray.get(j)).getEmail().add(email);

							} else {
								Partners partners = new Partners();
								partners.setCount(1);
								partners.getEmail().add(email);
								dateMap.put((String) dateArray.get(j), partners);
							}

						} else {
							LinkedHashMap<String, Partners> dateMap = new LinkedHashMap<String, Partners>();
							Partners partners = new Partners();
							partners.setCount(1);
							partners.getEmail().add(email);
							dateMap.put((String) dateArray.get(j), partners);
							countryMap.put(country, dateMap);
						}
					}
				}
			}

			// Creating the response json starts

			JSONArray jsonObjectCountries = new JSONArray();
			JSONObject jsonObjectCountry = new JSONObject();
			JSONObject resultObject = new JSONObject();
			for (String key : countryMap.keySet()) {
				jsonObjectCountry = new JSONObject();
				if (countryMap.get(key) == null || countryMap.get(key).size() == 0) {
					// Build an object with null as startdate if there are no days available for this country
					jsonObjectCountry.put("attendeeCount", 0);
					jsonObjectCountry.put("attendees", new JSONArray());
					jsonObjectCountry.put("name", key);
					jsonObjectCountry.put("startDate", null);
				} else {
					// Loop through the dateMap to find the date with max Count in the Partner object.
					LinkedHashMap<String, Partners> dateMap = countryMap.get(key);
					String resultDate = null;
					int maxCount = 0;
					for (String dateKey : dateMap.keySet()) {
						if (resultDate == null) {
							resultDate = dateKey;
							maxCount = dateMap.get(dateKey).getCount();
						} else {
							if (dateMap.get(dateKey).getCount() > maxCount) {
								resultDate = dateKey;
								maxCount = dateMap.get(dateKey).getCount();
							}
						}

					}
					jsonObjectCountry.put("attendeeCount", maxCount);
					JSONArray emailArray = new JSONArray();
					for (String email : dateMap.get(resultDate).getEmail()) {
						emailArray.add(email);
					}
					jsonObjectCountry.put("attendees", emailArray);
					jsonObjectCountry.put("name", key);
					jsonObjectCountry.put("startDate", resultDate);

				}
				jsonObjectCountries.add(jsonObjectCountry);
			}
			resultObject.put("countries", jsonObjectCountries);

			System.out.println(resultObject.toJSONString());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static String getRequest() {
		return "{\r\n" + "    \"partners\": [\r\n" + "        {\r\n" + "            \"firstName\": \"Darin\",\r\n"
				+ "            \"lastName\": \"Daignault\",\r\n"
				+ "            \"email\": \"ddaignault@hubspotpartners.com\",\r\n"
				+ "            \"country\": \"United States\",\r\n" + "            \"availableDates\": [\r\n"
				+ "                \"2017-05-03\",\r\n" + "                \"2017-05-06\"\r\n" + "            ]\r\n"
				+ "        },\r\n" + "        {\r\n" + "            \"firstName\": \"Crystal\",\r\n"
				+ "            \"lastName\": \"Brenna\",\r\n"
				+ "            \"email\": \"cbrenna@hubspotpartners.com\",\r\n"
				+ "            \"country\": \"Ireland\",\r\n" + "            \"availableDates\": [\r\n"
				+ "                \"2017-04-27\",\r\n" + "                \"2017-04-29\",\r\n"
				+ "                \"2017-04-30\"\r\n" + "            ]\r\n" + "        },\r\n" + "        {\r\n"
				+ "            \"firstName\": \"Janyce\",\r\n" + "            \"lastName\": \"Gustison\",\r\n"
				+ "            \"email\": \"jgustison@hubspotpartners.com\",\r\n"
				+ "            \"country\": \"Spain\",\r\n" + "            \"availableDates\": [\r\n"
				+ "                \"2017-04-29\",\r\n" + "                \"2017-04-30\",\r\n"
				+ "                \"2017-05-01\"\r\n" + "            ]\r\n" + "        },\r\n" + "        {\r\n"
				+ "            \"firstName\": \"Tifany\",\r\n" + "            \"lastName\": \"Mozie\",\r\n"
				+ "            \"email\": \"tmozie@hubspotpartners.com\",\r\n"
				+ "            \"country\": \"Spain\",\r\n" + "            \"availableDates\": [\r\n"
				+ "                \"2017-04-28\",\r\n" + "                \"2017-04-29\",\r\n"
				+ "                \"2017-05-01\",\r\n" + "                \"2017-05-04\"\r\n" + "            ]\r\n"
				+ "        },\r\n" + "        {\r\n" + "            \"firstName\": \"Temple\",\r\n"
				+ "            \"lastName\": \"Affelt\",\r\n"
				+ "            \"email\": \"taffelt@hubspotpartners.com\",\r\n"
				+ "            \"country\": \"Spain\",\r\n" + "            \"availableDates\": [\r\n"
				+ "                \"2017-04-28\",\r\n" + "                \"2017-04-29\",\r\n"
				+ "                \"2017-05-02\",\r\n" + "                \"2017-05-04\"\r\n" + "            ]\r\n"
				+ "        },\r\n" + "        {\r\n" + "            \"firstName\": \"Robyn\",\r\n"
				+ "            \"lastName\": \"Yarwood\",\r\n"
				+ "            \"email\": \"ryarwood@hubspotpartners.com\",\r\n"
				+ "            \"country\": \"Spain\",\r\n" + "            \"availableDates\": [\r\n"
				+ "                \"2017-04-29\",\r\n" + "                \"2017-04-30\",\r\n"
				+ "                \"2017-05-02\",\r\n" + "                \"2017-05-03\"\r\n" + "            ]\r\n"
				+ "        },\r\n" + "        {\r\n" + "            \"firstName\": \"Shirlene\",\r\n"
				+ "            \"lastName\": \"Filipponi\",\r\n"
				+ "            \"email\": \"sfilipponi@hubspotpartners.com\",\r\n"
				+ "            \"country\": \"Spain\",\r\n" + "            \"availableDates\": [\r\n"
				+ "                \"2017-04-30\",\r\n" + "                \"2017-05-01\"\r\n" + "            ]\r\n"
				+ "        },\r\n" + "        {\r\n" + "            \"firstName\": \"Oliver\",\r\n"
				+ "            \"lastName\": \"Majica\",\r\n"
				+ "            \"email\": \"omajica@hubspotpartners.com\",\r\n"
				+ "            \"country\": \"Spain\",\r\n" + "            \"availableDates\": [\r\n"
				+ "                \"2017-04-28\",\r\n" + "                \"2017-04-29\",\r\n"
				+ "                \"2017-05-01\",\r\n" + "                \"2017-05-03\"\r\n" + "            ]\r\n"
				+ "        },\r\n" + "        {\r\n" + "            \"firstName\": \"Wilber\",\r\n"
				+ "            \"lastName\": \"Zartman\",\r\n"
				+ "            \"email\": \"wzartman@hubspotpartners.com\",\r\n"
				+ "            \"country\": \"Spain\",\r\n" + "            \"availableDates\": [\r\n"
				+ "                \"2017-04-29\",\r\n" + "                \"2017-04-30\",\r\n"
				+ "                \"2017-05-02\",\r\n" + "                \"2017-05-03\"\r\n" + "            ]\r\n"
				+ "        },\r\n" + "        {\r\n" + "            \"firstName\": \"Eugena\",\r\n"
				+ "            \"lastName\": \"Auther\",\r\n"
				+ "            \"email\": \"eauther@hubspotpartners.com\",\r\n"
				+ "            \"country\": \"United States\",\r\n" + "            \"availableDates\": [\r\n"
				+ "                \"2017-05-04\",\r\n" + "                \"2017-05-09\"\r\n" + "            ]\r\n"
				+ "        }\r\n" + "    ]\r\n" + "}";
	}
}