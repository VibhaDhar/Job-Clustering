package com.project;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.project.bean.Cluster;
import com.project.bean.Job;
public class Clustering {
	static Map<String, Job> jobs;
	static String outputFile;
	static int noOfInitialClusters = 10;
	static int iterations = 5;
	
	public static void main(String[] args) {
		if(args.length < 2) {
			System.out.println("Please enter 2 arguments");
		}
		
		Date start = new Date();
		
		// Read all jobs from tsv files
		String inputDirectory = args[0];
		jobs = JobUtils.readJobs(inputDirectory + "/jobs.tsv");
		
		// Print jobs data to file 
		// This is to check if job features are found correctly
		JobUtils.printJobFeatures(jobs, inputDirectory + "/job-features.tsv");
		
		List<Cluster> clusters =  JobUtils.initClusters(noOfInitialClusters, jobs);
		
		JobUtils.clusterJobs(clusters, jobs);
		System.out.println("Clusters >>> : " + clusters.toString());
		
		// This is to check if job features are found correctly
		String outputFile = args[1];
		JobUtils.printClusters(clusters, outputFile);
		
		Date end = new Date();
		System.out.println("Total time taken : " + ((end.getTime() - start.getTime()))/1000*60*60 + " mins");
	}
}
