package com.liquid.rest.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class RoadSegmentUtility {

	
	public Map<String, Intersection> setRestrictions(Map<String, Intersection> intersections, Map<String, Relation> relations, Map<String, WayNode> potentialWays) {
			
		Map<String, Relation> potentialRestrictionRelations = getPortentialRelationsWithRestrictions(relations);
		Map<String, ArrayList<Restriction>> restrictions = getRestrictions(potentialRestrictionRelations, potentialWays);
		Iterator<Entry<String, ArrayList<Restriction>>> iterator = restrictions.entrySet().iterator();
		while(iterator.hasNext()){
			Entry<String, ArrayList<Restriction>> entry = iterator.next();
			if(intersections.containsKey(entry.getKey()))
				intersections.get(entry.getKey()).setRestrictions(entry.getValue());
		}
		return intersections;
	}

	
	
	private Map<String, ArrayList<Restriction>> getRestrictions(Map<String, Relation> potentialRestrictionRelations, Map<String, WayNode> potentialWays) {
		
		Map<String, ArrayList<Restriction>> restrictions = new HashMap<String, ArrayList<Restriction>>();
		Iterator<Entry<String, Relation>> iterator = potentialRestrictionRelations.entrySet().iterator();
		while(iterator.hasNext()){
			Entry<String, Relation> entry = iterator.next();
			Relation currentRelation = entry.getValue();
			ArrayList<Member> members = currentRelation.getMembers();
			ArrayList<Tag> tags = currentRelation.getTags();
			boolean valid = true;
			Restriction newRestriction = new Restriction();
			if(members.size()>=3) {
				for(int i = 0; i<members.size() && valid; i++) {
					// Set ID - FROM - TO
					if(members.get(i).getRole().equals("via") && members.get(i).getType().equals("node")) 
						newRestriction.setRestrictionId(members.get(i).getRef());
					else if(members.get(i).getRole().equals("from") && members.get(i).getType().equals("way")) 
						newRestriction.setFrom(members.get(i).getRef());
					else if(members.get(i).getRole().equals("to") && members.get(i).getType().equals("way")) 
						newRestriction.setTo(members.get(i).getRef());
					else {
						valid = false;
						break;
					}
				}
			} else 
				valid =false;
			
 			if(valid) {
 				for(int i = 0; i<tags.size() && valid; i++) {
 					if(tags.get(i).getK().equals("restriction")) {
 						newRestriction.setRestriction(tags.get(i).getV());
 						break;
 					}
 				}
 						
 				if(!restrictions.containsKey(newRestriction.getRestrictionId())) {
 					ArrayList<Restriction> restrictionsList = new ArrayList<Restriction>();
 					restrictionsList.add(newRestriction);
 					restrictions.put(newRestriction.getRestrictionId(), restrictionsList);
 				} else {
 					ArrayList<Restriction> restrictionsList = restrictions.get(newRestriction.getRestrictionId());
 					restrictionsList.add(newRestriction);
 					restrictions.put(newRestriction.getRestrictionId(), restrictionsList);
 				}
 			}
		}
		
		return restrictions;
	}



	private Map<String, Relation> getPortentialRelationsWithRestrictions(Map<String, Relation> relations) {
		
		Map<String, Relation> potentialRelations = new HashMap<String, Relation>();
		Iterator<Entry<String, Relation>> iterator = relations.entrySet().iterator();
		while(iterator.hasNext()){
			Entry<String, Relation> entry = iterator.next();
			Relation currentRelation = entry.getValue();
			for(int i=0; i< currentRelation.getTags().size(); i++) {
				if(currentRelation.getTags().get(i).getK().equals("type") && currentRelation.getTags().get(i).getV().equals("restriction")) {
					potentialRelations.put(currentRelation.getRelationId(), currentRelation);
					break;
				}
			}
		}
		return potentialRelations;
	}


	
	public Map<String, Intersection> setFromWays(
			Map<String, Intersection> intersections, Map<String, Node> nodes) {
		// TODO Auto-generated method stub
		return null;
	}



	public Map<String, Intersection> setToWays(
			Map<String, Intersection> intersections, Map<String, Node> nodes) {
		// TODO Auto-generated method stub
		return null;
	}

	


	public ArrayList<RoadSegment> getRoadSegments(Map<String, Intersection> intersections, Map<String, Node> nodes,
			Map<String, WayNode> potentialWays) {
		
		Iterator<Entry<String, Intersection>> iterator = intersections.entrySet().iterator();
		while(iterator.hasNext()){
			Entry<String, Intersection> entry = iterator.next();
			ArrayList<WayNode> waysConnected = entry.getValue().getWaysConnected();
			System.out.println("============");
			System.out.println(entry.getKey());
			for(int i = 0; i<waysConnected.size(); i++) {
				System.out.println(waysConnected.get(i).getId());
			}
		}
		return null;
	}


}

