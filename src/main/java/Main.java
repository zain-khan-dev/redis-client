import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String []args){
        RedisClient client = new RedisClient("127.0.0.1", 6379);
        String [] commands = {"SET HELLO WORLD NX", "GET HELLO"};
        List<String> result = new ArrayList<>();
        for(String command: commands){
            result.add(client.fireCommand(command));
        }
    }

}
