package com.classpath.assignment.model;

public class TimeSlot {

	private int pos ;
	private Workstation workstation ;
	
	public TimeSlot(int pos, Workstation workstation) {
		this.pos = pos ;
		this.workstation = workstation ;
	}

	public int getPos() {
		return pos ;
	}

	public Workstation getWorkstation() {
		return workstation;
	}
	
}
