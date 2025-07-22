package com.example.memorygame.Model;

import java.util.ArrayList;

public class EstadoDoJogo {

    // A lista de cartas na mesa, incluindo seu estado (combinada ou não)
    private ArrayList<CartaMemoriaModel> cartasNoJogo;

    private String nomeJogador1;
    private int pontosJogador1;
    private String nomeJogador2;
    private int pontosJogador2;

    private int jogadorAtual;
    private String modoDeJogo;

    /**
     * Construtor padrão vazio.
     * É essencial para que bibliotecas como Gson possam instanciar o objeto
     * ao converter o JSON de volta para um objeto Java.
     */
    public EstadoDoJogo() {
    }



    public ArrayList<CartaMemoriaModel> getCartasNoJogo() {
        return cartasNoJogo;
    }

    public void setCartasNoJogo(ArrayList<CartaMemoriaModel> cartasNoJogo) {
        this.cartasNoJogo = cartasNoJogo;
    }

    public String getNomeJogador1() {
        return nomeJogador1;
    }

    public void setNomeJogador1(String nomeJogador1) {
        this.nomeJogador1 = nomeJogador1;
    }

    public int getPontosJogador1() {
        return pontosJogador1;
    }

    public void setPontosJogador1(int pontosJogador1) {
        this.pontosJogador1 = pontosJogador1;
    }

    public String getNomeJogador2() {
        return nomeJogador2;
    }

    public void setNomeJogador2(String nomeJogador2) {
        this.nomeJogador2 = nomeJogador2;
    }

    public int getPontosJogador2() {
        return pontosJogador2;
    }

    public void setPontosJogador2(int pontosJogador2) {
        this.pontosJogador2 = pontosJogador2;
    }

    public int getJogadorAtual() {
        return jogadorAtual;
    }

    public void setJogadorAtual(int jogadorAtual) {
        this.jogadorAtual = jogadorAtual;
    }

    public String getModoDeJogo() {
        return modoDeJogo;
    }

    public void setModoDeJogo(String modoDeJogo) {
        this.modoDeJogo = modoDeJogo;
    }

}
