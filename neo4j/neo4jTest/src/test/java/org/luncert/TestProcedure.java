package org.luncert;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;

/**
 * Procedure 是 Java 编写然后部署到数据库中的扩展插件，可以在 Cypher 中调用。
 * 用 Java 编写并编译成 JAR 文件，放入每个独立或集群服务器上的 $NEO4J_HOME/plugins 目录中完成部署。
 * 需要重新启动数据库以使新的过程生效。<br></br>
 * 功能：
 * <ul>
 * <li>提供对 Cypher 中不可用的功能的访问，如手动索引
 * <li>提供对第三方系统的访问
 * <li>执行全局操作，如对连接的组件计数或查找密集节点
 * <li>实现难以用 Cypher 明确表达的操作
 * </ul>
 */
@RunWith(JUnit4.class)
public class TestProcedure {

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


}