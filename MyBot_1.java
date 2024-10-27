package org.example;

import net.thauvin.erik.crypto.CryptoPrice;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class MyBot_1 extends TelegramLongPollingBot {
    public MyBot_1() {
        super("7340475513:AAErxw3bBdIZ7yJlDCW87fodZ-A1VhDRIfg");
    }

    @Override
    public void onUpdateReceived(Update update) {
        var chatId = update.getMessage().getChatId();
        var text = update.getMessage().getText();

        try {
            var message = new SendMessage();
            message.setChatId(chatId);

            if (text.equals("/start")) {
                message.setText("Hello!");
            } else if (text.equals("btc")) {
                var price = CryptoPrice.spotPrice("BTC");
                message.setText("BTC price: " + price.getAmount().doubleValue());
            } else if (text.equals("eth")) {
                var price = CryptoPrice.spotPrice("ETH");
                message.setText("ETH price: " + price.getAmount().doubleValue());
            } else if (text.equals("doge")) {
                var price = CryptoPrice.spotPrice("DOGE");
                message.setText("Doge price: " + price.getAmount().doubleValue());
            } else if (text.equals("/all")) {
                var btc = CryptoPrice.spotPrice("BTC").getAmount().doubleValue();
                var eth = CryptoPrice.spotPrice("ETH").getAmount().doubleValue();
                var doge = CryptoPrice.spotPrice("DOGE").getAmount().doubleValue();
                message.setText("BTC price: " + btc + "\nETH price: " + eth + "\nDoge price: " + doge);
            } else {
                message.setText("Unknown command!");
            }

            execute(message);
        } catch (Exception e) {
            System.out.println("Error!");
        }
    }

    @Override
    public String getBotUsername() {
        return "startbot1234_bot";
    }
}