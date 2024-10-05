package org.github.ywoxys.kernel.base.server;

import org.github.ywoxys.kernel.base.Base;
import org.github.ywoxys.kernel.base.config.Config;
import org.github.ywoxys.kernel.base.server.enums.ServerStatus;
import org.github.ywoxys.kernel.base.server.enums.ServerType;

import java.util.Map;

public class ServerInfoManager {

    /**
     * Cria ou recupera informações do servidor "PROXY".
     */

    public ServerInfo createProxyServerInfo(Integer players) throws Exception {
        ServerInfo serverInfo;

        Map<String, Object> proxyConfig = (Map<String, Object>) Config.getInstance().get("servers.proxy");

        String displayName = (String) proxyConfig.get("display");
        String name = (String) proxyConfig.get("name");
        String address = (String) proxyConfig.get("address");
        Integer port = (Integer) proxyConfig.get("port");
        Integer maxPlayers = (Integer) proxyConfig.get("maxPlayers");

        if (!Base.getServerData().exists(name.toUpperCase())) {
            serverInfo = new ServerInfo(displayName, name, ServerType.PROXY, address, port, players, maxPlayers);
            serverInfo.setStatus(ServerStatus.ONLINE);
        } else {
            serverInfo = Base.getServerData().getServerInfo(name.toUpperCase()).get();
        }

        serverInfo.create();
        return serverInfo;
    }
}
