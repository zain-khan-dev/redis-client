package response;


import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import serde.RedisDeserializer;


public class ResponseReader {

    //TODO: Use reading of byte instead of complete line
    static Logger logger = LoggerFactory.getLogger(ResponseReader.class);
    private Socket socket;

    public ResponseReader(Socket socket){
        this.socket = socket;
    }


    public String readSimpleString() {
        
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            char firstByte = (char)reader.read();
            if(firstByte == '-'){
                logger.error("This is an error from redis", reader.readLine());
                return "";
            }
            return reader.readLine();
        }
        catch(IOException ex){
            logger.error("Redis simple string input interrupted ", ex);
            return "";
        }
    }


    public String readBulkString(){
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            reader.read();
            return RedisDeserializer.parseBulkString(reader);
        }
        catch(Exception ex){
            return "";
        }
    }

    public List<String> readList() {
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            reader.read();
            String currLine = reader.readLine();
            int listLength = Integer.valueOf(currLine);
            List<String>result = new ArrayList<>();
            for(int i=0;i<listLength;i++){
                char firstByte = (char)reader.read();
                if(firstByte == '$'){
                    result.add(RedisDeserializer.parseBulkString(reader));   
                }
            }
            return result;
        }
        catch(Exception exception){
            return new ArrayList<>();
        }
    }

    public Integer readInteger(){
        String data = this.readSimpleString();
        return Integer.valueOf(data);
    }




}
