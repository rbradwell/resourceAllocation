package com.classpath.assignment.constraints.generator;

import java.util.ArrayList;
import java.util.List;

import com.classpath.assignment.constraints.NECon;
import com.classpath.assignment.model.TimeSlot;
import com.classpath.assignment.model.WorkstationTimeSlots;

public class FollowsConGenerator extends AbstractConstraintsGenerator implements ConstraintsGeneratorIF {
	
	public FollowsConGenerator(List<TimeSlot> workstationsFirst,
			List<TimeSlot> workstationsSecond) {
		cons = new ArrayList<>() ;
		init(workstationsFirst, workstationsSecond) ;		
	}

	private void init(List<TimeSlot> workstationsFirstTimeslot,
						List<TimeSlot> workstationsSecondTimeslot) {
		for (TimeSlot first : workstationsFirstTimeslot) {
			for (TimeSlot second : workstationsSecondTimeslot) {
				if (!first.equals(second)) {
					for (int i = 0; i < WorkstationTimeSlots.NO_OF_TIMESLOTS -1; i++) {
						int firstPos = first.getPos() + i ;
						int secondPos = second.getPos() + i + 1;
						NECon con = new NECon(firstPos, secondPos) ;
						cons.add(con) ;
					}
				}
			}
		}
	}
	
}
