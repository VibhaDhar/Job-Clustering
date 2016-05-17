package com.project;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StateUtils {
	public static Pattern statePattern = Pattern.compile("Alabama|AL|Alaska|AK|Arizona|AZ|Arkansas|AR|California|CA|Colorado|CO|Connecticut|CT|Delaware|DE|Florida|FL|Georgia|GA|Hawaii|HI|Idaho|ID|Illinois|IL|Indiana|IN|Iowa|IA|Kansas|KS|Kentucky|KY|Louisiana|LA|Maine|ME|Maryland|MD|Massachusetts|MA|Michigan|MI|Minnesota|MN|Mississippi|MS|Missouri|MO|Montana|MT|Nebraska|NE|Nevada|NV|New Hampshire|NH|New Jersey|NJ|New Mexico|NM|New York|NY|North Carolina|NC|North Dakota|ND|Ohio|OH|Oklahoma|OK|Oregon|OR|Pennsylvania|PA|Rhode Island|RI|South Carolina|SC|South Dakota|SD|Tennessee|TN|Texas|TX|Utah|UT|Vermont|VT|Virginia|VA|Washington|WA|West Virginia|WV|Wisconsin|WI|Wyoming|WY");
	
	public static String getState(String str)	{
		String state = null;
		if(str != null) {
			Matcher matcher = statePattern.matcher(str);
			if(matcher.find()) {	
				int start = matcher.start();
				int end = matcher.end();
				state = str.substring(start,end);
			}
		}
		return state;
	}
	
	public static Integer getStateScore(String state) {
		if(stateMap.containsKey(state)) {
			return stateMap.get(state);
		}
		return 0;
	}
	
	public static Map<String, Integer> stateMap;
	
	static {
		stateMap = new HashMap<String, Integer>();
		stateMap.put("Alaska", 1000);
		stateMap.put("AK", 1000);
		stateMap.put("Hawaii", 1000);
		stateMap.put("HI", 1000);

		stateMap.put("Maryland", 2000);
		stateMap.put("MD", 2000);
		stateMap.put("Massachusetts", 2000);
		stateMap.put("MA", 2000);
		stateMap.put("Pennsylvania", 2000);
		stateMap.put("PA", 2000);
		stateMap.put("Rhode Island", 2000);
		stateMap.put("RI", 2000);
		stateMap.put("Vermont", 2000);
		stateMap.put("VT", 2000);
		stateMap.put("Connecticut", 2000);
		stateMap.put("CT", 2000);
		stateMap.put("Delaware", 2000);
		stateMap.put("DE", 2000);
		stateMap.put("New Jersey", 2000);
		stateMap.put("NJ", 2000);
		stateMap.put("New York", 2000);
		stateMap.put("NY", 2000);
		stateMap.put("New Hampshire", 2000);
		stateMap.put("NH", 2000);
		stateMap.put("Maine", 2000);
		stateMap.put("ME", 2000);

		stateMap.put("Virginia", 3000);
		stateMap.put("VA", 3000);
		stateMap.put("North Carolina", 3000);
		stateMap.put("NC", 3000);
		stateMap.put("South Carolina", 3000);
		stateMap.put("SC", 3000);
		stateMap.put("Florida", 3000);
		stateMap.put("FL", 3000);
		stateMap.put("Georgia", 3000);
		stateMap.put("GA", 3000);

		stateMap.put("Michigan", 4000);
		stateMap.put("MI", 4000);
		stateMap.put("Indiana", 4000);
		stateMap.put("IN", 4000);
		stateMap.put("Ohio", 4000);
		stateMap.put("OH", 4000);
		stateMap.put("Kentucky", 4000);
		stateMap.put("KY", 4000);
		stateMap.put("West Virginia", 4000);
		stateMap.put("WV", 4000);

		stateMap.put("Missouri", 5000);
		stateMap.put("MO", 5000);
		stateMap.put("Arkansas", 5000);
		stateMap.put("AR", 5000);
		stateMap.put("Louisiana", 5000);
		stateMap.put("LA", 5000);
		stateMap.put("Mississippi", 5000);
		stateMap.put("MS", 5000);
		stateMap.put("Tennessee", 5000);
		stateMap.put("TN", 5000);
		stateMap.put("Alabama", 5000);
		stateMap.put("AL", 5000);

		stateMap.put("Washington", 6000);
		stateMap.put("WA", 6000);
		stateMap.put("Oregon", 6000);
		stateMap.put("OR", 6000);
		stateMap.put("Idaho", 6000);
		stateMap.put("ID", 6000);
		stateMap.put("Montana", 6000);
		stateMap.put("MT", 6000);

		stateMap.put("Arizona", 7000);
		stateMap.put("AZ", 7000);
		stateMap.put("California", 7000);
		stateMap.put("CA", 7000);
		stateMap.put("Nevada", 7000);
		stateMap.put("NV", 7000);
		stateMap.put("Utah", 7000);
		stateMap.put("UT", 7000);

		stateMap.put("Wyoming", 8000);
		stateMap.put("WY", 8000);
		stateMap.put("North Dakota", 8000);
		stateMap.put("ND", 8000);
		stateMap.put("South Dakota", 8000);
		stateMap.put("SD", 8000);
		stateMap.put("Nebraska", 8000);
		stateMap.put("NE", 8000);

		stateMap.put("Colorado", 9000);
		stateMap.put("CO", 9000);
		stateMap.put("New Mexico", 9000);
		stateMap.put("NM", 9000);
		stateMap.put("Texas", 9000);
		stateMap.put("TX", 9000);
		stateMap.put("Kansas", 9000);
		stateMap.put("KS", 9000);
		stateMap.put("Oklahoma", 9000);
		stateMap.put("OK", 9000);

		stateMap.put("Minnesota", 10000);
		stateMap.put("MN", 10000);
		stateMap.put("Wisconsin", 10000);
		stateMap.put("WI", 10000);
		stateMap.put("Iowa", 10000);
		stateMap.put("IA", 10000);
		stateMap.put("Illinois", 10000);
		stateMap.put("IL", 10000);
	}
}
