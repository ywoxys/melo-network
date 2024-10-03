package org.github.ywoxys.kernel.base.account;

import lombok.Getter;
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

    public KernelAccount(UUID playerId, String playerName) {
        this.playerName = playerName;
        this.playerId = playerId;

        permission = new Permission(Rank.MEMBER, Tag.MEMBER, AssignedType.AUTO, "console", -1L);

    }
}
