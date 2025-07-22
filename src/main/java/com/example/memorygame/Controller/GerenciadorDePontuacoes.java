package com.example.memorygame.Controller;

import com.example.memorygame.Model.ResultadoPartida;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por gerenciar a persistência do histórico de pontuações.
 * Ela lida com a leitura e escrita de uma lista de resultados de partidas
 * em um arquivo JSON.
 */
public class GerenciadorDePontuacoes {

    // O nome do arquivo onde o histórico de placares será salvo.
    private static final String NOME_ARQUIVO = "pontuacoes.json";
    private final Gson gson;

    public GerenciadorDePontuacoes() { //serve para deixar o json legível
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    /**
     * Carrega a lista de resultados do arquivo JSON.
     * Se o arquivo não existir ou estiver vazio, retorna uma lista nova e vazia.
     *
     * @return Uma List<ResultadoPartida> com todos os resultados salvos.
     */
    public List<ResultadoPartida> carregarResultados() {
        try (FileReader reader = new FileReader(NOME_ARQUIVO)) {
            Type tipoListaResultados = new TypeToken<ArrayList<ResultadoPartida>>(){}.getType();
            
            List<ResultadoPartida> resultados = gson.fromJson(reader, tipoListaResultados);
            
            // Se o arquivo estiver vazio, gson.fromJson retorna null.
            if (resultados == null) {
                return new ArrayList<>();
            } else {
                return resultados;
            }

        } catch (IOException e) {
            // Se o arquivo não for encontrado (ex: na primeira vez que o jogo roda),
            // simplesmente retorna uma nova lista vazia.
            return new ArrayList<>();
        }
    }

    /**
     * Salva uma lista completa de resultados no arquivo JSON,
     * sobrescrevendo qualquer conteúdo anterior.
     *
     * @param resultados A lista de resultados a ser salva.
     */
    public void salvarResultados(List<ResultadoPartida> resultados) {
        try (FileWriter writer = new FileWriter(NOME_ARQUIVO)) {
            gson.toJson(resultados, writer);
        } catch (IOException e) {
            System.err.println("Erro ao salvar o arquivo de pontuações: " + e.getMessage());
            e.printStackTrace();
        }
    }
}