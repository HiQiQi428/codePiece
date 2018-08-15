package org.luncert;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.index.IndexManager;
import org.neo4j.helpers.collection.MapUtil;
import org.neo4j.logging.Log;
import org.neo4j.procedure.Context;
import org.neo4j.procedure.Name;
import org.neo4j.procedure.PerformsWrites;
import org.neo4j.procedure.Procedure;

public class FullTextIndex {

    // Procedure 类中只能有静态字段和 @Context-annotated 标注的字段
    private static final Map<String, String> FULL_TEXT = MapUtil.stringMap(
        IndexManager.PROVIDER, "lucene",
        "type", "fulltext");
    
    // 声明当这个类里的 procedure 被调用时，我们需要一个 GraphDatabaseService 作为上下文
    @Context
    public GraphDatabaseService graphDb;

    // 获取一个 log 实例用于输出消息到标准 log，`neo4j.log`
    @Context
    public Log log;

    private String indexName(String label) {
        return "label-" + label;
    }

    /**
     * procedure：用作手动索引查询<br></br>
     * 返回特定过程的记录流 {@link SearchHit}<br></br>
     * 过程参数由 {@link Name} 定义参数的位置、名称和类型，参数类型必须是以下几种：
     * <ul>
     * <li>{@link String}
     * <li>{@link Long} or {@code long}
     * <li>{@link Double} or {@code double}
     * <li>{@link Number}
     * <li>{@link Boolean} or {@code boolean}
     * <li>{@link java.util.Map} with key {@link String} and value {@link Object}
     * <li>{@link java.util.List} of elements of any valid argument type, including {@link java.util.List}
     * <li>{@link Object}, meaning any of the valid argument types
     * </ul>
     * @param label the label name to query by
     * @param query the lucene query, for instance `name:Brook*` to search by property `name` and find any value starting with `Brook`. Please refer to the Lucene Query Parser documentation for full avaliable syntax.
     * @return the nodes found by the query
     */
    @Procedure("org.luncert.search")
    @PerformsWrites
    public Stream<SearchHit> search(@Name("label") String label, @Name("query") String query) {
        String index = indexName(label);
        if (!graphDb.index().existsForNodes(index)) {
            log.debug("由于索引 `%s` 不存在，跳过索引查询", index);
            return Stream.empty();
        }
        // 如果存在一个索引，执行查找并转换结果到我们的 output record
        return graphDb.index()
            .forNodes(index)
            .query(query)
            .stream()
            .map(SearchHit::new);
    }

    /**
     * procedure：用于按照需要查询的节点更新 index，你可以多次发送同一个节点，如果节点在索引已经存在了，索引会更新到该节点的当前状态<br></br>
     * 这个过程和 {@link #search(String, String)} 很像，但是有两个显著的差异：
     * <ul>
     * <li>如果你想在你的 procedure 中更新图数据库，你必须加上 {@link PerformsWrites} 注解
     * <li>返回 {@code void} 而不是一个 stream
     * </ul>
     * @param nodeId the id of the node to index
     * @param propKeys a list of property keys to index, only the ones the node actually contains willl be added
     */
    @Procedure("org.luncert.index")
    @PerformsWrites
    public void index(@Name("nodeId") long nodeId, @Name("properties") List<String> propKeys) {
        Node node = graphDb.getNodeById(nodeId);
        // 加载 node 的所有属性，结果集只会包含 propKeys 限制的属性
        Set<Map.Entry<String, Object>> properties = node.getProperties(propKeys.toArray(new String[0])).entrySet();
        for (Label label : node.getLabels()) {
            Index<Node> index = graphDb.index().forNodes(indexName(label.name()), FULL_TEXT);
            // 考虑到 node 可能以前创建过索引，先移除它保证我们不会获取到旧的或重复的数据
            index.remove(node);
            for (Map.Entry<String, Object> property : properties) {
                index.add(node, property.getKey(), property.getValue());
            }
        }
    }

    /**
     * 自定义的 search 过程输出记录。所有有返回结果的过程返回这个类作为记录流。这种类只能有 public non-final 的字段，并且字段只能是以下类型：
     * <ul>
     * <li>{@link String}
     * <li>{@link Long} or {@code long}
     * <li>{@link Double} or {@code double}
     * <li>{@link Number}
     * <li>{@link Boolean} or {@code boolean}
     * <li>{@link org.neo4j.graphdb.Node}
     * <li>{@link org.neo4j.graphdb.Relationship}
     * <li>{@link org.neo4j.graphdb.Path}
     * <li>{@link java.util.Map} with key {@link String} and value {@link Object}
     * <li>{@link java.util.List} of elements of any valid field type, including {@link java.util.List}
     * <li>{@link Object}, meaning any of the valid field types
     * </ul>
     */
    public static class SearchHit {

        public long nodeId;

        public SearchHit(Node node) {
            this.nodeId = node.getId();
        }
        
    }

}