package com.liquid.rest.test;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.PrintStream;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.Map;
//import java.util.Map.Entry;
//
//import org.semanticweb.owlapi.apibinding.OWLManager;
//import org.semanticweb.owlapi.io.StreamDocumentTarget;
//import org.semanticweb.owlapi.model.AddAxiom;
//import org.semanticweb.owlapi.model.IRI;
//import org.semanticweb.owlapi.model.OWLClass;
//import org.semanticweb.owlapi.model.OWLClassAssertionAxiom;
//import org.semanticweb.owlapi.model.OWLDataFactory;
//import org.semanticweb.owlapi.model.OWLDataProperty;
//import org.semanticweb.owlapi.model.OWLDataPropertyAssertionAxiom;
//import org.semanticweb.owlapi.model.OWLDatatype;
//import org.semanticweb.owlapi.model.OWLIndividual;
//import org.semanticweb.owlapi.model.OWLLiteral;
//import org.semanticweb.owlapi.model.OWLObjectProperty;
//import org.semanticweb.owlapi.model.OWLObjectPropertyAssertionAxiom;
//import org.semanticweb.owlapi.model.OWLOntology;
//import org.semanticweb.owlapi.model.OWLOntologyCreationException;
//import org.semanticweb.owlapi.model.OWLOntologyManager;
//import org.semanticweb.owlapi.model.OWLOntologyStorageException;
//import org.semanticweb.owlapi.model.PrefixManager;
//import org.semanticweb.owlapi.util.DefaultPrefixManager;
//
//
//
public class RDFConverter {
//
//	// some definitions
//	final static String AGENT_NS     = "http://us.toyota-itc.com/agent/";
//	final static String MAP_NS       = "http://us.toyota-itc.com/map/";
//	final static String profiumPointDatatypeURI = "http://www.profium.com/schema/v5/point";
//
//	public void convertToRDF(Map<String, Node> nodes, Map<String, WayNode> ways, Map<String, Intersection> intersections) 
//			throws FileNotFoundException, OWLOntologyCreationException, OWLOntologyStorageException {	
//	
//		IRI agentIRI = IRI.create(AGENT_NS);
//		IRI profiumPointIRI = IRI.create(profiumPointDatatypeURI);
//
//		OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
//		OWLOntology ont = manager.createOntology(agentIRI);
//		OWLDataFactory factory = manager.getOWLDataFactory();
//
//		PrefixManager prefixManager = new DefaultPrefixManager(agentIRI.toString());
//		OWLClass linkClass = factory.getOWLClass("Link", prefixManager);
//		OWLDatatype pointDatatype = factory.getOWLDatatype(profiumPointIRI);
//		
//		OWLIndividual intersectionInstance = null, linkInstance = null, forwardWayInstance= null;
//		OWLDataProperty dataProp;
//		OWLObjectProperty objProp;
//		OWLLiteral val;
//		OWLDataPropertyAssertionAxiom axiom;
//		OWLObjectPropertyAssertionAxiom objAxiom;
//		AddAxiom addAxiom;
//		
//
//		int count = 0, linkCount = 0, intersectionCount = 0;
//		Iterator<Entry<String, Intersection>> intersectionIterator = intersections.entrySet().iterator();
//		while(intersectionIterator.hasNext()) {	
//			count++;
//
//			//fetching the intersection data
//			Entry<String, Intersection> entry = intersectionIterator.next();
//			Intersection intersection = entry.getValue();
//			String intersectionId = intersection.getIntersectionId();				
//			ArrayList<WayNode> waysConnected = intersection.getWaysConnected();
//			ArrayList<String> waysConnectedId = new ArrayList<String>();
//			for(int i=0; i<waysConnected.size(); i++) 
//				waysConnectedId.add(waysConnected.get(i).getId());
//
//			String type = intersection.getIntersectionType();
//			if(type != null && type.endsWith("link")) {
//				linkCount++;
//				//converting into RDF
//				linkInstance = factory.getOWLNamedIndividual(IRI.create(RDFConverter.MAP_NS+"link/"+intersectionId+"/L"));
//
//				//asserting Intersection
//				OWLClassAssertionAxiom assertion = factory.getOWLClassAssertionAxiom(linkClass, linkInstance);
//				manager.addAxiom(ont, assertion);
//
//				//asserting location
//				String location = intersection.getLocation().getLatitude()+" "+intersection.getLocation().getLongitude();
//				dataProp = factory.getOWLDataProperty(IRI.create(agentIRI + "hasLocation"));		    
//				val = factory.getOWLLiteral(location, pointDatatype);
//				axiom = factory.getOWLDataPropertyAssertionAxiom(dataProp, linkInstance, val);
//				addAxiom = new AddAxiom(ont, axiom);
//				manager.applyChange(addAxiom);
//
//				//asserting type
//				dataProp = factory.getOWLDataProperty(IRI.create(agentIRI + "intersectionType"));		    
//				val = factory.getOWLLiteral(type);
//				axiom = factory.getOWLDataPropertyAssertionAxiom(dataProp, linkInstance, val);
//				addAxiom = new AddAxiom(ont, axiom);
//				manager.applyChange(addAxiom);
//
//				for(int i=0; i<waysConnectedId.size(); i++)  {
//					forwardWayInstance = factory.getOWLNamedIndividual(IRI.create(RDFConverter.MAP_NS+"roadSegment/"+waysConnectedId.get(i)+"/F"));
//					objProp = factory.getOWLObjectProperty(IRI.create(agentIRI + "hasWayConnected"));
//					objAxiom = factory.getOWLObjectPropertyAssertionAxiom(objProp, linkInstance, forwardWayInstance);
//					addAxiom = new AddAxiom(ont, objAxiom);
//					manager.applyChange(addAxiom);
//				}
//			} else {				
//				intersectionCount++;
//				//converting into RDF
//				intersectionInstance = factory.getOWLNamedIndividual(IRI.create(RDFConverter.MAP_NS+"intersection/"+intersectionId+"/I"));
//
//				//asserting Intersection
//				OWLClassAssertionAxiom assertion = factory.getOWLClassAssertionAxiom(linkClass, intersectionInstance);
//				manager.addAxiom(ont, assertion);
//
//				//asserting location
//				String location = intersection.getLocation().getLatitude()+" "+intersection.getLocation().getLongitude();
//				dataProp = factory.getOWLDataProperty(IRI.create(agentIRI + "hasLocation"));		    
//				val = factory.getOWLLiteral(location, pointDatatype);
//				axiom = factory.getOWLDataPropertyAssertionAxiom(dataProp, intersectionInstance, val);
//				addAxiom = new AddAxiom(ont, axiom);
//				manager.applyChange(addAxiom);
//
//				//asserting type
//				if(type != null) {
//					dataProp = factory.getOWLDataProperty(IRI.create(agentIRI + "intersectionType"));		    
//					val = factory.getOWLLiteral(type);
//					axiom = factory.getOWLDataPropertyAssertionAxiom(dataProp, intersectionInstance, val);
//					addAxiom = new AddAxiom(ont, axiom);
//					manager.applyChange(addAxiom);
//				}
//
//				for(int i=0; i<waysConnectedId.size(); i++)  {
//					forwardWayInstance = factory.getOWLNamedIndividual(IRI.create(RDFConverter.MAP_NS+"roadSegment/"+waysConnectedId.get(i)+"/F"));
//					objProp = factory.getOWLObjectProperty(IRI.create(agentIRI + "hasWayConnected"));
//					objAxiom = factory.getOWLObjectPropertyAssertionAxiom(objProp, intersectionInstance, forwardWayInstance);
//					addAxiom = new AddAxiom(ont, objAxiom);
//					manager.applyChange(addAxiom);
//				}
//			}
//		}					
//
//	    manager.saveOntology(ont, new PrintStream(new FileOutputStream(new File("/home/icd/Downloads/map/OSM/mountainview/testOSM_MV_Intersection.rdf"))));
//		manager.saveOntology(ont, new StreamDocumentTarget(System.out));
//		System.out.println(linkCount);
//		System.out.println(intersectionCount);
//		System.out.println(linkCount+intersectionCount);
//		System.out.println(count);
//	}
}
//
//
//
////int count = 0;
////Iterator<Entry<String, Intersection>> intersectionIterator = intersections.entrySet().iterator();
////while(intersectionIterator.hasNext()) {	
////		count++;
////		Entry<String, Intersection> entry = intersectionIterator.next();
////		Intersection intersection = entry.getValue();
////		String id = intersection.getIntersectionId();
////		System.out.println("Int ID : "+id);
////		ArrayList<WayNode> waysConnected = intersection.getWaysConnected();
////		for(int i=0; i<waysConnected.size(); i++) {
////			System.out.println("Way : "+waysConnected.get(i).getId());
////			ArrayList<String> nodesForCurrentWay = waysConnected.get(i).getNodesReferences();
////			String location = "";
////			for(int j=0; j<nodesForCurrentWay.size(); j++) {
////				Node currentNode = nodes.get(nodesForCurrentWay.get(j));
////				Point currentPoint = currentNode.getPoint();
////				System.out.println(currentNode.getId()+" = "+currentPoint.getLatitude()+", "+currentPoint.getLongitude()+", ");
////				location = location+currentPoint.getLatitude()+" "+currentPoint.getLongitude()+", ";
////			}
////			location = location.substring(0, location.length()-2);
////			System.out.println("location : "+location);
////		}
////}