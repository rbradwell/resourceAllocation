package com.classpath.assignment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.classpath.assignment.ga.GeneticAlgorithm;

public class ResourceAssignment {

	private void run() throws IOException {
		ProblemRepresentationBuilder builder = new ProblemRepresentationBuilder()
				.addWorkstations(readFile("workstations.txt"))
				.addWorkers(readFile("operators.txt"))
				.addOperatorSkillLevels(readFile("skilllevels.txt"));
		new GeneticAlgorithm(builder.createVariables().getVariables(), builder.getEvalFunction()) ;
	}
	
	private List<String> readFile(String filename) throws IOException {
		ClassLoader classLoader = getClass().getClassLoader() ;
		InputStream inputStream = classLoader.getResourceAsStream(filename);
		return readFromInputStream(inputStream);		
	}
	
	private List<String> readFromInputStream(InputStream inputStream) throws IOException {
		List<String> lines = new ArrayList<>() ;
		try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		    	lines.add(line) ;
		    }
		}
		return lines;
	}

	public static void main(String[] args) throws IOException {
		new ResourceAssignment().run() ;
	}
	
}
