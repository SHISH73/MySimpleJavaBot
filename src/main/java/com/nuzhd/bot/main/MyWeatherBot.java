package com.nuzhd.bot.main;

import com.nuzhd.bot.service.MessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@PropertySource("classpath:application.properties")
@Component
public class MyWeatherBot extends TelegramLongPollingBot {

    private static Logger log = LoggerFactory.getLogger(MyWeatherBot.class);

    @Value("${bot.token}")
    private String botToken;
    @Value("${bot.username}")
    private String botUsername;

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (!update.hasMessage() || !update.getMessage().hasText()){
            return;
        }

        final String msgText = update.getMessage().getText();
        final long chatId = update.getMessage().getChatId();

        log.info("#### > Пользователь с id " + chatId + " отправил сообщение с текстом: " + msgText);

        StringBuilder reply = MessageHandler.handleInput(msgText);


        sendMessage(chatId,reply.toString());
    }

    private void sendMessage(long chatId,String text){
        SendMessage sendMsg = SendMessage
                .builder()
                .chatId(Long.toString(chatId))
                .text(text)
                .build();
        try {
            execute(sendMsg);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }
}
