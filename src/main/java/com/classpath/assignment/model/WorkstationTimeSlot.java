package com.classpath.assignment.model;

public class WorkstationTimeSlot {

	private int pos ;
	private Workstation workstation ;
	
	public WorkstationTimeSlot(int pos, Workstation workstation) {
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
