import org.junit.jupiter.api.Test;

import serde.RedisSerializer;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SerializerTest {

    @Test
    public void testSerialize(){
        RedisSerializer serializer = new RedisSerializer();
        String serialized_text = serializer.serialize("GET HELLO");
        String expected_serialized_test = "*2\r\n$3\r\nGET\r\n$5\r\nHELLO\r\n";
        assertEquals(expected_serialized_test, serialized_text);
    }
}
