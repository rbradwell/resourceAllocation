package com.classpath.assignment.constraints;

public class Variable {

	private SetDom<String> domain;
	private String value ;

	public Variable(Variable var) {
		this.value = var.value ;
		this.domain = new SetDom<>(var.domain) ;
	}
	
	public Variable(SetDom<String> domain) {
		this.domain = domain ;
	}

	public SetDom<String> getDomain() {
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
