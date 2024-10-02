package org.github.ywoxys.kernel.base.account.permission.tag;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Tag {

    ADMIN("Administrador"),
    MODERATOR("Moderador"),
    VIP("VIP"),
    MEMBER("Membro");

    private final String name;
}