package com.economigos.economigostelegram.bot;

import com.economigos.economigostelegram.controller.RendaController;
import com.economigos.economigostelegram.form.RendaForm;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;

import java.util.List;
import java.util.Locale;

public class BotTelegram {
    static TelegramBot tokenBot = new TelegramBot("1680012398:AAF9IkYcMyarOtXVwL4qVQn1733iUOLgtd8");
    static long chatId;
    static String nome;
    static String mensagem;
    static SendResponse resposta;

    public static void main(String[] args) {
        UpdatesListener updatesListener = new UpdatesListener() {
            @Override
            public int process(List<Update> updates) {

                for (Update update : updates){
                    chatId = update.message().chat().id();
                    nome= update.message().from().firstName();
                    mensagem = update.message().text().toLowerCase(Locale.ROOT);

                    switch (mensagem){
                        case "/start":
                            resposta = tokenBot.execute(new SendMessage(chatId,  nome + " vamos cadastrar sua renda!"));
                            resposta = tokenBot.execute(new SendMessage(chatId, "Insira o valor:"));

                            RendaController.createProducts(new RendaForm(1, 1,
                                    Double.parseDouble(update.message().text()), false,
                                    "fgd", false, "2021-03-24"));
                    }

                }

                return UpdatesListener.CONFIRMED_UPDATES_ALL;
            }

        };

        tokenBot.setUpdatesListener(updatesListener);
    }
}
