package com.example.memorygame.Model;

public class CartaMemoriaModel extends CartaModel {
    private boolean combinada;

    public CartaMemoriaModel(String naipe, String nomeDaFace) {
        super(naipe, nomeDaFace);
        this.combinada = false;
    }

    public boolean foiCombinada() {
        return combinada;
    }

    public void setCombinada(boolean combinada) {
        this.combinada = combinada;
    }

    /**
     * Este método retorna true se as duas Cartas de Memória
     * tiverem o mesmo naipe e nome de face.
     */
    public boolean eMesmaCarta(CartaMemoriaModel outraCarta) {
        return (this.getNaipe().equals(outraCarta.getNaipe()) &&
                (this.getNomeDaFace().equals(outraCarta.getNomeDaFace())));
    }
}
