package com.classpath.assignment.model;

public class Workstation {

	public static final int NO_OF_SESSIONS = 3 ;
	private String id ;
	private String name ;
	private ErgonomicRanking ranking ;
	
	public Workstation(String id, String name, 
						ErgonomicRanking ranking) {
		this.id = id ;
		this.name = name ;
		this.ranking = ranking ;
	}

	public ErgonomicRanking getRanking() {
		return ranking;
	}

	public String getName() {
		return name;
	}

	public static int getNoOfSessions() {
		return NO_OF_SESSIONS;
	}

	public String getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		
		if (obj == null) {
			return false;
		}
		
		if (getClass() != obj.getClass()) {
			return false;
		}
		
		Workstation other = (Workstation) obj ;
		if (name == null) {
			if (other.name != null) {
				return false ;
			}
		}

		if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}
}
