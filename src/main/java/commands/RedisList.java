package commands;

import java.util.List;

import reader.RedisDataReader;
import serializer.RedisSerializer;
import writer.RedisDataWriter;

public class RedisList {

    RedisDataReader redisReader;
    RedisDataWriter redisWriter;

    public RedisList(RedisDataReader reader, RedisDataWriter writer){
        this.redisReader = reader;
        this.redisWriter = writer;
    }

    public int lpush(String key, String value){
        String serializedCommand = RedisSerializer.serialize("LPUSH", List.of(key, value));
        redisWriter.sendData(serializedCommand);
        return Integer.valueOf(redisReader.readSimpleString());
    }
    public int rpush(String key, String value){
        String serializedCommand = RedisSerializer.serialize("RPUSH", List.of(key, value));
        redisWriter.sendData(serializedCommand);
        return Integer.valueOf(redisReader.readSimpleString());
    }

    public String lpop(String key){
        String serializedCommand = RedisSerializer.serialize("LPOP", List.of(key));
        redisWriter.sendData(serializedCommand);
        return redisReader.readSimpleString();
    }

}
