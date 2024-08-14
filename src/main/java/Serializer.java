


public class Serializer {

    final String CRLF = "\r\n";

    public String serialize(String raw_command){
        String [] components = raw_command.split(" ");
        StringBuilder serialized_command = new StringBuilder();
        serialized_command.append("*").append(components.length).append(CRLF);
        for(String component : components){
            serialized_command.append("$").append(component.length()).append(CRLF);
            serialized_command.append(component).append(CRLF);
        }
        return serialized_command.toString();
    }
}
