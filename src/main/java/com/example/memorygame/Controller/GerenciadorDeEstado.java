package com.example.memorygame.Controller;

import com.example.memorygame.Model.EstadoDoJogo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GerenciadorDeEstado {

    // O nome do arquivo onde o estado do jogo será salvo.
    private static final String NOME_ARQUIVO = "jogo_salvo.json";
    private final Gson gson;

    public GerenciadorDeEstado() {
        // Inicializa o Gson com a opção "pretty printing" para que o arquivo JSON
        // seja formatado de forma legível
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    /**
     * Salva o objeto de estado do jogo fornecido em um arquivo JSON.
     * Se já existir um arquivo de save, ele será sobrescrito.
     *
     * @param estado O objeto EstadoDoJogo a ser salvo.
     */
    public void salvarEstado(EstadoDoJogo estado) {
        try (FileWriter writer = new FileWriter(NOME_ARQUIVO)) {
            gson.toJson(estado, writer);
        } catch (IOException e) {
            System.err.println("Falha ao salvar o estado do jogo: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Carrega o estado do jogo a partir do arquivo JSON.
     *
     * @return Um objeto EstadoDoJogo se o arquivo existir e for lido com sucesso,
     * ou null se o arquivo não existir ou ocorrer um erro.
     */
    public EstadoDoJogo carregarEstado() {
        File arquivo = new File(NOME_ARQUIVO);
        if (!arquivo.exists()) {
            return null; // Não há jogo salvo.
        }

        try (FileReader reader = new FileReader(arquivo)) {
            return gson.fromJson(reader, EstadoDoJogo.class);
        } catch (IOException e) {
            System.err.println("Falha ao carregar o estado do jogo: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Exclui o arquivo de estado do jogo.
     * Isso deve ser chamado quando um novo jogo é iniciado ou quando um jogo é concluído,
     * para limpar o save antigo.
     */
    public void excluirEstado() {
        File arquivo = new File(NOME_ARQUIVO);
        if (arquivo.exists()) {
            if (arquivo.delete()) {
                System.out.println("Arquivo de jogo salvo foi excluído.");
            } else {
                System.err.println("Não foi possível excluir o arquivo de jogo salvo.");
            }
        }
    }
}
