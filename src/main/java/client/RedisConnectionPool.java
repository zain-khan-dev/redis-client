package client;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class RedisConnectionPool {
    private static final BlockingQueue<RedisClient> clients = new LinkedBlockingQueue<>();
    private final static int CONNECTION_POOL_SIZE = 10;

    // Static block ensures connections are initialized once at class loading
    static {
        instantiateRedisConnection(CONNECTION_POOL_SIZE);
    }

    private static void instantiateRedisConnection(int size) {
        for (int i = 0; i < size; i++) {
            clients.offer(new RedisClient("127.0.0.1", 6379));
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