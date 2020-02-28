package com.classpath.assignment.ga;

import java.util.ArrayList;
import java.util.List;

import com.classpath.assignment.constraints.ConstraintIF;
import com.classpath.assignment.constraints.Variable;
import com.classpath.assignment.model.Solution;

public class EvaluationFunction {

	private List<ConstraintIF> cons ;
	private List<ConstraintIF> cost ;
	
	public EvaluationFunction(List<ConstraintIF> cons, List<ConstraintIF> cost) {
		init(cons, cost) ;
	}
	
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

	public List<ConstraintIF> getHardConstraintsViolated(Solution solution) {
		List<Variable> variableAssignments = solution.getVariableAssignments() ;
		return getViolated(cons, variableAssignments) ;
	}

	public List<ConstraintIF> getSoftConstraintsViolated(Solution solution) {
		List<Variable> variableAssignments = solution.getVariableAssignments() ;
		return getViolated(cost, variableAssignments) ;
	}

}
