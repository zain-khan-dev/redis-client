package exception;

public class InvalidRedisCommandException extends Exception {
    
    public InvalidRedisCommandException(String message){
        super(message);
    }
}
