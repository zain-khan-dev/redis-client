package commands;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import request.CommandExecutor;
import response.ResponseReader;

public class SetCommands {
    CommandExecutor executor;
    ResponseReader reader;

    public SetCommands(Socket socket){
        this.executor = new CommandExecutor(socket);
        this.reader = new ResponseReader(socket);
    }
    public int sadd(String key, List<String>members){
        List<String>args = new ArrayList<>(List.of(key));
        args.addAll(members);
        executor.executeCommand("SADD", args);
        return reader.readInteger();
    }

    public int sadd(String key, Set<String>members){
        List<String>args = new ArrayList<>(List.of(key));
        args.addAll(members);
        executor.executeCommand("SADD", args);
        return reader.readInteger();
    }

    public int srem(String key, List<String>members){
        List<String>args = new ArrayList<>(List.of(key));
        args.addAll(members);
        executor.executeCommand("SREM", args);
        return reader.readInteger();
    }

    public int srem(String key, Set<String>members){
        List<String>args = new ArrayList<>(List.of(key));
        args.addAll(members);
        executor.executeCommand("SREM", args);
        return reader.readInteger();
    }

    public boolean sismember(String key, String command){
        executor.executeCommand("SISMEMBER", List.of(key, command));
        return reader.readInteger() == 1;
    }

    public List<String> sinter(List<String>keys){
        executor.executeCommand("SINTER", keys);
        return reader.readList();
    }

    public int scard(String key){
        executor.executeCommand("SCARD", List.of(key));
        return reader.readInteger();
    }
}
