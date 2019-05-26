package com.classpath.assignment.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.classpath.assignment.constraints.Variable;

public class Solution {

	private List<Variable> variableAssignments ;
	private int cost ;
	
	public Solution(List<Variable> varAssgn) {
		variableAssignments = new ArrayList<>() ;
		varAssgn.forEach(a -> this.variableAssignments.add(new Variable(a)));
	}

	public List<Variable> getVariableAssignments() {
		return variableAssignments;
	}

	public String getVariableValueAtPos(int pos) {
		return this.variableAssignments.get(pos).getValue() ;
	}
	
	public void setVariableValue(int pos, String value) {
		this.variableAssignments.get(pos).setValue(value);
	}

	public Solution reproduce(Solution otherParent) {
		Solution child = new Solution(this.variableAssignments) ;
		String childVal = null ;
		for (int pos=0; pos<child.getVariableAssignments().size();pos++) {
			if (new Random().nextInt(2) == 0) { 
				childVal = this.getVariableValueAtPos(pos) ;
			} else {
				childVal = otherParent.getVariableValueAtPos(pos) ;
			}	
			child.setVariableValue(pos, childVal);
		}
		return child ;
	}	
	
	public void random() {
		for (Variable var : variableAssignments) {
			Set<String> domValues = var.getDomain().getDomainValues() ;
			int randPos = new Random().nextInt(domValues.size()) ;
			String picked = null ;
			int i = 0 ;
			for (String dv : domValues) {
				if (i == randPos) {
					picked = dv ;
					break ;
				}
				i++ ;
			}
			var.setValue(picked);
		}
	}
	
	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer() ;
		sb.append("\n+++ Solution +++\n") ;
		for (Variable var : variableAssignments) {
			sb.append(var.getValue()) ;
			sb.append(" ") ;
		}
		sb.append("\n--- Solution ---\n") ;		
		return sb.toString() ;
	}
}
