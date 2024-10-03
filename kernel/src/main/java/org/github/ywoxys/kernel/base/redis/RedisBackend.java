package org.github.ywoxys.kernel.base.redis;

import lombok.Getter;
import org.github.ywoxys.kernel.base.Base;
import redis.clients.jedis.*;

@Getter
public class RedisBackend {

    private String hostname;
    private String password;
    private int port;
    private JedisPool pool;

    public void connect(String hostname, String password, int port) {
        this.hostname = hostname;
        this.password = password;
        this.port = port;

        JedisPoolConfig poolConfig = new JedisPoolConfig();
        if (password != null && !password.isEmpty()) {
            pool = new JedisPool(poolConfig, hostname, port, 0, password);
        } else {
            pool = new JedisPool(poolConfig, hostname, port, 0);
        }
    }

    // Publicar uma mensagem em um canal Redis
    public void publish(String channel, String message) {
        try (Jedis jedis = pool.getResource()) {
            jedis.publish(channel, message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Fechar o pool de conexões
    public void close() {
        if (pool != null && !pool.isClosed()) {
            pool.close();
        }
    }

    // Verificar se a conexão está ativa
    public boolean isConnected() {
        return pool != null && !pool.isClosed();
    }

    // Registrar um JedisPubSub em um ou mais canais
    public void registerPubSub(JedisPubSub pubSub, String... channels) {
        Base.getPlatform().runAsync(new PubSubTask(pubSub, channels));
    }

    // Classe responsável por gerenciar a assinatura de canais
    private class PubSubTask implements Runnable {

        private final JedisPubSub jedisPubSub;
        private final String[] channels;

        public PubSubTask(JedisPubSub pubSub, String... channels) {
            this.jedisPubSub = pubSub;
            this.channels = channels;
        }

        @Override
        public void run() {
            try (Jedis jedis = pool.getResource()) {
                jedis.subscribe(jedisPubSub, channels);
            } catch (Exception e) {
                e.printStackTrace();
                // Em caso de erro, desinscrever e tentar novamente
                try {
                    jedisPubSub.unsubscribe();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                run(); // Tentar reconectar
            }
        }

        // Adicionar novos canais à assinatura
        public void addChannel(String... newChannels) {
            jedisPubSub.subscribe(newChannels);
        }

        // Remover canais da assinatura
        public void removeChannel(String... channelsToRemove) {
            jedisPubSub.unsubscribe(channelsToRemove);
        }

        // Desinscrever de todos os canais
        public void unsubscribeAll() {
            jedisPubSub.unsubscribe();
        }
    }
}
