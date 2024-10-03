package org.github.ywoxys.kernel.base.account;

import lombok.Getter;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.bukkit.entity.Player;
import org.github.ywoxys.kernel.base.Base;
import org.github.ywoxys.kernel.base.account.enums.AccountType;
import org.github.ywoxys.kernel.base.account.permission.Permission;
import org.github.ywoxys.kernel.base.account.permission.enums.AssignedType;
import org.github.ywoxys.kernel.base.account.permission.group.Rank;
import org.github.ywoxys.kernel.base.account.permission.tag.Tag;

import java.util.UUID;

public class KernelAccount {
    @Getter
    private UUID playerId;
    @Getter
    private String playerName;

    private Permission permission;

    private AccountType accountType = AccountType.NONE;

    public KernelAccount(UUID playerId, String playerName) {
        this.playerName = playerName;
        this.playerId = playerId;

        permission = new Permission(Rank.MEMBER, Tag.MEMBER, AssignedType.AUTO, "console", -1L);

    }

    public void save() {
        Base.getPlayerData().save(this);
    }

    public void update(String field) {
        Base.getPlayerData().updateField(this, field);
    }

    public Player toPlayer() {
        return Base.getPlatform().getPlayerByUniqueId(playerId, Player.class);
    }

    public ProxiedPlayer toProxiedPlayer() {
        return Base.getPlatform().getPlayerByUniqueId(playerId, ProxiedPlayer.class);
    }

    public boolean hasGroupPermission(Rank rank) {
        return permission.getRank().ordinal() <= rank.ordinal();
    }
}
