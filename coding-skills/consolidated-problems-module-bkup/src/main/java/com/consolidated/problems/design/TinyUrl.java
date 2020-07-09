package com.consolidated.problems.design;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class TinyUrl {
	public static void main(String[] args) {
		System.out.println("Approach4: Tiny Url: ");
		Codec4 ob4 = new Codec4();
		String longUrl = "https://leetcode.com/problems/design-tinyurl";

		String tinyUrl = ob4.encode(longUrl);
		System.out.println("Encoded Url: " + tinyUrl);
		System.out.println("Decoded Url: " + ob4.encode(tinyUrl));
	}
}

// Approach 1- Using simple counter
class Codec1 {
	Map<Integer, String>	map	= new HashMap<>();
	int						i	= 0;

	// Encodes a URL to a shortened URL.
	public String encode(String longUrl) {
		map.put(i, longUrl);
		return "http://tinyurl.com/" + i++;
	}

	// Decodes a shortened URL to its original URL.
	public String decode(String shortUrl) {
		int key = Integer.parseInt(shortUrl.replace("http://tinyurl.com/", ""));
		return map.get(key);
	}
}

// Approach 2- using hashcode
class Codec2 {
	Map<Integer, String> map = new HashMap<>();

	public String encode(String longUrl) {
		map.put(longUrl.hashCode(), longUrl);
		return "http://tinyurl.com/" + longUrl.hashCode();
	}

	public String decode(String shortUrl) {
		return map.get(Integer.parseInt(shortUrl.replace("http://tinyurl.com/", "")));
	}
}

// Approach 3- using random function
class Codec3 {
	Map<Integer, String>	map	= new HashMap<>();
	Random					r	= new Random();
	int						key	= r.nextInt(10000);

	public String encode(String longUrl) {
		while (map.containsKey(key))
			key = r.nextInt(10000);
		map.put(key, longUrl);
		return "http://tinyurl.com/" + key;
	}

	public String decode(String shortUrl) {
		return map.get(Integer.parseInt(shortUrl.replace("http://tinyurl.com/", "")));
	}

}

class Codec4 {
	public String encode(String longUrl) {
		return Base64.getUrlEncoder().encodeToString(longUrl.getBytes(StandardCharsets.UTF_8));
	}

	public String decode(String shortUrl) {
		return new String(Base64.getUrlDecoder().decode(shortUrl));
	}
}