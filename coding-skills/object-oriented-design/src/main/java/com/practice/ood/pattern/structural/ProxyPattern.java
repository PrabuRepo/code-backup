package com.practice.ood.pattern.structural;

import java.util.ArrayList;
import java.util.List;

public class ProxyPattern {
	public static void main(String[] args) {
		Internet internet = new ProxyInternet();
		try {
			internet.connectTo("geeksforgeeks.org");
			internet.connectTo("abc.com");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}

interface Internet {
	public void connectTo(String serverhost) throws Exception;
}

class RealInternet implements Internet {
	@Override
	public void connectTo(String serverhost) {
		System.out.println("Connecting to " + serverhost);
	}
}

class ProxyInternet implements Internet {
	private Internet			internet	= new RealInternet();
	private static List<String>	bannedSites;

	static {
		bannedSites = new ArrayList<String>();
		bannedSites.add("abc.com");
		bannedSites.add("def.com");
		bannedSites.add("ijk.com");
		bannedSites.add("lnm.com");
	}

	@Override
	public void connectTo(String serverhost) throws Exception {
		if (bannedSites.contains(serverhost.toLowerCase())) {
			throw new Exception("Access Denied");
		}

		internet.connectTo(serverhost);
	}

}