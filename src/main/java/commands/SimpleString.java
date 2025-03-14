package commands;

import serializer.RedisSerializer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import reader.RedisDataReader;
import writer.RedisDataWriter;

public class SimpleString {
    
    private RedisDataReader redisReader;
    private RedisDataWriter redisWriter;

    public SimpleString(RedisDataReader redisReader, RedisDataWriter redisWriter){
        this.redisReader =  redisReader;
        this.redisWriter = redisWriter;
    }

    public String get(String key){
        String serializedCommand = RedisSerializer.serialize("GET", List.of(key));
        this.redisWriter.sendData(serializedCommand);
        String data = redisReader.readSimpleString();
        return data;
    }

    public String set(String key, String value){
        String serializedCommand = RedisSerializer.serialize("SET", List.of(key, value));
        this.redisWriter.sendData(serializedCommand);
        String data = redisReader.readSimpleString();
        return data;
    }

    public String del(String key){
        String serializedCommand = RedisSerializer.serialize("DEL", List.of(key));
        this.redisWriter.sendData(serializedCommand);
        return this.redisReader.readSimpleString();
    }

    public String set(String key, String value, int ttl){
        String serializedCommand = RedisSerializer.serialize("SET", List.of(key, value, String.valueOf(ttl)));
        this.redisWriter.sendData(serializedCommand);
        return this.redisReader.readSimpleString();
    }


    public String mset(Map<String, String>keyVal){
        List<String>args = new ArrayList<>();
        keyVal.forEach((key, val) -> {args.add(key); args.add(val);}); // build list from map
        return this.mset(args);
    }
    public String mset(List<String>args){
        String serializedCommand = RedisSerializer.serialize("MSET", args);
        this.redisWriter.sendData(serializedCommand);
        return this.redisReader.readSimpleString();
    }


    public long incr(String key){  
        String serializedCommand = RedisSerializer.serialize("INCR", List.of(key));
        this.redisWriter.sendData(serializedCommand);
        return Long.valueOf(this.redisReader.readSimpleString());
    }

    public long incrBy(String key, int incrVal){
        String serializedCommand = RedisSerializer.serialize("INCRBY", List.of(key, String.valueOf(incrVal)));
        this.redisWriter.sendData(serializedCommand);
        return Long.valueOf(this.redisReader.readSimpleString());
    }

}
