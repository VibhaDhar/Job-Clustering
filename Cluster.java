package com.project.bean;

import java.util.ArrayList;
import java.util.List;

public class Cluster {
	public Integer id; 
	public Centroid centroid;
	public List<Job> jobs;
	
	public Cluster(){
		jobs = new ArrayList<Job>();
	}
	
	@Override
	public String toString() {
		return "id:" + id + ", centroid: " + centroid.toString() + ", jobs : " + jobs.size();
	}
}
