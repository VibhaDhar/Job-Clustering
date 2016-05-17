package com.project;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.project.bean.Centroid;
import com.project.bean.Cluster;
import com.project.bean.Job;

public class JobUtils {
	public static Map<String, Job> readJobs(String filePath){
		Map<String, Job> jobs = new HashMap<String, Job>();
		File file = new File(filePath);
		FileInputStream fis = null;
		Scanner in = null;
		try {
			fis = new FileInputStream(file);
			in = new Scanner(fis);
			if(in.hasNext()) {
				in.nextLine(); // This is to ignore first title column in file
			}

			while (in.hasNext()) {
	            String nextLine = in.nextLine();
	            String[] columns = nextLine.split("\t");
	            Job job = new Job();
	            job.JobID = columns[0];
	            
	            if(columns.length > 1) {
	            	job.Description = eliminateSpecialCharacters(columns[1]);
	            }
	            
	            if(columns.length > 2) {
	            	job.Requirements = eliminateSpecialCharacters(columns[2]);
	            }	
	            
	            // Get experience
	            job.Experience = ExperienceUtils.getExperience(job.Requirements);
	            if(job.Experience == 0) {
	            	job.Experience = ExperienceUtils.getExperience(job.Description);
	            }
	            
	            // Get Qualification
	            /*if(job.JobID.equals("37155")){
	            	System.out.println(">>>>>>> " + job.JobID);
	            }*/
	            job.Qualification = QualificationUtils.getQualification(job.Requirements);
	            if(job.Qualification == null) {
	            	job.Qualification = QualificationUtils.getQualification(job.Description);
	            }
	            job.QualificationWeightage = QualificationUtils.getQualificationWeightage(job.Qualification) * 100;
	            
	            job.State = StateUtils.getState(job.Requirements);
	            if(job.State == null) {
	            	job.State = StateUtils.getState(job.Description);
	            }
	            job.StateWeightage = StateUtils.getStateScore(job.State);
	            
	            jobs.put(job.JobID, job);
	            //System.out.println("Processed job ID : " + job.JobID);
	        }
		} catch (FileNotFoundException e) {
			System.out.println("Exception in readJobs : " + e.getMessage());
		} finally {
			if(in != null) {
				in.close();
			}
			
			if(fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		System.out.println("Total Jobs : " + jobs.size());
		return jobs;
	}	
	
	public static String eliminateSpecialCharacters(String str)	{
		String result = "";
		Pattern specialChars = Pattern.compile("\\<.*?>|</.>|&nbsp;|&nbsp|\\\\r|\\\\n|\\t|(?i)<([a-zA-Z0-9-_]*)(\\s[^>]*)>|(?i)<(?!(/?(li|p)))[^>]*>", Pattern.CASE_INSENSITIVE);
		Matcher matcher = specialChars.matcher(str);
		if(matcher.find())
			result = matcher.replaceAll("");
		return result;
	}
	
	public static void printJobFeatures(Map<String, Job> jobs, String outputFile){
		// Create output file
		String result = "JobId \t Qualification \t\t Qua Weigtage \t Experience \t Role \t City \t State \t State Weigtage \n";
		for(Job job:jobs.values()) {
			result += job.JobID + "\t" + job.Qualification + "\t\t" + job.QualificationWeightage + "\t\t" + job.Experience + "\t" + job.Role + "\t" + job.City + "\t" + job.State + "\t" + job.StateWeightage + "\n";			
		}
		
		PrintWriter out = null;
		try {
			out = new PrintWriter(outputFile);
			out.print(result);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(out != null) {
				out.close();
			}
		}		
		System.out.println("Job features printed successfully");
	}
	
	public static void printClusters(List<Cluster> clusters, String outputFile){
		// Create output file
		String result = "JobId \t Cluster \n";
		for(Cluster cluster:clusters) {
			for(Job job:cluster.jobs){
				result += job.JobID + "\t" + cluster.id + "\n";
			}	
		}
		
		PrintWriter out = null;
		try {
			out = new PrintWriter(outputFile);
			out.print(result);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(out != null) {
				out.close();
			}
		}		
		System.out.println("Job-Clusters printed successfully");
	}
	
	public static List<Cluster> initClusters(int noOfInitialClusters, Map<String, Job> jobs){
		List<Cluster> clusters = new ArrayList<Cluster>();
		int jobCount = jobs.size();
		int interval = jobCount/noOfInitialClusters;
		List<Job> jobList = new ArrayList<Job>();
		jobList.addAll(jobs.values());
		
		for(int i=0; i<noOfInitialClusters; i++){
			Job centroidJob = jobList.get(i*interval);
			/*if(centroidJob.Experience == 0 || centroidJob.QualificationWeightage == 0) {
				continue;
			}*/
			Centroid centroid = new Centroid(centroidJob.Experience, centroidJob.QualificationWeightage, centroidJob.StateWeightage);
			
			Cluster cluster = new Cluster();
			cluster.id = i+1;
			cluster.centroid = centroid;
			clusters.add(cluster);
		}
		return clusters;
	}
	
	// This will be called recursively, until no clusters are changed 
	public static void clusterJobs(List<Cluster> clusters, Map<String, Job> jobs) {
		for(Job job:jobs.values()) {
			Double minDistance = 10000d;
			Cluster closestCluster = null;
			for(Cluster cluster:clusters) {
				Double eDistance = euclideanDistance(job, cluster.centroid);
				if(eDistance < minDistance) {
					minDistance = eDistance;
					closestCluster = cluster;
				}
			}
			//System.out.println("Job : " + job.JobID + ", min-dist : " + minDistance + " -- Cluster" + closestCluster.toString());
			closestCluster.jobs.add(job);
		}
		System.out.println("clusters >>>> " + clusters);
		
		if(Clustering.iterations-- < 0) {
			return;
		}
		recomputeCentroid(clusters, jobs);
	}

	public static double euclideanDistance(final Job job, final Centroid centroid) {
		double expDistance = Math.pow(job.Experience - centroid.experience, 2);
		double quaDistance = Math.pow(job.QualificationWeightage - centroid.qualification, 2);
		double stateDistance = Math.pow(job.StateWeightage - centroid.state, 2);
		return Math.sqrt(expDistance + quaDistance + stateDistance);
    }
	
	public static void recomputeCentroid(List<Cluster> clusters, Map<String, Job> jobs){
		for(Cluster cluster:clusters) {
			// Iterate through jobs in given cluster to find new centroid
			double exp = 0d;
			double qualification = 0d;
			double state = 0d;
			for(Job job:cluster.jobs){
				exp += job.Experience;
				qualification += job.QualificationWeightage;
				state += job.StateWeightage;
			}
			if(exp > 0) {
				cluster.centroid.experience = exp/cluster.jobs.size();
			}
			if(qualification > 0){
				cluster.centroid.qualification = qualification/cluster.jobs.size();
			}
			if(state > 0){
				cluster.centroid.state = state/cluster.jobs.size();
			}
		}
		
		// Init jobs list in all clusters
		for(Cluster cluster:clusters) {
			cluster.jobs = new ArrayList<Job>();
		}
		JobUtils.clusterJobs(clusters, jobs);
	}
}
