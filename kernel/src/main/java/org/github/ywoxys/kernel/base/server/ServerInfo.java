package org.github.ywoxys.kernel.base.server;

import lombok.Getter;
import lombok.Setter;
import org.github.ywoxys.kernel.base.Base;
import org.github.ywoxys.kernel.base.server.enums.ServerStatus;
import org.github.ywoxys.kernel.base.server.enums.ServerType;

@Getter @Setter
public class ServerInfo {

    private String displayName;
    private String name;

    private ServerType type;
    private ServerStatus status;

    private String address;
    private Integer port;

    private Integer players;
    private Integer maxPlayers;

    // Construtor padrão
    public ServerInfo(String displayName, String name, ServerType type, String address, Integer port, Integer players, Integer maxPlayers) {
        this.displayName = displayName;
        this.name = name;
        this.type = type;
        this.address = address;
        this.port = port;
        this.players = players;
        this.maxPlayers = maxPlayers;
    }

    // Cria ou salva o servidor
    public void create() {
        if (!Base.getServerData().exists(name)) {
            Base.getServerData().create(this);
        } else {
            save();
        }
    }

    // Salva o estado do servidor
    public void save() {
        Base.getServerData().save(this);
    }
}
