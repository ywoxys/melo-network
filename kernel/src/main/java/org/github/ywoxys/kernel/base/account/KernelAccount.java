package org.github.ywoxys.kernel.base.account;

import lombok.Getter;

import java.util.UUID;

public class KernelAccount {
    @Getter
    private String name;
    @Getter
    private UUID uuid;

    public KernelAccount(String name, UUID uuid) {
        this.name = name;
        this.uuid = uuid;
    }
}
