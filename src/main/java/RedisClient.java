import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;

public class RedisClient {

    Socket socket = null;
    static final Logger logger = LoggerFactory.getLogger(RedisClient.class);
    public RedisClient(String host, int port){
        this.socket = RedisSocket.getSocketConnection(host, port);
    }

    public void sendData(String serializedCommand) {
        try {
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            dos.writeBytes(serializedCommand);
        }
        catch(IOException e) {
            logger.error("Failed to send data to redis server {}", e.getMessage());
        }
    }
    public String receiveData(){
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            return readDataFromSocket(reader);
        }
        catch(IOException e) {
            logger.error("Failed to read data from redis server {}", e.getMessage());
        }
        return "";
    }

    private String readDataFromSocket(BufferedReader reader) throws IOException {
        char firstChar = (char)reader.read();
        String result = "";
        if(firstChar == '+' || firstChar == ':' || firstChar == '-'){
            result = reader.readLine();
        }
        else if(firstChar == '$'){
            result =  reader.readLine() +"\r\n";
        }
        else if(firstChar == '*'){
            int element = Integer.parseInt(reader.readLine().split("\\*")[1]);
            StringBuilder builder = new StringBuilder();
            for(int i = 0; i < element; i++){
                builder.append(readDataFromSocket(reader));
            }
            result = builder.toString();
        }
        return firstChar + result;
    }

    public String fireCommand(String command){
        Serializer serializer = new Serializer();
        String serialized_command = serializer.serialize(command);
        sendData(serialized_command);
        return receiveData();
    }
}
