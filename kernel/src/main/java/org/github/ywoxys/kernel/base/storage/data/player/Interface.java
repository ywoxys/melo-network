package org.github.ywoxys.kernel.base.storage.data.player;

import org.github.ywoxys.kernel.base.account.KernelAccount;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface Interface {

    KernelAccount create(UUID playerId, String playerName);

    void updateField(KernelAccount kernelAccount, String fieldName);

    void save(KernelAccount kernelAccount);

    boolean existsById(UUID playerId);

    boolean existsByName(String playerName);

    Optional<KernelAccount> findById(UUID playerId);

    Optional<KernelAccount> findByName(String playerName);

    Collection<KernelAccount> findAll();

    Collection<KernelAccount> getTopRanking(String fieldName);
}
