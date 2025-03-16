package commands;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import request.CommandExecutor;
import response.ResponseReader;

public class StringCommands {

    CommandExecutor executor;
    ResponseReader reader;
    
    public StringCommands(Socket socket){
        this.executor = new CommandExecutor(socket);
        this.reader = new ResponseReader(socket);
    }
    public String get(String key){
        executor.executeCommand("GET", List.of(key));
        return reader.readBulkString();
    }

    public String set(String key, String value){
        executor.executeCommand("SET", List.of(key, value));
        return this.reader.readSimpleString();
    }

    public String del(String key){
        executor.executeCommand("DEL", List.of(key));
        return this.reader.readSimpleString();
    }

    public String mset(Map<String, String>mp){
        List<String>args = new ArrayList<>();
        mp.forEach((key, value) -> {args.add(key); args.add(value);});
        executor.executeCommand("MSET", args);
        return this.reader.readSimpleString();
    }

    public String mset(List<String>args){
        executor.executeCommand("MSET", args);
        return this.reader.readSimpleString();
    }

    public long incr(String key){
        executor.executeCommand("INCR", List.of(key));
        return this.reader.readInteger();
    }

}
