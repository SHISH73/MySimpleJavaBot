package com.nuzhd.bot.service;

import com.nuzhd.bot.model.Coordinates;
import com.nuzhd.bot.model.WeatherObject;
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
public class WeatherService implements Requests {

    public static String weatherApiKey;

    @Value("${yandex.weather.key}")
    private void setWeatherApiKey(String yandexWeatherKey) {
        weatherApiKey = yandexWeatherKey;
    }

    public WeatherObject getWeather(String address) {

        WeatherObject weather = new WeatherObject();
        Coordinates point = new CoordinatesService().getCoordinates(address);
        weather.setCoords(point);
        JSONObject weatherInfo = getWeather(point);

        JSONObject weatherNow = weatherInfo.getJSONObject("fact");

        weather.setTemp(weatherNow.getInt("temp"));
        weather.setFeelsLike(weatherNow.getInt("feels_like"));
        weather.setPressure(weatherNow.getInt("pressure_mm"));
        weather.setWindSpeed(weatherNow.getInt("wind_speed"));
        weather.setWindGust(weatherNow.getDouble("wind_gust"));
        weather.setWindDir(weatherNow.getString("wind_dir"));
        weather.setHumidity(weatherNow.getInt("humidity"));
        weather.setCondition(weatherNow.getString("condition"));

        return weather;
    }

    private JSONObject getWeather(Coordinates coords) {

        HttpResponse<String> response = makeRequest("https://api.weather.yandex.ru/v2/informers?lat=" + coords.getLat() + "&lon=" + coords.getLon() + "&lang=ru");
        return new JSONObject(response.body());
    }

    @Override
    public HttpResponse<String> makeRequest(String data) {
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = null;
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(data))
                    .GET()
                    .header("X-Yandex-API-Key", weatherApiKey)
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
