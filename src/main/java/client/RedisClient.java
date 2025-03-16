package client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import commands.AuthCommands;
import commands.HashCommands;
import commands.ListCommands;
import commands.SetCommands;
import commands.StringCommands;
import redis_socket.RedisSocket;
import java.net.Socket;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RedisClient {


    Socket socket = null;
    static final Logger logger = LoggerFactory.getLogger(RedisClient.class);
    private final StringCommands stringCommands;
    private final ListCommands listCommands;
    private final SetCommands setCommands;
    private final HashCommands hashCommands;
    private final AuthCommands authCommands;


    public RedisClient(String host, int port){
        this.socket = RedisSocket.getSocketConnection(host, port);
        this.stringCommands = new StringCommands(socket);
        this.listCommands = new ListCommands(socket);
        this.setCommands = new SetCommands(socket);
        this.hashCommands = new HashCommands(socket);
        this.authCommands = new AuthCommands(socket);
        
    }

    public RedisClient(String host, int port, String username, String password){
        this(host, port);
        if(!this.authCommands.auth(username, password)){
            throw new RuntimeException("Authentication failed");
        }
    }


    public void closeConnection(){
        RedisConnectionPool.returnConnectionToPool(this);
    }


    public String get(String key){
        return this.stringCommands.get(key);
    }

    public String set(String key, String value){
        return this.stringCommands.set(key, value);
    }

    public String del(String key){
        return this.stringCommands.del(key);
    }

    public String mset(Map<String, String>mp){
        return this.stringCommands.mset(mp);
    }

    public String mset(List<String>args){
        return this.stringCommands.mset(args);
    }

    public long incr(String key){
        return this.stringCommands.incr(key);
    }

    // List Operations

    public int lpush(String key, List<String>values){
        return this.listCommands.lpush(key, values);
    }

    public int rpush(String key, List<String>values){
        return this.listCommands.rpush(key, values);
    }

    public String lpop(String key){
        return this.listCommands.lpop(key);
    }
    
    public List<String> lpop(String key, int count){
        return this.listCommands.lpop(key, count);
    }

    public List<String> rpop(String key, int count){
        return this.listCommands.rpop(key, count);
    }

    public int llen(String key){
        return this.listCommands.llen(key);
    }

    public String lmove(String sourceListKey, String destListKey, ListCommands.ListSourceMove sourceMove, ListCommands.ListSourceMove destMove){
        return this.listCommands.lmove(sourceListKey, destListKey, sourceMove, destMove);
    }

    public List<String> lrange(String key, int start, int end){
        return this.listCommands.lrange(key, start, end);
    }

    public String ltrim(String key, int start, int stop){
        return this.listCommands.ltrim(key, start, stop);
    }


    // Set Operations

    public int sadd(String key, List<String>members){
        return this.setCommands.sadd(key, members);
    }

    public int sadd(String key, Set<String>members){
        return this.setCommands.sadd(key, members);
    }

    public int srem(String key, List<String>members){
        return this.setCommands.srem(key, members);
    }

    public int srem(String key, Set<String>members){
        return this.setCommands.srem(key, members);
    }

    public boolean sismember(String key, String command){
        return this.setCommands.sismember(key, command);
    }

    public List<String> sinter(List<String>keys){
        return this.setCommands.sinter(keys);
    }

    public int scard(String key){
        return this.setCommands.scard(key);
    }

    // hash operations

    public int hset(String key, String field, String value){
        return this.hashCommands.hset(key, field, value);
    }

    public String hget(String key, String field){
        return this.hashCommands.hget(key, field);
    }

    public List<String> hmget(String key, List<String>fields){
        return this.hashCommands.hmget(key, fields);
    }


    public int hincrby(String key, String field, int increment){
        return this.hashCommands.hincrby(key, field, increment);
    }

}
