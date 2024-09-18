package exchange_rate.services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;

import com.google.gson.Gson;

import exchange_rate.domain.ResponseDto;

public class ExchangeRateService {

    private final String apiKey = "fd0cd8d86624f754e4002000";

    public ResponseDto send(String from, String to, float amount) throws IOException, InterruptedException {

        String fromToAmountAsString = from + "/" + to + "/" + amount;
        String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/pair/" + fromToAmountAsString;

        Gson gson = new Gson();

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

        return gson.fromJson(response.body(), ResponseDto.class);

    }

}
