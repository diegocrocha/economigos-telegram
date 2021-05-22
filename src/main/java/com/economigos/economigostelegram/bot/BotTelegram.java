package com.economigos.economigostelegram.bot;

import com.economigos.economigostelegram.controller.GastoController;
import com.economigos.economigostelegram.controller.ReceitaController;
import com.economigos.economigostelegram.form.GastoForm;
import com.economigos.economigostelegram.form.ReceitaForm;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class BotTelegram {
    static TelegramBot tokenBot = new TelegramBot("1680012398:AAF9IkYcMyarOtXVwL4qVQn1733iUOLgtd8");
    static String mensagem, nome, valor = "", descricao = "", categoria = "", conta = "";
    static boolean gasto, receita, fim = false;
    static Calendar calendar = Calendar.getInstance();
    static int cont = 0, hora = calendar.get(Calendar.HOUR_OF_DAY);
    static SendResponse resposta;
    static long chatId;

    public static void reiniciar(){
        gasto = false;
        receita = false;
        cont = 0;
    }

    public static void main(String[] args) {

        UpdatesListener updatesListener = new UpdatesListener() {
            @Override
            public int process(List<Update> updates) {

                try {
                    for (Update update : updates){
                        chatId = update.message().chat().id();
                        nome= update.message().from().firstName();
                        mensagem = update.message().text();

                        switch (mensagem) {
                            case "/start":
                                resposta = tokenBot.execute(new SendMessage(chatId, "Seja bem vindo(a) " + nome +
                                        ", aqui vc vai ter praticidade para cadastrar seus gastos e receitas.\n\n" +
                                        "Sempre que quiser cadastar digite '/' e escolha a opção ou selecione a / no cantinho! \uD83D\uDE09"));
                                resposta = tokenBot.execute(new SendMessage(chatId, nome + " insira o codigo de verificação:"));

                                System.out.println(update);
                                System.out.println(nome +" mandou: "+update.message().text());
                                break;



                            case "/gasto":
                                resposta = tokenBot.execute(new SendMessage(chatId,  nome + " vamos cadastrar seu gasto!"));
                                resposta = tokenBot.execute(new SendMessage(chatId, "Para cancelar digite a qualquer momento:\ncancelar"));
                                resposta = tokenBot.execute(new SendMessage(chatId, "Insira o valor:"));
                                gasto = true;
                                cont ++;
                                System.out.println(nome +" mandou: "+update.message().text());

                                break;

                            case "/receita":
                                resposta = tokenBot.execute(new SendMessage(chatId,  nome + " vamos cadastrar sua receita!"));
                                resposta = tokenBot.execute(new SendMessage(chatId, "Para cancelar digite a qualquer momento:\ncancelar"));
                                resposta = tokenBot.execute(new SendMessage(chatId, "Insira o valor:"));
                                receita = true;
                                cont ++;
                                System.out.println(nome +" mandou: "+update.message().text());
                                break;

                            //Cancelando o cadastro
                            case "cancelar":
                            case "/cancelar":
                                resposta = tokenBot.execute(new SendMessage(chatId,  nome + " cancelamos o cadastro! \uD83D\uDE09"));
                                reiniciar();
                                System.out.println(nome +" mandou: "+update.message().text());
                                break;

                            //Cadastro realizado
                            case "sim":
                            case "/sim":
                                if (gasto){
                                    //Criando post gasto
                                    GastoController.createProducts(new GastoForm(Double.parseDouble(valor), descricao, categoria, conta));
                                    resposta = tokenBot.execute(new SendMessage(chatId, "Gasto cadastrado com sucesso \uD83D\uDE0D"));
                                    reiniciar();
                                    System.out.println(nome +" mandou: "+update.message().text());
                                    break;
                                }
                                if (receita){
                                    //Criando post receita
                                    ReceitaController.createProducts(new ReceitaForm(Double.parseDouble(valor), descricao, categoria, conta));
                                    resposta = tokenBot.execute(new SendMessage(chatId, "Receita cadastrado com sucesso \uD83D\uDE0D"));
                                    reiniciar();
                                    System.out.println(nome +" mandou: "+update.message().text());
                                    break;
                                }


                            default:
                                //inserindo a descrição
                                if (cont==1){
                                    resposta = tokenBot.execute(new SendMessage(chatId, "Insira a descrição:"));
                                    valor = mensagem.replace(",",".");
                                    cont ++;
                                    System.out.println(nome +" mandou: "+update.message().text());
                                }else
                                    //inserindo a categoria
                                    if (cont==2){
                                        resposta = tokenBot.execute(new SendMessage(chatId,"Insira a categoria:"));
                                        descricao = mensagem;
                                        cont ++;
                                        System.out.println(nome +" mandou: "+update.message().text());
                                    }else
                                        //inserindo a conta
                                        if (cont==3){
                                            if (gasto){
                                                resposta = tokenBot.execute(new SendMessage(chatId,"Insira a conta que " +
                                                        "o gasto estrá relacionado:"));
                                            }else {
                                                resposta = tokenBot.execute(new SendMessage(chatId,"Insira a conta que a " +
                                                        "receita estrá relacionada:"));
                                            }
                                            categoria = mensagem;
                                            cont ++;
                                            System.out.println(nome +" mandou: "+update.message().text());

                                        }else
                                            //Confirmando os dados
                                            if (cont==4){
                                                conta = mensagem;
                                                if (gasto){
                                                    resposta = tokenBot.execute(new SendMessage(chatId,"Confirmar gasto? " +
                                                            "\nValor: " + valor + "\nDescrição: " + descricao + "\nCategoria: " +
                                                            categoria + "\nConta: " + conta));
                                                    resposta = tokenBot.execute(new SendMessage(chatId, "/sim\n\n/cancelar"));
                                                    System.out.println(nome +" mandou: "+update.message().text());
                                                }else
                                                if (receita){
                                                    resposta = tokenBot.execute(new SendMessage(chatId,"Confirmar receita? " +
                                                            "\nValor: " + valor + "\nDescrição: " + descricao + "\nCategoria: " +
                                                            categoria + "\nConta: " + conta));
                                                    resposta = tokenBot.execute(new SendMessage(chatId, "/sim\n\n/cancelar"));
                                                    System.out.println(nome +" mandou: "+update.message().text());
                                                }
                                            }else
                                            if (hora > 4 && hora < 12){
                                                resposta = tokenBot.execute(new SendMessage(chatId,"Bom dia " + nome + " \uD83D\uDE03 \nQuer " +
                                                        "cadastrar uma receita ou um gasto agora?"));
                                                System.out.println(nome +" mandou: "+update.message().text());
                                            }else if (hora > 12 && hora < 18){
                                                resposta = tokenBot.execute(new SendMessage(chatId,"Boa tarde " + nome + " \uD83D\uDE03 \nQuer " +
                                                        "cadastrar uma receita ou um gasto agora?"));
                                                System.out.println(nome +" mandou: "+update.message().text());
                                            }else {
                                                resposta = tokenBot.execute(new SendMessage(chatId,"Boa noite " + nome + " \uD83D\uDE03 \nQuer " +
                                                        "cadastrar uma receita ou um gasto agora?"));
                                                System.out.println(nome +" mandou: "+update.message().text());
                                            }

                        }
                    }
                }catch (Exception e){
                    System.out.println(e);
                    resposta = tokenBot.execute(new SendMessage(chatId,"Desculpe " + nome + ", não entendi. \uD83D\uDE22" +
                            "\nFica mais facil se você usar os comandos para cadastar. \n/gasto e /receita"));
                    reiniciar();
                }

                return UpdatesListener.CONFIRMED_UPDATES_ALL;
            }

        };

        tokenBot.setUpdatesListener(updatesListener);
    }
}
