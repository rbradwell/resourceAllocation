package com.classpath.assignment.constraints.generator;

import java.util.ArrayList;
import java.util.List;

import com.classpath.assignment.constraints.NECon;
import com.classpath.assignment.model.PositionedWorkstation;
import com.classpath.assignment.model.Workstations;

public class SlotsDiffConGenerator extends AbstractConstraintsGenerator implements ConstraintsGeneratorIF {

	public SlotsDiffConGenerator(List<PositionedWorkstation> workstations) {
		cons = new ArrayList<>() ;
		init(workstations) ;		
	}

	private void init(List<PositionedWorkstation> workstations) {
		for (PositionedWorkstation workstation : workstations) {
			for (int i=0; i < Workstations.NO_OF_SESSIONS-1; i++) {
				for (int j=i+1; j < Workstations.NO_OF_SESSIONS; j++) {
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
