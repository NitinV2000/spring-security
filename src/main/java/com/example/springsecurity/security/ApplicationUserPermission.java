package com.example.springsecurity.security;

public enum ApplicationUserPermission {
	
	STUDENT_READ("student:read"),
	STUDENT_WRITE("student:write"),
	COURSE_READ("course:read"),
	COURSE_WRITE("course:write");
	
	private final String permission;

	public String getPermission() {
		return permission;
	}

	private ApplicationUserPermission(String permission) {
		this.permission = permission;
	}
	
	
}
