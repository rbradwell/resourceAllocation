package com.classpath.assignment.constraints;

import java.util.ArrayList;
import java.util.List;

import com.classpath.assignment.model.Solution;

public class EvaluationFunction {

	private List<ConstraintIF> cons ;
	private List<ConstraintIF> cost ;
	
	public EvaluationFunction(List<ConstraintIF> cons, List<ConstraintIF> cost) {
		init(cons, cost) ;
	}

	/**
	 * 
	 * 
	 * @param workstations.  All workstations
	 */
	private void init(List<ConstraintIF> cons, List<ConstraintIF> cost) {
		this.cons = cons ;
		this.cost = cost ;
	}
	
	public int eval(Solution solution) {
		List<Variable> variableAssignments = solution.getVariableAssignments() ;
		int v1 = getViolated(cons, variableAssignments).size();
		int v2 = getViolated(cost, variableAssignments).size();
		return v1 * v1 + v2;
	}

	public List<ConstraintIF> getViolated(List<ConstraintIF> cons, 
											List<Variable> solution) {
		List<ConstraintIF> result = new ArrayList<>() ;
		for (ConstraintIF con : cons) {
			if (con.eval(solution) == 1) {
				result.add(con) ;
			}
		}
		return result ;
	}

	public String dumpConstraintViolations(Solution solution) {
		List<Variable> variableAssignments = solution.getVariableAssignments() ;
		StringBuffer sb = new StringBuffer() ;
		List<ConstraintIF> violated = null ;
		violated = getViolated(cons, variableAssignments) ;
		if (!violated.isEmpty()) {
			sb.append("The following constraints were violated\n") ;
			sb.append(dumpConstraints(solution, violated)) ;
		}
		violated = getViolated(cost, variableAssignments) ;
		if (!violated.isEmpty()) {
			sb.append("The following soft constraints were violated\n") ;
			sb.append(dumpConstraints(solution, violated)) ;
		}
		return sb.toString() ;
	}
	
	private String dumpConstraints(Solution solution, List<ConstraintIF> cons) {
		StringBuffer sb = new StringBuffer() ;
		for (ConstraintIF con : cons) {
			sb.append(con.debugEval(solution.getVariableAssignments())) ;
		}
		return sb.toString() ;
	}
}
