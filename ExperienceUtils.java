package com.project;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExperienceUtils {
	public static int getExperience(String str)	{
		int number = 0;
		if(str != null) {
			Matcher matcher = noExpPattern.matcher(str);
			if(!matcher.find()) {
				Matcher matcher1 = expPattern.matcher(str);
				if(matcher1.find()) {	
					int start = matcher1.start();
					int end = matcher1.end();
					String str1 = str.substring(start,end);
					
					String firstNumber = str1.replaceFirst(".*?(\\d+).*", "$1");
					if(!firstNumber.matches(".*\\d.*")) {
						int endIndex = 0;
						if(str1.contains("year"))
							endIndex = str1.indexOf("year");
						else if(str1.contains("years"))
							endIndex = str1.indexOf("years");
						String ab = str1.substring(0, endIndex);
						ab = ab.replace("At least ", "").replace("plus ", "");
						number = wordToNumber(ab);						
					} else {
						number = Integer.parseInt(firstNumber.trim());
					}	
				}
			}
		}
		return number;
	}
	
	public static Pattern expPattern = Pattern.compile(" Minimum [0-9]\\+ months experience in"
			+ "|[0-9]\\+ years (in|experience|of)"
			+ "|At least [a-zA-Z0-9]\\+ year[s] [a-zA-Z0-9]\\+ experience "
			+ "|(M|m)inimum [0-9] years"
			+ "|Candidates with [0-9]\\+ years of [a-zA-Z0-9]\\+ experience"
			+ "|[0-9]\\+-[0-9]\\+ years experience in"
			+ "|Min. [0-9]\\+ yrs [a-zA-Z0-9]\\+ Exp"
			+ "|[a-zA-Z0-9]\\+ years' experience in"
			+ "|at least [a-zA-Z0-9]\\+ years experience"
			+ "|[0-9]\\+ - [0-9]\\+ years of [a-zA-Z0-9]\\+ experience"
			+ "|[0-9]\\+ years of hands on"
			+ "|[0-9]\\+ years min experience"
			+ "|[0-9]\\+ to [0-9]\\+ years experience"
			+ "|[0-9]\\+ years of"
			+ "|[0-9] \\+ years"
			+ "|[0-9] years"
			+ "|A minimum of [a-zA-Z0-9]\\+ years of experience"
			+ "|[a-zA-Z0-9] years of related experience"
			+ "|[a-zA-Z0-9]* plus years of related experience"
			+ "|\\([0-9]\\) years experience");
	
	public static Pattern noExpPattern = Pattern.compile("no experience is required"
			+ "|No experience necessary");
	
	public static int wordToNumber(String word) {
		word = word.replaceAll(" ","").toLowerCase();
		if(numberStrings.containsKey(word)) {
			return numberStrings.get(word);
		}
		return 0;
	}
	
	public static Map<String, Integer> numberStrings;
	
	static {
		numberStrings = new HashMap<String, Integer>();
		numberStrings.put("one", 1);
		numberStrings.put("two", 2);
		numberStrings.put("three", 3);
		numberStrings.put("four", 4);
		numberStrings.put("five", 5);
		numberStrings.put("six", 6);
		numberStrings.put("seven", 7);
		numberStrings.put("eight", 8);
		numberStrings.put("nine", 9);
		numberStrings.put("ten", 10);
		// No records with following experiences
		/*numberStrings.put("eleven", 11);
		numberStrings.put("twelve", 12);
		numberStrings.put("thirteen", 13);
		numberStrings.put("fourteen", 14);
		numberStrings.put("fifteen", 15);
		numberStrings.put("sixteen", 16);
		numberStrings.put("seventeen", 17);
		numberStrings.put("eighteen", 18);
		numberStrings.put("nineteen", 19);
		numberStrings.put("twenty", 20);
		numberStrings.put("thirty", 30);
		numberStrings.put("forty", 40);
		numberStrings.put("fifty", 50);*/
	}
}
