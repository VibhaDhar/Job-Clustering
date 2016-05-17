package com.project.bean;

public class Centroid {
	public double experience;
	public double qualification;
	public double state;
	
	public Centroid(double experience, double qualification, double state){
		this.experience = experience;
		this.qualification = qualification;		
		this.state = state;
	}
	
	@Override
	public String toString() {
		return "exp : " + experience + " , qua : " + qualification;
	}
}
