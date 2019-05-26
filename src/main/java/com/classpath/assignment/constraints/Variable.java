package com.classpath.assignment.constraints;

public class Variable {

	private IntSetDom domain;
	private String value ;

	public Variable(Variable var) {
		this.value = var.value ;
		this.domain = new IntSetDom(var.domain) ;
	}
	
	public Variable(IntSetDom domain) {
		this.domain = domain ;
	}

	public IntSetDom getDomain() {
		return domain;
	}

	public void setValue(String val) {
		if (this.getDomain().inDomain(val)) {
			value = val ;
		}
	}
	
	public String getValue() {
		return value;
	}
	
}
