package com.classpath.assignment.constraints.generator;

import java.util.ArrayList;
import java.util.List;

import com.classpath.assignment.constraints.NECon;
import com.classpath.assignment.model.PositionedWorkstation;
import com.classpath.assignment.model.Workstations;

public class FollowsConGenerator extends AbstractConstraintsGenerator implements ConstraintsGeneratorIF {
	
	public FollowsConGenerator(List<PositionedWorkstation> workstationsFirst,
			List<PositionedWorkstation> workstationsSecond) {
		cons = new ArrayList<>() ;
		init(workstationsFirst, workstationsSecond) ;		
	}

	private void init(List<PositionedWorkstation> workstationsFirst, 
						List<PositionedWorkstation> workstationsSecond) {
		for (PositionedWorkstation first : workstationsFirst) {
			for (PositionedWorkstation second : workstationsSecond) {
				if (!first.equals(second)) {
					for (int i=0; i < Workstations.NO_OF_SESSIONS-1; i++) {
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
