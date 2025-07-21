package com.example.memorygame.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeckCartasModel {
    private ArrayList<CartaModel> baralho;

    public DeckCartasModel() {
        this.baralho = new ArrayList<>();
        List<String> naipes = CartaModel.getNaipesValidos();
        List<String> nomesDasFaces = CartaModel.getNomesDasFacesValidos();

        for (String naipe : naipes) {
            for (String nomeDaFace : nomesDasFaces) {
                baralho.add(new CartaModel(naipe, nomeDaFace));
            }
        }
    }

    /**
     * Este método embaralha as cartas.
     */
    public void embaralhar() {
        Collections.shuffle(baralho);
    }

    /**
     * Este método retorna a carta do topo do baralho.
     * Se o baralho estiver vazio, retorna null.
     */
    public CartaModel distribuirCartaDoTopo() {
        if (baralho.size() > 0)
            return baralho.remove(0);
        else
            return null;
    }

    /**
     * Retorna o número de cartas restantes no baralho.
     */
    public int getNumeroDeCartas() {
        return baralho.size();
    }
}
