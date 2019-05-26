package com.classpath.assignment.constraints;

import java.util.List;

public class NECon implements ConstraintIF {

	private int pos1 ;
	private int pos2 ;
	
	public int getPos1() {
		return pos1;
	}

	public int getPos2() {
		return pos2;
	}

	public NECon(int pos1, int pos2) {
		this.pos1 = pos1 ;
		this.pos2 = pos2 ;
	}
	
	public int eval(List<Variable> solution) {
		String val1 = solution.get(pos1).getValue() ;
		String val2 = solution.get(pos2).getValue() ;
		if (solution.get(pos1) != null && 
			solution.get(pos2) != null && 
			val1.equals(val2)) {
			return 1 ;
		}
		return 0 ;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + pos1 + pos2;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		
		if (obj == null) {
			return false;
		}
		
		if (getClass() != obj.getClass()) {
			return false;
		}
		
		NECon other = (NECon) obj ;
		if (other.getPos1() == this.getPos1() && other.getPos2() == this.getPos2()) {
			return true ;
		}
		return false;
	}

	@Override
	public String debugEval(List<Variable> solution) {
		StringBuffer sb = new StringBuffer() ;
		sb.append(String.format("Not Equals constraint violation - pos %s and pos %s have the same value of %s \n", this.pos1, this.pos2, solution.get(pos2).getValue())) ;
		return sb.toString() ;
	}
	
}
