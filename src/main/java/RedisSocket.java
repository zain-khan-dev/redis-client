import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class RedisSocket {

    private static Socket socket = null;

    private RedisSocket(){
    }

    public static Socket getSocketConnection(String port, int host){
        if(socket == null){
            try{
                socket = new Socket();
                socket.connect(new InetSocketAddress(port,host));
            }
            catch(IOException e){
                System.out.println("Could not connect to Redis Server");
                throw new RuntimeException(e);
            }
        }
        return socket;
    }
}
