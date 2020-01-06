package com.classpath.assignment.ga;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.classpath.assignment.constraints.ConstraintIF;
import com.classpath.assignment.constraints.Variable;
import com.classpath.assignment.model.Solution;

public class GeneticAlgorithm {

	private List<Variable> variables ;
	private EvaluationFunction evalFn ;
	
	private static final int POPULATION_SIZE = 10000 ;
	private static final int GENERATIONS = 1000 ;
	private List<Solution> population = new ArrayList<>();
	private int bestCost ;
	private boolean hasBest ;

	public GeneticAlgorithm(List<Variable> variables, EvaluationFunction evalFn) {
		this.variables = variables;
		this.evalFn = evalFn;
		evolve() ;
		System.out.println("Finished");
	}

	private void evolve() {
		createInitialPopulation() ;
		for (int i=0; i < GENERATIONS; i++) {
			nextGeneration() ;
		}
	}
	
	private void nextGeneration() {
		for (int i=0;i<POPULATION_SIZE;i++) {
			Solution parent1 = population.get(i) ;
			int randPos = new Random().nextInt(POPULATION_SIZE) ;
			Solution parent2 = population.get(randPos) ;
			Solution child = parent1.reproduce(parent2) ;
			evaluateSolution(child) ;
			if (parent1.getCost() < parent2.getCost()) {
				if (child.getCost() < parent1.getCost()) {
					population.set(i, child) ;
				}
			} else {
				if (child.getCost() < parent2.getCost()) {
					population.set(randPos, child) ;
				}				
			}
		}
	}
	
	private void createInitialPopulation() {
		for (int i=0;i<POPULATION_SIZE;i++) {
			Solution solution = new Solution(variables) ;
			solution.random();
			evaluateSolution(solution) ;
			population.add(solution) ;
		}
	}
	
	private void updateBestSoFar(Solution solution) {
		if ((solution.getCost() < this.bestCost) || !hasBest) {
			hasBest = true ;
			bestCost = solution.getCost() ;
			System.out.println(String.format("NEW BEST SOLUTION with value of %d ", bestCost));
			System.out.println(solution.toString());
			System.out.println(dumpConstraintViolations(solution));
		}
	}

	public String dumpConstraintViolations(Solution solution) {
		StringBuffer sb = new StringBuffer() ;
		List<ConstraintIF> violated = evalFn.getHardConstraintsViolated(solution);
		if (!violated.isEmpty()) {
			sb.append("The following constraints were violated\n") ;
			sb.append(dumpConstraints(solution, violated)) ;
		}
		violated = evalFn.getSoftConstraintsViolated(solution);
		if (!violated.isEmpty()) {
			sb.append("The following soft constraints were violated\n") ;
			sb.append(dumpConstraints(solution, violated)) ;
		}
		return sb.toString() ;
	}

	private String dumpConstraints(Solution solution, List<ConstraintIF> cons) {
		StringBuffer sb = new StringBuffer() ;
		for (ConstraintIF con : cons) {
			sb.append(con.debugEval(solution.getVariableAssignments())) ;
		}
		return sb.toString() ;
	}

	private int evaluateSolution(Solution solution) {
		int eval = evalFn.eval(solution) ;
		solution.setCost(eval);
		updateBestSoFar(solution) ;
		return eval ;
	}
	
}
