package org.github.ywoxys.kernel.base.account.permission.tag;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Tag {

    ADMIN("Administrador", "§4", false),
    MODERATOR("Moderador", "§5", false),
    VIP("VIP", "§a", false),
    MEMBER("Membro", "§7", false);

    private final String name;
    private final String color;
    private final boolean specialTag;
}