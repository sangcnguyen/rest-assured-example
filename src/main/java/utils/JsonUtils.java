package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;

import java.io.InputStream;
import java.util.Set;

public class JsonUtils {
    private JsonUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static boolean isJsonSchemaValid(String fileName, String jsonStr) {
        JsonSchemaFactory schemaFactory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V201909);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            InputStream schemaStream = inputStreamFromClasspath(fileName);
            JsonNode json = objectMapper.readTree(jsonStr);
            JsonSchema schema = schemaFactory.getSchema(schemaStream);
            Set<ValidationMessage> validationResult = schema.validate(json);
            if (validationResult.isEmpty()) return true;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static InputStream inputStreamFromClasspath(String path) {
        // returning stream
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
    }
}
