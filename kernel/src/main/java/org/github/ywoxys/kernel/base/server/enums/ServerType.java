package org.github.ywoxys.kernel.base.server.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ServerType {

    PROXY(0, "Proxy"),

    LOGIN(1, "Login"),

    LOBBY(2, "Lobby"),
    LOBBY_PVP(3, "Lobby PVP"),
    LOBBY_HG(4, "Lobby Hunger Games"),
    LOBBY_DUELS(5, "Lobby Duels"),

    ROOM_ARENA(6, "Sala Arena"),
    ROOM_FPS(7, "Sala FPS"),
    ROOM_LAVA(8, "Sala Lava"),
    ROOM_DAMAGE(9, "Sala Damage"),
    ROOM_HG(10, "Sala HG"),
    ROOM_DUELS(11, "Sala Duels");

    Integer id;
    String name;

    public static ServerType valueOf(Integer id) {
        for (ServerType serverType : values()) {
            if (serverType.getId().equals(id)) {
                return serverType;
            }
        }
        return null;
    }

    public static ServerType valueOfName(String name) {
        for (ServerType serverType : values()) {
            if (serverType.getName().equalsIgnoreCase(name)) {
                return serverType;
            }
        }
        return null;
    }
}
