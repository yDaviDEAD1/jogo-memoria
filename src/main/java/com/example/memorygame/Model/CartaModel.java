package com.example.memorygame.Model;

import javafx.scene.image.Image;

import java.util.Arrays;
import java.util.List;

public class CartaModel {
    private String naipe;
    private String nomeDaFace;

    public CartaModel(String naipe, String nomeDaFace) {
        setNaipe(naipe);
        setNomeDaFace(nomeDaFace);
    }

    public String getNaipe() {
        return naipe;
    }

    public static List<String> getNaipesValidos() {
        return Arrays.asList("hearts", "diamonds", "clubs", "spades");
    }

    public void setNaipe(String naipe) {
        naipe = naipe.toLowerCase();
        if (getNaipesValidos().contains(naipe))
            this.naipe = naipe;
        else
            throw new IllegalArgumentException(naipe + " é inválido, deve ser um de " + getNaipesValidos());
    }

    public String getNomeDaFace() {
        return nomeDaFace;
    }

    public static List<String> getNomesDasFacesValidos() {
        return Arrays.asList("2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king", "ace");
    }

    public void setNomeDaFace(String nomeDaFace) {
        nomeDaFace = nomeDaFace.toLowerCase();
        if (getNomesDasFacesValidos().contains(nomeDaFace))
            this.nomeDaFace = nomeDaFace;
        else
            throw new IllegalArgumentException(nomeDaFace + " é inválido, deve ser um de " + getNomesDasFacesValidos());
    }

    @Override
    public String toString() {
        return nomeDaFace + " de " + naipe;
    }

    public Image getImagem() {
        String nomeArquivo = "/com/example/memorygame/images/" + this.nomeDaFace + "_of_" + this.naipe + ".png";
        return new Image(getClass().getResourceAsStream(nomeArquivo));
    }
    /**
     * Este método retorna a Imagem que representa o verso da carta.
     */
    public Image getImagemVersoCarta() {
        String nomeArquivo = "/com/example/memorygame/carta_de_tras.png";
        return new Image(getClass().getResourceAsStream(nomeArquivo));
    }
}
