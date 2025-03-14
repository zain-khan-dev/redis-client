package commands;

import serializer.RedisSerializer;

import reader.RedisDataReader;
import writer.RedisDataWriter;

public class SimpleString {
    
    private RedisDataReader redisReader;
    private RedisDataWriter redisWriter;

    public SimpleString(RedisDataReader redisReader, RedisDataWriter redisWriter){
        this.redisReader = redisReader;
        this.redisWriter = redisWriter;
    }

    public String get(String key){
        String rawCommand = "GET " + key;
        String serializedCommand = RedisSerializer.serialize(rawCommand);
        this.redisWriter.sendData(serializedCommand);
        return this.redisReader.receiveData();   
    }

    public String put(String key, String value){
        String rawCommand = "SET " + key + " " + value;
        String serializedCommand = RedisSerializer.serialize(rawCommand);
        this.redisWriter.sendData(serializedCommand);
        return this.redisReader.receiveData();
    }
}
