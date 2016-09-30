package com.liquid.rest.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.liquid.rest.test.Point;
import com.liquid.rest.test.WayNode;

public class Intersection {

	private String intersectionId;
	private ArrayList<WayNode> waysConnected, from, to;
	private Point location;
	private String intersectionType;
	private ArrayList<Restriction> restrictions;

	
	public Intersection() {
		this.intersectionId = null;
		this.intersectionType = null;
		this.location = new Point(0.0, 0.0);
		this.to = new ArrayList<WayNode>();
		this.from = new ArrayList<WayNode>();
		this.waysConnected = new ArrayList<WayNode>();
		this.restrictions = new ArrayList<Restriction>();
	}
	public Intersection(String id) {
		this.intersectionId = id;
		this.intersectionType = null;
		this.location = new Point(0.0, 0.0);
		this.to = new ArrayList<WayNode>();
		this.from = new ArrayList<WayNode>();
		this.waysConnected = new ArrayList<WayNode>();
		this.restrictions = new ArrayList<Restriction>();
	}
	public String getIntersectionId() {
		return intersectionId;
	}
	public void setIntersectionId(String intersectionId) {
		this.intersectionId = intersectionId;
	}
	public ArrayList<WayNode> getWaysConnected() {
		return waysConnected;
	}
	public void setWaysConnected(ArrayList<WayNode> waysConnected) {
		this.waysConnected = waysConnected;
	}
	public Point getLocation() {
		return location;
	}
	public void setLocation(Point location) {
		this.location = location;
	}	
	public String getIntersectionType() {
		return intersectionType;
	}
	public void setIntersectionType(String type) {
		this.intersectionType = type;
	}	
	public ArrayList<Restriction> getRestrictions() {
		return restrictions;
	}
	public void setRestrictions(ArrayList<Restriction> restrictions) {
		this.restrictions = restrictions;
	}
	public ArrayList<WayNode> getFrom() {
		return from;
	}
	public void setFrom(ArrayList<WayNode> from) {
		this.from = from;
	}
	public ArrayList<WayNode> getTo() {
		return to;
	}
	public void setTo(ArrayList<WayNode> to) {
		this.to = to;
	}
	@Override
	public String toString() {
		String result = "";
		result = "[\n INTERSECTION(NODE) ID : "+this.intersectionId+", type = "+this.intersectionType+", location : "+this.location.getLatitude()+" "+this.location.getLongitude()+" \n";
		for(int i = 0; i<this.waysConnected.size(); i++) {
			result = result + "waysConnected = "+waysConnected.get(i).getId()+" | ";
//			for(int j = 0; j<this.waysConnected.get(i).getNodesReferences().size(); j++) {
//				result = result + "node = "+waysConnected.get(i).getNodesReferences().get(j)+", \n";
//			}
		}
		for(int i = 0; i < this.restrictions.size(); i++)
			result = result + ", \n restriction = "+this.restrictions.get(i).toString();
		result = result + "]";
		return result;
	}
	
}
