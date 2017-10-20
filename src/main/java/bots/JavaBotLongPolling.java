package bots;


import bots.cont.NewsParcer;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

/**
 * Created by aer on 11.10.17.
 */
public class JavaBotLongPolling extends TelegramLongPollingBot {

    public static void main(String[] args) throws Exception {
        ApiContextInitializer.init(); // Инициализируем апи
        TelegramBotsApi botapi = new TelegramBotsApi();
        try {

            botapi.registerBot(new JavaBotLongPolling());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return "jjeebot";
        //возвращаем юзера
    }

    @Override
    public void onUpdateReceived(Update e) {
        Message msg = e.getMessage(); // Это нам понадобится
        String txt = msg.getText();
        if (txt.equals("/start")) {
            sendMsg(msg, "Welcome to Java Bots chat");
        }

        if (txt.equals("/time")) {
            sendMsg(msg, new java.util.Date().toString());
        }

        if(txt.equals("/news")){
            sendMsg(msg, NewsParcer.getLastNewsTitle());


        }
    }

    @Override
    public String getBotToken() {
        return "411821404:AAGR2-HsdRktxqNKxsFILeuaT9p2rRsZRk4";
        //Токен бота
    }

    @SuppressWarnings("deprecation") // Означает то, что в новых версиях метод уберут или заменят
    private void sendMsg(Message msg, String text) {
        SendMessage s = new SendMessage();
        s.setChatId(msg.getChatId()); // Боту может писать не один человек, и поэтому чтобы отправить сообщение, грубо говоря нужно узнать куда его отправлять
        s.setText(text);
        try { //Чтобы не крашнулась программа при вылете Exception
            sendMessage(s);
        } catch (TelegramApiException e){
            e.printStackTrace();
        }
    }

}