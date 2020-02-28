package com.classpath.assignment.constraints;

import java.util.List;

public interface ConstraintIF {
	int eval(List<Variable<String>> solution) ;
	String debugEval(List<Variable<String>> solution) ;
}
