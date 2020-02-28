package com.classpath.assignment.constraints;

public class Variable<X> {

	private SetDom<X> domain;
	private X value ;

	public Variable(Variable<X> var) {
		this.value = var.getValue() ;
		this.domain = new SetDom<>(var.getDomain()) ;
	}

	public Variable(SetDom<X> domain) {
		this.domain = domain ;
	}

	public SetDom<X> getDomain() {
		return domain;
	}

	public void setValue(X val) {
		if (this.getDomain().inDomain(val)) {
			value = val ;
		}
	}
	
	public X getValue() {
		return value;
	}
	
}
