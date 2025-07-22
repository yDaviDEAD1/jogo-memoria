package com.example.memorygame.Model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Representa o resultado final de uma única partida 1v1.
 * Esta classe é usada para salvar um histórico de placares em um arquivo JSON.
 */
public class ResultadoPartida {

    private String nomeJogador1;
    private int pontosJogador1;
    private String nomeJogador2;
    private int pontosJogador2;
    private String dataHora;

    /**
     * Construtor padrão vazio, necessário para a biblioteca Gson.
     */
    public ResultadoPartida() {
    }

    /**
     * Cria um novo registro de resultado.
     * Captura automaticamente a data e hora atuais.
     *
     * @param nomeJ1   Nome do Jogador 1
     * @param pontosJ1 Pontuação do Jogador 1
     * @param nomeJ2   Nome do Jogador 2
     * @param pontosJ2 Pontuação do Jogador 2
     */
    public ResultadoPartida(String nomeJ1, int pontosJ1, String nomeJ2, int pontosJ2) {
        this.nomeJogador1 = nomeJ1;
        this.pontosJogador1 = pontosJ1;
        this.nomeJogador2 = nomeJ2;
        this.pontosJogador2 = pontosJ2;

        // Formata a data e hora atuais para um padrão legível (ex: 18/07/2025 16:47:10)
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        this.dataHora = LocalDateTime.now().format(formatador);
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

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    /**
     * Retorna uma representação em String do resultado, útil para logs e debug.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s: %d vs %s: %d",
                dataHora, nomeJogador1, pontosJogador1, nomeJogador2, pontosJogador2);
    }

}