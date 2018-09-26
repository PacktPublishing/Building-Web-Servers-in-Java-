package Product5.HttpClient;


import jdk.incubator.http.HttpClient;
import jdk.incubator.http.HttpRequest;
import jdk.incubator.http.HttpResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Main {
    private static String TO_TRANSLATE = "Hello world and welcome to Java 9";

    //You can try to be clever and use my API key but I disabled it after recording ;-)
    private static String API_KEY = "AIzaSyCIZCfQjE3eF1-enZ__YDaSpqrIC63JAXg";

    private static String TARGET_LANGUAGE = "de";

    public static void main(String args[]) throws URISyntaxException, IOException, InterruptedException, ParseException {

        HttpResponse<String> response = makeRequest(TO_TRANSLATE, API_KEY, TARGET_LANGUAGE);
        JSONParser parser = new JSONParser();
        JSONObject payload = (JSONObject) parser.parse(response.body());

        JSONObject data = (JSONObject) payload.get("data");
        JSONArray translations = (JSONArray) data.get("translations");
        JSONObject translation = (JSONObject) translations.get(0);

        System.out.println(TO_TRANSLATE + " in " + TARGET_LANGUAGE + " is " + translation.get("translatedText"));
    }

    private static HttpResponse<String> makeRequest(String toTranslate, String APIKey, String targetLanguage) throws URISyntaxException, IOException, InterruptedException {
        String translationString = tokenizeInputPhrase(toTranslate);

        String callURI = buildURI(translationString, APIKey, targetLanguage);

        HttpClient httpClient = HttpClient.newHttpClient(); //Create a HttpClient
        System.out.println(httpClient.version());
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(new URI(callURI)).GET().build(); //Create a GET request for the given URI
        return httpClient.send(httpRequest, HttpResponse.BodyHandler.asString());
    }

    private static String tokenizeInputPhrase(String inputPhrase) {
        return inputPhrase.replaceAll(" ", "+");
    }

    private static String buildURI(String translationString, String APIKey, String targetLanguage) {
        return "https://www.googleapis.com/language/translate/v2?key="
            + APIKey
            + "&q="
            + translationString
            + "&source=en&target="
            + targetLanguage;
    }
}
