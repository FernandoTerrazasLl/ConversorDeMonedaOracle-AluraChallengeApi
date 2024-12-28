import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class ApiConexion {
    private String monedaAbreviacion;

    public ApiConexion(String monedaAbreviacion){
        this.monedaAbreviacion=monedaAbreviacion;
    }

    public Map<String,Double> getApiResults(){
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://v6.exchangerate-api.com/v6/25f0f240d4e1a5b195d199d0/latest/"+monedaAbreviacion))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            Gson gson = new Gson();
            CambioEstructuraApi cambio=gson.fromJson(response.body(),CambioEstructuraApi.class);
            return cambio.conversion_rates();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
