import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class RedisSocket {

    private static Socket socket = null;
    public static Logger logger = LoggerFactory.getLogger(RedisSocket.class);

    private RedisSocket(){
    }

    public static Socket getSocketConnection(String port, int host){
        if(socket == null){
            try{
                socket = new Socket();
                socket.connect(new InetSocketAddress(port,host));
            }
            catch(IOException e){
                logger.error("Failed to connect to redis server {}", e.getMessage());
                throw new RuntimeException(e);
            }
        }
        return socket;
    }
}
