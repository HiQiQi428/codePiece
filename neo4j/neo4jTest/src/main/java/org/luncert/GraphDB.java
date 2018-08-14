package org.luncert;

import java.io.File;
import java.nio.file.Paths;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.graphdb.factory.GraphDatabaseSettings;

public class GraphDB {

    private static GraphDatabaseService graphDb;

    private static void init() {
        File dbStoreDir = Paths.get(System.getProperty("user.dir"), "db").toFile();
        // load db with configuration
        // 针对一个 Neo4j 数据库，GraphDatabaseService 实例只能创建一个，该实例可以多进程共享，但统一时间只能有一个进程操作它
        graphDb = new GraphDatabaseFactory()
            .newEmbeddedDatabaseBuilder(dbStoreDir)
            .setConfig(GraphDatabaseSettings.pagecache_memory, "512M")
            .setConfig(GraphDatabaseSettings.string_block_size, "60")
            .setConfig(GraphDatabaseSettings.array_block_size, "300")
            // .setConfig(GraphDatabaseSettings.read_only, "true")
            .newGraphDatabase();
        // register shutdown hook
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                graphDb.shutdown();
            }
        });
    }
    
    public static GraphDatabaseService getInstance() {
        if (graphDb == null)
            init();
        return graphDb;
    }

}