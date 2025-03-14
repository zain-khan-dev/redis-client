import client.RedisClient;

public class Main {

    public static void main(String []args){
        RedisClient client = new RedisClient("127.0.0.1", 6379);
        client.put("HELLO1", "WORLD");
        String data = client.get("HELLO1");
        System.out.println(data);
    }
}
