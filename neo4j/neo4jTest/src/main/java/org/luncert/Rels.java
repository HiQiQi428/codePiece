package org.luncert;

import org.neo4j.graphdb.RelationshipType;

public enum Rels implements RelationshipType {
    ACTED_IN,
    DIRECTED,
    PRODUCED,
    LIKES,
    KNOWS,
}