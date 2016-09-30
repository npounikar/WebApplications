package com.liquid.rest.test;

public class Restriction {
		
	private String restrictionId, from, to, restriction;

	
	public Restriction() {
		this.restrictionId = null;
		this.from = null;
		this.to = null;
		this.restriction = null;
	}
	
	Restriction(String id) {
		this.restrictionId = id;
		this.from = null;
		this.to = null;
		this.restriction = null;
	}

	public String getRestrictionId() {
		return restrictionId;
	}

	public void setRestrictionId(String restrictionId) {
		this.restrictionId = restrictionId;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getRestriction() {
		return restriction;
	}

	public void setRestriction(String restriction) {
		this.restriction = restriction;
	}
	
	@Override
	public String toString() {
		return "[ restrictionId = "+this.restrictionId+", from = "+this.from+", to = "+this.to+", restriction = "+this.restriction+" ]";
	}
	
}
