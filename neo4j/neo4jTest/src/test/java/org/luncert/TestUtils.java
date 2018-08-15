package org.luncert;

import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;

public class TestUtils {

    public static void printNode(Node node) {
        System.out.print(node.getId() + " - ");
        System.out.print(node.getLabels());
        System.out.println(node.getAllProperties());
    }

    public static void printRel(Relationship rel) {
        System.out.print(rel.getId() + " - ");
        System.out.print(rel.getType());
        System.out.println(rel.getAllProperties());
    }
    
}