package com.classpath.assignment.constraints.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.classpath.assignment.constraints.CountCon;
import com.classpath.assignment.model.TimeSlot;
import com.classpath.assignment.model.WorkstationTimeSlots;

public class PerShiftConGenerator extends AbstractConstraintsGenerator implements ConstraintsGeneratorIF {

	private List<Integer> pos ;
	private int maxCount = 0 ;
	
	/**
	 *
	 * 
	 * @param workstation - all workstations of certain ergonomic type
	 */
	public PerShiftConGenerator(List<TimeSlot> workstations, Set<String> workerIds, int maxCount) {
		this.maxCount = maxCount ;
		cons = new ArrayList<>() ;
		pos = new ArrayList<>() ;
		init(workstations, workerIds) ;
	}
	
	private void recordPositions(List<TimeSlot> workstations) {
		for (TimeSlot first : workstations) {
			for (int i = 0; i < WorkstationTimeSlots.NO_OF_TIMESLOTS; i++) {
				pos.add(first.getPos() + i) ;
			}
		}
	}
	
	private void init(List<TimeSlot> workstations, Set<String> workerIds) {
		this.recordPositions(workstations) ;
		workerIds.forEach(id -> cons.add(new CountCon(this.pos, id, maxCount)));
	}
	
}
