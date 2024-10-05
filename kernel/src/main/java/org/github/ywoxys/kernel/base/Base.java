package org.github.ywoxys.kernel.base;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import lombok.Getter;
import lombok.Setter;
import org.github.ywoxys.kernel.base.storage.MongoStorage;
import org.github.ywoxys.kernel.base.storage.data.player.PlayerData;
import org.github.ywoxys.kernel.base.storage.data.server.ServerData;

import java.util.logging.Logger;

public class Base {

    @Getter
    private static final MongoStorage mongoStorage = new MongoStorage();

    @Getter
    private static final Gson gson = new Gson();

    @Getter
    private static final JsonParser jsonParser = new JsonParser();

    @Getter @Setter
    private static Logger logger;

    @Getter @Setter
    private static PlayerData playerData;
    
    @Getter @Setter
    private static ServerData serverData;

    @Getter @Setter
    private static Platform platform;
}
