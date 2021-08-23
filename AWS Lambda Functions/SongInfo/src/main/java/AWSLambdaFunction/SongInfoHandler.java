package AWSLambdaFunction;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.google.gson.*;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class SongInfoHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private final Gson gson = new Gson();

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent apiGatewayProxyRequestEvent, Context context) {
        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
        response.setIsBase64Encoded(false);
        response.setStatusCode(200);

        HashMap<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        response.setHeaders(headers);

        Map<String, String> queryStringParameters = apiGatewayProxyRequestEvent.getQueryStringParameters();
        String songID = queryStringParameters.get("songID");

        String RAPID_API_HOST = System.getenv("RAPID_API_HOST");
        String RAPID_API_KEY = System.getenv("RAPID_API_KEY");

        String request = "https://shazam.p.rapidapi.com/songs/get-details?key=" + songID;

        JsonObject results;

        try {
            URL url = new URL(request);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestProperty("x-rapidapi-host", RAPID_API_HOST);
            httpURLConnection.setRequestProperty("x-rapidapi-key", RAPID_API_KEY);
            JsonElement fileElement = JsonParser.parseReader(new InputStreamReader((httpURLConnection.getInputStream())));
            httpURLConnection.disconnect();

            results = fileElement.getAsJsonObject();
            response.setBody(String.valueOf(results));

            return response;

        } catch (IOException ioException) {
            response.setStatusCode(503);
            String error = ioException.getMessage();
            response.setBody(gson.toJson(error));

            return response;
        }
    }
}