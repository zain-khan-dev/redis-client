public class Main {

    public static void main(String []args){
        RedisClient client = new RedisClient("127.0.0.1", 6379);
        String [] commands = {"SET HELLO WORLD NX", "GET HELLO"};
        for(String command: commands){
            System.out.println(client.fireCommand(command));
        }
    }

}
