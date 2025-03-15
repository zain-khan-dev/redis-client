package serde;

import java.util.List;

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

    public static String serialize(String commandName, List<String>args){
        StringBuilder serializedCommand = new StringBuilder();
        serializedCommand.append("*"); // start of the array
        int numComponents = args.size() + 1; // extra for commandname;
        serializedCommand.append(numComponents);
        serializedCommand.append(CRLF);
        serializedCommand.append("$").append(commandName.length());
        serializedCommand.append(CRLF);
        serializedCommand.append(commandName);
        serializedCommand.append(CRLF);
        for(String argument:args){
            int length = argument.length();
            serializedCommand.append("$");
            serializedCommand.append(length);
            serializedCommand.append(CRLF);
            serializedCommand.append(argument);
            serializedCommand.append(CRLF);
        }
        return serializedCommand.toString();
    }

}
