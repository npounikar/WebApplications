package com.liquid.rest.test;

public class Member {

	private String type;
	private String ref;
	private String role;
	
	Member() {
		this.type = "";
		this.ref = "";
		this.role = "";
	}
	
	Member(String type, String ref, String role) {
		this.type = type;
		this.ref = ref;
		this.role = role;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	@Override
	public String toString() {
		return "[ type = "+this.type+", ref = "+this.ref+", role = "+this.role+"]";
	}
}
