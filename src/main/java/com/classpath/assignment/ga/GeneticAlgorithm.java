package com.classpath.assignment.ga;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.classpath.assignment.constraints.ConstraintIF;
import com.classpath.assignment.constraints.Variable;
import com.classpath.assignment.model.Solution;

public class GeneticAlgorithm {

	private List<Variable<String>> variables ;
	private EvaluationFunction evalFn ;
	
	private static final int POPULATION_SIZE = 10000 ;
	private static final int GENERATIONS = 1000 ;
	private List<Solution<String>> population = new ArrayList<>();
	private int bestCost ;
	private boolean hasBest ;

	public GeneticAlgorithm(List<Variable<String>> variables, EvaluationFunction evalFn) {
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
			Solution<String> parent1 = population.get(i) ;
			int randPos = new Random().nextInt(POPULATION_SIZE) ;
			Solution<String> parent2 = population.get(randPos) ;
			Solution<String> child = parent1.reproduce(parent2) ;
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
			Solution<String> solution = new Solution<String>(variables) ;
			solution.random();
			evaluateSolution(solution) ;
			population.add(solution) ;
		}
	}
	
	private void updateBestSoFar(Solution<String> solution) {
		if ((solution.getCost() < this.bestCost) || !hasBest) {
			hasBest = true ;
			bestCost = solution.getCost() ;
			System.out.println(String.format("NEW BEST SOLUTION with value of %d ", bestCost));
			System.out.println(solution.toString());
			System.out.println(dumpConstraintViolations(solution));
		}
	}

	public String dumpConstraintViolations(Solution<String> solution) {
		StringBuilder sb = new StringBuilder() ;
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

	private String dumpConstraints(Solution<String> solution, List<ConstraintIF> cons) {
		StringBuilder sb = new StringBuilder() ;
		for (ConstraintIF con : cons) {
			sb.append(con.debugEval(solution.getVariableAssignments())) ;
		}
		return sb.toString() ;
	}

	private void evaluateSolution(Solution<String> solution) {
		int eval = evalFn.eval(solution) ;
		solution.setCost(eval);
		updateBestSoFar(solution) ;
	}
	
}
