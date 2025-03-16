package commands;

import java.net.Socket;
import java.util.List;
import request.CommandExecutor;
import response.ResponseReader;

public class AuthCommands {
    CommandExecutor executor;
    ResponseReader reader;

    public AuthCommands(Socket socket){
        this.executor = new CommandExecutor(socket);
        this.reader = new ResponseReader(socket);
    }
    public boolean auth(String username, String password){
        executor.executeCommand("AUTH", List.of(username, password));
        String response = reader.readSimpleString();
        return response.equals("OK");
    }
}
