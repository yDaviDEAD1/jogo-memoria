package com.example.memorygame.Controller;

import com.example.memorygame.Model.CartaMemoriaModel;
import com.example.memorygame.Model.CartaModel;
import com.example.memorygame.Model.DeckCartasModel;
import com.example.memorygame.Model.EstadoDoJogo;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class MemoryGameController {

    @FXML private VBox caixaJogador1;
    @FXML private VBox caixaJogador2;
    @FXML private Label rotuloNomeJogador1;
    @FXML private Label rotuloPontosJogador1;
    @FXML private Label rotuloNomeJogador2;
    @FXML private Label rotuloPontosJogador2;
    @FXML private Label rotuloVez;
    @FXML private Button botaoJogarNovamente;
    @FXML private GridPane gradeDeImagens;

    private ArrayList<CartaMemoriaModel> cartasNoJogo;
    private CartaMemoriaModel primeiraCarta, segundaCarta;
    private Image imagemVersoCarta;
    private String modoDeJogo;
    private MenuController controladorDoMenu;
    private int jogadorAtual;
    private String nomeJogador1, nomeJogador2;
    private int pontosJogador1, pontosJogador2;

    private Stage palco; // Referência à janela principal do jogo
    private final GerenciadorDeEstado gerenciadorDeEstado = new GerenciadorDeEstado();

    /**
     * Prepara um jogo completamente novo.
     * @param modoDeJogo "um_jogador" ou "1v1"
     * @param nomeJ1 Nome do jogador 1
     * @param nomeJ2 Nome do jogador 2
     * @param controladorDoMenu Referência ao controlador do menu para voltar
     * @param palco A janela (Stage) principal do jogo
     */
    public void inicializarJogo(String modoDeJogo, String nomeJ1, String nomeJ2, MenuController controladorDoMenu, Stage palco) {
        this.controladorDoMenu = controladorDoMenu;
        this.palco = palco;
        this.imagemVersoCarta = new Image(getClass().getResourceAsStream("/com/example/memorygame/carta_de_tras.png"));

        configurarSalvamentoAoFechar();
        inicializarVisualizadoresImagem();

        // Inicia um jogo completamente novo com as configurações passadas
        iniciarNovoJogo(nomeJ1, nomeJ2, modoDeJogo);
    }

    /**
     * Carrega um jogo a partir de um estado previamente salvo.
     * @param estado O objeto contendo todas as informações do jogo salvo
     * @param controladorDoMenu Referência ao controlador do menu
     * @param palco A janela (Stage) principal do jogo
     */
    public void carregarJogoSalvo(EstadoDoJogo estado, MenuController controladorDoMenu, Stage palco) {
        this.cartasNoJogo = estado.getCartasNoJogo();
        this.modoDeJogo = estado.getModoDeJogo();
        this.nomeJogador1 = estado.getNomeJogador1();
        this.pontosJogador1 = estado.getPontosJogador1();
        this.nomeJogador2 = estado.getNomeJogador2();
        this.pontosJogador2 = estado.getPontosJogador2();
        this.jogadorAtual = estado.getJogadorAtual();


        this.controladorDoMenu = controladorDoMenu;
        this.palco = palco;
        this.imagemVersoCarta = new Image(getClass().getResourceAsStream("/com/example/memorygame/carta_de_tras.png"));

        configurarSalvamentoAoFechar();
        inicializarVisualizadoresImagem();

        for (int i = 0; i < cartasNoJogo.size(); i++) {
            ImageView visualizadorImagem = (ImageView) gradeDeImagens.getChildren().get(i);
            CartaMemoriaModel carta = cartasNoJogo.get(i);
            if (carta.foiCombinada()) {
                visualizadorImagem.setImage(carta.getImagem());
            } else {
                visualizadorImagem.setImage(imagemVersoCarta);
            }
        }

        gradeDeImagens.setDisable(false);
        atualizarRotulos();
    }

    /**
     * Ação do botão "Jogar Novamente" do FXML.
     * Reinicia o jogo com as mesmas configurações da partida atual.
     */
    @FXML
    void botaoJogarNovamentePressionado(ActionEvent event) {
        iniciarNovoJogo(this.nomeJogador1, this.nomeJogador2, this.modoDeJogo);
    }

    /**
     * Lógica central para iniciar uma partida do zero.
     */
    private void iniciarNovoJogo(String nomeJ1, String nomeJ2, String modoDeJogo) {
        gerenciadorDeEstado.excluirEstado(); // Apaga qualquer save anterior

        this.nomeJogador1 = nomeJ1;
        this.nomeJogador2 = nomeJ2;
        this.modoDeJogo = modoDeJogo;
        this.primeiraCarta = null;
        this.segundaCarta = null;
        this.pontosJogador1 = 0;
        this.pontosJogador2 = 0;
        this.jogadorAtual = 1;
        this.gradeDeImagens.setDisable(false);

        DeckCartasModel baralho = new DeckCartasModel();
        baralho.embaralhar();
        cartasNoJogo = new ArrayList<>();

        int numPares = gradeDeImagens.getChildren().size() / 2;
        for (int i = 0; i < numPares; i++) {
            CartaModel cartaDistribuida = baralho.distribuirCartaDoTopo();
            cartasNoJogo.add(new CartaMemoriaModel(cartaDistribuida.getNaipe(), cartaDistribuida.getNomeDaFace()));
            cartasNoJogo.add(new CartaMemoriaModel(cartaDistribuida.getNaipe(), cartaDistribuida.getNomeDaFace()));
        }
        Collections.shuffle(cartasNoJogo);

        for (int i = 0; i < cartasNoJogo.size(); i++) {
            cartasNoJogo.get(i).setCombinada(false);
            ImageView visualizadorImagem = (ImageView) gradeDeImagens.getChildren().get(i);
            visualizadorImagem.setImage(imagemVersoCarta);
        }
        atualizarRotulos();
    }

    private void inicializarVisualizadoresImagem() {
        for (int i = 0; i < gradeDeImagens.getChildren().size(); i++) {
            ImageView visualizadorImagem = (ImageView) gradeDeImagens.getChildren().get(i);
            visualizadorImagem.setUserData(i);
            visualizadorImagem.setOnMouseClicked(mouseEvent -> {
                if (primeiraCarta == null || segundaCarta == null) {
                    virarCarta((int) visualizadorImagem.getUserData());
                }
            });
        }
    }

    private void virarCarta(int indiceDaCarta) {
        if (segundaCarta != null) return;

        ImageView visualizadorImagem = (ImageView) gradeDeImagens.getChildren().get(indiceDaCarta);

        if (cartasNoJogo.get(indiceDaCarta).foiCombinada() || visualizadorImagem.getImage() != imagemVersoCarta) {
            return;
        }

        if (primeiraCarta == null) {
            primeiraCarta = cartasNoJogo.get(indiceDaCarta);
            visualizadorImagem.setImage(primeiraCarta.getImagem());
        } else {
            segundaCarta = cartasNoJogo.get(indiceDaCarta);
            visualizadorImagem.setImage(segundaCarta.getImagem());
            verificarCombinacao();
        }
    }

    private void atualizarRotulos() {
        if (modoDeJogo.equals("1v1")) {
            rotuloNomeJogador1.setText(nomeJogador1);
            rotuloNomeJogador2.setText(nomeJogador2);
            rotuloPontosJogador1.setText(Integer.toString(pontosJogador1));
            rotuloPontosJogador2.setText(Integer.toString(pontosJogador2));
            rotuloVez.setText("Vez de: " + (jogadorAtual == 1 ? nomeJogador1 : nomeJogador2));
            caixaJogador1.setVisible(true);
            caixaJogador2.setVisible(true);
        } else { // Modo um jogador
            rotuloNomeJogador1.setText("Pontos");
            rotuloPontosJogador1.setText(Integer.toString(pontosJogador1));
            rotuloVez.setText("Jogo da Memória");
            caixaJogador2.setVisible(false);
        }
    }

    private void verificarCombinacao() {
        if (primeiraCarta.eMesmaCarta(segundaCarta)) {
            primeiraCarta.setCombinada(true);
            segundaCarta.setCombinada(true);

            if (modoDeJogo.equals("1v1")) {
                if (jogadorAtual == 1) pontosJogador1++;
                else pontosJogador2++;
            } else {
                pontosJogador1++;
            }

            primeiraCarta = null;
            segundaCarta = null;
            atualizarRotulos();
            verificarFimDeJogo();
        } else {
            gradeDeImagens.setDisable(true);
            PauseTransition pausa = new PauseTransition(Duration.seconds(1));
            pausa.setOnFinished(event -> {
                int primeiroIndice = cartasNoJogo.indexOf(primeiraCarta);
                int segundoIndice = cartasNoJogo.indexOf(segundaCarta);
                ((ImageView) gradeDeImagens.getChildren().get(primeiroIndice)).setImage(imagemVersoCarta);
                ((ImageView) gradeDeImagens.getChildren().get(segundoIndice)).setImage(imagemVersoCarta);

                if (modoDeJogo.equals("1v1")) {
                    jogadorAtual = (jogadorAtual == 1) ? 2 : 1;
                }

                primeiraCarta = null;
                segundaCarta = null;
                gradeDeImagens.setDisable(false);
                atualizarRotulos();
            });
            pausa.play();
        }
    }

    private void verificarFimDeJogo() {
        int numPares = gradeDeImagens.getChildren().size() / 2;
        int pontuacaoTotal = modoDeJogo.equals("1v1") ? pontosJogador1 + pontosJogador2 : pontosJogador1;

        if (pontuacaoTotal == numPares) {
            gerenciadorDeEstado.excluirEstado(); // APAGA o save porque o jogo acabou

            String mensagem;
            if (modoDeJogo.equals("1v1")) {
                if (pontosJogador1 > pontosJogador2) mensagem = nomeJogador1 + " venceu!";
                else if (pontosJogador2 > pontosJogador1) mensagem = nomeJogador2 + " venceu!";
                else mensagem = "Empate!";
            } else {
                mensagem = "Você encontrou todos os pares!";
            }

            Alert alerta = new Alert(Alert.AlertType.INFORMATION, mensagem);
            alerta.setTitle("Fim de Jogo");
            alerta.setHeaderText("Parabéns!");

            alerta.setOnHidden(evt -> {
                try {
                    FXMLLoader carregador = new FXMLLoader(getClass().getResource("/com/example/memorygame/menu-view.fxml"));
                    Scene cena = new Scene(carregador.load());

                    MenuController novoControladorMenu = carregador.getController();
                    if (modoDeJogo.equals("1v1")) {
                        novoControladorMenu.atualizarPontuacoes(nomeJogador1, pontosJogador1, nomeJogador2, pontosJogador2);
                    }

                    Stage palcoAtual = (Stage) botaoJogarNovamente.getScene().getWindow();
                    palcoAtual.setScene(cena);
                    palcoAtual.setTitle("Jogo da Memória - Menu");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            alerta.show();
        }
    }

    /**
     * Salva o estado atual do jogo em um arquivo JSON.
     */
    private void salvarEstadoAtual() {
        // Não salva se o jogo já terminou
        int pontuacaoTotal = modoDeJogo.equals("1v1") ? pontosJogador1 + pontosJogador2 : pontosJogador1;
        // SE a pontuação total for igual ao número de pares... (ou seja, SE o jogo terminou)
        if (pontuacaoTotal == gradeDeImagens.getChildren().size() / 2) {
            gerenciadorDeEstado.excluirEstado();
            return;
        }

        EstadoDoJogo estado = new EstadoDoJogo();
        estado.setCartasNoJogo(this.cartasNoJogo);
        estado.setModoDeJogo(this.modoDeJogo);
        estado.setNomeJogador1(this.nomeJogador1);
        estado.setPontosJogador1(this.pontosJogador1);
        estado.setNomeJogador2(this.nomeJogador2);
        estado.setPontosJogador2(this.pontosJogador2);
        estado.setJogadorAtual(this.jogadorAtual);

        gerenciadorDeEstado.salvarEstado(estado);
        System.out.println("Jogo salvo!");
    }

    /**
     * Configura um listener para o evento de fechamento da janela,
     * que aciona o método para salvar o estado do jogo.
     */
    private void configurarSalvamentoAoFechar() {
        if (this.palco != null) {
            //serve para interceptar a acao de fechar do usuario, ai executar o metodo de salvar o estado
            this.palco.setOnCloseRequest(event -> {
                System.out.println("Janela fechando, salvando estado...");
                salvarEstadoAtual();
            });
        }
    }
}