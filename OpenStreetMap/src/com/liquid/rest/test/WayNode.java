package com.liquid.rest.test;

import java.util.ArrayList;

public class WayNode {

	private String id;
	private ArrayList<String> nodesReferences;
	private ArrayList<Tag> tags;
	
	WayNode() {
		this.id = null;
		this.nodesReferences = new ArrayList<String>();
		this.tags = new ArrayList<Tag>();
	}
	
	WayNode(String id) {
		this.id = id;
		this.nodesReferences = new ArrayList<String>();
		this.tags = new ArrayList<Tag>();
				
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ArrayList<String> getNodesReferences() {
		return nodesReferences;
	}

	public void setNodesReferences(ArrayList<String> nodesReferences) {
		this.nodesReferences = nodesReferences;
	}

	public ArrayList<Tag> getTags() {
		return tags;
	}

	public void setTags(ArrayList<Tag> tags) {
		this.tags = tags;
	}
	
}
