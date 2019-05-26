package com.classpath.assignment.constraints.generator;

import java.util.List;

import com.classpath.assignment.constraints.ConstraintIF;

public abstract class AbstractConstraintsGenerator implements ConstraintsGeneratorIF {

	protected List<ConstraintIF> cons ;

	public List<ConstraintIF> getConstraints() {
		return cons ;
	};
	
}
