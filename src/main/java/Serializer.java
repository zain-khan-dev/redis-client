import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Serializer {

    final String CRLF = "\r\n";
    Logger logger = LoggerFactory.getLogger(Serializer.class);
    public String serialize(String raw_command){
        String [] components = raw_command.split(" ");
        logger.info("Hello lets start");
        StringBuilder serialized_command = new StringBuilder();
        serialized_command.append("*").append(components.length).append(CRLF);
        for(String component : components){
            serialized_command.append("$").append(component.length()).append(CRLF);
            serialized_command.append(component).append(CRLF);
        }
        return serialized_command.toString();
    }
}
