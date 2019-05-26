package com.classpath.assignment.constraints.generator;

import java.util.ArrayList;
import java.util.List;

import com.classpath.assignment.constraints.NECon;
import com.classpath.assignment.model.PositionedWorkstation;
import com.classpath.assignment.model.Workstations;

public class OnePerSlotConGenerator extends AbstractConstraintsGenerator implements ConstraintsGeneratorIF {
	
	public OnePerSlotConGenerator(List<PositionedWorkstation> workstations) {
		cons = new ArrayList<>() ;
		init(workstations) ;		
	}

	private void init(List<PositionedWorkstation> workstations) {
		for (PositionedWorkstation first : workstations) {
			for (PositionedWorkstation second : workstations) {
				if (!first.equals(second)) {
					for (int i=0; i < Workstations.NO_OF_SESSIONS; i++) {
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
