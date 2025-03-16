package client;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import config.RedisConfig;

public class RedisConnectionPool {
    private static final BlockingQueue<RedisClient> clients = new LinkedBlockingQueue<>();

    static {
        instantiateRedisConnection();
    }

    private static void instantiateRedisConnection() {
        RedisConfig config = RedisConfig.getRedisConfigFromFile();

        for (int i = 0; i < config.getConnectionPoolSize(); i++) {
            if(config.isAuth()){
                clients.offer(new RedisClient(config.getHost(), config.getPort(), config.getUsername(), config.getPassword()));
            }else{
                clients.offer(new RedisClient(config.getHost(), config.getPort()));
            }
        }
    }

    public static RedisClient getConnectionFromPool() {
        try {
            return clients.take();  // Blocking call ensures a connection is always returned
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();  // Restore interrupted state
            throw new RuntimeException("Failed to get connection from pool", e);
        }
    }

    public static void returnConnectionToPool(RedisClient client) {
        if (client != null) {
            clients.offer(client);
        }
    }
}