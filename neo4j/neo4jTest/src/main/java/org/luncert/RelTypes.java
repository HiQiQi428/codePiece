package org.luncert;

import org.neo4j.graphdb.RelationshipType;

public enum RelTypes implements RelationshipType {
    ACTED_IN,
    DIRECTED,
    PRODUCED,
}