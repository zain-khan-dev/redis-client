package reader;


import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import exception.InvalidRedisCommandException;
import serializer.RedisSerializer;

public class RedisDataReader {


    static Logger logger = LoggerFactory.getLogger(RedisDataReader.class);
    private Socket socket;

    public RedisDataReader(Socket socket){
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

}
