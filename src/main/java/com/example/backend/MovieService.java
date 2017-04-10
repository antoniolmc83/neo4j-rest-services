package com.example.backend;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.neo4j.helpers.collection.Iterators;
import org.neo4j.helpers.collection.MapUtil;

import com.example.executor.BoltCypherExecutor;
import com.example.executor.CypherExecutor;



public class MovieService {
    private final CypherExecutor cypher;

    public MovieService(String uri) {
        cypher = createCypherExecutor(uri);
    }

    private CypherExecutor createCypherExecutor(String uri) {
        try {
            String auth = new URL(uri.replace("bolt","http")).getUserInfo();
            if (auth != null) {
                String[] parts = auth.split(":");
                return new BoltCypherExecutor(uri,parts[0],parts[1]);
            }
            return new BoltCypherExecutor(uri);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Invalid Neo4j-ServerURL " + uri);
        }
    }

    public Map findMovie(String title) {
        if (title==null) return Collections.emptyMap();
        return Iterators.singleOrNull(cypher.query(
                "MATCH (movie:Movie {title:{title}})" +
                " OPTIONAL MATCH (movie)<-[r]-(person:Person)\n" +
                " RETURN movie.title as title, collect({name:person.name, job:head(split(lower(type(r)),'_')), role:r.roles}) as cast LIMIT 1",
                MapUtil.map("title", title)));
    }

    @SuppressWarnings("unchecked")
    public Iterable<Map<String,Object>> search(String query) {
        if (query==null || query.trim().isEmpty()) return Collections.emptyList();
        return Iterators.asCollection(cypher.query(
                "MATCH (movie:Movie)\n" +
                " WHERE lower(movie.title) CONTAINS {part}\n" +
                " RETURN movie",
                MapUtil.map("part", query.toLowerCase())));
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> graph(int limit) {
        Iterator<Map<String,Object>> result = cypher.query(
                "MATCH (m:Movie)<-[:ACTED_IN]-(a:Person) " +
                " RETURN m.title as movie, collect(a.name) as cast " +
                " LIMIT {limit}", MapUtil.map("limit",limit));
        List nodes = new ArrayList();
        List rels= new ArrayList();
        int i=0;
        while (result.hasNext()) {
            Map<String, Object> row = result.next();
            nodes.add(MapUtil.map("title",row.get("movie"),"label","movie"));
            int target=i;
            i++;
            for (Object name : (Collection) row.get("cast")) {
                Map<String, Object> actor = MapUtil.map("title", name,"label","actor");
                int source = nodes.indexOf(actor);
                if (source == -1) {
                    nodes.add(actor);
                    source = i++;
                }
                rels.add(MapUtil.map("source",source,"target",target));
            }
        }
        return MapUtil.map("nodes", nodes, "links", rels);
     }
}
