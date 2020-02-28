package com.classpath.assignment.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.classpath.assignment.constraints.Variable;

public class Solution<T> {

	private List<Variable<T>> variableAssignments ;
	private int cost ;
	
	public Solution(List<Variable<T>> varAssgn) {
		variableAssignments = new ArrayList<>() ;
		varAssgn.forEach(a -> this.variableAssignments.add(new Variable<T>(a)));
	}

	public List<Variable<T>> getVariableAssignments() {
		return variableAssignments;
	}

	public T getVariableValueAtPos(int pos) {
		return this.variableAssignments.get(pos).getValue() ;
	}
	
	public void setVariableValue(int pos, T value) {
		this.variableAssignments.get(pos).setValue(value);
	}

	public Solution<T> reproduce(Solution<T> otherParent) {
		Solution<T> child = new Solution<T>(this.variableAssignments) ;
		T childVal = null ;
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
		for (Variable<T> var : variableAssignments) {
			Set<T> domValues = var.getDomain().getDomainValues() ;
			int randPos = new Random().nextInt(domValues.size()) ;
			T picked = null ;
			int i = 0 ;
			for (T dv : domValues) {
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
		StringBuilder sb = new StringBuilder() ;
		sb.append("\n+++ Solution +++\n") ;
		for (Variable<T> var : variableAssignments) {
			sb.append(var.getValue()) ;
			sb.append(" ") ;
		}
		sb.append("\n--- Solution ---\n") ;		
		return sb.toString() ;
	}
}
