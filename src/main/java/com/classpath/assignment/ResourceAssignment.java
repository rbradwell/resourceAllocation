package com.classpath.assignment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.classpath.assignment.ga.EvaluationFunctionBuilder;
import com.classpath.assignment.ga.GeneticAlgorithm;
import com.classpath.assignment.util.FileHelper;

public class ResourceAssignment {

	private void run() throws IOException {
		FileHelper fh = new FileHelper();
		ProblemRepresentationBuilder representation = new ProblemRepresentationBuilder()
				.addWorkstations(fh.readFile("workstations.txt"))
				.addWorkers(fh.readFile("operators.txt"))
				.addOperatorSkillLevels(fh.readFile("skilllevels.txt"));
		new GeneticAlgorithm(representation.createVariables().getVariables(), new EvaluationFunctionBuilder(representation).getEvalFunction()) ;
	}

	public static void main(String[] args) throws IOException {
		new ResourceAssignment().run() ;
	}
	
}
