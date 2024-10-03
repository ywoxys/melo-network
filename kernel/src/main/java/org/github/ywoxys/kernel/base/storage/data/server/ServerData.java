package org.github.ywoxys.kernel.base.storage.data.server;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.google.gson.Gson;
import lombok.var;
import org.bson.Document;
import org.github.ywoxys.kernel.base.Base;
import org.github.ywoxys.kernel.base.server.ServerInfo;
import org.github.ywoxys.kernel.base.server.enums.ServerType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ServerData implements Interface {

    private final MongoCollection<Document> collection;
    private final Gson gson;

    public ServerData() {
        this.collection = Base.getMongoStorage().getClient().getDatabase("servers").getCollection("servers");
        this.gson = Base.getGson();
    }

    @Override
    public void create(ServerInfo serverInfo) {
        if (!exists(serverInfo.getName())) {
            collection.insertOne(toDocument(serverInfo));
        }
    }

    @Override
    public void save(ServerInfo serverInfo) {
        if (exists(serverInfo.getName())) {
            collection.updateOne(Filters.eq("name", serverInfo.getName()),
                    new Document("$set", toDocument(serverInfo)));
        }
    }

    @Override
    public boolean exists(String name) {
        return collection.find(Filters.eq("name", name)).first() != null;
    }

    @Override
    public Optional<ServerInfo> getServerInfo(String name) {
        Document found = collection.find(Filters.eq("name", name)).first();
        return Optional.ofNullable(found != null ? fromDocument(found) : null);
    }

    @Override
    public List<ServerInfo> getServers() {
        return getServersByFilter(new Document());
    }

    @Override
    public List<ServerInfo> getServers(ServerType type) {
        return getServersByFilter(Filters.eq("type", type.name()));
    }

    // Utilitário para converter um objeto ServerInfo em um documento BSON
    private Document toDocument(ServerInfo serverInfo) {
        return Document.parse(gson.toJson(serverInfo));
    }

    // Utilitário para converter um documento BSON em um objeto ServerInfo
    private ServerInfo fromDocument(Document document) {
        return gson.fromJson(document.toJson(), ServerInfo.class);
    }

    // Método privado reutilizável para buscar servidores com um filtro
    private List<ServerInfo> getServersByFilter(Object filter) {
        List<ServerInfo> servers = new ArrayList<>();

        // Usando MongoCursor para percorrer os documentos
        try (var cursor = collection.find((Document) filter).iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                servers.add(fromDocument(doc));
            }
        }

        return servers;
    }
}
