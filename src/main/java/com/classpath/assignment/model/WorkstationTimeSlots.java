package com.classpath.assignment.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WorkstationTimeSlots {

	public static int NO_OF_TIMESLOTS = 3 ;
	
	private List<TimeSlot> timeSlots;

	public WorkstationTimeSlots(List<Workstation> wkstns) {
		this.timeSlots = new ArrayList<>() ;
		this.addWorkstations(wkstns);
	}
	
	private void addWorkstations(List<Workstation> wkstns) {
		for (Workstation workstation : wkstns) {
			int pos = this.timeSlots.size() * NO_OF_TIMESLOTS;
			this.timeSlots.add(new TimeSlot(pos, workstation)) ;
		}
	}
		
	public List<TimeSlot> getAll() {
		return timeSlots;
	}
	
	public List<TimeSlot> getAllWithRanking(ErgonomicRanking ranking) {
		return timeSlots.stream().filter(pw -> pw.getWorkstation().getRanking().equals(ranking)).collect(Collectors.toList()) ;
	}

	public List<TimeSlot> getTimeSlots() {
		return timeSlots;
	}
	
}
