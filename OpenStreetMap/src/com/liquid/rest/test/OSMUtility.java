package com.liquid.rest.test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class OSMUtility {

	
	public Document readFile(String filepath) throws ParserConfigurationException, SAXException, IOException {

		File file = new File(filepath);
		DocumentBuilderFactory dBuilder =  DocumentBuilderFactory.newInstance();
		DocumentBuilder doc = dBuilder.newDocumentBuilder();
		Document d = doc.parse(file);
		return d;
	}


	public Map<String,Node> getAllNodes(Document d) {
		
		//For the Nodes with Points
		Map<String,Node> nodes = new HashMap<String, Node>();
		
		//Parse the content of the given input source as an XML document and return a new DOM
		NodeList nodeslist = d.getElementsByTagName("node");
		long countOfNodes = nodeslist.getLength();
		for (int i = 0; i < countOfNodes; i++) {
			Node node = new Node();
			String nodeId = nodeslist.item(i).getAttributes().item(0).toString().substring(
					nodeslist.item(i).getAttributes().item(0).toString().lastIndexOf("id=")+4, nodeslist.item(i).getAttributes().item(0).toString().lastIndexOf('"'));
			String nodeLatitude = nodeslist.item(i).getAttributes().item(1).toString().substring(
					nodeslist.item(i).getAttributes().item(1).toString().lastIndexOf("lat=")+5, nodeslist.item(i).getAttributes().item(1).toString().lastIndexOf('"'));
			String nodeLongitude = nodeslist.item(i).getAttributes().item(2).toString().substring(
					nodeslist.item(i).getAttributes().item(2).toString().lastIndexOf("lon=")+5, nodeslist.item(i).getAttributes().item(2).toString().lastIndexOf('"'));


			ArrayList<Tag> tags = new ArrayList<Tag>();
			Element element = (Element) nodeslist.item(i);
			NodeList tagsList = (NodeList) element.getElementsByTagName("tag");
			for (int k = 0; k < tagsList.getLength(); k++) {
				String key = tagsList.item(k).getAttributes().item(0).toString().substring(
						tagsList.item(k).getAttributes().item(0).toString().lastIndexOf("k=")+3, tagsList.item(k).getAttributes().item(0).toString().lastIndexOf('"'));
				String value = tagsList.item(k).getAttributes().item(1).toString().substring(
						tagsList.item(k).getAttributes().item(1).toString().lastIndexOf("v=")+3, tagsList.item(k).getAttributes().item(1).toString().lastIndexOf('"'));

				tags.add(new Tag(key, value));
			}
			
			//setting the elements in Nodes - 
			node.setId(nodeId);
			node.setPoint(new Point(Double.parseDouble(nodeLatitude), Double.parseDouble(nodeLongitude)));
			node.setTags(tags);
			
			//adding node  to node map
			if(!nodes.containsKey(node.getId()))
				nodes.put(node.getId(), node);	
		}
		return nodes;
	}
	
	
	
	
	public Map<String,WayNode> getAllWayNodes(Document d) {
		
		//For the WayNodes
		Map<String,WayNode> ways = new HashMap<String, WayNode>();

		//For Every <way> Node
		NodeList waysList = d.getElementsByTagName("way");
		for (int i = 0; i < waysList.getLength(); i++) {
			WayNode wayNode = new WayNode();
			String wayId = waysList.item(i).getAttributes().item(0).toString().substring(
					waysList.item(i).getAttributes().item(0).toString().lastIndexOf("id=")+4, waysList.item(i).getAttributes().item(0).toString().lastIndexOf('"'));

			Element element = (Element) waysList.item(i);
			
			//adding nodes
			ArrayList<String> nodeReferences = new ArrayList<String>();
			NodeList nodeList = (NodeList) element.getElementsByTagName("nd");
			if(nodeList.getLength() == 0)
				continue;
			for (int j = 0; j < nodeList.getLength(); j++) {
				String referredNodeId = nodeList.item(j).getAttributes().item(0).toString().substring(
						nodeList.item(j).getAttributes().item(0).toString().lastIndexOf("ref=")+5, nodeList.item(j).getAttributes().item(0).toString().lastIndexOf('"'));

				nodeReferences.add(referredNodeId);
			}
			
			//adding tags with key value pair
			ArrayList<Tag> tags = new ArrayList<Tag>();
			NodeList tagList = (NodeList) element.getElementsByTagName("tag");
			for (int k = 0; k < tagList.getLength(); k++) {
				String key = tagList.item(k).getAttributes().item(0).toString().substring(
						tagList.item(k).getAttributes().item(0).toString().lastIndexOf("k=")+3, tagList.item(k).getAttributes().item(0).toString().lastIndexOf('"'));
				String value = tagList.item(k).getAttributes().item(1).toString().substring(
						tagList.item(k).getAttributes().item(1).toString().lastIndexOf("v=")+3, tagList.item(k).getAttributes().item(1).toString().lastIndexOf('"'));

				tags.add(new Tag(key, value));
			}

			//setting the way
			wayNode.setId(wayId);
			wayNode.setNodesReferences(nodeReferences);
			wayNode.setTags(tags);
			
			// adding waysNode to way Map
			if(!ways.containsKey(wayId))
				ways.put(wayId, wayNode);
		}
		
		return ways;
	}
		
	

	
	public Map<String,Relation> getAllRelations(Document d) {
		
		// For relations
		Map<String, Relation> relations = new HashMap<String, Relation>();
		
		NodeList relationslist = d.getElementsByTagName("relation");
		long countOfRelations = relationslist.getLength();
		for (int i = 0; i < countOfRelations; i++) {
			Relation relation = new Relation();
			String relationId = relationslist.item(i).getAttributes().item(0).toString().substring(
					relationslist.item(i).getAttributes().item(0).toString().lastIndexOf("id=")+4, relationslist.item(i).getAttributes().item(0).toString().lastIndexOf('"'));
			
			Element element = (Element) relationslist.item(i);
			
			//adding members
			ArrayList<Member> members = new ArrayList<Member>();
			NodeList membersList = (NodeList) element.getElementsByTagName("member");
			if(membersList.getLength() == 0)
				continue;	
			for (int j = 0; j < membersList.getLength(); j++) {
				String memberRef = membersList.item(j).getAttributes().item(0).toString().substring(
						membersList.item(j).getAttributes().item(0).toString().lastIndexOf("ref=")+5, membersList.item(j).getAttributes().item(0).toString().lastIndexOf('"'));
				String memberRole = membersList.item(j).getAttributes().item(1).toString().substring(
						membersList.item(j).getAttributes().item(1).toString().lastIndexOf("role=")+6, membersList.item(j).getAttributes().item(1).toString().lastIndexOf('"'));
				String memberType = membersList.item(j).getAttributes().item(2).toString().substring(
						membersList.item(j).getAttributes().item(2).toString().lastIndexOf("type=")+6, membersList.item(j).getAttributes().item(2).toString().lastIndexOf('"'));

				members.add(new Member(memberType, memberRef, memberRole));
			}
			
			//adding tags with key value pair
			ArrayList<Tag> relationTags = new ArrayList<Tag>();
			NodeList tagsList = (NodeList) element.getElementsByTagName("tag");	
			for (int k = 0; k < tagsList.getLength(); k++) {
				String key = tagsList.item(k).getAttributes().item(0).toString().substring(
						tagsList.item(k).getAttributes().item(0).toString().lastIndexOf("k=")+3, tagsList.item(k).getAttributes().item(0).toString().lastIndexOf('"'));
				String value = tagsList.item(k).getAttributes().item(1).toString().substring(
						tagsList.item(k).getAttributes().item(1).toString().lastIndexOf("v=")+3, tagsList.item(k).getAttributes().item(1).toString().lastIndexOf('"'));

				relationTags.add(new Tag(key, value));
			}
			
			//setting the relations
			relation.setRelationId(relationId);
			relation.setMembers(members);
			relation.setTags(relationTags);
			
			// adding relation for a relation map
			if(!relations.containsKey(relationId))
				relations.put(relationId, relation);
		}
		
		return relations;	
	}
		
	
	public Set<String> setValidNodeTagValues() {
		Set<String> validWays = new HashSet<String>();
		
		// Init the valid ways
		validWays.add("mini_roundabout");
		validWays.add("traffic_signals");
		validWays.add("motorway_junction");

		return validWays;
	}
	
	public Set<String> setValidHighwayLinkValues() {
		Set<String> validWays = new HashSet<String>();
		
		// Init the valid ways
		validWays.add("primary");
		validWays.add("primary_link");
		validWays.add("secondary");
		validWays.add("secondary_link");
		validWays.add("tertiary");
		validWays.add("tertiary_link");
		validWays.add("trunk");
		validWays.add("trunk_link");
		validWays.add("motorway");
		validWays.add("motorway_link");
		validWays.add("unclassified");
		validWays.add("residential");
		validWays.add("service");

		return validWays;
	}
	
	public Set<String> setValidLinkTags() {
		Set<String> validWays = new HashSet<String>();
		
		// Init the valid links
		validWays.add("primary_link");
		validWays.add("secondary_link");
		validWays.add("tertiary_link");
		validWays.add("trunk_link");
		validWays.add("motorway_link");
		validWays.add("unclassified");
		validWays.add("residential");
		validWays.add("service");

		return validWays;
	}
	
	
	// Get Nodes With Traffic Signals
	public Map<String, Node> getAllNodesWithPotentialIntersectionTags(Map<String, Node> nodes, Set<String> validHighwayValues) {
		//For nodes with Traffic Signals
		Map<String,Node> nodesWithIntersections = new HashMap<String, Node>();
		Iterator<Entry<String, Node>> nodeIterator = nodes.entrySet().iterator();
		while(nodeIterator.hasNext()){
			boolean valid = false;
			Entry<String,Node> entry = nodeIterator.next();
			Node currentNode = entry.getValue();
			
			for(int i = 0; i < currentNode.getTags().size(); i++) 
				if(currentNode.getTags().get(i).getK().equals("highway") && validHighwayValues.contains(currentNode.getTags().get(i).getV())) 
					valid = true;

			if(valid && !nodesWithIntersections.containsKey(currentNode.getId()))
				nodesWithIntersections.put(currentNode.getId(), currentNode);
			
		}
		return nodesWithIntersections;
	}
	
	
	public Map<String, Node> getNodesWithoutIntersectionTags(Map<String, Node> nodes) {
		Map<String,Node> nodesWithoutIntersectionTags = new HashMap<String, Node>();
		Iterator<Entry<String, Node>> it = nodes.entrySet().iterator();
		while(it.hasNext()){
			Entry<String, Node> entry = it.next();
			Node currentNode = entry.getValue();
			if(currentNode.getTags().size() == 0)
				nodesWithoutIntersectionTags.put(entry.getKey(), entry.getValue());
		}
		return nodesWithoutIntersectionTags;
	}

	
	
	public Map<String, Intersection> getIntersections(Map<String, Node> nodesToCheckForIntersections, Map<String, WayNode> potentialWays, Map<String, Node> nodes) {
		//For Intersection
		Map<String,Intersection> intersections = new HashMap<String, Intersection>();
		Map<String,ArrayList<String>> waysConnectedToANode = new HashMap<String, ArrayList<String>>();
		ArrayList<String> waysConnected = new ArrayList<String>();
		Iterator<Entry<String, Node>> nodeIterator = nodesToCheckForIntersections.entrySet().iterator();
		while(nodeIterator.hasNext()){
			Entry<String,Node> nodeEntry = nodeIterator.next();
			Node currentNode = nodeEntry.getValue();
			waysConnected = getInsersectingWays(currentNode, potentialWays);
			if(waysConnected != null)
				waysConnectedToANode.put(currentNode.getId(), waysConnected);
		}		
		intersections = getMappedIntersectionToWays(waysConnectedToANode, potentialWays, nodes);
		return intersections;
	}



	private Map<String, Intersection> getMappedIntersectionToWays(Map<String, ArrayList<String>> waysConnectedToANode, 
			Map<String, WayNode> potentialWays, Map<String, Node> nodes) {

		//For Intersection
		Set<String> validLinkTags = setValidLinkTags();
		Set<String> validNodeTags = setValidNodeTagValues();
		Map<String,Intersection> intersections = new HashMap<String, Intersection>();
		Intersection intersection;
		Iterator<Entry<String, ArrayList<String>>> iterator = waysConnectedToANode.entrySet().iterator();
		while(iterator.hasNext()){
			Entry<String, ArrayList<String>> entry = iterator.next();
			ArrayList<WayNode> connectedWayNodes = new ArrayList<WayNode>();
			intersection = new Intersection();
			
			String intersectionType = null;
			String intersectionId = entry.getKey();
			for(int i = 0; i < entry.getValue().size(); i++) {
				WayNode currentWayNode = potentialWays.get(entry.getValue().get(i));
				if(currentWayNode.getTags().size() >= 1) {
					for(int j = 0; j<currentWayNode.getTags().size(); j++)
						if(currentWayNode.getTags().get(j).getK().equals("highway") && validLinkTags.contains(currentWayNode.getTags().get(j).getV()) ) {
							intersectionType = currentWayNode.getTags().get(j).getV();
						}
				}
				connectedWayNodes.add(currentWayNode);
			}
			//setting the intersection
			intersection.setIntersectionId(intersectionId);
			intersection.setWaysConnected(connectedWayNodes);
			intersection.setLocation(nodes.get(intersectionId).getPoint());
			
			if(intersectionType == null) {
				if(nodes.get(intersectionId).getTags().size() >= 1) {
					for(int i = 0; i<nodes.get(intersectionId).getTags().size(); i++)
						if(nodes.get(intersectionId).getTags().get(i).getK().equals("highway"))
							intersection.setIntersectionType(nodes.get(intersectionId).getTags().get(i).getV());
				} else 
					intersection.setIntersectionType(intersectionType);
			} else {
				if(intersectionType.equals("residential") || intersectionType.equals("service") || intersectionType.equals("unclassified")) {
					if(nodes.get(intersectionId).getTags().size() >= 1) {
						for(int i = 0; i<nodes.get(intersectionId).getTags().size(); i++) {
							if(nodes.get(intersectionId).getTags().get(i).getK().equals("highway")) {
								if(validNodeTags.contains(nodes.get(intersectionId).getTags().get(i).getV()))
									intersectionType = nodes.get(intersectionId).getTags().get(i).getV();
							}
						}
					}
				}
				intersection.setIntersectionType(intersectionType);
			}
			
			//Adding to map intersections
			intersections.put(intersectionId, intersection);
		}
		
		return intersections;
	}

	
	private ArrayList<String> getInsersectingWays(Node currentNode, Map<String, WayNode> potentialWays) {

		//for insersecting ways
		ArrayList<String> intersectingWays = new ArrayList<String>();
		Iterator<Entry<String, WayNode>> wayIterator = potentialWays.entrySet().iterator();
		while(wayIterator.hasNext()){
			Entry<String,WayNode> wayEntry = wayIterator.next();
			WayNode currentWay = wayEntry.getValue();
			for(int i=0; i<currentWay.getNodesReferences().size(); i++) {
				if(currentNode.getId().equals(currentWay.getNodesReferences().get(i))) {
					intersectingWays.add(currentWay.getId());
					continue;
				}
			}
		}
		if(intersectingWays.size() > 1) 
			return intersectingWays;
		else 
			
			return null;
	}
	
	


	public Map<String, WayNode> getPotentialWaysForIntersection(Map<String, WayNode> ways, Set<String> validHighwayLinkValues) {
		Map<String, WayNode> waysTemp = new HashMap<String, WayNode>();
		Iterator<Entry<String, WayNode>> wayIterator = ways.entrySet().iterator();
		while(wayIterator.hasNext()){
			Entry<String,WayNode> wayEntry = wayIterator.next();
			WayNode currentWay = wayEntry.getValue();
			ArrayList<String> nodeList = currentWay.getNodesReferences();
			if(nodeList.get(0).equals(nodeList.get(nodeList.size()-1))) 
				continue;
			if(currentWay.getTags().size() >= 1) {
				for(int j = 0; j<currentWay.getTags().size(); j++) {
					if(currentWay.getTags().get(j).getK().equals("highway") && validHighwayLinkValues.contains(currentWay.getTags().get(j).getV())) {
						waysTemp.put(currentWay.getId(), currentWay);
						break;
					} 
				}
			} else 
				waysTemp.put(currentWay.getId(), currentWay);
		}
		return waysTemp;
	}


	public Map<String, Intersection> mergeAllIntersections(Map<String, Intersection> intersectionsWithoutTags,
			Map<String, Intersection> intersectionsWithTags) {
		
		Map<String,Intersection> intersections = new HashMap<String, Intersection>();
		
		// Adding Intersections with tags
		Iterator<Entry<String, Intersection>> withTagsIterator = intersectionsWithTags.entrySet().iterator();
		while(withTagsIterator.hasNext()){
			Entry<String, Intersection> entry = withTagsIterator.next();
			String intersectionId = entry.getKey();
			intersections.put(intersectionId, entry.getValue());
		}
		
		//adding intersections without tags
		Iterator<Entry<String, Intersection>> withoutTagsIterator = intersectionsWithoutTags.entrySet().iterator();
		while(withoutTagsIterator.hasNext()){
			Entry<String, Intersection> entry = withoutTagsIterator.next();
			String intersectionId = entry.getKey();
			if(!intersectionsWithTags.containsKey(intersectionId))
				intersections.put(intersectionId, entry.getValue());

		}
		return intersections;
	}

	

	public void printIntersections(Map<String, Intersection> intersections) {
		Iterator<Entry<String, Intersection>> iterator = intersections.entrySet().iterator();
		while(iterator.hasNext()){
			Entry<String, Intersection> entry = iterator.next();
			System.out.println(entry.getValue().toString());
		}
	}
	
}
