package com.classpath.assignment.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Workstations {

	public static int NO_OF_SESSIONS = 3 ;
	
	private List<PositionedWorkstation> workstations ;
	
	public Workstations() {
		workstations = new ArrayList<>() ;
	}
	
	public Workstations(List<Workstation> wkstns) {
		this.workstations = new ArrayList<>() ;
		this.addWorkstations(wkstns);
	}
	
	private void addWorkstations(List<Workstation> wkstns) {
		for (Workstation workstation : wkstns) {
			int pos = this.workstations.size() * NO_OF_SESSIONS ;
			this.workstations.add(new PositionedWorkstation(pos, workstation)) ;
		}
	}
		
	public List<PositionedWorkstation> getAll() {
		return workstations ;
	}
	
	public List<PositionedWorkstation> getAllWithRanking(ErgonomicRanking ranking) {
		return workstations.stream().filter(pw -> pw.getWorkstation().getRanking().equals(ranking)).collect(Collectors.toList()) ;
	}

	public List<PositionedWorkstation> getWorkstations() {
		return workstations;
	}
	
}
