package util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;

public class RequestReader {
    public static JsonObject readRequest(HttpServletRequest req) {
        StringBuilder sb = new StringBuilder();
        String line;

        try {
            BufferedReader reader = req.getReader();
            while ((line = reader.readLine()) != null)
                sb.append(line);
        }
        catch (Exception e) {
            System.out.println("Error reading requst: " + e);
        }
        JsonObject jsonObject = new JsonParser().parse(sb.toString()).getAsJsonObject();
        return jsonObject;
    }
}
