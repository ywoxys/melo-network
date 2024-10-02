package org.github.ywoxys.kernel.base.account.permission.group;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.github.ywoxys.kernel.base.account.permission.tag.Tag;

@AllArgsConstructor
@Getter
public enum Rank {

    ADMIN("Administrador", 00000, "admin", Tag.ADMIN, new String[] {"admin", "adm"}),
    MODERATOR("Moderador", 00000, "moderator", Tag.MODERATOR, new String[] {"mod", "moderator"}),
    VIP("VIP", 00000, "vip", Tag.VIP, new String[] {"vip", "vipuser"}),
    MEMBER("Membro", 00000, "member", Tag.MEMBER, new String[] {"member", "user"});

    private final String name;
    private final int discordId;
    private final String permission;
    private final Tag tag;
    private final String[] aliases;
}