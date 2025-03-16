package commands;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import request.CommandExecutor;
import response.ResponseReader;

public class HashCommands {
    CommandExecutor executor;
    ResponseReader reader;

    public HashCommands(Socket socket){
        this.executor = new CommandExecutor(socket);
        this.reader = new ResponseReader(socket);
    }

    public int hset(String key, String field, String value){
        executor.executeCommand("HSET", List.of(key, field, value));
        return reader.readInteger();
    }

    public String hget(String key, String field){
        executor.executeCommand("HGET", List.of(key, field));
        return reader.readBulkString();
    }

    public List<String> hmget(String key, List<String>fields){  
        List<String>args = new ArrayList<>(List.of(key));
        args.addAll(fields);
        executor.executeCommand("HMGET", args);
        return reader.readList();
    }

    public int hincrby(String key, String field, int increment){
        executor.executeCommand("HINCRBY", List.of(key, field, String.valueOf(increment)));
        return reader.readInteger();
    }   
    
    public int hdel(String key, String field){
        executor.executeCommand("HDEL", List.of(key, field));
        return reader.readInteger();
    }   
}
