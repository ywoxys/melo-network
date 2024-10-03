package org.github.ywoxys.kernel.base.storage;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import lombok.Getter;

@Getter
public class MongoStorage {

	@Getter
	private MongoDatabase database;

	@Getter
	private MongoClient client;

	public void connect(String host, int port, String db) {
		client = new MongoClient(host, port);
		database = client.getDatabase(db);
	}

	public void close() {
		client.close();
	}

}