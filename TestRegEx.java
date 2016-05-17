package com.project;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class TestRegEx {
	public static void main(String[] args) {
		//String str = "two to four years of related experience";
		String str = "bachelor's";
		Pattern pattern = Pattern.compile("(M|m)aster('*)(s*)|M(\\.*)S(\\.*)|MS[a-zA-Z]+"
			+ "|Bachelor(s*)|B(\\.*)S(\\.*)|(B|b)achelor('*)(s*)|BS[a-zA-Z]+"
			+ "|AA|AS|(C|c)ollege (D|d)egree|MBA|BA|High School Diploma|High School|Associate's");
				//+ "|Bachelors|Bachelor|BS|B.S|B.S.|Bachelor's|bachelor|bachelors|bachelor's|BS[a-zA-Z]\\+"
				//+ "|AA|AS|(C|c)ollege (D|d)egree|MBA|BA|High School Diploma|High School|Associate\\'s");
		Matcher matcher = pattern.matcher(str);
		if(matcher.find()) {
			int start = matcher.start();
			int end = matcher.end();
			String str1 = str.substring(start,end);
			System.out.println("Matched : " + str1);
		}
		//System.out.println("Exp : " + JobUtils.getExperience(str));
	}
}
