package com.economigos.economigostelegram.bot;

import com.economigos.economigostelegram.controller.RendaController;
import com.economigos.economigostelegram.form.RendaForm;
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
    static Calendar calendar = Calendar.getInstance();
    static int hora = calendar.get(Calendar.HOUR_OF_DAY);
    static boolean fim = false;
    static SendResponse resposta;
    static long chatId;
    static String mensagem;
    static String nome;
    static int cont = 0;
    static boolean gasto, receita, valorB, descricaoB, categoriaB = false;
    static String valor = "", descricao = "", categoria = "";
    static String codigoVerificador = "";

    public static void reiniciar(){
        valorB = false;
        descricaoB = false;
        categoriaB = false;
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
                        mensagem = update.message().text().toLowerCase(Locale.ROOT);

                        switch (mensagem) {
                            case "/start":
                                resposta = tokenBot.execute(new SendMessage(chatId, "Seja bem vindo(a) " + nome +
                                        ", aqui vc vai ter praticidade para cadastrar seus gastos e receitas.\n\n" +
                                        "Sempre que quiser cadastar digite '/' e escolha a opção ou selecione a / no cantinho! \uD83D\uDE09"));
                                resposta = tokenBot.execute(new SendMessage(chatId, nome + "insira o codigo de verificação:"));

                                System.out.println(update);
                                System.out.println(nome +" mandou: "+update.message().text());
                                break;



                            case "/gasto":
                                resposta = tokenBot.execute(new SendMessage(chatId,  nome + " vamos cadastrar seu gasto!"));
                                resposta = tokenBot.execute(new SendMessage(chatId, "Para cancelar digite a qualquer momento:\ncancelar"));
                                resposta = tokenBot.execute(new SendMessage(chatId, "Insira o valor:"));
                                gasto = true;
                                valorB = true;
                                cont ++;
                                System.out.println(nome +" mandou: "+update.message().text());

                                break;

                            case "/receita":
                                resposta = tokenBot.execute(new SendMessage(chatId,  nome + " vamos cadastrar sua receita!"));
                                resposta = tokenBot.execute(new SendMessage(chatId, "Para cancelar digite a qualquer momento:\ncancelar"));
                                resposta = tokenBot.execute(new SendMessage(chatId, "Insira o valor:"));
                                receita = true;
                                valorB = true;
                                cont ++;
                                System.out.println(nome +" mandou: "+update.message().text());
                                break;

                            //Cancelando o cadastro
                            case "cancelar":
                            case "Cancelar":
                            case "/cancelar":
                                resposta = tokenBot.execute(new SendMessage(chatId,  nome + " cancelamos o cadastro! \uD83D\uDE09"));
                                reiniciar();
                                System.out.println(nome +" mandou: "+update.message().text());
                                break;

                            //Cadastro realizado
                            case "sim":
                            case "Sim":
                            case "/sim":
                                if (categoriaB && gasto){
                                    resposta = tokenBot.execute(new SendMessage(chatId, "Gasto cadastrado com sucesso \uD83D\uDE0D"));
                                    reiniciar();
                                    System.out.println(nome +" mandou: "+update.message().text());
                                    break;
                                }
                                if (categoriaB && receita){
                                    resposta = tokenBot.execute(new SendMessage(chatId, "Receita cadastrado com sucesso \uD83D\uDE0D"));
                                    reiniciar();
                                    System.out.println(nome +" mandou: "+update.message().text());
                                    break;
                                }


                            default:
                                //inserindo a descrição
                                if (valorB){
                                    resposta = tokenBot.execute(new SendMessage(chatId, "Insira a descrição:"));
                                    //valor = df.format(mensagem.replace(",","."));
                                    valor = mensagem;
                                    valorB = false;
                                    descricaoB = true;
                                    cont ++;
                                    System.out.println(nome +" mandou: "+update.message().text());
                                }else
                                    //inserindo a categoria
                                    if (descricaoB){
                                        resposta = tokenBot.execute(new SendMessage(chatId,"Insira a categoria:"));
                                        descricao = mensagem;
                                        descricaoB = false;
                                        categoriaB = true;
                                        cont ++;
                                        System.out.println(nome +" mandou: "+update.message().text());
                                    }else
                                        //Confirmando os dados
                                        if (cont==3){
                                            categoria = mensagem;
                                            if (categoriaB && gasto){
                                                resposta = tokenBot.execute(new SendMessage(chatId,"Confirmar gasto? \n" +
                                                        "Valor: " + valor + "\nDescrição: " + descricao + "\nCategoria: " + categoria));
                                                resposta = tokenBot.execute(new SendMessage(chatId, "/sim\n\n/cancelar"));

                                                System.out.println(nome +" mandou: "+update.message().text());
                                            }else
                                            if (categoriaB && receita){
                                                resposta = tokenBot.execute(new SendMessage(chatId,"Confirmar receita? \n" +
                                                        "Valor: " + valor + "\nDescrição: " + descricao + "\nCategoria: " + categoria));
                                                resposta = tokenBot.execute(new SendMessage(chatId, "/sim\n\n/cancelar"));
                                                RendaController.createProducts(new RendaForm(Double.parseDouble(valor), descricao));
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
                    resposta = tokenBot.execute(new SendMessage(chatId,"Desculpe " + nome + ", não entendi. \uD83D\uDE22" +
                            "\nFica mais facil se você usar os comandos para cadastar. \n/gasto e /receita"));
                }

                return UpdatesListener.CONFIRMED_UPDATES_ALL;
            }

        };

        tokenBot.setUpdatesListener(updatesListener);
    }
}
