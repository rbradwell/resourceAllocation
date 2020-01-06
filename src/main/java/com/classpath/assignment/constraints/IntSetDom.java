package com.classpath.assignment.constraints;

import java.util.HashSet;
import java.util.Set;

public class IntSetDom {

	private Set<String> domainValues ;
	
	public IntSetDom() {
		domainValues = new HashSet<String>() ;		
	}
	
	public IntSetDom(IntSetDom dom) {
		domainValues = new HashSet<String>() ;
		dom.getDomainValues().forEach(v -> domainValues.add(v));
	}

	public void addDomVal(String val) {
		domainValues.add(val) ;
	}
	
	public boolean inDomain(String val) {
		return domainValues.contains(val) ;
	}

	public Set<String> getDomainValues() {
		return domainValues;
	}
	
}
