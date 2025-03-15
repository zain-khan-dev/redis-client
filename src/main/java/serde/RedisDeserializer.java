package serde;

import java.io.BufferedReader;
import java.io.IOException;

public class RedisDeserializer {
    // here the reader offset will be 

    static String parseSimpleString(BufferedReader reader) throws IOException{
        // read 
        return reader.readLine();
    }

    public static String parseBulkString(BufferedReader reader) throws IOException{
        int length = Integer.valueOf(reader.readLine()); // consume length
        return reader.readLine();
    }

}
