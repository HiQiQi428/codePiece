package org.luncert;

import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Path;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.index.IndexHits;
import org.neo4j.graphdb.index.IndexManager;
import org.neo4j.graphdb.index.RelationshipIndex;
import org.neo4j.graphdb.traversal.Evaluators;
import org.neo4j.graphdb.traversal.Traverser;
import org.neo4j.graphdb.traversal.Uniqueness;
import org.neo4j.helpers.collection.MapUtil;

/**
 * 测试图数据遍历功能<br></br>
 * Neo4j 遍历 API 采用的是一种基于回调的、惰性执行的机制<br></br>
 * 基本概念：
 * <ul>
 * <li>路径拓展：定义将要对图数据库中的什么进行遍历，一般是指针对关系的指向和关系的类型进行遍历
 * <li>顺序：深度优先或广度优先
 * <li>唯一性：在遍历过程中确保每个节点（关系、路径）只被遍历一次
 * <li>评估器：用来决定返回是什么结果，以及是否停止继续遍历当前位置，用于在每个位置确定如果遍历继续，当前节点是否应包括在结果中
 * <li>开始节点：启动遍历最先开始的节点
 * </ul>
 * 289
 */
@RunWith(JUnit4.class)
public class TestHyper {

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
    public void createGraph() {
        try {
            // create nodes
            Node lisa = graphDb.createNode();
            lisa.setProperty("name", "lisa");
            Node ed = graphDb.createNode();
            ed.setProperty("name", "Ed");
            Node joe = graphDb.createNode();
            joe.setProperty("name", "Joe");
            Node lars = graphDb.createNode();
            lars.setProperty("name", "lars");
            Node dirk = graphDb.createNode();
            dirk.setProperty("name", "Dirk");
            Node peter = graphDb.createNode();
            peter.setProperty("name", "Peter");
            Node sara = graphDb.createNode();
            sara.setProperty("name", "Sara");
            // create rels
            lisa.createRelationshipTo(joe, Rels.LIKES);
            lisa.createRelationshipTo(lars, Rels.KNOWS);
            ed.createRelationshipTo(lars, Rels.KNOWS);
            joe.createRelationshipTo(sara, Rels.KNOWS);
            lars.createRelationshipTo(dirk, Rels.KNOWS);
            dirk.createRelationshipTo(peter, Rels.KNOWS);
            peter.createRelationshipTo(sara, Rels.KNOWS);
            tx.success();
        } catch (Exception e) {
            e.printStackTrace();
            tx.failure();
        }
    }

    @Test
    public void traversal() {
        // 使用遍历器遍历
        try {
            Node joe = graphDb.getNodeById(44);
            Traverser traverser = graphDb.traversalDescription()
                    .depthFirst()
                    .relationships(Rels.KNOWS)
                    .relationships(Rels.LIKES, Direction.INCOMING)
                    .uniqueness(Uniqueness.RELATIONSHIP_GLOBAL) // 设置关系唯一性
                    .evaluator(Evaluators.fromDepth(2)) // 通过评估器设置遍历深度
                    .evaluator(Evaluators.toDepth(4))
                    .traverse(joe);
            for (Path position : traverser) {
                System.out.println(position);
            }
            // 将遍历器转换为可迭代的节点
            for (Node node : traverser.nodes()) {
                TestUtils.printNode(node);
            }
            // traverser.relationships()
            tx.success();
        }
        catch (Exception e) {
            e.printStackTrace();
            tx.failure();
        }
    }

    // 使用数据索引，每个索引都绑定到某个唯一的属性名称上

    @Test
    public void createIndex() {
        try {
            IndexManager indexManager = graphDb.index();
            Index<Node> actorsIndex = indexManager.forNodes("actors");
            Index<Node> moviesIndex = indexManager.forNodes("movies");
            RelationshipIndex rolesIndex = indexManager.forRelationships("roles");
            // 演员节点
            Node reeves = graphDb.createNode();
            reeves.setProperty("name", "Keanu Reeves"); // 关联字符串值
            actorsIndex.add(reeves, "name", reeves.getProperty("name")); // 关联节点
            Node bellucci = graphDb.createNode();
            bellucci.setProperty("name", "Monica Bellucci");
            actorsIndex.add(bellucci, "name", bellucci.getProperty("name"));
            // 在本例中仅用作搜索，所以 name 索引被关联多个值，包括节点和字符串值
            actorsIndex.add(bellucci, "name", "La Bellucci");
            // 电影节点
            Node theMatrix = graphDb.createNode();
            theMatrix.setProperty("title", "The metrix");
            theMatrix.setProperty("year", 1999);
            moviesIndex.add(theMatrix, "title", theMatrix.getProperty("title"));
            moviesIndex.add(theMatrix, "year", theMatrix.getProperty("year"));
            Node theMatrixReloaded = graphDb.createNode();
            theMatrixReloaded.setProperty("title", "The Matrix Reloaded");
            theMatrixReloaded.setProperty("year", 2003);
            moviesIndex.add(theMatrixReloaded, "title", theMatrixReloaded.getProperty("title"));
            moviesIndex.add(theMatrixReloaded, "year", theMatrixReloaded.getProperty("year"));
            Node malena = graphDb.createNode();
            malena.setProperty("title", "Malena");
            malena.setProperty("year", 2000);
            moviesIndex.add(malena, "title", malena.getProperty("title"));
            moviesIndex.add(malena, "year", malena.getProperty("year"));
            // indexManager.existsForNodes("actors"); // 检查存在性
            // actorsIndex.remove(bellucci); // 从 bellucci 上完全移除演员索引（actors index）
            // actorsIndex.delete(); // 删除索引
            // 关系
            Relationship role1 = reeves.createRelationshipTo(theMatrix, Rels.ACTED_IN);
            role1.setProperty("role", "Neo");
            rolesIndex.add(role1, "role", role1.getProperty("role"));
            Relationship role2 = reeves.createRelationshipTo(theMatrixReloaded, Rels.ACTED_IN);
            role2.setProperty("role", "Neo");
            rolesIndex.add(role2, "role", role2.getProperty("role"));
            Relationship role3 = bellucci.createRelationshipTo(theMatrixReloaded, Rels.ACTED_IN);
            role3.setProperty("role", "Persephone");
            rolesIndex.add(role3, "role", role3.getProperty("role"));
            Relationship role4 = bellucci.createRelationshipTo(malena, Rels.ACTED_IN);
            role4.setProperty("role", "Malena Scordia");
            rolesIndex.add(role4, "role", role4.getProperty("role"));
            tx.success();
        } catch (Exception e) {
            e.printStackTrace();
            tx.failure();
        }
    }

    @Test
    public void updateIndex() {
        // 更新索引，先删除旧的索引条目，然后再添加新的索引条目
        try {
            IndexManager indexManager = graphDb.index();
            Index<Node> actorsIndex = indexManager.forNodes("actors");
            // 创建一个带有属性的节点，然后如更新这个属性
            Node fishburn = graphDb.createNode();
            fishburn.setProperty("name", "Fishburn");
            // 对 name 创建索引
            actorsIndex.add(fishburn, "name", fishburn.getProperty("name"));
            // 当属性值更改后，我们再重新更新这个索引
            actorsIndex.remove(fishburn, "name", fishburn.getProperty("name"));
            fishburn.setProperty("name", "Laurence Fishburn");
            actorsIndex.add(fishburn, "name", fishburn.getProperty("name"));
            tx.success();
        } catch (Exception e) {
            e.printStackTrace();
            tx.failure();
        }
    }

    @Test
    public void queryOnIndex() {
        try {
            // 1.节点索引下的查询
            IndexManager indexManager = graphDb.index();
            Index<Node> actorsIndex = indexManager.forNodes("actors");
            Index<Node> moviesIndex = indexManager.forNodes("movies");
            RelationshipIndex rolesIndex = indexManager.forRelationships("roles");
            // get 方法
            IndexHits<Node> hits = actorsIndex.get("name", "Keanu Reeves");
            Node reeves = hits.getSingle(); // IndexHits 是 Iterable 类的继承，而 getSingle 返回结果迭代器中的第一个（也只有一个），没有数据时返回 null
            TestUtils.printNode(reeves);
            Relationship persephone = rolesIndex.get("role", "Persephone").getSingle();
            TestUtils.printNode(persephone.getStartNode());
            TestUtils.printNode(persephone.getEndNode());
            // query 方法，可以直接使用索引查询更底层的功能，如模糊查询（正则表达式）
            for (Node actor : actorsIndex.query("name", "*e*")) {
                TestUtils.printNode(actor);
            }
            hits = moviesIndex.query("title: *Matrix* AND year:1999");
            for (Node movie : hits) {
                TestUtils.printNode(movie);
                System.out.println(hits.currentScore()); // 结果评分，评价结果的匹配相似度
            }

            // 2.关系索引下的查询
            // 查询一个以 reeves 作为开始节点的关系
            IndexHits<Relationship> reevesAsNeoHits = rolesIndex.get("role", "Neo", reeves, null);
            Relationship reevesAsNeo = reevesAsNeoHits.iterator().next();
            TestUtils.printRel(reevesAsNeo);
            reevesAsNeoHits.close();
            // 查询一个以 theMatrix 作为结束节点的关系
            hits = moviesIndex.get("title", "The Matrix");
            Node theMatrix = hits.getSingle();
            hits.close();
            IndexHits<Relationship> matrixNeoHits = rolesIndex.query("role", "*eo", null, theMatrix);
            Relationship matrixNeo = matrixNeoHits.iterator().next();
            matrixNeoHits.close();
            TestUtils.printRel(matrixNeo);
            tx.success();
        } catch (Exception e) {
            e.printStackTrace();
            tx.failure();
        }
    }

    @Test
    public void queryOnIndexWithType() {
        try {
            IndexManager indexManager = graphDb.index();
            Index<Node> actorsIndex = indexManager.forNodes("actors");
            RelationshipIndex rolesIndex = indexManager.forRelationships("roles");
            // 在关系的 type 上添加到索引
            IndexHits<Node> reevesHits = actorsIndex.get("name", "Keanu Reeves");
            Node reeves = reevesHits.getSingle();
            IndexHits<Relationship> reevesAsNeoHits = rolesIndex.get("role", "Neo", reeves, null);
            Relationship reevesAsNeo = reevesAsNeoHits.iterator().next();
            reevesAsNeoHits.close();
            TestUtils.printRel(reevesAsNeo);
            rolesIndex.add(reevesAsNeo, "type", reevesAsNeo.getType().name());
            // 索引查询
            IndexHits<Relationship> typeHits = rolesIndex.query("type:ACTED_IN AND role:Neo");
            Relationship typeNeo = typeHits.iterator().next();
            typeHits.close();
            TestUtils.printRel(typeNeo);

            tx.success();
        } catch (Exception e) {
            e.printStackTrace();
            tx.failure();
        }
    }

    @Test
    public void fulltextIndex() {
        // 不区分大小写的全文索引
        try {
            Map<String, String> config = MapUtil.stringMap(
                IndexManager.PROVIDER, "lucene",
                "type", "fulltext",
                "to_lower_case", "true");
            IndexManager indexManager = graphDb.index();
            Index<Node> fulltextMovies = indexManager.forNodes("movies-fulltext", config);
            Node theMatrix = graphDb.getNodeById(51);
            Node theMatrixReloaded = graphDb.getNodeById(52);
            fulltextMovies.add(theMatrix, "title", "The Matrix");
            fulltextMovies.add(theMatrixReloaded, "title", "The Matrix Reloaded");
            // 在全文索引中搜索
            IndexHits<Node> hits = fulltextMovies.query("title", "reloAdEd");
            Node found = hits.iterator().next();
            System.out.println(hits.currentScore());
            hits.close();
            TestUtils.printNode(found);
            tx.success();
        } catch (Exception e) {
            e.printStackTrace();
            tx.failure();
        }
    }

    public void numericIndex() {
        // // 创建数值索引，并按照年份范围进行查询
        // movies.add(theMatrix, "year-numeric", new ValueContext(1999).indexNumeric());
        // movies.add(theMatrixReloaded, "year-numeric", new ValueContext(2003).indexNumeric());
        // movies.add(malena, "year-numeric", new ValueContext(2000).indexNumeric());
        // int from = 1997;
        // int to = 1999;
        // hits = movies.query(QueryContext.numericRange("year-numeric", from, to));
    }

    public void sort() {
        // hits = movies.query("title", new QueryContext("*").sort("title"));
        // for (Node hit : hits) {
        //     // 按照 title 排序
        // }
        // hits = movies.query(new QueryContext("title:*").sort("year", "title"));
        // for (Node movies : hits) {
        //     // 首先按照 year 排序然后再按照 title 排序
        // }
        // hits = movies.query("title", new QueryContext("The*").sortByScore());
        // for (Node movie : hits) {
        //     // 按照相似度排序
        // }
    }

    public void queryObject() {
        // TermQuery 基本上与在索引上使用 get 方法一样
        // Node actor = actors.query(new TermQuery(new Term("name", "Keanu Reeves"))).getSingle();

        // 使用通配符
        // hits = movies.query(new WildCardQuery(new Term("title", "The Matrix*")));
        // for (Node movie : hits) {
        //     TestUtils.printNode(movie);
        // }

        // 复合查询，无法对已经创建索引条目和尚未创建索引条目的属性同时进行搜索
        // hits = movie.query("title:*Matrix* AND year:1999");

        // 操作符
        // QueryContext query = new QueryContext("title:*Matrix* year:1999").defaultOperator(Operator.AND);
        // hits = movies.query(query);
    }

}