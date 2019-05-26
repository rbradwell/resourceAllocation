package com.classpath.assignment.model;

import java.util.Optional;

public enum SkillLevel {
	
	ZERO("0", false, false),
	ONE("1", true, false),
	TWO("2", true, false),
	THREE("3", true, false),
	FOUR("4", true, true),
	FIVE("5", true, true) ;
	
	private String name ;
	private boolean canOperate ;
	private boolean canTrain ;
	
	private SkillLevel(String name, boolean canOperate, boolean canTrain) {
		this.name = name ;
		this.canOperate = canOperate ;
		this.canTrain = canTrain ;
	}

	public String getName() {
		return name;
	}

	public boolean isCanOperate() {
		return canOperate;
	}

	public boolean isCanTrain() {
		return canTrain;
	}

	public static Optional<SkillLevel> getSkillLevelByName(String name) {
		for (SkillLevel level :SkillLevel.values()) {
			if (level.getName().equals(name)) {
				return Optional.of(level) ;
			}
		}
		return Optional.empty() ;
	}
}
