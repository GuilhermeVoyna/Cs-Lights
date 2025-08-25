package org.example.utils.json;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public final class JsonUtils {

    public static String inputStreamToString(InputStream inputStream) throws IOException {
        return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
    }

}
