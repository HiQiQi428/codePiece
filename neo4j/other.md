# 使用 Java 在线备份 Neo4j
用 Java 备份完整或后续增量的数据
<pre><code>OnlineBackup backup = OnlineBackup.from("127.0.0.1");
backup.full(backupPath.getPath());
assertTrue("Should be consistent", backup.isConsistent());
backup.incremental(backupPath.getPath());</code></pre>
# 使用 JMX 监控 Neo4j
## JMX 接入 Neo4j
Neo4j Enterprise Server 默认不允许远程 JMX 连接，需要先取消 conf/neo4j.conf 文件中 com.sun.management.jmxremote 选项取消注释。确保 conf/jmx.password 具有正确的文件权限，其所有者必须是将运行服务的用户，并且权限为只读（0600）
* 启动 Neo4j：$NEO4j_HOME/bin/neo4j start
* 启动 jconsole：$JAVA_HOME/bin/jconsole
# 使用 Neo4j Driver
驱动程序对象实例可以在整个应用程序中跨多个线程进行全局共享，它包含一个到集群的各个成员的连接池，关闭驱动程序将关闭该驱动生成的所有连接，即使正在使用
<pre><code>Driver driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "neo4j"));
try (Session session = driver.session()) {
    try (Transaction tx = session.beginTransaction()) {
        tx.run("CREATE (a:Person {name: {name}, title: {title}})", Values.parameters("name", "Arthur", "title", "King"));
        tx.success();
    }
    try (Transaction tx = session.beginTransaction()) {
        StatementResult result = tx.run("MATCH (a:Person) WHERE a.name = {name} RETURN a.name AS name, a.title AS title", Values.parameters("name", "Arthur"));
        whlie (result.hasNext()) {
            Record record = result.next();
            System.out.println(String.format("%s %s", record.get("title").asString(), record.get("name").asString()));
        }
    }
}
driver.close();</code></pre>
<pre><code>driver = GraphDatabase.driver("bolt://localhost", auth=basic_auth("neo4j", "neo4j"))
session = driver.session()
session.run("CREATE (a:Person {name: 'Arthur', title: 'King'})")
result = session.run("MATCH (a:Person) WHERE a.name = 'Arthur' RETURN a.name AS name, a.title AS title")
for record in result:
    print("%s %s" % (record['title'], record['name']))
session.close()</code></pre>
"直接 URI"和"路由 URI"
<pre><code>public Driver acquireDriver(List<String> uris, AuthToken authToken, Config config) {
    for (String uri : uris) {
        try {
            return GraphDatabase.driver(uri, authToken, config);
        } catch (ServiceUnavailableException ex) {
            // This URI failed, so loop around again if we have another.
        }
    }
    throw new ServiceUnavailableException("No valid database URI found");
}</code></pre>
加密
<pre><code>Driver driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "neo4j"), Config.build().withEncryptionLevel(Config.EncryptionLevel.REQUIRED).toConfig());
// 最新版 withEncryptLevel 被废弃了</code></pre>
可信连接
<pre><code>Driver driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "neo4j"), Config.build()
    .withEncryptionLevel(Config.EncryptionLevel.REQUIRED)
    .withTrustStrategy(Config.TrustStrategy.trustOnFirstUse(new File("/path/to/neo4j_known_hosts")))
    .toConfig());</code></pre>
<pre><code>Driver driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "neo4j"),
    Config.build().withEncryptionLevel(Config.EncryptionLevel.REQUIRED)
    .withTrustStrategy(Config.TrustStrategy.trustCustomCertificateSignedBy(new File("/path/to/ca-certificate.pem")))
    .toConfig());</code></pre>
非线程安全的 Session 会话
书签：书签确保之后的操作对依赖于先前的操作，无论数据是否已复制到集群的每个实例，书签都保证下一个操作会被应用于先前设置了标签的实例。获取书签 session.lastBookmark()，使用书签 session.beginTransaction(bookmark)
结果摘要
<pre><code>try (Transaction tx = session.beginTransaction()) {
    StatementResult result = tx.run("PROFILE MATCH (p:Person {name: {name}}) RETURN id(p)", Values.parameters("name", "Arthur"));
    ResultSummary summary = result.consume();
    System.out.println(summary.statementType());
    System.out.println(summary.profile());
}</code></pre>
HTTP API P342
Spring-Data-Neo4j P356
JDBC -> Neo4j
<pre><code>Connection conn = DriverManager.getConnection("jdbc:neo4j:bolt://localhost");
trt (Statement stmt = conn.creatStatement()) {
    ResultSet rs = stmt.executeQuery("MATCH (n:User) RETURN n.name");
    while (rs.next()) {
        System.out.println(rs.getString("n.name"));
    }
}
conn.close();</code></pre>
JCypher
P361