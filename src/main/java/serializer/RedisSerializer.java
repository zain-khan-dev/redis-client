package serializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RedisSerializer {

    static final String CRLF = "\r\n";
    static Logger logger = LoggerFactory.getLogger(RedisSerializer.class);
    public static String serialize(String raw_command){
        String [] components = raw_command.split(" ");
        logger.info("Hello lets start");
        StringBuilder serialized_command = new StringBuilder();
        serialized_command.append("*").append(components.length).append(CRLF);
        for(String component : components){
            // component = component.replace("\n", "\\n").replace("\r", "\\r");
            serialized_command.append("$").append(component.length()).append(CRLF);
            serialized_command.append(component).append(CRLF);
        }
        return serialized_command.toString();
    }
}
