import java.util.List;

import client.RedisClient;

public class Main {

    public static void main(String []args){
        RedisClient client = new RedisClient("127.0.0.1", 6379);
        client.set("HELLO", "WORLD");
        System.out.print(client.get("HELLO"));
        client.set("NEWVAL", "1");
        System.out.println(client.incr("NEWVAL"));
        client.mset(List.of("HELLO1", "WORLD1", "HELLO2", "WORLD2"));
    }
}
