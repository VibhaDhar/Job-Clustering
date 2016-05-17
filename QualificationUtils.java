package com.project;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QualificationUtils {
	public static int MASTERS = 10, BACHELORS = 5, COLLEGE_DEGREE = 3, HIGH_SCHOOL = 2, OTHERS = 1;
	public static List<String> mastersDegrees = Arrays.asList("Masters","Master","MS", "M.S", "M.S.", "Master's", "master", "masters", "master's", "MBA"); 
	public static List<String> bachelorsDegrees = Arrays.asList("Bachelors", "Bachelor", "BS", "B.S", "B.S.", "Bachelor's", "bachelor", "bachelors", "bachelor's", "BA");
	public static List<String> collegeDegrees = Arrays.asList("College Degrgee", "AA", "AS", "Associate's");
	public static List<String> highSchoolDegrees = Arrays.asList("High School Diploma", "|High School");
			
	public static int getQualificationWeightage(String str)	{
		int weightage = 0;
		if(str != null) {
			if(mastersDegrees.contains(str)) {
				weightage = MASTERS;
			} else if(bachelorsDegrees.contains(str)) {
				weightage = BACHELORS;
			} else if(collegeDegrees.contains(str)) {
				weightage = COLLEGE_DEGREE;
			} else if(highSchoolDegrees.contains(str)) {
				weightage = HIGH_SCHOOL;
			} else {
				weightage = OTHERS;
			}
		}
		return weightage;
	}
	
	public static String getQualification(String str)	{
		String qualification = null;
		if(str != null) {
			Matcher matcher = qualificationPattern.matcher(str);
			if(matcher.find()) {	
				qualification = str.substring(matcher.start(), matcher.end());
			}
		}
		return qualification;
	}
	
	public static Pattern qualificationPattern = Pattern.compile("(M|m)aster('*)(s*)|M(\\.*)S(\\.*)|MS[a-zA-Z]+"
			+ "|Bachelor(s*)|B(\\.*)S(\\.*)|(B|b)achelor('*)(s*)|BS[a-zA-Z]+"
			+ "|AA|AS|(C|c)ollege (D|d)egree|MBA|BA|High School Diploma|High School|Associate's");
	

}
