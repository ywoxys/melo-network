package org.github.ywoxys.kernel.base;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import lombok.Getter;
import org.github.ywoxys.kernel.base.storage.MongoStorage;

public class Base {

    @Getter
    private static final MongoStorage mongoStorage = new MongoStorage();

    @Getter
    private static final Gson gson = new Gson();

    @Getter
    private static final JsonParser jsonParser = new JsonParser();
}
