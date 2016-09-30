package com.liquid.rest.test;

import java.util.ArrayList;

public class Relation {
	
	private String relationId;
	private ArrayList<Tag> tags;
	private ArrayList<Member> members;

	Relation() {
		this.relationId = "";
		this.tags = new ArrayList<Tag>();
		this.members = new ArrayList<Member>();
	}
	
	Relation(String id) {
		this.relationId = id;
		this.tags = new ArrayList<Tag>();
		this.members = new ArrayList<Member>();
	}

	public String getRelationId() {
		return relationId;
	}

	public void setRelationId(String relationId) {
		this.relationId = relationId;
	}

	public ArrayList<Tag> getTags() {
		return tags;
	}

	public void setTags(ArrayList<Tag> tags) {
		this.tags = tags;
	}

	public ArrayList<Member> getMembers() {
		return members;
	}

	public void setMembers(ArrayList<Member> members) {
		this.members = members;
	}
	
	
}
