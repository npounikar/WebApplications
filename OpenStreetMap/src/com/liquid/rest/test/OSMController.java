package com.liquid.rest.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;

//import org.semanticweb.owlapi.model.OWLOntologyCreationException;
//import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class OSMController {
	
	//OSM file to parse
	public static final String filepath = "C:/Liquid/SpringIntern/TOYOTA/work/mvBlock.osm";

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		
		
		OSMUtility osmUtility = new OSMUtility();
		RoadSegmentUtility roadSegmentUtility = new RoadSegmentUtility();
		
		
		//readNodes
		System.out.println("1. Reading File....");
		Document doc = osmUtility.readFile(filepath);

		//For the Nodes with Points
		System.out.println("2. Getting Nodes, Ways & Relations....");
		Map<String,Node> nodes = new HashMap<String, Node>();
		nodes = osmUtility.getAllNodes(doc);
		Map<String,WayNode> ways = new HashMap<String, WayNode>();
		ways = osmUtility.getAllWayNodes(doc);
		Map<String, Relation> relations = new HashMap<String, Relation>();
		relations = osmUtility.getAllRelations(doc);
		
		//Set to hold up the value check for valid ways
		System.out.println("3. Setting Valid Ways and links for Intersections....");
		Set<String> validHighwayValues = new HashSet<String>();
		validHighwayValues = osmUtility.setValidNodeTagValues();
		Set<String> validHighwayLinkValues = new HashSet<String>();
		validHighwayLinkValues = osmUtility.setValidHighwayLinkValues();
		
		//get Intersections where ways have same nodes
		System.out.println("4. Getting Potential ways that might be intersecting ....");
		Map<String, WayNode> potentialWays = new HashMap<String, WayNode>();
		potentialWays = osmUtility.getPotentialWaysForIntersection(ways, validHighwayLinkValues);

				
		//get Nodes with traffic signals
		System.out.println("5. Getting Nodes With Potentials Intersections....");
		Map<String,Node> nodesWithIntersections = new HashMap<String, Node>();
		nodesWithIntersections = osmUtility.getAllNodesWithPotentialIntersectionTags(nodes, validHighwayValues);
		
		//get Nodes with traffic signals
		System.out.println("6. Getting Intersections Using Traffic Signals....");
		Map<String,Intersection> intersectionsWithTags = new HashMap<String, Intersection>();
		intersectionsWithTags = osmUtility.getIntersections(nodesWithIntersections, potentialWays, nodes);

		//get potential intersecting nodes
		System.out.println("7. Getting Potential nodes that might be an intersection without any tags....");
		Map<String, Node> potentialNodes = new HashMap<String, Node>();
		potentialNodes = osmUtility.getNodesWithoutIntersectionTags(nodes);
		
		//get Intersections where ways have same nodes
		System.out.println("8. Getting Potential Intersections for potential nodes..........");
		Map<String,Intersection> intersectionsWithoutTags = new HashMap<String, Intersection>();
		System.out.println();
		intersectionsWithoutTags = osmUtility.getIntersections(potentialNodes, potentialWays, nodes);
		
		//Merge all intersections
		System.out.println("9. Merge all  Intersections..........");
		Map<String,Intersection> intersections = new HashMap<String, Intersection>();
		intersections = osmUtility.mergeAllIntersections(intersectionsWithoutTags, intersectionsWithTags);
		
		//Set Restrictions to all intersections
		System.out.println("10. Set Restrictions..........");
		intersections = roadSegmentUtility.setRestrictions(intersections, relations, potentialWays);
		
		//Set all ways going out from intersections
		System.out.println("10. Set from Ways..........");
		intersections = roadSegmentUtility.setFromWays(intersections, nodes);
		
		//Set all ways coing into intersections
		System.out.println("10. Set To Ways..........");
		intersections = roadSegmentUtility.setToWays(intersections, nodes);
		
		//get the RoadSegments
		System.out.println("Getting RoadSegments ");
		ArrayList<RoadSegment> roadSegments = new ArrayList<RoadSegment>();
		roadSegments = roadSegmentUtility.getRoadSegments(intersections, nodes, potentialWays);
		
		//Print all intersections
//		System.out.println("11. Print all  Intersections..........");
//		RDFConverter rdfConverter = new RDFConverter();
//		rdfConverter.convertToRDF(nodes, ways, intersections);
//		osmUtility.printIntersections(intersections);

		
		
//		System.out.println("=========================================================================");
//		System.out.println("Nodes: "+nodes.size());
//		System.out.println("Intersection(W/out one way in it & with tags) : "+intersectionsWithTags.size());
//		System.out.println("Intersection(withput tags) : "+intersectionsWithoutTags.size());
//		System.out.println("Intersection : "+intersections.size());
//		System.out.println("=========================================================================");
	
	}

}
