package com.classpath.assignment.constraints.generator;

import java.util.ArrayList;
import java.util.List;

import com.classpath.assignment.constraints.NECon;
import com.classpath.assignment.model.TimeSlot;
import com.classpath.assignment.model.WorkstationTimeSlots;

public class SlotsDiffConGenerator extends AbstractConstraintsGenerator implements ConstraintsGeneratorIF {

	public SlotsDiffConGenerator(List<TimeSlot> workstations) {
		cons = new ArrayList<>() ;
		init(workstations) ;		
	}

	private void init(List<TimeSlot> workstations) {
		for (TimeSlot workstation : workstations) {
			for (int i = 0; i < WorkstationTimeSlots.NO_OF_TIMESLOTS -1; i++) {
				for (int j = i+1; j < WorkstationTimeSlots.NO_OF_TIMESLOTS; j++) {
					int firstPos = workstation.getPos() + i ;
					int secondPos = workstation.getPos() + j;
					if (firstPos != secondPos) {
						NECon con = new NECon(secondPos, firstPos) ;
						cons.add(con) ;						
					}
				}
			}
		}
	}

}
