package client;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class RedisConnectionPool {

    private static BlockingQueue<RedisClient> clients;


    private final static int CONNECTION_POOL_SIZE = 10;


    private static class ConnectionPoolHolder{
        private static final RedisConnectionPool INSTANCE = new RedisConnectionPool(CONNECTION_POOL_SIZE);
    }


    private static void instantiateRedisConnection(int size){
        clients = new LinkedBlockingQueue<>();
        for(int i = 0; i < size; i++){
            RedisClient client = new RedisClient("127.0.0.1", 6379);
            clients.offer(client);
        }
    }

    private RedisConnectionPool(int size){
        instantiateRedisConnection(size);
    }

    public static RedisConnectionPool getInstance(){
        return ConnectionPoolHolder.INSTANCE;
    }
    

    public static RedisClient getConnectionFromPool(){    
        try{
            return clients.take();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        return null;
    }

    public static void returnConnectionToConnectionPool(RedisClient client){
        clients.offer(client);
    }
}
