package com.liquid.rest.test;

import java.util.ArrayList;

public class RoadSegment {

	private String id;
	private ArrayList<WayNode> wayNodes;
	
	RoadSegment() {
		this.id = null;
		this.wayNodes = new ArrayList<WayNode>();
	}
	
	RoadSegment(String id) {
		this.id = id;
		this.wayNodes = new ArrayList<WayNode>();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ArrayList<WayNode> getWayNodes() {
		return wayNodes;
	}

	public void setWayNodes(ArrayList<WayNode> wayNodes) {
		this.wayNodes = wayNodes;
	}
	
}
