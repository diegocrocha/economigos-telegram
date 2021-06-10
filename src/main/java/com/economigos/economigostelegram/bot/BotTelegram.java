package com.economigos.economigostelegram.bot;

import com.economigos.economigostelegram.controller.GastoController;
import com.economigos.economigostelegram.controller.ReceitaController;
import com.economigos.economigostelegram.dominio.Gasto;
import com.economigos.economigostelegram.dominio.Receita;
import com.economigos.economigostelegram.form.GastoForm;
import com.economigos.economigostelegram.form.ReceitaForm;
import com.economigos.economigostelegram.service.FilaObj;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;

import java.util.Calendar;
import java.util.List;

public class BotTelegram {
    static FilaObj<Receita> filaReceita = new FilaObj<>(20);
    static FilaObj<Gasto> filaGasto = new FilaObj<>(20);
    static TelegramBot tokenBot = new TelegramBot("1680012398:AAF9IkYcMyarOtXVwL4qVQn1733iUOLgtd8");
    static String mensagem, nome, email, valor = "", descricao = "", categoria = "", conta = "";
    static boolean gasto, receita= false;
    static Calendar calendar = Calendar.getInstance();
    static int frasePlural, cont = -1, hora = calendar.get(Calendar.HOUR_OF_DAY);
    static SendResponse resposta;
    static long chatId;

    public static void reiniciar(){
        gasto = false;
        receita = false;
        cont = -1;
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
                                reiniciar();
                                resposta = tokenBot.execute(new SendMessage(chatId, "Seja bem vindo(a) " + nome +
                                        ", aqui vc vai ter praticidade para cadastrar seus gastos e receitas"));
                                resposta = tokenBot.execute(new SendMessage(chatId, nome + " insira o seu email cadastrado no economigos:"));
                                cont++;
                                System.out.println(update);
                                System.out.println(nome +" mandou: "+update.message().text());
                                break;


                            case "sim":
                            case"/sim":
                                reiniciar();
                                resposta = tokenBot.execute(new SendMessage(chatId, nome + " Deseja cadastrar um gastou ou receita?"));
                                resposta = tokenBot.execute(new SendMessage(chatId, "\uD83D\uDCB5\n/receita\n\n\uD83D\uDCB8\n/gasto"));
                                break;

                            case "/gasto":
                                resposta = tokenBot.execute(new SendMessage(chatId,  nome + " vamos cadastrar seu gasto!"));
                                resposta = tokenBot.execute(new SendMessage(chatId, "Para cancelar digite a qualquer momento:\ncancelar"));
                                resposta = tokenBot.execute(new SendMessage(chatId, "Insira o valor:"));
                                gasto = true;
                                cont ++;
                                frasePlural++;
                                System.out.println(nome +" mandou: "+update.message().text());

                                break;

                            case "/receita":
                                resposta = tokenBot.execute(new SendMessage(chatId,  nome + " vamos cadastrar sua receita!"));
                                resposta = tokenBot.execute(new SendMessage(chatId, "Para cancelar digite a qualquer momento:\ncancelar"));
                                resposta = tokenBot.execute(new SendMessage(chatId, "Insira o valor:"));
                                receita = true;
                                cont ++;
                                frasePlural++;
                                System.out.println(nome +" mandou: "+update.message().text());
                                break;

                            //Cancelando o cadastro
                            case "cancelar":
                            case "/cancelar":
                                resposta = tokenBot.execute(new SendMessage(chatId,  nome + " deseja cancelar somente o ultimo ou todos?"));
                                resposta = tokenBot.execute(new SendMessage(chatId, "/ultimo\n\n/todos"));
                                System.out.println(nome +" mandou: "+update.message().text());
                                break;
                                
                            case "ultimo":
                            case "/ultimo":
                                if (receita){
                                    filaReceita.poll();
                                }
                                if (gasto){
                                    filaGasto.poll();

                                }
                                reiniciar();
                                frasePlural --;
                                resposta = tokenBot.execute(new SendMessage(chatId, nome + "Cadastro cancelado \uD83D\uDE09"));
                                resposta = tokenBot.execute(new SendMessage(chatId, nome + " Deseja cadastrar um gastou ou receita?"));
                                resposta = tokenBot.execute(new SendMessage(chatId, "\uD83D\uDCB5 \n/receita\n\n \uD83D\uDCB8 \n/gasto"));
                                break;

                            case "todos":
                            case "/todos":
                                filaReceita.esvaziar();
                                filaGasto.esvaziar();
                                reiniciar();
                                frasePlural = 0;
                                resposta = tokenBot.execute(new SendMessage(chatId, nome + " Cadastros cancelados \uD83D\uDE09"));
                                resposta = tokenBot.execute(new SendMessage(chatId, nome + " Deseja cadastrar um gastou ou receita?"));
                                resposta = tokenBot.execute(new SendMessage(chatId, "\uD83D\uDCB5 \n/receita\n\n \uD83D\uDCB8 \n/gasto"));
                                break;

                            case "confirmar":
                            case "/confirmar":
                                if (receita){
                                    Receita receita = new Receita(Double.parseDouble(valor), descricao, categoria, conta);
                                    filaReceita.insert(receita);
                                    reiniciar();
                                }
                                if (gasto){
                                    Gasto gasto = new Gasto(Double.parseDouble(valor), descricao, categoria, conta);
                                    filaGasto.insert(gasto);
                                    reiniciar();
                                }
                                resposta = tokenBot.execute(new SendMessage(chatId,"Deseja realizar mais" +
                                        " um cadastro? "));
                                resposta = tokenBot.execute(new SendMessage(chatId, "\uD83D\uDFE2 \n/sim\n\n \uD83D\uDD34 \n/nao"));
                                break;

                            //Cadastro realizado
                            case "nao":
                            case "/nao":
                                //Criando post receita
                                while (!filaReceita.isEmpty()){
                                    Receita cadastrarReceita = filaReceita.poll();
                                    ReceitaController.createProducts(new ReceitaForm(cadastrarReceita.getValor(), cadastrarReceita.getDescricao(),
                                            cadastrarReceita.getCategoria(), cadastrarReceita.getConta()));

                                }

                                //Criando post gasto
                                while (!filaGasto.isEmpty()){
                                    Gasto cadastrarGasto = filaGasto.poll();
                                    GastoController.createProducts(new GastoForm(cadastrarGasto.getValor(), cadastrarGasto.getDescricao(),
                                            cadastrarGasto.getCategoria(), cadastrarGasto.getConta()));
                                }
                                if (frasePlural > 1){
                                    resposta = tokenBot.execute(new SendMessage(chatId, "Os cadastros foram realizados com sucesso \uD83D\uDE0D"));
                                }else {
                                    resposta = tokenBot.execute(new SendMessage(chatId, "Cadastro realizado com sucesso \uD83D\uDE0D"));
                                }
                                reiniciar();
                                frasePlural = 0;

                                System.out.println(nome +" mandou: "+update.message().text());
                                break;


                            default:
                                //validando usuario
                                if(cont==0){
                                    resposta = tokenBot.execute(new SendMessage(chatId, "\uD83D\uDCB5 \n/receita\n\n \uD83D\uDCB8 \n/gasto"));
                                    email = mensagem;
                                    cont ++;
                                    System.out.println(nome +" mandou: "+update.message().text());
                                }
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
                                                    resposta = tokenBot.execute(new SendMessage(chatId, "\uD83D\uDFE2 \n/confirmar" +
                                                            "\n\n \uD83D\uDD34 \n/cancelar"));
                                                    System.out.println(nome +" mandou: "+update.message().text());
                                                }else
                                                if (receita){
                                                    resposta = tokenBot.execute(new SendMessage(chatId,"Confirmar receita? " +
                                                            "\nValor: " + valor + "\nDescrição: " + descricao + "\nCategoria: " +
                                                            categoria + "\nConta: " + conta));
                                                    resposta = tokenBot.execute(new SendMessage(chatId, "\uD83D\uDFE2 \n/confirmar" +
                                                            "\n\n \uD83D\uDD34 \n/cancelar"));
                                                    cont ++;
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
