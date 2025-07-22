# jogo-memoria
Jogo da Memória em JavaFX
Bem-vindo ao projeto Jogo da Memória! Esta é uma aplicação de desktop clássica, desenvolvida com Java e o framework JavaFX. O jogo oferece uma interface gráfica limpa e funcionalidades modernas, como salvamento automático e histórico de pontuações.

Visão Geral
O projeto foi criado como uma demonstração prática das capacidades do JavaFX, implementando um jogo completo com diferentes modos, gerenciamento de estado e uma arquitetura de software bem definida (MVC).

Tela de Menu
(Nota: Para exibir as imagens, tire screenshots do seu aplicativo, adicione-as a uma pasta assets ou screenshots no seu repositório e substitua os links abaixo.)

Tela de Jogo
✨ Funcionalidades
🎮 Dois Modos de Jogo: Jogue sozinho para testar sua memória ou compita contra um amigo no modo 1 vs 1.

💾 Salvamento Automático: Fechou o jogo sem querer? Sem problemas! A aplicação salva seu progresso automaticamente, permitindo que você continue de onde parou.

📊 Histórico de Pontuações: Todas as partidas 1 vs 1 têm seus resultados salvos. O placar da última partida é exibido no menu principal.

🧑‍🤝‍🧑 Nomes de Jogadores Personalizáveis: No modo 1 vs 1, os jogadores podem inserir seus nomes para uma experiência mais personalizada.

✨ Interface Gráfica Intuitiva: Uma interface limpa e responsiva, criada com FXML e estilizada com CSS.

🛠️ Tecnologias Utilizadas
Java 11+

JavaFX 17+

Gson 2.8+ (Para manipulação de dados JSON)

FXML (Para estruturação da interface gráfica)

CSS (Para estilização visual)

🚀 Como Executar o Projeto
Para executar este projeto em sua máquina local, siga os passos abaixo.

Pré-requisitos
JDK (Java Development Kit) - Versão 11 ou superior.

JavaFX SDK - Versão 17 ou superior. Faça o download aqui.

Uma IDE de sua preferência (Eclipse, IntelliJ IDEA, VS Code com extensões Java).

Passos para Configuração
Clone o repositório:

Bash

git clone https://github.com/yDaviDEAD1/jogo-memoria.git
Abra na sua IDE:

Importe o projeto como um projeto Java padrão.

Configure o JavaFX:

Adicione o JavaFX SDK como uma biblioteca ao seu projeto.

Configure as Opções de VM (VM Options): Na sua configuração de execução (Run Configuration), adicione as seguintes opções, substituindo /path/to/your/javafx-sdk/lib pelo caminho real para a pasta lib do seu JavaFX SDK:

--module-path /path/to/your/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml
Execute:
