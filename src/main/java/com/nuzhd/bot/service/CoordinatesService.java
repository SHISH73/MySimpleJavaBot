package com.nuzhd.bot.service;

import com.nuzhd.bot.model.Coordinates;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@PropertySource("classpath:application.properties")
@Component
public class CoordinatesService implements Requests {

    public static String mapsApiKey;

    @Value("${yandex.maps.key}")
    private void setMapsApiKey(String yandexMapsKey) {
        mapsApiKey = yandexMapsKey;
    }

    public Coordinates getCoordinates(String enteredAddress) {

        Coordinates coords = new Coordinates();

        JSONObject response = getCoordinatesJSON(enteredAddress);

        JSONObject data = response.getJSONObject("response").getJSONObject("GeoObjectCollection").getJSONArray("featureMember").getJSONObject(0);
        String[] coordsArr = data.getJSONObject("GeoObject").getJSONObject("Point").getString("pos").split(" ");

        coords.setAddress(data.getJSONObject("GeoObject").getJSONObject("metaDataProperty").getJSONObject("GeocoderMetaData").getJSONObject("Address").getString("formatted"));
        coords.setLon(Double.parseDouble(coordsArr[0]));
        coords.setLat(Double.parseDouble(coordsArr[1]));

        return coords;
    }

    private JSONObject getCoordinatesJSON(String cityName) {

        cityName = cityName.replaceAll("[\s,]", "+");
        HttpResponse<String> response = makeRequest("https://geocode-maps.yandex.ru/1.x/?apikey=" + mapsApiKey + "&geocode=" + cityName + "&format=json");
        return new JSONObject(response.body());
    }

    @Override
    public HttpResponse<String> makeRequest(String cityName) {
        HttpClient client = HttpClient.newHttpClient();

        HttpResponse<String> response = null;
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(cityName))
                    .GET()
                    .build();
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return response;
    }
}
