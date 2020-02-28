package com.classpath.assignment.model;

import java.util.HashSet;
import java.util.Set;

public class WorkerWorkstationLevel {
	
	private Worker worker;
	
	private Workstation workstation ;
	
	private SkillLevel skillLevel ;
	
	public WorkerWorkstationLevel(Worker worker, Workstation workstation, SkillLevel skillLevel) {
		this.worker = worker ;
		this.workstation = workstation ;
		this.skillLevel = skillLevel ;
	}

	public Worker getWorker() {
		return worker;
	}

	public Workstation getWorkstation() {
		return workstation;
	}

	public SkillLevel getSkillLevel() {
		return skillLevel;
	}

	public boolean isCanBeTrainer() {
		return skillLevel == SkillLevel.FOUR || skillLevel == SkillLevel.FIVE ;	
	}

	public boolean isCanTrain() {
		return skillLevel.equals(SkillLevel.ZERO);		
	}

	public boolean isCanOperateWithSupervision() {
		return skillLevel.equals(SkillLevel.ONE);
	}

	public boolean isCanOperate() {
		
		Set<SkillLevel> skillSets = new HashSet<SkillLevel>() ;
		skillSets.add(SkillLevel.ONE) ;
		skillSets.add(SkillLevel.TWO) ;
		
		// an op with a skill level < 3 cannot operate an 'A' (apart from trainees)
		if (workstation.getRanking().equals(ErgonomicRanking.A) && (skillSets.contains(skillLevel))) {
			return false ;
		}
		
		return skillLevel.isCanOperate() && 
				!skillLevel.equals(SkillLevel.ZERO) && 
				!skillLevel.equals(SkillLevel.ONE);
	}

}
