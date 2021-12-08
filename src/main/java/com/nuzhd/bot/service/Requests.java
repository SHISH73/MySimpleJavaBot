package com.nuzhd.bot.service;

import java.net.http.HttpResponse;

public interface Requests {
    HttpResponse<String> makeRequest(String request);
}
