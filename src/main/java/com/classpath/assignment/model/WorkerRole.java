package com.classpath.assignment.model;

public enum WorkerRole {
	TEAM_LEADER("T", "Team Leader"), 
	OPERATOR("O", "Operator") ;
	
	private String roleType ;
	private String roleDesc ;
	
	private WorkerRole(String roleType, String roleDesc) {
		this.roleType = roleType ;
		this.roleDesc = roleDesc ;
	}

	public String getRoleType() {
		return roleType;
	}

	public String getRoleDesc() {
		return roleDesc;
	}
	
}