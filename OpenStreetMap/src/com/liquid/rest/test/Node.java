package com.liquid.rest.test;

import java.util.ArrayList;

public class Node {
	private String id;
	private Point point;
	private ArrayList<Tag> tags;

	Node() {
		this.id = null;
		this.point = new Point(0.0, 0.0);
		this.tags = new ArrayList<Tag>();
	}
	
	Node(String id) {
		this.id = id;
		this.point = new Point(0.0, 0.0);
		this.tags = new ArrayList<Tag>();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	public ArrayList<Tag> getTags() {
		return tags;
	}

	public void setTags(ArrayList<Tag> tags) {
		this.tags = tags;
	}
	
	
}
