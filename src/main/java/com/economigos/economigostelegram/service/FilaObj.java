package com.economigos.economigostelegram.service;

public class FilaObj<T> {

    private Integer tamanho;
    private T[] fila;

    public FilaObj(Integer capacidade) {
        this.tamanho = 0;
        this.fila = (T[]) new Object[capacidade];
    }

    public Boolean isEmpty(){
        return tamanho.equals(0);
    }

    public Boolean isFull(){
        return tamanho.equals(fila.length);
    }

    public void insert(T informacao){
        if (isFull()){
            System.out.println("Fila cheia!");
        }else {
            fila[tamanho++] = informacao;
        }

    }

    public T peek(){
        return fila[0];
    }

    public T poll(){
        T primeiro = peek();
        if (!isEmpty()){
            for (int i = 0; i < tamanho - 1; i++){
                fila[i] = fila[i + 1];
            }
            fila[tamanho - 1] = null;
            tamanho --;
        }
        return primeiro;
    }

    public void exibe(){
        if (isEmpty()){
            System.out.println("Fila vazia");
        }else {
            for (int i = 0; i < tamanho; i++){
                System.out.println(fila[i]);
            }
        }
    }

    public void esvaziar(){
        while (!isEmpty()){
            peek();
        }
    }
}

