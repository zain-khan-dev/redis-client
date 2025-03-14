package writer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class RedisDataWriter {
    private final Socket socket;

    public RedisDataWriter(Socket socket) {
        this.socket = socket;
    }

    public void sendData(String data) {
        try {
            System.out.println("The data being sent is " + data);
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(data.getBytes());
            System.out.println("Data succesfully written");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}