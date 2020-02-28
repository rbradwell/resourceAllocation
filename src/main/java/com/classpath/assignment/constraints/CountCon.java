package com.classpath.assignment.constraints;

import java.util.List;

public class CountCon implements ConstraintIF {

	private List<Integer> positions ;
	private String valueToCount ;
	private long max ;
	
	public CountCon(List<Integer> positions, String value, long max) {
		this.positions = positions ;
		this.valueToCount = value ;
		this.max = max ;
	}
	
	@Override
	public int eval(List<Variable> solution) {
		long count = positions.stream()
				.map(pos -> solution.get(pos).getValue())
				.filter(val -> val.equals(this.valueToCount))
				.count() ;
		return count > max ? 1 : 0 ;
	}

	@Override
	public String debugEval(List<Variable> solution) {
		StringBuilder sb = new StringBuilder() ;
		
		long count = positions.stream()
				.map(pos -> solution.get(pos).getValue())
				.filter(val -> val.equals(this.valueToCount))
				.count() ;

		sb.append(String.format("Counting constraint - value %s occurs %s times considering the following positions %s \n", this.valueToCount, count, getPosAsString())) ;
		return sb.toString() ;
	}

	private String getPosAsString() {
		StringBuilder sb = new StringBuilder() ;
		for (Integer pos : positions) {
			sb.append(pos).append(" ") ;
		}
		return sb.toString() ;
	}
}
