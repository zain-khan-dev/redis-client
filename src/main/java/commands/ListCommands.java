package commands;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import request.CommandExecutor;
import response.ResponseReader;

public class ListCommands {

    CommandExecutor executor;
    ResponseReader reader;

    public enum ListSourceMove {
        LEFT, 
        RIGHT
    }

    public ListCommands(Socket socket){
        this.executor = new CommandExecutor(socket);
        this.reader = new ResponseReader(socket);
    }
    
    public int lpush(String key, List<String>values){
        List<String>args = new ArrayList<>();
        args.add(key);
        args.addAll(values);
        executor.executeCommand("LPUSH", args);
        return this.reader.readInteger();
    }

    public int rpush(String key, List<String>values){
        executor.executeCommand("RPUSH", values);
        return this.reader.readInteger();
    }

    public String lpop(String key){
        executor.executeCommand("LPOP", List.of(key));
        return reader.readSimpleString();
    }
    
    public List<String> lpop(String key, int count){
        executor.executeCommand("LPOP", List.of(key, String.valueOf(count)));
        return reader.readList();
    }

    public List<String> rpop(String key, int count){
        executor.executeCommand("RPOP", List.of(key, String.valueOf(count)));
        return reader.readList();
    }

    public int llen(String key){
        executor.executeCommand("LLEN", List.of(key));
        return reader.readInteger();
    }

    public String lmove(String sourceListKey, String destListKey, ListSourceMove sourceMove, ListSourceMove destMove){
        executor.executeCommand("LMOVE", List.of(sourceListKey, destListKey, sourceMove.toString(), destMove.toString()));
        return reader.readSimpleString();
    }

    public List<String> lrange(String key, int start, int end){
        executor.executeCommand("LRANGE", List.of(key, String.valueOf(start), String.valueOf(end)));
        return reader.readList();
    }

    public String ltrim(String key, int start, int stop){
        executor.executeCommand("LTRIM", List.of(key, String.valueOf(start), String.valueOf(stop)));
        return reader.readSimpleString();
    }

}
