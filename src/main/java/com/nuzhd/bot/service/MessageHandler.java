package com.nuzhd.bot.service;

import com.nuzhd.bot.model.WeatherObject;

import java.util.Locale;

public class MessageHandler {

    public static StringBuilder handleInput(String requestMessage) {

        StringBuilder botResponse = new StringBuilder();

        if (requestMessage.startsWith("!") || requestMessage.startsWith("/")){
            botResponse = CommandHandler.handleCommand(requestMessage.substring(1).toLowerCase(Locale.ROOT));
        }
        else {
            WeatherObject weather = new WeatherService().getWeather(requestMessage);

            botResponse.append("Погода по запросу: " + weather.getCoords().getAddress() + "\n"
                    + "Температура воздуха: " + weather.getTemp() + "°C (Ощущается как " + weather.getFeelsLike() + "°C)\n"
                    + "Влажность воздуха: " + weather.getHumidity() + "%\n"
                    + "Давление: " + weather.getPressure() + " мм.рт.ст.\n"
                    + "Ветер: " + weather.getWindDir() + ", " + weather.getWindSpeed() + " м/с, с порывами до " + weather.getWindGust() + "м/с\n"
                    + weather.getCondition() + "\n");
        }
        return botResponse;
    }
}
