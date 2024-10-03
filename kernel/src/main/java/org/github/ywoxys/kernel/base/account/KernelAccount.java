package org.github.ywoxys.kernel.base.account;

import lombok.Getter;
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
        Base.getDataPlayer().save(this);
    }

    public void update(String field) {
        Base.getDataPlayer().updateField(this, field);
    }

    //adicionar dep do spigot
    //public Player toPlayer() {
    //    return Core.getPlatform().getPlayerByUniqueId(uniqueId, Player.class);
    //}

    //adiconar dep do bungee
    //public ProxiedPlayer toProxiedPlayer() {
    //    return Core.getPlatform().getPlayerByUniqueId(uniqueId, ProxiedPlayer.class);
    //}
}
