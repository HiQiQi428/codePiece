package org.luncert;

import java.util.function.Consumer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.ResourceIterable;
import org.neo4j.graphdb.ResourceIterator;
import org.neo4j.graphdb.Result;
import org.neo4j.graphdb.Transaction;

/**
 * 测试基本操作
 */
@RunWith(JUnit4.class)
public class TestBaseOp {

    private GraphDatabaseService graphDb;

    private Transaction tx;

    @Before
    public void before() {
        graphDb = GraphDB.getInstance();
        tx = graphDb.beginTx();
    }

    @After
    public void after() {
        tx.close();
    }

    @Test
    public void getNodesNum() {
        try {
            Result result = graphDb.execute("MATCH (n) RETURN count(n) as num");
            ResourceIterator<Object> iterator = result.columnAs("num");
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
            tx.success();
        } catch (Exception e) {
            e.printStackTrace();
            tx.failure();
        }
    }

    @Test
    public void traversalNodes() {
        try {
            ResourceIterable<Node> iterable = graphDb.getAllNodes();
            iterable.forEach(new Consumer<Node>() {
                @Override
                public void accept(Node node) {
                    System.out.print(node.getId() + " - ");
                    System.out.print(node.getLabels());
                    System.out.println(node.getAllProperties());
                }
            });
            tx.success();
        } catch (Exception e) {
            e.printStackTrace();
            tx.failure();
        }
    }

    @Test
    public void createNode() {
        // 创建节点并添加属性
        try {
            Node theMatrix = graphDb.createNode();
            theMatrix.setProperty("title", "The Matrix");
            theMatrix.setProperty("released", 1999);
            theMatrix.setProperty("tagline", "Welcome to the Real World");

            Node keanu = graphDb.createNode();
            keanu.setProperty("name", "Keanu Reeves");
            keanu.setProperty("born", 1960);
            tx.success();
        } catch (Exception e) {
            e.printStackTrace();
            tx.failure();
        }
    }

    @Test
    public void addNodeLabel() {
        // 为节点添加标签
        try {
            Node theMatrix = graphDb.getNodeById(40);
            theMatrix.addLabel(Labels.Movie);
            theMatrix.removeLabel(Labels.Person);
            graphDb.getNodeById(41).addLabel(Labels.Person);
            tx.success();
        } catch (Exception e) {
            e.printStackTrace();
            tx.failure();
        }
    }

    @Test
    public void queryByLabel() {
        try {
            ResourceIterator<Node> movies = graphDb.findNodes(Labels.Movie, "title", "The Matrix");
            while (movies.hasNext()) {
                System.out.println(movies.next());
            }
            tx.success();
        } catch (Exception e) {
            e.printStackTrace();
            tx.failure();
        }
    }

    @Test
    public void createRel() {
        // 创建关系及其属性与类型
        try {
            Node keanu = graphDb.getNodeById(41);
            Node theMatrix = graphDb.getNodeById(40);
            Relationship rel = keanu.createRelationshipTo(theMatrix, RelTypes.ACTED_IN);
            rel.setProperty("roles", "Neo");
            System.out.println(rel);
            tx.success();
        } catch (Exception e) {
            e.printStackTrace();
            tx.failure();
        }
    }

    @Test
    public void deleteData() {
        // 删除节点和关系
        try {
            Node keanu = graphDb.getNodeById(41);
            keanu.getSingleRelationship(RelTypes.ACTED_IN, Direction.OUTGOING).delete();
            keanu.delete();
            Node theMatrix = graphDb.getNodeById(40);
            theMatrix.delete();            
            tx.success();
        } catch (Exception e) {
            e.printStackTrace();
            tx.failure();
        }
    }

}