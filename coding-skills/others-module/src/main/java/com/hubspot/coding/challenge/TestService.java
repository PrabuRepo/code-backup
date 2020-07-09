package com.hubspot.coding.challenge;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestService {

	public static void main(String[] args) {
		try {
			Service ob = new Service();
			System.out.println("Schdule Meetings: ");
			ob.scheduleEvent(mockPartners());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static List<Partner> mockPartners() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Partners partners = null;
		partners = mapper.readValue(getRequest(), Partners.class);
		return partners.getPartners();
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
