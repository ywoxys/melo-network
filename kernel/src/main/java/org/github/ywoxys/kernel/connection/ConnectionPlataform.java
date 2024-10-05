package org.github.ywoxys.kernel.connection;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.github.ywoxys.kernel.base.Platform;

import java.util.UUID;

public class ConnectionPlataform implements Platform {

    @Override
    public UUID getUUID(String playerName) {
        ProxiedPlayer player = getPlayerByName(playerName);
        return player != null ? player.getUniqueId() : null;
    }

    @Override
    public <T> T getPlayerByName(String playerName, Class<T> clazz) {
        ProxiedPlayer player = getPlayerByName(playerName);
        return player != null ? clazz.cast(player) : null;
    }

    @Override
    public <T> T getExactPlayerByName(String playerName, Class<T> clazz) {
        for (ProxiedPlayer player : ProxyServer.getInstance().getPlayers()) {
            if (player.getName().equals(playerName)) {
                return clazz.cast(player);
            }
        }
        return null;
    }

    @Override
    public <T> T getPlayerByUniqueId(UUID uniqueId, Class<T> clazz) {
        ProxiedPlayer player = ProxyServer.getInstance().getPlayer(uniqueId);
        return player != null ? clazz.cast(player) : null;
    }

    @Override
    public boolean isOnline(UUID uniqueId) {
        return getPlayerByUniqueId(uniqueId, ProxiedPlayer.class) != null;
    }

    @Override
    public boolean isOnline(String name) {
        return getPlayerByName(name, ProxiedPlayer.class) != null;
    }

    @Override
    public void sendMessage(UUID uniqueId, String message) {
        ProxiedPlayer player = getPlayerByUniqueId(uniqueId, ProxiedPlayer.class);
        if (player != null) {
            player.sendMessage(new TextComponent(message));
        }
    }

    @Override
    public void sendMessage(UUID uniqueId, BaseComponent message) {
        ProxiedPlayer player = getPlayerByUniqueId(uniqueId, ProxiedPlayer.class);
        if (player != null) {
            player.sendMessage(message);
        }
    }

    @Override
    public void sendMessage(UUID uniqueId, BaseComponent[] message) {
        ProxiedPlayer player = getPlayerByUniqueId(uniqueId, ProxiedPlayer.class);
        if (player != null) {
            player.sendMessage(message);
        }
    }

    @Override
    public void runAsync(Runnable runnable) {
        ProxyServer.getInstance().getScheduler().runAsync(Connection.getInstance(), runnable);
    }

    private ProxiedPlayer getPlayerByName(String playerName) {
        return ProxyServer.getInstance().getPlayer(playerName);
    }
}
