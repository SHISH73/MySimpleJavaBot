package com.nuzhd.bot.model;

public class WeatherObject {
    private Coordinates coords;
    private int temp;
    private int feelsLike;
    private int windSpeed;
    private int pressure;
    private int humidity;
    private String cloudness;
    private String condition;
    private String precType;
    private double windGust;
    private String windDir;

    public WeatherObject(Coordinates coords) {
        this.coords = coords;
    }

    public WeatherObject() {
    }

    public String getWindDir() {
        return windDir;
    }

    public void setWindDir(String windDir) {
        switch (windDir) {
            case "nw" -> this.windDir="Северо-западный";
            case "n" -> this.windDir="Северный";
            case "ne" -> this.windDir="Северо-восточный";
            case "e" -> this.windDir="Восточный";
            case "se" -> this.windDir="Юго-восточный";
            case "s" -> this.windDir="Южный";
            case "sw" -> this.windDir="Южный-западный";
            case "w" -> this.windDir="Западный";
            case "с" -> this.windDir="Штиль";
        }
    }

    public double getWindGust() {
        return windGust;
    }

    public void setWindGust(double windGust) {
        this.windGust = windGust;
    }

    public String getCloudness() {
        return cloudness;
    }

    public int getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(int feelsLike) {
        this.feelsLike = feelsLike;
    }

    public void setCloudness(double cloudness) {
        if (cloudness == 0) {
            this.cloudness = "Ясно";
        } else if (cloudness == 0.25) {
            this.cloudness = "Малооблачно";
        } else if (cloudness == 0.5 || cloudness == 0.75) {
            this.cloudness = "Облачно с прояснениями";
        } else {
            this.cloudness = "Пасмурно";
        }
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        switch (condition) {
            case "clear" -> this.condition = "Ясно";
            case "partly-cloudy", "cloudy" -> this.condition = "Облачно с прояснениями";
            case "overcast" -> this.condition = "Пасмурно";
            case "drizzle" -> this.condition = "Морось";
            case "light-rain" -> this.condition = "Небольшой дождь";
            case "rain" -> this.condition = "Дождь";
            case "moderate-rain" -> this.condition = "Умеренно сильный дождь";
            case "heavy-rain" -> this.condition = "Сильный дождь";
            case "continuous-heavy-rain" -> this.condition = "Длительный сильный дождь";
            case "showers" -> this.condition = "Ливень";
            case "wet-snow" -> this.condition = "Дождь со снегом";
            case "light-snow" -> this.condition = "Небольшой снег";
            case "snow" -> this.condition = "Снег";
            case "snow-showers" -> this.condition = "Снегопад";
            case "hail" -> this.condition = "Град";
            case "thunderstorm" -> this.condition = "Гроза";
            case "thunderstorm-with-rain" -> this.condition = "Дождь с грозой";
            case "thunderstorm-with-hail" -> this.condition = "Гроза с градом";
        }
    }

    public String getPrecType() {
        return precType;
    }

    public void setPrecType(int precType) {
        switch (precType) {
            case 0 -> this.precType = "Без осадков";
            case 1 -> this.precType = "дождь";
            case 2 -> this.precType = "дождь со снегом";
            case 3 -> this.precType = "снег";
            case 4 -> this.precType = "град";
        }
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public int getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(int windSpeed) {
        this.windSpeed = windSpeed;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public Coordinates getCoords() {
        return coords;
    }

    public void setCoords(Coordinates coords) {
        this.coords = coords;
    }
}
