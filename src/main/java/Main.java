

import client.RedisClient;

public class Main {

    public static void main(String []args){
        RedisClient client = new RedisClient("127.0.0.1", 6379);
        client.set("HELLO", "WORLD");
        System.out.println(client.get("HELLO"));
    }
}
