package client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import commands.SimpleString;
import writer.RedisDataWriter;
import reader.RedisDataReader;
import redis_socket.RedisSocket;

import java.net.Socket;
import java.util.List;
import java.util.Map;

public class RedisClient {
    Socket socket = null;
    static final Logger logger = LoggerFactory.getLogger(RedisClient.class);
    private final RedisDataWriter writer;
    private final RedisDataReader reader;

    public RedisClient(String host, int port){
        this.socket = RedisSocket.getSocketConnection(host, port);
        this.writer = new RedisDataWriter(socket);
        this.reader = new RedisDataReader(socket);
    }

    public String get(String key){
        return new SimpleString(this.reader, this.writer).get(key);
    }

    public String set(String key, String value){
        return new SimpleString(this.reader, this.writer).set(key, value);
    }

    public String del(String key){
        return new SimpleString(this.reader, this.writer).del(key);
    }

    public String mset(Map<String, String>mp){
        return new SimpleString(this.reader, this.writer).mset(mp);
    }

    public String mset(List<String>args){
        return new SimpleString(this.reader, this.writer).mset(args);
    }

    public long incr(String key){
        return new SimpleString(this.reader, this.writer).incr(key);
    }

}
