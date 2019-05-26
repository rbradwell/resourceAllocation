package com.classpath.assignment.constraints;

import java.util.List;

public interface ConstraintIF {
	int eval(List<Variable> solution) ;
	String debugEval(List<Variable> solution) ;
}
