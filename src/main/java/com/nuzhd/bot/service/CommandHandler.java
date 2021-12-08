package com.nuzhd.bot.service;

public class CommandHandler {

    public static StringBuilder handleCommand(String command) {
        StringBuilder reply = new StringBuilder();
        command = command.replaceAll("[\s/.]","");
        switch (command) {
            case "start" -> reply.append("Доброго времени суток! Я погодный бот.\n" +
                    "Для получения сведений о погоде необходимо указать название города или адрес места для более конкретного прогноза. " +
                    "Адрес можно вводить через запятую или пробелы.\n\n" +
                    "Для управления мной вы можете воспользоваться специальными командами. " +
                    "Команды должны начинаться со знака '!' или '/'\nНапример /start или !start.\nСписок доступных на данный момент команд: /cmlist");
            case "cmlist" -> reply.append("Вот список всего, что я умею на данный момент:\n"+
                    "/start - Начало работы с ботом\n" +
                    "/cmlist - Получить список доступных команд\n" +
                    "/info - Краткая инструкция по использованию бота");
            case "info" -> reply.append("Для получения данных о погоде введите название города или укажите ваш адрес для более точного прогноза.\n" +
                    "Пример указания адреса: Москва тверская 7\n" +
                    "Для получения списка доступных команд введите команду /cmlist");
            default -> reply.append("Я ещё не знаю такой команды! Проверьте правильность ввода или воспользуйтесь списком доступных команд /cmlist");
        }
        return reply;
    }

}