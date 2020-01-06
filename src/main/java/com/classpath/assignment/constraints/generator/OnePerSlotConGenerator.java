package com.classpath.assignment.constraints.generator;

import java.util.ArrayList;
import java.util.List;

import com.classpath.assignment.constraints.NECon;
import com.classpath.assignment.model.TimeSlot;
import com.classpath.assignment.model.WorkstationTimeSlots;

public class OnePerSlotConGenerator extends AbstractConstraintsGenerator implements ConstraintsGeneratorIF {
	
	public OnePerSlotConGenerator(List<TimeSlot> workstations) {
		cons = new ArrayList<>() ;
		init(workstations) ;		
	}

	private void init(List<TimeSlot> workstations) {
		for (TimeSlot first : workstations) {
			for (TimeSlot second : workstations) {
				if (!first.equals(second)) {
					for (int i = 0; i < WorkstationTimeSlots.NO_OF_TIMESLOTS; i++) {
						int firstPos = first.getPos() + i ;
						int secondPos = second.getPos() + i ;
						NECon con = new NECon(firstPos, secondPos) ;
						NECon opCon = new NECon(secondPos, firstPos) ;
						if (!this.cons.contains(con) && !this.cons.contains(opCon)) {
							cons.add(con) ;
						}							
					}
				}
			}			
		}		
	}

}
