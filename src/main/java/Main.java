

import client.RedisClient;
import client.RedisConnectionPool;

public class Main {

    public static void main(String []args){
        RedisClient redisClient = RedisConnectionPool.getConnectionFromPool();
        redisClient.set("HELLO", "WORLD");
        System.out.println(redisClient.get("HELLO"));
        redisClient.closeConnection();
    }
}
