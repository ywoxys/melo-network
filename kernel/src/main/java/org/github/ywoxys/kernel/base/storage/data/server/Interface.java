package org.github.ywoxys.kernel.base.storage.data.server;

import org.github.ywoxys.kernel.base.server.ServerInfo;
import org.github.ywoxys.kernel.base.server.enums.ServerType;

import java.util.List;
import java.util.Optional;

public interface Interface {

    void create(ServerInfo serverInfo);

    void save(ServerInfo serverInfo);

    boolean exists(String name);

    Optional<ServerInfo> getServerInfo(String name);

    List<ServerInfo> getServers();

    List<ServerInfo> getServers(ServerType type);
}
