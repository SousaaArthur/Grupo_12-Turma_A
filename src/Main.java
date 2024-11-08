import javax.swing.*; // Importa todas as classes do pacote javax.swing
import java.awt.*; // Importa todas as classes do pacote java.awt
import java.awt.event.KeyEvent; // Importa a classe KeyEvent do pacote java.awt.event
import java.net.URL;
import java.util.ArrayList; // Importa a classe ArrayList do pacote java.util

public class Main {
    public static void main(String[] args) {
        new GameWindow();
    }

    public static class GameWindow extends JFrame {
        JTextField inputTextField;
        JTextArea outputTextArea;
        String currentGameState;
        RpgComponent.MusicPlayer musicPlayer;

        RpgComponent rpgComponent = new RpgComponent();

        // Metodo para inicializar a tela do RPG
        public GameWindow(){
            setTitle("The Age Of Etheris");
            setVisible(true);
            setSize(800, 600);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Fecha o programa ao fechar a janela
            setResizable(false);  // Impede o redimensionamento da janela
            setLocationRelativeTo(null);
            getContentPane().setBackground(Color.BLACK);
            setLayout(null);

            // Exibir texto na tela
            outputTextArea = new JTextArea();
            outputTextArea.setFont(new Font("Courier New", Font.PLAIN, 18));
            outputTextArea.setForeground(Color.green);
            outputTextArea.setBackground(Color.black);
            outputTextArea.setLineWrap(true);
            outputTextArea.setWrapStyleWord(true);
            outputTextArea.setEditable(false);
            add(outputTextArea);

            JScrollPane scroll = new JScrollPane(outputTextArea);
            scroll.setBounds(0, 0, 800, 520);
            scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS); // Adiciona barra de rolagem
            scroll.setBorder(null);
            add(scroll);

            showMainMenu();

            // Cria uma caixa de texto
            inputTextField = new JTextField();
            inputTextField.setBounds(0, 520, 600, 40);
            inputTextField.setFont(new Font("Courier New", Font.PLAIN, 20));
            inputTextField.setForeground(Color.green);
            inputTextField.setBackground(Color.black);
            inputTextField.setCaretColor(Color.green);
            inputTextField.setBorder(BorderFactory.createLineBorder(Color.green));
            add(inputTextField);

            // Adicionando KeyListener para enviar o texto ao pressionar Enter
            inputTextField.addKeyListener(new java.awt.event.KeyAdapter() { // Adiciona um evento de teclado
                public void keyPressed(java.awt.event.KeyEvent evt) { // Função para verificar se a tecla pressionada é Enter
                    if (evt.getKeyCode() == KeyEvent.VK_ENTER) { // Verifica se a tecla pressionada é Enter
                        String mainInput = inputTextField.getText();
                        inputTextField.setText("");
                        handleUserInput(mainInput);
                    }
                }
            });

            // Cria um botão de enviar
            JButton btnSend = new JButton("Enviar");
            btnSend.setBounds(600, 520, 183, 40);
            btnSend.setFont(new Font("Courier New", Font.PLAIN, 20));
            btnSend.setForeground(Color.green);
            btnSend.setBackground(Color.black);
            btnSend.setBorder(BorderFactory.createLineBorder(Color.green));
            add(btnSend);

            btnSend.addActionListener(e -> { // Função lambda para enviar a mensagem
                String mainInput = inputTextField.getText();
                inputTextField.setText("");
                handleUserInput(mainInput);
            });

            repaint(); // Redesenha a tela
            revalidate(); // Revalida a tela
            
            // Instanciando RpgComponent e MusicPlayer
            RpgComponent rpgComponent = new RpgComponent(); // Instanciando o RpgComponent
            URL url = getClass().getResource("/Sounds/musicMenu.wav"); // URL do arquivo de música
            musicPlayer = rpgComponent.new MusicPlayer(url.getPath()); // Instanciando o musicPlayer

            // Inicia a música
            musicPlayer.play();
        }

        void autoScroll() {
            outputTextArea.setCaretPosition(outputTextArea.getDocument().getLength());
        }
        // Métodos para exibir menu
        void showMainMenu() {
            URL soundSelect = getClass().getResource("/Sounds/SoundsEffect/soundSelect.wav");
            outputTextArea.setText(
                    "THE AGE OF ETHERIS\n\n" +
                            "Selecione uma opção:\n" +
                            "1. Instruções\n" +
                            "2. Jogar\n" +
                            "3. Créditos\n" +
                            "4. Sair\n"
            );
            currentGameState = "menu"; // Definindo o estado inicial como "menu"
            if (soundSelect != null) {
                rpgComponent.new MusicPlayer(soundSelect.getPath()).playOnce(soundSelect.getPath());
            } else {
                System.out.println("Efeito sonoro não encontrado!");
            }
        }

        // Metodo para receber a entrada do usuário
        void handleUserInput(String input){
            switch (currentGameState) {
                case "menu":
                    handleMainMenuInput(input);
                    break;
                case "instructions":
                    handleInstructionsInput(input);
                    break;
                case "arithmeticSystem":
                    arithmeticSystemInput(input);
                    break;
                case "battleSystem":
                    battleSystemInput(input);
                    break;
                case "credits":
                    showMainMenu();
                    break;
                case "restartGame":
                    restartGame(input);
                    break;
                case "initialHistory":
                    chooseCharacterClass(input);
                    break;
                case "playerName":
                    confirmPlayerName(input);
                    break;
                case "questionName":
                    handleNameConfirmation(input);
                    break;
                case "dialogo_ato1_cena2":
                    processCharacterDialogInput(input);
                    break;
                case "ato1_cena2":
                    entradaAto1Cena2(input);
                break;
                case "ato1_cena3":
                    entradaAto1Cena3(input);
                break;
                case "option2Ato1Cena4":
                    option2Ato1Cena4(input);
                break;
                case "opcaoFugir2Ato1Cena5":
                    confirmToEscape(input);
                    break;
                case "option2Ato1Cena6":
                    escapeEnemy("inputEscapeAto1Cena6");
                    break;
                case "inputEscapeAto1Cena6":
                    inputEscapeEnemy(input, "questionEscape01");
                    break;
                case "questionEscape01":
                    verifyQuestionEscape(input,
                            "Narrador:\n" +
                                    "Com um esforço final, você resolve o enigma e escapa da Floresta das Almas Perdidas. " +
                                    "A névoa se dissipa, e a escuridão ao seu redor se desfaz, revelando um caminho claro à sua frente. " +
                                    "O Guardião Espectral, frustrado por sua fuga, desaparece nas sombras, mas sua presença assombrosa permanece, lembrando-o de que a floresta não esquece aqueles que a desafiam.\n\n" +
                                    "Agora, você está livre para continuar sua jornada até Arcadelis, mas a lembrança da Floresta das Almas Perdidas permanecerá com você, testemunha silenciosa de sua coragem e determinação.\n" +
                                    "1. Continuar", "entradaProximaCena", "opcaoLutarAto1Cena5"
                            );
                    break;
                case "opcaoLutarAto1Cena5":
                    batalharGuardiaoEspectral();
                    break;
                case "batalharGuardiãoEspectral":
                    battleInput(input, "verificaRespostaAto1Cena5");
                    break;
                case "verificaRespostaAto1Cena5":
                    verifyQuestion(input, "batalharGuardiãoEspectral", "entradaProximaCena",
                            "Ao derrotar o Guardião Espectral, a névoa ao seu redor começa a dissipar-se, e a escuridão da floresta dá lugar a um brilho pálido e sereno. " +
                                    "Uma força nova e vigorosa flui em você, fortalecendo seu corpo e espírito. Você agora sente que está mais preparado para o que a jornada até Arcadelis exigirá.");
                    break;
                case "entradaProximaCena":
                    inputAto1Cena6(input);
                    break;
                case "ato1Cena8":
                    entradaAto1Cena7(input);
                    break;
                case "opcaoLutarAto1Cena8":
                    entradaAto1Cena8(input);
                    break;
                case "opcaoBatalharAto1Cena9":
                    opcaoBatalharAto1Cena9(input);
                    break;
                case "lutaContraVultoDasLamentações":
                    batalharVultoLamentacoes();
                    break;
                case "batalharVultoLamentacoes":
                    battleInput(input, "verificaRespostaAto1Cena8");
                    break;
                case "verificaRespostaAto1Cena8":
                    verifyQuestion(input, "batalharVultoLamentacoes", "entradaProximaCenaAto1Cena8",
                            "Ao derrotar o Vulto das Lamentações, você sente uma sensação de alívio e paz. " +
                                    "A escuridão que o envolvia se dissipa, revelando um caminho iluminado que se estende à sua frente. " +
                                    "Você percebe que está mais próximo de Arcadelis, e a esperança de restaurar o equilíbrio em Etheris se fortalece em seu coração.");
                    break;
            }
        }

        void invalidInput(){
            outputTextArea.append("\nOpção inválida.");
            autoScroll();
        }

        // Metodo para receber a entrada do usuário no menu
        public void handleMainMenuInput(String input){
            URL soundSelect = getClass().getResource("/Sounds/SoundsEffect/soundSelect.wav");
            switch (input) {
                case "1":
                    showInstructions();

                    if (soundSelect != null) {
                        rpgComponent.new MusicPlayer(soundSelect.getPath()).playOnce(soundSelect.getPath());
                    } else {
                        System.out.println("Efeito sonoro não encontrado!");
                    }
                    break;
                case "2":
                    initialHistory();

                    if (soundSelect != null) {
                        rpgComponent.new MusicPlayer(soundSelect.getPath()).playOnce(soundSelect.getPath());
                    }

                    if (musicPlayer != null) { // Verifica se o musicPlayer não é nulo
                        musicPlayer.stop();
                    }

                    URL url = getClass().getResource("/Sounds/musicBegin.wav");

                    if (url != null) { // Verifica se a URL não é nula
                        musicPlayer = rpgComponent.new MusicPlayer(url.getPath());
                        musicPlayer.play();
                    } else {
                        System.out.println("Arquivo de música não encontrado!");
                    }
                    break;
                case "3":
                    credits();
                    if (soundSelect != null) {
                        rpgComponent.new MusicPlayer(soundSelect.getPath()).playOnce(soundSelect.getPath());
                    } else {
                        System.out.println("Efeito sonoro não encontrado!");
                    }
                    break;
                case "4":
                    System.exit(0);
                    if (soundSelect != null) {
                        rpgComponent.new MusicPlayer(soundSelect.getPath()).playOnce(soundSelect.getPath());
                    } else {
                        System.out.println("Efeito sonoro não encontrado!");
                    }
                    break;
                default:
                    invalidInput();
                    break;
            }
        }

        // Créditos
        void credits(){
            outputTextArea.setText(
                    "Desenvolvido por:\n" +
                    "Arthur Araújo\n" +
                    "Maria Eduarda\n\n" +
                    "Grupo: 12\n" +
                    "Turma: A\n\n" +
                    "Agradecimentos especiais a todos os professores e colegas que nos apoiaram e ajudaram durante o desenvolvimento deste projeto.\n\n" +
                    "Obrigado por jogar The Age Of Etheris!\n\n" +
                            "1. Voltar"
            );
            currentGameState = "credits";
        }

        // Métodos para exibir instruções
        void showInstructions(){
            outputTextArea.setText(
                    "Seja bem vindo as instruções!\n" +
                            "Aqui você podera entender como funciona todos o sistema do jogo.\n" +
                            "Escolha uma opção:\n" +
                            "1. Sistema Aritmético\n" +
                            "2. Sistema de Batalha\n" +
                            "3. Voltar\n"
            );
            currentGameState = "instructions";
        }

        void handleInstructionsInput(String input){
            URL soundSelect = getClass().getResource("/Sounds/SoundsEffect/soundSelect.wav");
            switch (input) {
                case "1":
                    arithmeticSystem();
                    if (soundSelect != null) {
                        rpgComponent.new MusicPlayer(soundSelect.getPath()).playOnce(soundSelect.getPath());
                    } else {
                        System.out.println("Efeito sonoro não encontrado!");
                    }
                    break;
                case "2":
                    battleSystem();
                    if (soundSelect != null) {
                        rpgComponent.new MusicPlayer(soundSelect.getPath()).playOnce(soundSelect.getPath());
                    } else {
                        System.out.println("Efeito sonoro não encontrado!");
                    }
                    break;
                case "3":
                    showMainMenu();
                    if (soundSelect != null) {
                        rpgComponent.new MusicPlayer(soundSelect.getPath()).playOnce(soundSelect.getPath());
                    } else {
                        System.out.println("Efeito sonoro não encontrado!");
                    }
                    break;
            }
        }

        void arithmeticSystem(){
            outputTextArea.setText(
                    "Sistema Aritmético\n" +
                            "O sistema aritmético é um sistema de cálculos de aritmética binaria, octal e hexadeciamal que você terá que resolver para avançar no jogo.\n\n" +
                            "Como vai funcionar?\n" +
                            "Bom, em várias partes do jogo você terá que resolver cálculos aritméticos para avançar, por exemplo, se você encontrar um baú trancado, você terá que resolver um cálculo para abri-lo.\n" +
                            "Os cáculos serão de conversão de bases numéricas para binário e de binário para decimal.\n\n" +
                            "Conversões de texto para binário e de binário para texto.\n" +
                            "Exemplo: 'Hello' em binário é '01001000 01100101 01101100 01101100 01101111'.\n\n" +
                            "Opções:\n" +
                            "1. Voltar\n"
            );
            currentGameState = "arithmeticSystem";
        }

        void arithmeticSystemInput(String input){
            switch (input) {
                case "1":
                    showInstructions();
                    URL soundSelect = getClass().getResource("/Sounds/SoundsEffect/soundSelect.wav");
                    if (soundSelect != null) {
                        rpgComponent.new MusicPlayer(soundSelect.getPath()).playOnce(soundSelect.getPath());
                    } else {
                        System.out.println("Efeito sonoro não encontrado!");
                    }
                    break;
            }
        }

        void battleSystem(){
            outputTextArea.setText(
                    "Sistema de Batalha\n" +
                            "O sistema de batalha é um sistema de combate baseado em turnos, onde você terá que derrotar inimigos para avançar no jogo.\n\n" +
                            "Como vai funcionar?\n" +
                            "Bom, em várias partes do jogo você terá que enfrentar inimigos para avançar, por exemplo, se você encontrar um inimigo, você terá que derrotá-lo para avançar.\n" +
                            "Os cáculos serão de conversão de bases numéricas para binário e de binário para decimal.\n\n" +
                            "Exemplo de batalha:\n" +
                            "Para derrotar o inimigo você terá que resolver um cálculo aritmético.\n" +
                            "Se você acertar o cálculo, você ataca o inimigo.\n" +
                            "Se você errar o cálculo, o inimigo ataca você.\n\n" +

                            "Opções:\n" +
                            "1. Voltar\n"
            );
            currentGameState = "battleSystem"; // Definindo o estado como "battleSystem"
        }

        void battleSystemInput(String input){
            switch (input) {
                case "1":
                    showInstructions();
                    URL soundSelect = getClass().getResource("/Sounds/SoundsEffect/soundSelect.wav");
                    if (soundSelect != null) {
                        rpgComponent.new MusicPlayer(soundSelect.getPath()).playOnce(soundSelect.getPath());
                    } else {
                        System.out.println("Efeito sonoro não encontrado!");
                    }
                    break;
                default:
                    invalidInput();
                    break;
            }
        }
        // Fim dos métodos para exibir instruções

        int questionBattleBinary = rpgComponent.questionBinary();
        int randomQuestionBinary = questionBattleBinary;
        String numBinary = Integer.toBinaryString(randomQuestionBinary);

        // Metodo para iniciar a batalha
        void battle(String texto, String status){
            String inputBattle = texto;
            String battleOptions =
                    "Converta " + randomQuestionBinary + " para binairo:\n" +
                    "Opções:\n" +
                    "1. Mostrar tabela de conversão\n" +
                    "2. Responder\n" +
                    "3. Ajudar\n" +
                    "=======================================================================\n";

            outputTextArea.setText(inputBattle + battleOptions);
            currentGameState = status;
        }

        boolean viewTable = true; // Variável para verificar se a tabela já foi mostrada

        void battleInput(String input, String gameState) {
            switch (input) {
                case "1":
                    tabelaBinario();
                    viewTable = false;
                    break;
                case "2":
                    currentGameState = gameState;
                    outputTextArea.append("\nDigite o número binário:\n");
                    autoScroll();
                    break;
                case "3":
                    opcaoAjuda();
                    autoScroll();
                    break;
            }
        }

        void opcaoAjuda(){
            outputTextArea.append(
                    "\nA resposta é: " + numBinary
            );
        }

        // Metodo para mostrar a tabela de conversão
        void tabelaBinario(){
            String tabela = "\nTabela de conversão:\n" +
                    "Decimal - Binário\n" +
                    "1 - 0001\n" +
                    "2 - 0010\n" +
                    "4 - 0100\n" +
                    "8 - 1000\n" +
                    "16 - 0001 0000\n" +
                    "32 - 0010 0000\n" +
                    "64 - 0100 0000\n" +
                    "128 - 1000 0000\n" +
                    "256 - 0001 0000 0000\n" +
                    "512 - 0010 0000 0000\n";
            if (viewTable) {
                outputTextArea.append(tabela);
                autoScroll();
            }
        }



        RpgComponent.Enemy urrentEnemy = rpgComponent.new Enemy(0, 0);

        void lutaAcertou(){
            urrentEnemy.life -= playerAttack;

            URL hitSound = getClass().getResource("/Sounds/SoundsEffect/hitBattle.wav");
            if (hitSound != null) {
                rpgComponent.new MusicPlayer(hitSound.getPath()).playOnce(hitSound.getPath());
            }

            if(urrentEnemy.life > 0){
                textoDeAcerto(playerClasse);
                autoScroll();
            } else {
                outputTextArea.append(
                        "Danos causados: " + playerAttack + "\n" +
                                "Vida do inimigo: 0"
                );
                autoScroll();
            }
        }

        void textoDeAcerto(String input){
            switch (input){
                case "Elfo":
                    outputTextArea.append(
                            "Com sua força e agilidade, você acerta o inimigo com seu poderoso arco e flecha, causando " + playerAttack + " de dano.\n" +
                                    "Vida do inimigo: " + urrentEnemy.life
                    );
                    break;
                case "Mago":
                    outputTextArea.append(
                            "Você conjura um feitiço arcano e lança uma bola de fogo contra o inimigo, causando " + playerAttack + " de dano.\n" +
                                    "Vida do inimigo: " + urrentEnemy.life
                    );
                    break;
                case "Guerreiro":
                    outputTextArea.append(
                            "Com sua força bruta, você desfere um golpe poderoso com sua espada contra o inimigo, causando " + playerAttack + " de dano.\n" +
                                    "Vida do inimigo: " + urrentEnemy.life
                    );
                    break;
            }
        }

        void lutaErrou(){
            playerLife -= urrentEnemy.attack;
            URL hitSound = getClass().getResource("/Sounds/SoundsEffect/hitBattle.wav");
            if (hitSound != null) {
                rpgComponent.new MusicPlayer(hitSound.getPath()).playOnce(hitSound.getPath());
            }

            if (playerLife > 0){
                outputTextArea.append(
                        "\n\nVocê acidentalmente errou o ataque contra o inimigo! O inimigo se aproveita da oportunidade e te acerta com um ataque...\n" +
                                "Dano recebido: " + urrentEnemy.attack + "\n" +
                                "Sua vida: " + playerLife
                );
                autoScroll();
            } else {
                outputTextArea.append(
                        "\n\nVocê acidentalmente errou o ataque contra o inimigo! O inimigo se aproveita da oportunidade e te acerta com um ataque...\n" +
                                "Dano recebido: " + urrentEnemy.attack + "\n" +
                                "Sua vida: 0\n" +
                                "\nInfelizmente você foi derrotado pelo inimigo. Sua jornada acaba aqui." +
                                "\n1. Reiniciar\n"
                );
                currentGameState = "restartGame";
                autoScroll();
            }
        }

        void restartGame(String input){
            if (input.equals("1")) {
                showMainMenu();
                if (musicPlayer != null) { // Verifica se o musicPlayer não é nulo
                    musicPlayer.stop();
                }

                URL url = getClass().getResource("/Sounds/musicMenu.wav");

                if (url != null) { // Verifica se a URL não é nula
                    musicPlayer = rpgComponent.new MusicPlayer(url.getPath());
                    musicPlayer.play();
                }
            } else {
                invalidInput();
            }
        }

        void verifyQuestion(String input, String returnBattle, String gameState, String text){
            if(numBinary.equals(input)){
                lutaAcertou();
                if(urrentEnemy.life > 0){
                    newBattle(returnBattle);
                } else {
                    // Efeito sonoro de vitória
                    URL hitSound = getClass().getResource("/Sounds/SoundsEffect/winBattle.wav");
                    if (hitSound != null) {
                        rpgComponent.new MusicPlayer(hitSound.getPath()).playOnce(hitSound.getPath());
                    }

                    // Musica depois da batalha
                    if (musicPlayer != null) { // Verifica se o musicPlayer não é nulo
                        musicPlayer.stop();
                    }

                    URL url = getClass().getResource("/Sounds/musicBegin.wav");

                    if (url != null) { // Verifica se a URL não é nula
                        musicPlayer = rpgComponent.new MusicPlayer(url.getPath());
                        musicPlayer.play();
                    }

                    outputTextArea.append(
                            "\n====================================\n" +
                            text + "\n1. Continuar"
                    );
                    autoScroll();
                    currentGameState = gameState;
                }
            } else {
                lutaErrou();
                if(playerLife > 0){
                    newBattle(returnBattle);
                }
                // outputTextArea.append(numBinary);

                autoScroll();
            }
        }

        // imprimi uma nova batalha
        void newBattle(String returnBattle) {
            randomQuestionBinary = rpgComponent.questionBinary();
            numBinary = Integer.toBinaryString(randomQuestionBinary);
            outputTextArea.append("\n\nConverta " + randomQuestionBinary + " para binário:\n" +
                    "Opções:\n" +
                    "1. Mostrar tabela de conversão\n" +
                    "2. Responder\n" +
                    "3. Ajuda\n"
            );
            autoScroll();
            viewTable = true;
            currentGameState = returnBattle;
        }

        // Cria uma nova batalha
        void secondBattle() {
            urrentEnemy.life = 100;
            randomQuestionBinary = rpgComponent.questionBinary();
            numBinary = Integer.toBinaryString(randomQuestionBinary);
            battle(
                    "Enquanto você anda pela masmorra, você se depara com um inimigo.\n" +
                            "Você está preparado para a batalha?\n" +
                            "Resolva o desafio para acerta-lo.\n\n", "battle"
            );
        }

        //Inicio Historia
        void initialHistory(){
            outputTextArea.setText(
                    "Aenor:\n" +
                    "Finalmente você acordou, Equilibrador. O destino de Etheris depende de você. " +
                            "A Ruptura destruiu o tempo e o espaço, e agora você deve restaurar o equilíbrio." +
                            "\nQual será sua linhagem?\n\n" +
                            "1. Elfo: Habilidades de agilidade e conexão espiritual.\n" +
                            "2. Mago: Mestre em manipulação de feitiços.\n" +
                            "3. Guerreiro: Força bruta e resistência física.\n"
            );
            currentGameState = "initialHistory";
        }

        // Variaveis para armazenar a classe e o nome do jogador
        String playerClasse;
        String playerName;
        int playerLife = 0;
        int playerAttack = 0;
        int playerLevel = 0;

        // Instanciando a classe Player
        RpgComponent.Player jogador = rpgComponent.new Player(playerLife, playerAttack);

        // 1º ATO da historia do jogo
        void chooseCharacterClass(String input) {
            switch (input) {
                case "1":
                    outputTextArea.append(
                            "\nNarrador: " +
                            "\nVocê escolheu a linhagem dos Elfos, seres ancestrais conectados ao espírito da natureza. " +
                                    "Sua agilidade e habilidades místicas serão essenciais para enfrentar os desafios à frente. " +
                                    "Somente os Elfos compreendem a verdadeira essência do equilíbrio entre as forças naturais e espirituais de Etheris."
                    );
                    playerClasse = "Elfo";
                    playerLife = 25;
                    playerAttack = 10;
                    playerLevel = 2;
                    promptForPlayerName();
                    break;
                case "2":
                    outputTextArea.append(
                            "\nNarrador: " +
                            "\nVocê escolheu a linhagem dos Magos, mestres das artes arcanas e guardiões dos segredos da magia." +
                                    " Seu conhecimento e controle sobre os feitiços será a chave para domar as distorções da Ruptura. " +
                                    "Mas lembre-se, com grande poder vem grande responsabilidade."
                    );
                    playerClasse = "Mago";
                    playerLife = 30;
                    playerAttack = 15;
                    playerLevel = 3;
                    promptForPlayerName();
                    break;
                case "3":
                    outputTextArea.append(
                            "\nNarrador: " +
                                    "\nVocê escolheu a linhagem dos Guerreiros, heróis de força incomparável e destemidos em batalha. " +
                                    "Com sua resistência e coragem, você será capaz de enfrentar até mesmo as criaturas mais temíveis que vagam pelas terras distorcidas de Etheris."
                    );
                    playerClasse = "Guerreiro";
                    playerLife = 20;
                    playerAttack = 20;
                    playerLevel = 2;
                    promptForPlayerName();
                    break;
                default:
                    invalidInput();
                    break;
            }
        }

        void promptForPlayerName(){
            outputTextArea.append(
                    "\n\nAenor:" +
                    "\nAgora, diga-me, qual é o nome do " + playerClasse + " que restaurará o equilíbrio?" + "\nDigite o seu nome:"
            );
            currentGameState = "playerName";
        }

        void confirmPlayerName(String input){
            playerName = input;
            outputTextArea.append(
                    "\n\nSeu nome é " + playerName + ", o " + playerClasse + "?" +
                            "\n1. Sim" +
                            "\n2. Não"
            );
            currentGameState = "questionName";
        }

        void handleNameConfirmation(String input) {
            switch (input) {
                case "1":
                    outputTextArea.setText(
                            "Local: Antigo templo em ruínas\n\n" +
                            "Aenor:\n" +
                            "Muito bem, " + playerName + ". A Ruptura pode ter distorcido o tempo e o espaço, mas sua missão é clara. " +
                                    "Localizada no coração de Etheris, Arcadelis é a última cidade sagrada protegida pela magia dos Guardiões do Véu. " +
                                    "É lá que você encontrará as respostas para a Ruptura e o caminho para a restauração do equilíbrio.\n" +
                            "Alguma dúvida antes de partirmos?\n"
                    );
                    initiateCharacterDialog();
                    break;
                case "2":
                    outputTextArea.setText(
                            "Aenor:\n" +
                            "Ah, parece que houve um engano. O nome que carrega é de grande importância para sua jornada, Equilibrador. " +
                                    "Vamos corrigir isso. Qual será o verdadeiro nome pelo qual você será conhecido?" +
                                    "\n\nDigite o seu nome:"
                    );
                    currentGameState = "playerName";
                    break;
                default:
                    invalidInput();
                    break;
            }
        }

        ArrayList<String> question;

        // Diálogo com Aenor (1º Ato, Cena 1)
        void initiateCharacterDialog(){

            question = new ArrayList<>();
            question.add("1. Quem é você?");
            question.add("2. O que é o Conselho do Véu?");
            question.add("3. O que é a Ruptura?");
            question.add("4. Me fale sobre Arcadelis.");
            question.add("5. Estou pronto. Como chegamos a Arcadelis?");

            // Exibi as perguntas
            for (int i = 0; i < question.size(); i++) {
                outputTextArea.append("\n" + question.get(i));
            }
            outputTextArea.append("\n=======================================================================");

            currentGameState = "dialogo_ato1_cena2";
        }

        // Arrays com valores booleano para verifica quando uma opção for utilizada
        boolean[] numQuestion = {true, true, true, true};

        void processCharacterDialogInput(String input){
        switch (input){
            case "1":
                if (numQuestion[0]){
                    outputTextArea.append(
                            "\n\n" + playerName + " o " + playerClasse + ":\n" +
                                    "Me diga, quem é você? Como é que alguém como você se envolve com tudo isso?" +
                                    "\n\nAenor:" +
                                    "\nEu sou Aenor, um dos últimos Elfos da Era dos Espíritos, guardião dos segredos antigos e do equilíbrio entre as forças naturais. " +
                                    "Fui escolhido pelos Guardiões do Véu para orientar você nesta jornada. Meu papel é guiá-lo até os artefatos que podem restaurar Etheris e ajudá-lo a entender o verdadeiro significado da Ruptura. " +
                                    "Mas, lembre-se, meu conhecimento tem limites, e o verdadeiro caminho a seguir será revelado por suas escolhas.\n"
                    );
                    question.set(0, "");
                    for (int i = 0; i < question.size(); i++) {
                        outputTextArea.append("\n" + question.get(i));
                    }
                    outputTextArea.append("\n=======================================================================");
                       numQuestion[0] = false; // impede que o a opção 1 seja utilizada novamente
                } else {
                    invalidInput();
                }
                    break;
            case "2":
                if (numQuestion[1]){
                    outputTextArea.append(
                            "\n\n" + playerName + " o " + playerClasse + ":\n" +
                                    "O que é o Conselho do Véu? Eles realmente podem me ajudar a salvar Etheris?" +
                                    "\n\nAenor:" +
                                    "\nConselho do Véu é composto pelos últimos sábios e sobreviventes das três eras. " +
                                    "Eles representam os fragmentos do antigo conhecimento de Etheris. " +
                                    "Seus membros dedicaram suas vidas a estudar a Ruptura, buscando uma forma de restaurar o equilíbrio. " +
                                    "Eles estão entre os poucos que ainda resistem ao caos, protegidos pela magia de Arcadelis. " +
                                    "Embora não sejam perfeitos, sua orientação será crucial para sua jornada.\n"
                    );
                    question.set(1, "");
                    for (int i = 0; i < question.size(); i++) {
                            outputTextArea.append("\n" + question.get(i));
                    }
                    outputTextArea.append("\n=======================================================================");
                    numQuestion[1] = false; // impede que o a opção 2 seja utilizada novamente
                } else {
                    invalidInput();
                }
                break;
            case "3":
                    if (numQuestion[2]){
                        outputTextArea.append(
                                "\n\n" + playerName + " o " + playerClasse + ":\n" +
                                        "O que é a Ruptura? Como ela afetou Etheris?" +
                                        "\n\nAenor:" +
                                        "\nA Ruptura é uma distorção no tecido da realidade, um evento cataclísmico que rasgou o equilíbrio de Etheris. " +
                                        "Ela surgiu de uma fonte desconhecida, desencadeando uma onda de caos e destruição que afetou todas as eras e dimensões. " +
                                        "Seus efeitos são imprevisíveis e perigosos, distorcendo o tempo, o espaço e a magia de Etheris. " +
                                        "A Ruptura é a causa de todas as anomalias que você encontrará em sua jornada, e sua missão é restaurar o equilíbrio antes que seja tarde demais.\n"
                        );
                        question.set(2, "");
                        for (int i = 0; i < question.size(); i++) {
                            outputTextArea.append("\n" + question.get(i));
                        }
                        outputTextArea.append("\n=======================================================================");
                         numQuestion[2] = false; // impede que o a opção 3 seja utilizada novamente
                    } else {
                        invalidInput();
                    }
                    break;
                case "4":
                if (numQuestion[3]){
                    outputTextArea.append(
                            "\n\n" + playerName + " o " + playerClasse + ":\n" +
                                    "Me fale sobre Arcadelis. O que podemos esperar ao chegar lá?" +
                                    "\n\nAenor:" +
                                    "\nArcadelis é a última cidade sagrada de Etheris, protegida pela magia dos Guardiões do Véu. " +
                                    "Ela é um refúgio seguro em meio ao caos da Ruptura, um farol de esperança para os poucos que ainda resistem. " +
                                    "Lá você encontrará os sábios do Conselho do Véu, os artefatos antigos que podem restaurar o equilíbrio e os segredos ocultos da magia de Etheris. " +
                                    "Mas cuidado, Arcadelis também é um lugar de mistérios e perigos, onde cada escolha pode ter consequências imprevisíveis. " +
                                    "Sua jornada até lá será repleta de desafios, mas é o único caminho para salvar Etheris.\n"
                    );
                    question.set(3, "");;
                    for (int i = 0; i < question.size(); i++) {
                        outputTextArea.append("\n" + question.get(i));
                    }
                    outputTextArea.append("\n=======================================================================");
                    numQuestion[3] = false; // impede que o a opção 4 seja utilizada novamente
                } else {
                    invalidInput();
                }
                break;
                case "5":
                ato1_Cena2();
                break;
                default:
                invalidInput();
                break;
            }
        }
        // Fim do diálogo com Aenor

        // Escolha do caminho (1º Ato, Cena 2)
        void ato1_Cena2 (){
            outputTextArea.setText( 
                "Aenor:" + 
                "\nCorajoso como esperado, " + playerName + " o " + playerClasse + ". No entanto, a jornada Arcadelis não será facíl. As terras de Etheris foram devastadas pela Ruptura, e ocaminho que antes era claro agora é fragmentado por distorções do tempo e do espaço.\n" +
                playerName + " o " + playerClasse + " a jornada até Arcadelis é perigosa, mas necessária. Você tem dois caminhos diante de si, cada um com seus próprios perigos e recompensas. Ouça com atenção. \n\n" +
                "A primeira opção é atravessar a Floresta das Almas Perdidas, onde os espíritos das eras passadas vagam sem descanso. Esse caminho é traiçoeiro e silencioso, mas os espíritos tentarão invadir sua mente, testando sua determinação. Se conseguir passar pela floresta, você poderá encontrar o Santuário dos Espíritos, onde segredos antigos podem ser revelados a você. No entanto, será testado espiritualmente, e aqueles que falham perdem-se para sempre. \n\n" +
                "Sua segunda opção é enfrentar as Montanhas Quebradas, uma região desolada, corrompida por fissuras temporais. As distorções no tempo tornam este lugar caótico e imprevisível. Criaturas de todas as épocas caçam indiscriminadamente, e você enfrentará inimigos de eras distantes, todos misturados e sem controle. Atravesse essas montanhas, e encontrará a Fortaleza Esquecida, onde as armas e armaduras da Era dos Titãs estão enterradas nas ruínas. Esse caminho testará sua força e coragem, mas as recompensas podem ser imensas.\n\n" +
                "1. Florestas das Almas Perdidas\n" + "2. Montanhas Quebradas"
            );
            currentGameState = "ato1_cena2";
        }

        //  Floresta das Almas Perdidas
        void entradaAto1Cena2 (String input){
            switch (input) {
                case "1":
                    outputTextArea.setText(
                        "O Equilibrador escolheu o caminho da Floresta das Almas Perdidas. À medida que você avança, a temperatura cai, e a névoa começa a envolver tudo ao seu redor. As árvores, com troncos retorcidos e galhos contorcidos, parecem sussurrar em uma língua antiga, suas sombras se movendo de maneira inquietante à medida que você segue adiante. \n\n" + 
                        "A luz do sol se apaga lentamente, e o mundo parece perder suas cores, ficando cada vez mais cinza e desolado. Não há som de vida, apenas o sussurro dos espíritos que vagam pela floresta. Suas presenças são como sombras de eras passadas, almas que não encontraram descanso desde a Ruptura. Eles observam cada movimento, silenciosamente esperando por aqueles que falham na resistência mental para reivindicar suas mentes e suas almas.\n\n" +
                        "1. Continuar" 
                    );
                    currentGameState = "ato1_cena3";
                break;
                default:
                    invalidInput();
                    break;
            }
        }

        void entradaAto1Cena3( String input){
            switch (input)    {
                case "1":
                ato1_Cena3();
                    if (musicPlayer != null) { // Verifica se o musicPlayer não é nulo
                        musicPlayer.stop();
                    }

                    URL url = getClass().getResource("/Sounds/musicBattle.wav");

                    if (url != null) { // Verifica se a URL não é nula
                        musicPlayer = rpgComponent.new MusicPlayer(url.getPath());
                        musicPlayer.play();
                    } else {
                        System.out.println("Arquivo de música não encontrado!");
                    }
                break;
                default:
                invalidInput();
            }
        }

        void ato1_Cena3(){
            outputTextArea.setText(
                "Narrador: \nCaminhando mais fundo na Floresta das Almas Perdidas, você sente uma presença intensa. O ar fica denso e uma estranha sensação de frio envolve seu corpo. Das sombras retorcidas de uma árvore, uma figura se destaca: um espírito guerreiro, conhecido como o Guardião Espectral de Selendis. \n\n" +
                "Descrição do Inimigo: \nO Guardião Espectral é uma figura encapuzada, com um manto esfarrapado que parece feito de névoa. Ele carrega uma lâmina longa, desgastada e corroída, mas com um brilho sinistro nas bordas. Seus olhos são dois pontos de luz espectral, frios e vazios, como se sua alma tivesse sido drenada há séculos. Suas mãos são garras translúcidas, e ele flutua levemente acima do chão, deixando um rastro de névoa. Sua voz ecoa como um murmúrio antigo, repetindo palavras desconexas em uma língua esquecida.\n\n" +
                "O Guardião Espectral: \nNinguém passa… ninguém escapa… aquele que busca Arcadelis, prove sua coragem ou junte-se às almas errantes desta floresta.\n\n" +
                "1. Lutar\n" + "2. Fugir\n"
            );
            currentGameState = "option2Ato1Cena4";
        }

        // Ação de fugir
        void option2Ato1Cena4(String input){
            switch (input){
                case "1":
                    outputTextArea.setText(
                            "Narrador:\n" +
                                    "Com determinação, você encara o Guardião Espectral. " +
                                    "As sombras em volta dele parecem se agitar, como se a própria floresta o tivesse escolhido para defender suas profundezas. " +
                                    "Seus olhos vazios brilham com um tom pálido e ameaçador, e sua figura espectral flutua levemente sobre o solo, emoldurada pela névoa fria e densa da Floresta das Almas Perdidas.\n" +
                                    "O Guardião Espectral ergue sua mão esquelética e uma lâmina etérea se materializa em sua garra, pulsando com uma energia misteriosa e antiga. Sua voz ecoa, reverberando com a força de mil almas perdidas:\n" +
                                    "\nGuardião Espectral:\n" +
                                    "Corajoso, mas imprudente, Equilibrador. Somente aqueles que enfrentam a morte com valor merecem cruzar para além desta floresta. Mostre-me a força da sua alma!\n\n" +
                                    "Narrador:\n " +
                                    "O ar ao seu redor fica mais pesado, e o frio penetra até os ossos, testando sua resistência. A névoa, que antes o ocultava, agora parece puxá-lo para a batalha. A floresta observa, silenciosa, e os sussurros das almas perdidas intensificam-se, como se aguardassem para ver quem triunfará.\n\n" +
                                    "1. Lutar contra o Guardião Espectral\n"
                    );
                    currentGameState = "opcaoLutarAto1Cena5";
                    break;
                case "2":
                    outputTextArea.setText(
                            "Narrador:\n" +
                                    "Sentindo o peso da presença do Guardião Espectral, você decide que lutar contra essa alma inquieta é um risco que talvez não possa se dar ao luxo de correr agora. " +
                                    "No entanto, escapar da Floresta das Almas Perdidas exige mais do que simplesmente correr. " +
                                    "As almas errantes clamam por energia vital, e o próprio tecido da realidade ao seu redor começa a se desintegrar, como se a floresta tentasse segurá-lo no seu domínio.\n\n" +
                                    "Os sussurros dos espíritos se intensificam, e o Guardião Espectral o observa, intrigado pela sua hesitação. Ele se move, bloqueando o caminho enquanto murmura:\n\n" +
                                    "Guardião Espectral:\n" +
                                    "Fugir... é recusar o fardo. Mas ninguém escapa deste lugar sem ser marcado pela escuridão.\n\n" +
                                    "Para escapar de sua vigilância, uma última barreira espiritual surge, com números antigos que se distorcem diante dos seus olhos. " +
                                    "Um enigma temporal se revela — uma sequência decimal que só pode ser superada se você encontrar sua essência binária, liberando-se da corrente espiritual que tenta prendê-lo aqui.\n\n" +
                                    "1. Resolver o enigma e fugir\n" + "2. Ficar e lutar contra o Guardião Espectral\n"
                    );
                    currentGameState = "opcaoFugir2Ato1Cena5";
                    break;
                default:
                    invalidInput();
                    break;
            }
        }

        // Ação de lutar
        void batalharGuardiaoEspectral() {
            urrentEnemy.life = 20;
            urrentEnemy.attack = 10;
            randomQuestionBinary = rpgComponent.questionBinary();
            numBinary = Integer.toBinaryString(randomQuestionBinary);
            battle(
                    "Desafio:\n" +
                    "O combate com o Guardião Espectral começa! Use suas habilidades e estratégias com sabedoria para sobreviver à sua lâmina etérea e superar sua defesa de sombras. " +
                            "A cada golpe desferido, a essência espectral do Guardião se desvanece um pouco mais, mas cuidado — ele também possui ataques que drenam sua energia vital.\n\n" +
                    "Resolva o desafio para acertá-lo.\n\n" +
                            "Guardião Espetral: Level [2]\n" +
                            "Vida: " + urrentEnemy.life +
                    "\nAtaque: " + urrentEnemy.attack +
                    "\n\n" + playerName + " o " + playerClasse + ": Level[" + playerLevel + "]\n" +
                    "Sua vida: " + playerLife +
                    "\nSua força: " + playerAttack + "\n\n", "batalharGuardiãoEspectral"
            );
        }

        void inputAto1Cena6(String input){
            switch (input){
                case "1":
                    ato1Cena7();
                    if (musicPlayer != null) { // Verifica se o musicPlayer não é nulo
                        musicPlayer.stop();
                    }

                    URL url = getClass().getResource("/Sounds/musicBegin.wav");

                    if (url != null) { // Verifica se a URL não é nula
                        musicPlayer = rpgComponent.new MusicPlayer(url.getPath());
                        musicPlayer.play();
                    }
                    break;
                default:
                    invalidInput();
                    break;
            }
        }

        void confirmToEscape(String input){
            switch (input){
                case "1":
                    outputTextArea.append(
                            "\nTem certeza que deseja fugir? Se vocẽ fugir agora você não aumentarar suas forças e vidas e continara sendo um " + playerClasse + " fraco. " +
                            "Mas poupara sua vida e poderar enfretar inimigos mais fortes no futuro e se redimir pela sua vergonhar.\n" +
                            "1. Sim\n" + "2. Não"
                    );
                    autoScroll();
                    break;
                case "2":
                    batalharGuardiaoEspectral();
                    break;
                default:
                    invalidInput();
                    break;
            }
            currentGameState = "option2Ato1Cena6";
        }

        // Enigma pra fugir
        void escapeEnemy(String gameState){
            outputTextArea.setText(
                    "Desafio de fuga:\n" +
                            "Converta o número " + questionBattleBinary + " para binário e insira o resultado corretamente. Caso erre, a presença do Guardião Espectral o impedirá de fugir, e você terá que enfrentá-lo.\n\n" +
                            "1. Mostrar tabela de conversão\n" +
                            "2. Responder\n" +
                            "3. Ajuda\n"
            );
            currentGameState = gameState;
        }

        void inputEscapeEnemy(String input, String gameState){
            switch (input) {
                case "1":
                    tabelaBinario();
                    break;
                case "2":
                    outputTextArea.append("\nDigite o número binário: ");
                    currentGameState = gameState;
                    break;
                case "3":
                    opcaoAjuda();
                    break;
                default:
                    invalidInput();
                    break;
            }
        }

        // Verifica se o jogador acertou o enigma
        boolean escapeEnemy = false;
        void verifyQuestionEscape(String input, String text, String acertou, String errou){
            outputTextArea.append("\nVocê digitou: " + input);
            if(numBinary.equals(input)){
                outputTextArea.append("\n\nVocê conseguiu fugir do Guardião Espectral!\n\n");
                outputTextArea.append(text);
                escapeEnemy = true;
                currentGameState = acertou;
                autoScroll();
            } else {
                outputTextArea.append(
                                "\nNarrador:\n" +
                                "Você tenta fugir, mas algo dá errado ao resolver o enigma. " +
                                "Sua mente corre, mas a resposta escapa pelos dedos, e, no último segundo, um silêncio pesado preenche o ar. O Guardião Espectral, sem piedade, avança sobre você antes que possa escapar\n\n" +
                                "Agora, não há outra opção: sua única escolha é lutar para sobreviver e provar sua coragem diante dos espíritos da floresta.\n" +
                                "1. Continuar"
                );
                escapeEnemy = false;
                currentGameState = errou;
                autoScroll();
            }
        }
        
        void ato1Cena7(){
            outputTextArea.setText(
                "Narrador:\n" +
                "Após a intensa batalha, ou a astúcia de escapar, você segue pela trilha nebulosa da Floresta das Almas Perdidas. O caminho à frente continua envolto em uma penumbra inquietante, onde as sombras das árvores retorcidas parecem observá-lo com olhos invisíveis. O sussurro dos espíritos diminui, como se até eles tivessem sido acalmados — ou advertidos — pela sua passagem.\n\n" +
                "A bruma que antes ocultava tudo ao redor começa a clarear aos poucos, revelando uma luz distante, fraca, mas constante, indicando que o final da floresta talvez esteja próximo. Em cada passo, o peso das eras perdidas parece diminuir, enquanto o silêncio, antes opressivo, se transforma em uma quietude mais serena.\n\n" +
                "Com a saída quase ao alcance, você sente que seu caminho até Arcadelis não está longe. Mas, em um lugar como este, qualquer alívio pode ser passageiro.\n\n" +
                "1. Continuar"
            );
            currentGameState = "ato1Cena8";
        }
        void entradaAto1Cena7(String input){
            switch (input) {
                case "1":
                    ato1Cena8();
                    if (musicPlayer != null) { // Verifica se o musicPlayer não é nulo
                        musicPlayer.stop();
                    }

                    URL url = getClass().getResource("/Sounds/musicBattle.wav");

                    if (url != null) { // Verifica se a URL não é nula
                        musicPlayer = rpgComponent.new MusicPlayer(url.getPath());
                        musicPlayer.play();
                    } else {
                        System.out.println("Arquivo de música não encontrado!");
                    }
                    break;
                default:
                invalidInput();
                    break;
            }
        }

        void ato1Cena8(){
            outputTextArea.setText(
                "Narrador:\n" +
                "Das sombras mais densas da floresta, emerge uma figura sinistra. Seu corpo é uma mistura de ossos e vapor etéreo, formando uma silhueta disforme e espectral. Ele se arrasta silenciosamente, como se estivesse flutuando, seus braços estendendo-se em garras retorcidas que parecem capazes de atravessar o próprio véu da realidade. Sua cabeça, envolta em rachaduras pulsantes com uma luz azul-gélida, transmite um frio penetrante e inumano\n\n" +
                "Essa criatura, conhecida como Vulto das Lamentações, carrega a energia inquieta das almas que nunca encontraram paz. Ao seu redor, o ar fica pesado, os murmúrios dos espíritos aumentam, ecoando com a dor dos tempos esquecidos. Ele avança lentamente, bloqueando seu caminho como o último teste antes de deixar a floresta. Suas intenções são claras: apenas aqueles fortes o suficiente sobreviverão a sua presença avassaladora.\n\n" +
                "1. Lutar\n" + "2. Fugir\n"
            );
            currentGameState = "opcaoLutarAto1Cena8";
        }

        void entradaAto1Cena8(String input){
            switch (input) {
                case "1":
                    outputTextArea.setText(
                            "Narrador:\n" +
                                    "Com bravura, você encara o Vulto das Lamentações. A figura à sua frente é sombria, envolta em mantos esvoaçantes que parecem feitos da própria neblina da Floresta das Almas Perdidas. Suas mãos ossudas flutuam ao redor do corpo, e um manto de escuridão emana dele, como se carregasse o luto de eras esquecidas. " +
                                    "Os olhos do Vulto, vazios e profundos, brilham com um leve tom azul acinzentado, refletindo a dor das almas que ele arrasta consigo.\n\n" +
                                    "O Vulto das Lamentações ergue seus braços finos, e, com um gesto lento, forma uma foice feita de sombras e lamentos, pulsando com uma energia misteriosa e inquietante. " +
                                    "Sua voz surge num sussurro arrepiado, como uma sinfonia de gritos abafados e saudades eternas:\n\n" +
                                    "Vulto das Lamentações:\n" +
                                    "Aquele que busca a paz neste mundo perdido deve provar que entende a dor e o sacrifício. Equilibrador, sinta o peso das eras e prove que é digno de carregar a esperança de Etheris!\n\n" +
                                    "Narrador:\n" +
                                    "O ar ao seu redor se enche de melancolia, e a temperatura cai ainda mais, fazendo seu corpo estremecer. A névoa, que parecia protegê-lo, agora se fecha ao seu redor, tornando o ambiente claustrofóbico. " +
                                    "Ao longe, as sombras das almas perdidas se agitam, observando em silêncio como testemunhas do combate, ansiosas por ver qual lado prevalecerá.\n\n" +
                                    "1. Lutar contra o Vulto das Lamentações\n"
                    );
                    currentGameState = "opcaoBatalharAto1Cena9";
                default:
//                invalidInput();
                    break;
            }
        }

        void opcaoBatalharAto1Cena9(String input){
            switch (input) {
                case "1":
                    batalharVultoLamentacoes();
                    break;
                default:
                invalidInput();
                    break;
            }
        }

        void batalharVultoLamentacoes() {
            urrentEnemy.life = 25;
            urrentEnemy.attack = 5;
            randomQuestionBinary = rpgComponent.questionBinary();
            numBinary = Integer.toBinaryString(randomQuestionBinary);
            battle(
                    "Desafio:\n" +
                           "\n" +
                            "O combate com o Vulto das Lamentações começa! Use suas habilidades e estratégias com cautela para enfrentar sua presença opressiva e esquivar dos golpes fantasmagóricos que ele desferirá.\n" +
                            "Cada ataque bem-sucedido enfraquece um pouco mais as sombras densas que o envolvem, mas cuidado — ele possui um poder que drena sua vitalidade, alimentando-se do medo e das hesitações de seus oponentes.\n" +
                            "Resolva o desafio para acertá-lo.\n\n" +
                            "Vulto das Lamentações: Level [2]\n" +
                            "Vida: " + urrentEnemy.life +
                            "\nAtaque: " + urrentEnemy.attack +
                            "\n\n" + playerName + " o " + playerClasse + ": Level[" + playerLevel + "]\n" +
                            "Sua vida: " + playerLife +
                            "\nSua força: " + playerAttack + "\n\n", "batalharGuardiãoEspectral"
            );
            currentGameState = "batalharVultoLamentacoes";
        }
    }

    void ato1Cena9(String input){

    }
}
