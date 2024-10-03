package org.github.ywoxys.kernel.base;

import java.util.UUID;

public interface Platform {

    /**
     * Obtém o UUID de um jogador a partir do nome.
     *
     * @param playerName Nome do jogador.
     * @return UUID do jogador ou null se não for encontrado.
     */
    UUID getUUID(String playerName);

    /**
     * Retorna uma instância genérica de um jogador baseado no nome,
     * sem verificar se o nome é exato (pode ser parcial).
     *
     * @param playerName Nome do jogador.
     * @param clazz Classe do tipo genérico T para retorno.
     * @param <T> Tipo genérico do jogador.
     * @return Instância do jogador correspondente ao nome.
     */
    <T> T getPlayerByName(String playerName, Class<T> clazz);

    /**
     * Retorna uma instância genérica de um jogador baseado no nome,
     * verificando se o nome é exatamente o informado.
     *
     * @param playerName Nome exato do jogador.
     * @param clazz Classe do tipo genérico T para retorno.
     * @param <T> Tipo genérico do jogador.
     * @return Instância do jogador correspondente ao nome exato.
     */
    <T> T getExactPlayerByName(String playerName, Class<T> clazz);

    /**
     * Retorna uma instância genérica de um jogador com base no UUID.
     *
     * @param uniqueId UUID do jogador.
     * @param clazz Classe do tipo genérico T para retorno.
     * @param <T> Tipo genérico do jogador.
     * @return Instância do jogador correspondente ao UUID.
     */
    <T> T getPlayerByUniqueId(UUID uniqueId, Class<T> clazz);

    /**
     * Verifica se um jogador está online através do UUID.
     *
     * @param uniqueId UUID do jogador.
     * @return true se o jogador estiver online, false caso contrário.
     */
    boolean isOnline(UUID uniqueId);

    /**
     * Verifica se um jogador está online através do nome.
     *
     * @param name Nome do jogador.
     * @return true se o jogador estiver online, false caso contrário.
     */
    boolean isOnline(String name);

    /**
     * Envia uma mensagem em formato String para o jogador identificado por UUID.
     *
     * @param uniqueId UUID do jogador.
     * @param message Mensagem a ser enviada.
     */
    void sendMessage(UUID uniqueId, String message);

    /**
     * Envia uma mensagem composta em formato String[] para o jogador identificado por UUID.
     *
     * @param uniqueId UUID do jogador.
     * @param messages Array de mensagens a serem enviadas.
     */
    void sendMessage(UUID uniqueId, String[] messages);

    /**
     * Executa uma tarefa de forma assíncrona.
     *
     * @param runnable Tarefa a ser executada.
     */
    void runAsync(Runnable runnable);
}
