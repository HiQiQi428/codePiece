package org.luncert;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;

public class App {

    public static void main(String[] args) {
        GraphDatabaseService graphDb = GraphDB.getInstance();
        Transaction tx = graphDb.beginTx();
        try {
            tx.success();
        } catch (Exception e) {
            tx.failure();
        } finally {
            tx.terminate();
        }
    }

}
