package com.classpath.assignment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class AppTest {
	
	@Before
	public void initialize() {
	}
	
	/**
	 * 
	 * Test that a person can be only on one workstation in a given slot
	 * 
	 */
	@Test
	public void assertOnePerSlotViolated() {	
		/*
		Workstations workstations = new Workstations() ;
		Workstation workstation = null ;
		workstation = new Workstation("1","wk1", ErgonomicRanking.A);
		workstations.addWorkstation(workstation);
		workstation = new Workstation("2","wk2", ErgonomicRanking.B);
		workstations.addWorkstation(workstation);
		workstation = new Workstation("3","wk3", ErgonomicRanking.A);
		workstations.addWorkstation(workstation);		
		ConstraintIF onePerSlot = new OnePerSlotCon(workstations.getAll()) ;
		List<Variable> solution = getSolution("1,2,3,1,3,2,3,2,1") ;
		int eval = onePerSlot.eval(solution) ;
		assertEquals(eval, 2) ;
		*/
	}

	@Test
	public void assertOnePerSlotNotViolated() {	
		/*
		Workstations workstations = new Workstations() ;
		Workstation workstation = null ;
		workstation = new Workstation("1","wk1", ErgonomicRanking.A);
		workstations.addWorkstation(workstation);
		workstation = new Workstation("2","wk2", ErgonomicRanking.B);
		workstations.addWorkstation(workstation);
		workstation = new Workstation("3","wk3", ErgonomicRanking.A);
		workstations.addWorkstation(workstation);		
		ConstraintIF onePerSlot = new OnePerSlotCon(workstations.getAll()) ;
		List<Variable> solution = getSolution("1,2,3,2,3,1,3,1,2") ;
		int eval = onePerSlot.eval(solution) ;
		assertEquals(eval, 0) ;
		*/
	}

	/**
	 * 
	 * An allocation to an a workstation cannot be followed by a b
	 * 
	 */
	@Test
	public void assertWSFollowsViolated() {	
		/*
		Workstations workstations = new Workstations() ;
		Workstation workstation = null ;
		workstation = new Workstation("1","wk1", ErgonomicRanking.A);
		workstations.addWorkstation(workstation);
		workstation = new Workstation("2","wk2", ErgonomicRanking.B);
		workstations.addWorkstation(workstation);
		workstation = new Workstation("3","wk3", ErgonomicRanking.A);
		workstations.addWorkstation(workstation);		
		workstation = new Workstation("4", "wk4", ErgonomicRanking.B);
		workstations.addWorkstation(workstation);
		List<PositionedWorkstation> aWorkstations = workstations.getAllWithRanking(ErgonomicRanking.A) ;
		List<PositionedWorkstation> bWorkstations = workstations.getAllWithRanking(ErgonomicRanking.B) ;
		ConstraintIF con = new FollowsCon(aWorkstations, bWorkstations) ;
		//									  A A A B B B A A A B B B
		List<Variable> solution = null ;
		// 1 assigned to first slot on first wks
		// 1 assigned to second slot on second wks
		solution = getSolution("1,2,3,2,1,4,5,6,7,8,9,3") ;
		assertEquals(con.eval(solution), 1) ;		
		// 1 assigned to second slot on first wks
		// 1 assigned to third slot on last wks
		// 5 assigned to first slot of third wks
		// 5 assigned to send slot of lazt wks
		solution = getSolution("7,1,3,2,9,4,5,6,7,8,5,1") ;
		assertEquals(con.eval(solution), 2) ;
*/
	}

	public void assertWSFollowsNotViolated() {	
		/*
		Workstations workstations = new Workstations() ;
		Workstation workstation = null ;
		workstation = new Workstation("1","wk1", ErgonomicRanking.A);
		workstations.addWorkstation(workstation);
		workstation = new Workstation("2","wk2", ErgonomicRanking.B);
		workstations.addWorkstation(workstation);
		workstation = new Workstation("3","wk3", ErgonomicRanking.A);
		workstations.addWorkstation(workstation);		
		workstation = new Workstation("4","wk4", ErgonomicRanking.B);
		workstations.addWorkstation(workstation);
		List<PositionedWorkstation> aWorkstations = workstations.getAllWithRanking(ErgonomicRanking.A) ;
		List<PositionedWorkstation> bWorkstations = workstations.getAllWithRanking(ErgonomicRanking.B) ;
		ConstraintIF con = new FollowsCon(aWorkstations, bWorkstations) ;
		//									  A A A B B B A A A B B B
		List<Variable> solution = null ;
		solution = getSolution("1,2,3,2,3,4,5,6,7,8,9,3") ;
		assertEquals(con.eval(solution), 0) ;
		solution = getSolution("7,2,3,2,9,4,5,6,7,8,6,1") ;
		assertEquals(con.eval(solution), 0) ;
		*/
	}

	@Test
	public void assertPerShiftViolated() {	
		/*
		Workstations workstations = new Workstations() ;
		Workstation workstation = null ;
		workstation = new Workstation("1","wk1", ErgonomicRanking.A);
		workstations.addWorkstation(workstation);
		workstation = new Workstation("2","wk2", ErgonomicRanking.B);
		workstations.addWorkstation(workstation);
		workstation = new Workstation("3","wk3", ErgonomicRanking.A);
		workstations.addWorkstation(workstation);		
		workstation = new Workstation("4","wk4", ErgonomicRanking.B);
		workstations.addWorkstation(workstation);
		List<PositionedWorkstation> aWorkstations = workstations.getAllWithRanking(ErgonomicRanking.A) ;
		List<PositionedWorkstation> bWorkstations = workstations.getAllWithRanking(ErgonomicRanking.B) ;
		ConstraintIF con = new PerShiftCon(aWorkstations, 1) ;
		List<Variable> solution = null ;
		// 2 is in the first A and the second A
		// 						A A A B B B A A A B B B
		solution = getSolution("1,2,3,2,1,4,5,2,7,8,9,3") ;
		assertEquals(con.eval(solution), 1) ;
		// 2 is in the first A and the second A
		// 3 is in the first A and the second A
		// 						A A A B B B A A A B B B
		solution = getSolution("1,2,3,2,1,4,5,2,3,8,9,3") ;
		assertEquals(con.eval(solution), 2) ;

		workstation = new Workstation("5","wk5", ErgonomicRanking.A);
		workstations.addWorkstation(workstation);		
		workstation = new Workstation("6","wk6", ErgonomicRanking.B);
		workstations.addWorkstation(workstation);		
		bWorkstations = workstations.getAllWithRanking(ErgonomicRanking.B) ;
		con = new PerShiftCon(bWorkstations, 2) ;
		// 2,1,4 are in the first, second and third B
		solution = getSolution("1,2,3,2,1,4,5,2,7,2,1,4,2,3,4,2,1,4") ;
		assertEquals(con.eval(solution), 3) ;
		*/
	}

	
	@Test
	public void assertPerShiftNotViolated() {	
		/*
		Workstations workstations = new Workstations() ;
		Workstation workstation = null ;
		workstation = new Workstation("1","wk1", ErgonomicRanking.A);
		workstations.addWorkstation(workstation);
		workstation = new Workstation("2","wk2", ErgonomicRanking.B);
		workstations.addWorkstation(workstation);
		workstation = new Workstation("3","wk3", ErgonomicRanking.A);
		workstations.addWorkstation(workstation);		
		workstation = new Workstation("4","wk4", ErgonomicRanking.B);
		workstations.addWorkstation(workstation);
		List<PositionedWorkstation> aWorkstations = workstations.getAllWithRanking(ErgonomicRanking.A) ;
		List<PositionedWorkstation> bWorkstations = workstations.getAllWithRanking(ErgonomicRanking.B) ;
		ConstraintIF con = new PerShiftCon(aWorkstations, 1) ;
		List<Variable> solution = null ;
		con = new PerShiftCon(bWorkstations, 2) ;
		// 2,1,4 are in the first B and the second B
		// but that's only 2 of each
		// 						A A A B B B A A A B B B
		solution = getSolution("1,2,3,2,1,4,5,2,7,2,1,4") ;
		assertEquals(con.eval(solution), 0) ;
		*/
	}

	@Test
	public void assertSlotsDifferent() {
		/*
		Workstations workstations = new Workstations() ;
		Workstation workstation = null ;
		workstation = new Workstation("1","wk1", ErgonomicRanking.A);
		workstations.addWorkstation(workstation);
		ConstraintIF con = new SlotsDiffCon(workstations.getAll()) ;
		List<Variable> solution = null ;
		solution = getSolution("1,2,3") ;
		assertEquals(con.eval(solution), 0) ;
		solution = getSolution("2,2,3") ;
		assertEquals(con.eval(solution), 1) ;
		solution = getSolution("2,2,2") ;
		assertEquals(con.eval(solution), 2) ;
		workstation = new Workstation("2","wk2", ErgonomicRanking.B);
		workstations.addWorkstation(workstation);
		con = new SlotsDiffCon(workstations.getAll()) ;
		solution = getSolution("1,2,3,1,2,3") ;
		assertEquals(con.eval(solution), 0) ;
		solution = getSolution("2,2,3,2,2,3") ;
		assertEquals(con.eval(solution), 2) ;
		solution = getSolution("2,2,2,2,2,2") ;
		assertEquals(con.eval(solution), 4) ;
		*/
		
	}
	/*
	private List<Variable> getSolution(String solution) {
		String[] solVals = solution.split(",") ;
		IntSetDom domain = new IntSetDom() ;
		for (String numStr : solVals) {
			domain.addDomVal(numStr);
		}
		Variable var = new Variable(domain) ;
		List<Variable> result = new ArrayList<>() ;
		for (String numStr : solVals) {
			var.setValue(numStr);
		}
		return result ;
	}
	*/
}
