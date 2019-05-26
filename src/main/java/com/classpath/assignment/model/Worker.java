package com.classpath.assignment.model;

public class Worker {
	
	private WorkerRole role ;
	private String id ;
	protected String fullName ;
	
	public Worker(WorkerRole role, String id, String fullName) {
		this.fullName = fullName ;
		this.id = id ;
	}
	
	public String getId() {
		return id;
	}

	public String getFullName() {
		return fullName;
	}
	
	public WorkerRole getRole() {
		return role;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fullName == null) ? 0 : fullName.hashCode());
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
		
		Worker other = (Worker) obj ;
		if (id == null) {
			if (other.id != null) {
				return false ;
			}
		}

		if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}
}
