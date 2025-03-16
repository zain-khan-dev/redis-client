package request;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;

import serde.RedisSerializer;

public class CommandExecutor {
    private final Socket socket;

    public CommandExecutor(Socket socket) {
        this.socket = socket;
    }


    public void sendData(String data) {
        try {
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(data.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void executeCommand(String command, List<String>args){
        String serializedCommand = RedisSerializer.serialize(command, args);
        this.sendData(serializedCommand);
    }
    
}