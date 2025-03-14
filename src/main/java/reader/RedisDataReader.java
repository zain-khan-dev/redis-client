package reader;
import java.net.Socket;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class RedisDataReader {
    
    private Socket socket;

    public RedisDataReader(Socket socket){
        this.socket  = socket;
    }


    public String receiveData() {
        try{
            System.out.println("Starting data read");
            BufferedReader reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            String currLine = reader.readLine();
            if(currLine.charAt(0) == '+'){ // simple string parse
                return currLine;
            }
            else
            if(currLine.charAt(0) == '-') // error data parsing
            {
                return currLine;
            }
            else{
                currLine = reader.readLine();
            }
            System.out.println("Finished data reading " + currLine);
            
            return currLine;
        }
        catch(IOException exception){
            System.out.println(exception);
            return "";
        }
    }

}
