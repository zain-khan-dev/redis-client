package client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import commands.SimpleString;
import writer.RedisDataWriter;
import reader.RedisDataReader;
import redis_socket.RedisSocket;
import serde.RedisSerializer;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RedisClient {


    enum ListSourceMove {
        LEFT, 
        RIGHT
    }


    Socket socket = null;
    static final Logger logger = LoggerFactory.getLogger(RedisClient.class);
    private final RedisDataWriter writer;
    private final RedisDataReader reader;

    public RedisClient(String host, int port){
        this.socket = RedisSocket.getSocketConnection(host, port);
        this.writer = new RedisDataWriter(socket);
        this.reader = new RedisDataReader(socket);
    }

    private void executeCommand(String command, List<String>args){
        String serializedCommand = RedisSerializer.serialize(command, args);
        this.writer.sendData(serializedCommand);
    }


    public String get(String key){
        this.executeCommand("GET", List.of(key));
        return reader.readBulkString();
    }

    public String set(String key, String value){
        this.executeCommand("SET", List.of(key, value));
        return this.reader.readSimpleString();
    }

    public String del(String key){
        this.executeCommand("DEL", List.of(key));
        return this.reader.readSimpleString();
    }

    public String mset(Map<String, String>mp){
        List<String>args = new ArrayList<>();
        mp.forEach((key, value) -> {args.add(key); args.add(value);});
        this.executeCommand("MSET", args);
        return new SimpleString(this.reader, this.writer).mset(mp);
    }

    public String mset(List<String>args){
        this.executeCommand("MSET", args);
        return this.reader.readSimpleString();
    }

    public long incr(String key){
        this.executeCommand("INCR", null);
        return this.reader.readInteger();
    }

    // List Operations

    public int lpush(String key, List<String>values){
        List<String>args = new ArrayList<>();
        args.add(key);
        args.addAll(values);
        this.executeCommand("LPUSH", args);
        return this.reader.readInteger();
    }

    public int rpush(String key, List<String>values){
        this.executeCommand("RPUSH", values);
        return this.reader.readInteger();
    }

    public String lpop(String key){
        this.executeCommand("LPOP", List.of(key));
        return reader.readSimpleString();
    }
    
    public List<String> lpop(String key, int count){
        this.executeCommand("LPOP", List.of(key, String.valueOf(count)));
        return reader.readList();
    }

    public List<String> rpop(String key, int count){
        this.executeCommand("RPOP", List.of(key, String.valueOf(count)));
        return reader.readList();
    }

    public int llen(String key){
        this.executeCommand("LLEN", List.of(key));
        return reader.readInteger();
    }

    public String lmove(String sourceListKey, String destListKey, ListSourceMove sourceMove, ListSourceMove destMove){
        this.executeCommand("LMOVE", List.of(sourceListKey, destListKey, sourceMove.toString(), destMove.toString()));
        return reader.readSimpleString();
    }

    public List<String> lrange(String key, int start, int end){
        this.executeCommand("LRANGE", List.of(key, String.valueOf(start), String.valueOf(end)));
        return this.reader.readList();
    }

    public String ltrim(String key, int start, int stop){
        this.executeCommand("LTRIM", List.of(key, String.valueOf(start), String.valueOf(stop)));
        return this.reader.readSimpleString();
    }


    // Set Operations

    public int sadd(String key, List<String>members){
        List<String>args = new ArrayList<>(List.of(key));
        args.addAll(members);
        this.executeCommand("SADD", args);
        return this.reader.readInteger();
    }

    public int srem(String key, List<String>members){
        List<String>args = new ArrayList<>(List.of(key));
        args.addAll(members);
        this.executeCommand("SREM", args);
        return this.reader.readInteger();
    }

    public boolean sismember(String key, String command){
        this.executeCommand("SISMEMBER", List.of(key, command));
        return this.reader.readInteger() == 1;
    }

    public List<String> sinter(List<String>keys){
        this.executeCommand("SINTER", keys);
        return this.reader.readList();
    }

    public int scard(String key){
        this.executeCommand("SCARD", List.of(key));
        return this.reader.readInteger();
    }

}
