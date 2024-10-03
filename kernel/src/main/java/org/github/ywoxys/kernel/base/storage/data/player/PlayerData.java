package org.github.ywoxys.kernel.base.storage.data.player;

import com.google.gson.JsonObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.github.ywoxys.kernel.base.Base;
import org.github.ywoxys.kernel.base.account.KernelAccount;
import org.github.ywoxys.kernel.base.tools.json.JsonUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;

public class PlayerData implements Interface {

    private final MongoCollection<Document> collection;

    public PlayerData() {
        this.collection = Base.getMongoStorage().getDatabase().getCollection("players");
    }

    @Override
    public KernelAccount create(UUID playerId, String playerName) {
        KernelAccount kernelAccount = new KernelAccount(playerId, playerName);
        if (!existsById(playerId)) {
            collection.insertOne(toDocument(kernelAccount));
        }
        return kernelAccount;
    }

    @Override
    public void updateField(KernelAccount kernelAccount, String fieldName) {
        JsonObject playerJson = JsonUtil.toJsonTree(kernelAccount);
        if (playerJson.has(fieldName)) {
            Document update = new Document("$set", new Document(fieldName, JsonUtil.jsonElementToBson(playerJson.get(fieldName))));
            collection.updateOne(Filters.eq("playerId", kernelAccount.getPlayerId().toString()), update);
            // Notificando outros serviços da atualização do campo
            //Base.publishFieldUpdate(player, fieldName);
        }
    }

    @Override
    public void save(KernelAccount kernelAccount) {
        if (existsById(kernelAccount.getPlayerId())) {
            collection.replaceOne(Filters.eq("playerId", kernelAccount.getPlayerId().toString()), toDocument(kernelAccount));
        }
    }

    @Override
    public boolean existsById(UUID playerId) {
        return collection.find(Filters.eq("playerId", playerId.toString())).first() != null;
    }

    @Override
    public boolean existsByName(String playerName) {
        return collection.find(Filters.eq("playerName", playerName)).first() != null;
    }

    @Override
    public Optional<KernelAccount> findById(UUID playerId) {
        Document doc = collection.find(Filters.eq("playerId", playerId.toString())).first();
        return Optional.ofNullable(doc != null ? fromDocument(doc) : null);
    }

    @Override
    public Optional<KernelAccount> findByName(String playerName) {
        Document doc = collection.find(Filters.eq("playerName", playerName)).first();
        return Optional.ofNullable(doc != null ? fromDocument(doc) : null);
    }

    @Override
    public Collection<KernelAccount> findAll() {
        List<KernelAccount> players = new ArrayList<>();
        // Uso correto de forEach com Consumer
        collection.find().forEach((Consumer<? super Document>) document -> players.add(fromDocument(document)));
        return players;
    }

    @Override
    public Collection<KernelAccount> getTopRanking(String fieldName) {
        List<KernelAccount> players = new ArrayList<>();
        // Uso correto de forEach com Consumer
        collection.find().sort(new Document(fieldName, -1)).limit(10).forEach((Consumer<? super Document>) document -> players.add(fromDocument(document)));
        return players;
    }

    private Document toDocument(KernelAccount kernelAccount) {
        return Document.parse(Base.getGson().toJson(kernelAccount));
    }

    private KernelAccount fromDocument(Document document) {
        return Base.getGson().fromJson(document.toJson(), KernelAccount.class);
    }
}
