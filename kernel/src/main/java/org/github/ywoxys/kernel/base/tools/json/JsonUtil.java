package org.github.ywoxys.kernel.base.tools.json;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mongodb.BasicDBObject;
import org.bson.Document;
import org.bson.conversions.Bson;

public class JsonUtil {

	private static final Gson gson = new Gson();

	// Converte um objeto Java para JsonObject
	public static JsonObject toJsonTree(Object object) {
		return gson.toJsonTree(object).getAsJsonObject();
	}

	// Converte um JsonElement para um tipo compatível com BSON (MongoDB)
	public static Bson jsonElementToBson(JsonElement element) {
		// Converte JsonElement para uma string JSON e em seguida para um BasicDBObject
		BasicDBObject dbObject = BasicDBObject.parse(element.toString());
		return new Document(dbObject);
	}

	// Converte um objeto Java para um Document do MongoDB
	public static Document toDocument(Object object) {
		return Document.parse(gson.toJson(object));
	}

	// Converte um Document do MongoDB para um objeto Java
	public static <T> T fromDocument(Document document, Class<T> clazz) {
		return gson.fromJson(document.toJson(), clazz);
	}
}
