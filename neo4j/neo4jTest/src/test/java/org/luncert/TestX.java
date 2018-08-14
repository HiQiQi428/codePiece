package org.luncert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;

/**
 * 测试图数据遍历功能<br></br>
 * Neo4j 遍历 API 采用的是一种基于回调的、惰性执行的机制<br></br>
 * 基本概念：
 * <ul>
 * <li>路径拓展：定义将要对图数据库中的什么进行遍历，一般是指针对关系的指向和关系的类型进行遍历
 * <li>顺序：深度优先或广度优先
 * <li>唯一性：在遍历过程中确保每个节点（关系、路径）只被遍历一次
 * <li>评估器：用来决定返回是什么结果，以及是否停止继续遍历当前位置
 * <li>开始节点：启动遍历最先开始的节点
 * </ul>
 */
@RunWith(JUnit4.class)
public class TestX {

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
    public void test() {

    }

}