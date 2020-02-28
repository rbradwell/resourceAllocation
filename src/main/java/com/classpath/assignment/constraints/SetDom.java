package com.classpath.assignment.constraints;

import java.util.HashSet;
import java.util.Set;

public class SetDom<T> {

	private Set<T> domainValues ;
	
	public SetDom() {
		domainValues = new HashSet<T>() ;
	}
	
	public SetDom(SetDom<T> dom) {
		domainValues = new HashSet<T>() ;
		domainValues.addAll(dom.getDomainValues());
	}

	public void addDomVal(T val) {
		domainValues.add(val) ;
	}
	
	public boolean inDomain(T val) {
		return domainValues.contains(val) ;
	}

	public Set<T> getDomainValues() {
		return domainValues;
	}
	
}
