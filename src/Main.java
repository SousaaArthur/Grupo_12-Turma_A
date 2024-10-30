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
            URL url = getClass().getResource("/Sounds/music01.wav"); // URL do arquivo de música
            musicPlayer = rpgComponent.new MusicPlayer(url.getPath()); // Instanciando o musicPlayer

            // Inicia a música
            musicPlayer.play();
        }

        void autoScroll() {
            outputTextArea.setCaretPosition(outputTextArea.getDocument().getLength());
        }

        // Métodos para exibir menu
        void showMainMenu() {
            outputTextArea.setText(
                    "THE AGE OF ETHERIS\n\n" +
                            "Selecione uma opção:\n" +
                            "1. Instruções\n" +
                            "2. Jogar\n" +
                            "3. Créditos\n" +
                            "4. Sair\n"
            );
            currentGameState = "menu"; // Definindo o estado inicial como "menu"
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
                case "initialHistory":
                    chooseCharacterClass(input);
                    break;
                case "playerName":
                    confirmPlayerName(input);
                    break;
                case "questionName":
                    handleNameConfirmation(input);
                    break;
                case "battle":
                    battleInput(input);
                    break;
                case "calcular":
                    calcularInput(input);
                    break;
                case "responder":
                    verifyQuestion(input);
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
                case "option2Ato1Cena5":
                    confirmToEscape(input);
                    break;
                case "option2Ato1Cena6":
                    inputEscapeAto1Cena6(input);
                    break;
                case "responderQuestao01":
                    verifyQuestionEscape(input);
                    break;
            }
        }

        void invalidInput(){
            outputTextArea.append("\nOpção inválida.");
            autoScroll();
        }

        // Metodo para receber a entrada do usuário no menu
        public void handleMainMenuInput(String input){
            switch (input) {
                case "1":
                    showInstructions();
                    break;
                case "2":
                    initialHistory();
                    if (musicPlayer != null) { // Verifica se o musicPlayer não é nulo
                        musicPlayer.stop();
                    }

                    URL url = getClass().getResource("/Sounds/music02.wav");

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
            switch (input) {
                case "1":
                    arithmeticSystem();
                    break;
                case "2":
                    battleSystem();
                    break;
                case "3":
                    showMainMenu();
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
                    break;
            }
        }
        // Fim dos métodos para exibir instruções

        int questionBattleBinary = rpgComponent.questionBinary();
        int randomQuestionBinary = questionBattleBinary;
        String numBinary = Integer.toBinaryString(randomQuestionBinary);

        void initialBattle(){
            battle(
                    "Enquanto você procurava por um dos artefatos, você se depara com um inimigo.\n" +
                            "Você está preparado para a batalha?\n" +
                            "Resolva o desafio para acerta-lo.\n\n"
            );
        }

        // Metodo para iniciar a batalha
        void battle(String input){
            String inputBattle = input;
            String battleOptions =
                    "Converta " + randomQuestionBinary + " para binairo:\n" +
                    "Opções:\n" +
                    "1. Mostrar tabela de conversão\n" +
                    "2. Calcular\n" +
                    "3. Responder\n" +
                    "=======================================================================\n";

            outputTextArea.setText(inputBattle + battleOptions);
            currentGameState = "battle";
        }

        boolean viewTable = true; // Variável para verificar se a tabela já foi mostrada

        void battleInput(String input) {
            switch (input) {
                case "1":
                    tabelaBinario();
                    viewTable = false;
                    break;
                case "2":
                    calcular();
                    sum = 0;
                    break;
                case "3":
                    currentGameState = "responder";
                    outputTextArea.append("\nDigite o número binário: ");
                    break;
                case "4":
                    showMainMenu();
                    break;
            }
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

        void calcular(){
            outputTextArea.setText(
                    "Questão: " + randomQuestionBinary + "\n" +
                            "Insira um dos valores para faze a soma em decimal:\n" +
                            "1, 2, 4, 8, 16, 32, 64, 128, 256, 512\n\n" +
                            "Escolha uma das opções: \n" +
                            "0. Sair\n"
            );
            currentGameState = "calcular";
        }

        int sum = 0;
        void calcularInput(String input){
            int num = Integer.parseInt(input);

            switch (input){
                case "-1":
                    calcular();
                    sum = 0;
                    break;
                case "0":
                    battle("Retornando batalha...");
                    viewTable = true;
                    break;
                case "1", "2", "4", "8",  "16", "32",  "64", "128", "256", "512":
                    sum += num;
                    outputTextArea.append("\n" + sum);
                    break;
                default:
                    outputTextArea.append("\nEsse número não pertence a base binária.");
                    autoScroll();
            }
        }

        RpgComponent.Enemy urrentEnemy = rpgComponent.new Enemy(100, 10);

        void lutaAcertou(){
            urrentEnemy.life -= 50;
            if(urrentEnemy.life > 0){
                outputTextArea.append("\nVocê acertou o inimigo!\n" +
                        "Vida do inimigo: " + urrentEnemy.life);
                autoScroll();
            } else {
                outputTextArea.append(
                        "\nVida do inimigo: 0"
                );
                autoScroll();
            }
        }

        void verifyQuestion(String input){
            if(numBinary.equals(input)){
                lutaAcertou();
                if(urrentEnemy.life > 0){
                    newBattle();
                } else {
                    outputTextArea.append(
                            "\n====================================" +
                            "\nVocê derrotou o inimigo!"
                    );
                    autoScroll();
                    secondBattle();
                }
            } else {
                outputTextArea.append("\nVocê errou!");
                outputTextArea.append(numBinary);
                autoScroll();
            }
        }

        // imprimi uma nova batalha
        void newBattle() {
            randomQuestionBinary = rpgComponent.questionBinary();
            numBinary = Integer.toBinaryString(randomQuestionBinary);
            outputTextArea.append("\n\nConverta " + randomQuestionBinary + " para binário:\n" +
                    "Opções:\n" +
                    "1. Mostrar tabela de conversão\n" +
                    "2. Calcular\n");
            autoScroll();
            viewTable = true;
            currentGameState = "battle";
        }

        // Cria uma nova batalha
        void secondBattle() {
            urrentEnemy.life = 100;
            randomQuestionBinary = rpgComponent.questionBinary();
            numBinary = Integer.toBinaryString(randomQuestionBinary);
            battle(
                    "Enquanto você anda pela masmorra, você se depara com um inimigo.\n" +
                            "Você está preparado para a batalha?\n" +
                            "Resolva o desafio para acerta-lo.\n\n"
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
                    promptForPlayerName();
                    break;
                case "3":
                    outputTextArea.append(
                            "\nNarrador: " +
                                    "\nVocê escolheu a linhagem dos Guerreiros, heróis de força incomparável e destemidos em batalha. " +
                                    "Com sua resistência e coragem, você será capaz de enfrentar até mesmo as criaturas mais temíveis que vagam pelas terras distorcidas de Etheris."
                    );
                    playerClasse = "Guerreiro";
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

        void option2Ato1Cena4(String input){
            switch (input){
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
                    break;
                default:
                    invalidInput();
                    break;
            }
            currentGameState = "option2Ato1Cena5";
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
                    outputTextArea.append("\nContinuando a jornada...");
                    break;
                default:
                    invalidInput();
                    break;
            }
            currentGameState = "option2Ato1Cena6";
        }

        void escapeAto1Cena6(String input){
            outputTextArea.setText(
                    "Desafio de fuga:\n" +
                            "Converta o número " + questionBattleBinary + " para binário e insira o resultado corretamente. Caso erre, a presença do Guardião Espectral o impedirá de fugir, e você terá que enfrentá-lo.\n\n" +
                            "1. Mostrar tabela de conversão\n" +
                            "2. Responder\n" +
                            "3. Ajuda\n"
            );
            currentGameState = "escapeAto1Cena6";
        }

        void inputEscapeAto1Cena6(String input){
            switch (input) {
                case "1":
                    tabelaBinario();
                    break;
                case "2":
                    outputTextArea.append("\nDigite o número binário: ");
                    currentGameState = "responderQuestao01";
                    break;
                default:
                    break;
            }
        }

        void verifyQuestionEscape(String input){
            if(numBinary.equals(input)){
                outputTextArea.append("\nVocê conseguiu fugir do Guardião Espectral!");
                autoScroll();
            } else {
                outputTextArea.append("\nVocê errou!");
                outputTextArea.append(numBinary);
                autoScroll();
            }
        }
    }
}

/*
 *         void verifyQuestion(String input){
            if(numBinary.equals(input)){
                lutaAcertou();
                if(urrentEnemy.life > 0){
                    newBattle();
                } else {
                    outputTextArea.append(
                            "\n====================================" +
                            "\nVocê derrotou o inimigo!"
                    );
                    autoScroll();
                    secondBattle();
                }
 */

