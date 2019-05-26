package com.classpath.assignment.constraints.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.classpath.assignment.constraints.CountCon;
import com.classpath.assignment.model.PositionedWorkstation;
import com.classpath.assignment.model.Workstations;

public class PerShiftConGenerator extends AbstractConstraintsGenerator implements ConstraintsGeneratorIF {

	private List<Integer> pos ;
	private int maxCount = 0 ;
	
	/**
	 *
	 * 
	 * @param workstation - all workstations of certain ergonomic type
	 */
	public PerShiftConGenerator(List<PositionedWorkstation> workstations, Set<String> workerIds, int maxCount) {
		this.maxCount = maxCount ;
		cons = new ArrayList<>() ;
		pos = new ArrayList<>() ;
		init(workstations, workerIds) ;
	}
	
	private void recordPositions(List<PositionedWorkstation> workstations) {
		for (PositionedWorkstation first : workstations) { 
			for (int i=0; i < Workstations.NO_OF_SESSIONS; i++) {
				pos.add(first.getPos() + i) ;
			}
		}
	}
	
	private void init(List<PositionedWorkstation> workstations, Set<String> workerIds) {
		this.recordPositions(workstations) ;
		workerIds.forEach(id -> cons.add(new CountCon(this.pos, id, maxCount)));
	}
	
}
