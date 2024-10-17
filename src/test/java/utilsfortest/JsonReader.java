package utilsfortest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.ResponseMethod;

public class JsonReader {
    public ResponseMethod mapJsonToResponseMethod(String jsonString, ObjectMapper objectMapper){
        try {
            return objectMapper.readValue(jsonString, ResponseMethod.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
