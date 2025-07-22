package com.example.memorygame.Controller;

import com.example.memorygame.Model.EstadoDoJogo;
import com.example.memorygame.Model.ResultadoPartida;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class MenuController {
    @FXML private VBox caixaUltimaPontuacao;
    @FXML private Label rotuloUltimaPontuacao;
    @FXML private GridPane gradeNomesJogadores;
    @FXML private TextField campoTextoJogador1;
    @FXML private TextField campoTextoJogador2;

    private String modoDeJogo = "um_jogador";

    private GerenciadorDePontuacoes gerenciadorDePontuacoes = new GerenciadorDePontuacoes();
    private GerenciadorDeEstado gerenciadorDeEstado = new GerenciadorDeEstado();

    @FXML
    public void initialize() {
        EstadoDoJogo estadoSalvo = gerenciadorDeEstado.carregarEstado();
        if (estadoSalvo != null) {
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
            alerta.setTitle("Jogo Salvo Encontrado");
            alerta.setHeaderText("Você tem um jogo não terminado.");
            alerta.setContentText("Deseja continuar de onde parou?");

            ButtonType botaoSim = new ButtonType("Sim, continuar");
            ButtonType botaoNao = new ButtonType("Não, começar novo jogo");
            alerta.getButtonTypes().setAll(botaoSim, botaoNao);

            Optional<ButtonType> resultado = alerta.showAndWait();
            if (resultado.isPresent() && resultado.get() == botaoSim) {
                // Diz ao JavaFX para rodar este código um pouco depois,
                Platform.runLater(() -> {
                    try {
                        continuarJogo(estadoSalvo);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            } else {
                // Se escolher "Não" ou fechar o alerta, apaga o save e exibe o menu normalmente
                gerenciadorDeEstado.excluirEstado();
                carregarUltimaPontuacao();
            }
        } else {
            // Se não há jogo salvo, apenas carrega o histórico de placares
            carregarUltimaPontuacao();
        }
    }

    private void carregarUltimaPontuacao() {
        List<ResultadoPartida> resultadosAnteriores = gerenciadorDePontuacoes.carregarResultados();
        if (!resultadosAnteriores.isEmpty()) {
            ResultadoPartida ultimoResultado = resultadosAnteriores.get(resultadosAnteriores.size() - 1);
            caixaUltimaPontuacao.setVisible(true);
            rotuloUltimaPontuacao.setText(String.format("%s: %d  x  %s: %d",
                    ultimoResultado.getNomeJogador1(), ultimoResultado.getPontosJogador1(),
                    ultimoResultado.getNomeJogador2(), ultimoResultado.getPontosJogador2()));
        }
    }

    @FXML
    void botaoUmJogador_clique() {
        modoDeJogo = "um_jogador";
        gradeNomesJogadores.setVisible(false);
    }

    @FXML
    void botaoMultijogador_clique() {
        modoDeJogo = "1v1";
        gradeNomesJogadores.setVisible(true);
    }

    @FXML
    void iniciarJogo(ActionEvent event) throws IOException {
        String nomeJ1 = campoTextoJogador1.getText().isEmpty() ? "Jogador 1" : campoTextoJogador1.getText();
        String nomeJ2 = campoTextoJogador2.getText().isEmpty() ? "Jogador 2" : campoTextoJogador2.getText();

        FXMLLoader carregador = new FXMLLoader(getClass().getResource("/com/example/memorygame/memory-game.fxml"));
        Scene cena = new Scene(carregador.load());

        MemoryGameController controladorJogo = carregador.getController();

        // Pega o Stage (a janela) a partir do evento do botão
        Stage palco = (Stage)((Node)event.getSource()).getScene().getWindow();

        controladorJogo.inicializarJogo(modoDeJogo, nomeJ1, nomeJ2, this, palco);

        palco.setScene(cena);
        palco.setTitle("Jogo da Memória - Em Jogo");
        palco.show();
    }

    private void continuarJogo(EstadoDoJogo estado) throws IOException {
        FXMLLoader carregador = new FXMLLoader(getClass().getResource("/com/example/memorygame/memory-game.fxml"));
        Scene cena = new Scene(carregador.load());

        MemoryGameController controladorJogo = carregador.getController();

        Stage palco = (Stage) gradeNomesJogadores.getScene().getWindow();

        controladorJogo.carregarJogoSalvo(estado, this, palco);

        palco.setScene(cena);
        palco.setTitle("Jogo da Memória - Continuando Jogo");
        palco.show();
    }

    /**
     * Este método é chamado pelo ControladorJogoMemoria quando um jogo 1v1 termina.
     */
    public void atualizarPontuacoes(String nomeJ1, int pontosJ1, String nomeJ2, int pontosJ2) {
        caixaUltimaPontuacao.setVisible(true);
        rotuloUltimaPontuacao.setText(String.format("%s: %d  x  %s: %d", nomeJ1, pontosJ1, nomeJ2, pontosJ2));

        List<ResultadoPartida> resultados = gerenciadorDePontuacoes.carregarResultados();
        ResultadoPartida novoResultado = new ResultadoPartida(nomeJ1, pontosJ1, nomeJ2, pontosJ2);
        resultados.add(novoResultado);
        gerenciadorDePontuacoes.salvarResultados(resultados);
    }
}