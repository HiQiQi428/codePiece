package org.luncert;

import static org.junit.Assert.assertThat;

import org.hamcrest.core.IsEqual;
import org.junit.Rule;
import org.junit.Test;
import org.neo4j.driver.v1.Config;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Values;
import org.neo4j.harness.junit.Neo4jRule;

public class ManualFullTextIndexTest {

    @Rule
    public Neo4jRule neo4j = new Neo4jRule().withProcedure(FullTextIndex.class);

    @Test
    public void shouldAllowIndexinAndFindindANode() {
        Config config = Config.build().withEncryptionLevel(Config.EncryptionLevel.NONE).toConfig();
        try (Driver driver = GraphDatabase.driver(neo4j.boltURI(), config)) {
            Session session = driver.session();
            long nodeId = session.run("CREATE (p:User { name: 'Brookreson' }) RETURN id(p)").single().get(0).asLong();
            session.run("CALL org.luncert.index({id}, ['name'])", Values.parameters("id", nodeId)); // 创建索引
            StatementResult result = session.run("CALL org.luncert.search('User', 'name:Brook*')"); // 索引查询
            assertThat(result.single().get("nodeId").asLong(), IsEqual.equalTo(nodeId));
        }
    }
}