package com.classpath.assignment.ga;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.classpath.assignment.ProblemRepresentationBuilder;
import com.classpath.assignment.constraints.EvaluationFunction;
import com.classpath.assignment.constraints.Variable;
import com.classpath.assignment.model.Solution;

public class GeneticAlgorithm {

	private List<Variable> variables ;
	private EvaluationFunction evalFn ;
	
	private static final int POPULATION_SIZE = 10000 ;
	private static final int GENERATIONS = 1000 ;
	private List<Solution> population ;
	private int bestCost ;
	private boolean hasBest ;
	
	public GeneticAlgorithm(List<String> workstationLines, List<String> workerLines, List<String> operatorSkillLevels) {
		population = new ArrayList<>() ;
		ProblemRepresentationBuilder builder = new ProblemRepresentationBuilder() ;
		this.variables = builder
									.addWorkstations(workstationLines)
									.addWorkers(workerLines)
									.addOperatorSkillLevels(operatorSkillLevels)
									.createVariables()
									.getVariables() ;		
		evalFn = builder.getEvalFunction() ;
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
			System.out.println(evalFn.dumpConstraintViolations(solution));
		}
	}
	

	private int evaluateSolution(Solution solution) {
		int eval = evalFn.eval(solution) ;
		solution.setCost(eval);
		updateBestSoFar(solution) ;
		return eval ;
	}
	
}
