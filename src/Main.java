import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Main {
    public static void main(String[] args) {
        new Screen();
    }

    public static class Screen extends JFrame {
        JTextField textField;
        JTextArea textArea;

        String gameState;

        // Metodo para inicializar a tela do RPG
        public Screen(){
            setTitle("The Age Of Etheris");
            setVisible(true);
            setSize(800, 600);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Fecha o programa ao fechar a janela
            setResizable(false);  // Impede o redimensionamento da janela
            setLocationRelativeTo(null);
            getContentPane().setBackground(Color.BLACK);
            setLayout(null);

            // Exibir texto na tela
            textArea = new JTextArea();
            textArea.setFont(new Font("Courier New", Font.PLAIN, 18));
            textArea.setForeground(Color.green);
            textArea.setBackground(Color.black);
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
            textArea.setEditable(false);
            add(textArea);

            JScrollPane scroll = new JScrollPane(textArea);
            scroll.setBounds(0, 0, 800, 520);
            scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS); // Adiciona barra de rolagem
            scroll.setBorder(null);
            add(scroll);

            menu();

            // Cria uma caixa de texto
            textField = new JTextField();
            textField.setBounds(0, 520, 600, 40);
            textField.setFont(new Font("Courier New", Font.PLAIN, 20));
            textField.setForeground(Color.green);
            textField.setBackground(Color.black);
            textField.setCaretColor(Color.green);
            textField.setBorder(BorderFactory.createLineBorder(Color.green));
            add(textField);

            // Adicionando KeyListener para enviar o texto ao pressionar Enter
            textField.addKeyListener(new java.awt.event.KeyAdapter() { // Adiciona um evento de teclado
                public void keyPressed(java.awt.event.KeyEvent evt) { // Função para verificar se a tecla pressionada é Enter
                    if (evt.getKeyCode() == KeyEvent.VK_ENTER) { // Verifica se a tecla pressionada é Enter
                        String mainInput = textField.getText();
                        textField.setText("");
                        mainInput(mainInput);
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
                String mainInput = textField.getText();
                textField.setText("");
                mainInput(mainInput);
            });

            repaint(); // Redesenha a tela
            revalidate(); // Revalida a tela
        }

        void autoScroll() {
            textArea.setCaretPosition(textArea.getDocument().getLength());
        }

        // Métodos para exibir menu
        void menu() {
            textArea.setText(
                    "THE AGE OF ETHERIS\n\n" +
                            "Selecione uma opção:\n" +
                            "1. Instruções\n" +
                            "2. Jogar\n" +
                            "3. Créditos\n" +
                            "4. Sair\n"
            );
            gameState = "menu"; // Definindo o estado inicial como "menu"
        }

        // Metodo para receber a entrada do usuário
        void mainInput(String input){
            switch (gameState) {
                case "menu":
                    menuInput(input);
                    break;
                case "instructions":
                    instructionsInput(input);
                    break;
                case "arithmeticSystem":
                    arithmeticSystemInput(input);
                    break;
                case "battleSystem":
                    battleSystemInput(input);
                    break;
                case "initialHistory":
                    inputClass(input);
                    break;
                case "playerName":
                    inputName(input);
                    break;
                case "questionName":
                    questionName(input);
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
            }
        }

        // Metodo para receber a entrada do usuário no menu
        public void menuInput(String input){
            switch (input) {
                case "1", "Instruções", "instruções":
                    instructions();
                    break;
                case "2", "Jogar", "jogar":
                    initialHistory();
                    break;
                default:
                    textArea.append("Opção inválida.\n");
                    autoScroll();
                    break;
            }
        }

        // Métodos para exibir instruções
        void instructions(){
            textArea.setText(
                    "Seja bem vindo as instruções!\n" +
                            "Aqui você podera entender como funciona todos o sistema do jogo.\n" +
                            "Escolha uma opção:\n" +
                            "1. Sistema Aritmético\n" +
                            "2. Sistema de Batalha\n" +
                            "3. Voltar\n"
            );
            gameState = "instructions";
        }

        void instructionsInput(String input){
            switch (input) {
                case "1", "Sistema Aritmético", "sistema aritmético":
                    arithmeticSystem();
                    break;
                case "2", "Sistema de Batalha", "sistema de batalha":
                    battleSystem();
                    break;
                case "3", "Voltar", "voltar":
                    menu();
                    break;
                default:
                    textArea.append("Opção inválida.\n");
                    autoScroll();
                    break;
            }
        }

        void arithmeticSystem(){
            textArea.setText(
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
            gameState = "arithmeticSystem";
        }

        void arithmeticSystemInput(String input){
            switch (input) {
                case "1", "Voltar", "voltar":
                    instructions();
                    break;
            }
        }

        void battleSystem(){
            textArea.setText(
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
            gameState = "battleSystem"; // Definindo o estado como "battleSystem"
        }

        void battleSystemInput(String input){
            switch (input) {
                case "1", "Voltar", "voltar":
                    instructions();
                    break;
            }
        }
        // Fim dos métodos para exibir instruções

        RpgComponent rpgComponent = new RpgComponent();
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

            textArea.setText(inputBattle + battleOptions);
            gameState = "battle";
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
                    gameState = "responder";
                    textArea.append("\nDigite o número binário: ");
                    break;
                case "4":
                    menu();
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
                textArea.append(tabela);
                autoScroll();
            }
        }

        void calcular(){
            textArea.setText(
                    "Questão: " + randomQuestionBinary + "\n" +
                            "Insira um dos valores para faze a soma em decimal:\n" +
                            "1, 2, 4, 8, 16, 32, 64, 128, 256, 512\n\n" +
                            "Escolha uma das opções: \n" +
                            "0. Sair\n"
            );
            gameState = "calcular";
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
                    textArea.append("\n" + sum);
                    break;
                default:
                    textArea.append("\nEsse número não pertence a base binária.");
                    autoScroll();
            }
        }

        RpgComponent.Enemy enemy = rpgComponent.new Enemy(100, 10);

        void lutaAcertou(){
            enemy.life -= 50;
            if(enemy.life > 0){
                textArea.append("\nVocê acertou o inimigo!\n" +
                        "Vida do inimigo: " + enemy.life);
                autoScroll();
            } else {
                textArea.append(
                        "\nVida do inimigo: 0"
                );
                autoScroll();
            }
        }

        void verifyQuestion(String input){
            if(numBinary.equals(input)){
                lutaAcertou();
                if(enemy.life > 0){
                    newBattle();
                } else {
                    textArea.append(
                            "\n====================================" +
                            "\nVocê derrotou o inimigo!"
                    );
                    autoScroll();
                    secondBattle();
                }
            } else {
                textArea.append("\nVocê errou!");
                textArea.append(numBinary);
                autoScroll();
            }
        }

        // imprimi uma nova batalha
        void newBattle() {
            randomQuestionBinary = rpgComponent.questionBinary();
            numBinary = Integer.toBinaryString(randomQuestionBinary);
            textArea.append("\n\nConverta " + randomQuestionBinary + " para binário:\n" +
                    "Opções:\n" +
                    "1. Mostrar tabela de conversão\n" +
                    "2. Calcular\n");
            autoScroll();
            viewTable = true;
            gameState = "battle";
        }

        // Cria uma nova batalha
        void secondBattle() {
            enemy.life = 100;
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
            textArea.setText(
                    "Aenor:\n" +
                    "Finalmente você acordou, Equilibrador. O destino de Etheris depende de você. " +
                            "A Ruptura destruiu o tempo e o espaço, e agora você deve restaurar o equilíbrio." +
                            "\nQual será sua linhagem?\n\n" +
                            "1. Elfo: Habilidades de agilidade e conexão espiritual.\n" +
                            "2. Mago: Mestre em manipulação de feitiços.\n" +
                            "3. Guerreiro: Força bruta e resistência física.\n"
            );
            gameState = "initialHistory";
        }

        // Variaveis para armazenar a classe e o nome do jogador
        String playerClasse;
        String playerName;

        // 1º ATO da historia do jogo
        void inputClass(String input) {
            switch (input) {
                case "1":
                    textArea.append(
                            "\nNarrador: " +
                            "\nVocê escolheu a linhagem dos Elfos, seres ancestrais conectados ao espírito da natureza. " +
                                    "Sua agilidade e habilidades místicas serão essenciais para enfrentar os desafios à frente. " +
                                    "Somente os Elfos compreendem a verdadeira essência do equilíbrio entre as forças naturais e espirituais de Etheris."
                    );
                    playerClasse = "Elfo";
                    playerName();
                    break;
                case "2":
                    textArea.append(
                            "\nNarrador: " +
                            "\nVocê escolheu a linhagem dos Magos, mestres das artes arcanas e guardiões dos segredos da magia." +
                                    " Seu conhecimento e controle sobre os feitiços será a chave para domar as distorções da Ruptura. " +
                                    "Mas lembre-se, com grande poder vem grande responsabilidade."
                    );
                    playerClasse = "Mago";
                    playerName();
                    break;
                case "3":
                    textArea.append(
                            "\nNarrador: " +
                                    "\nVocê escolheu a linhagem dos Guerreiros, heróis de força incomparável e destemidos em batalha. " +
                                    "Com sua resistência e coragem, você será capaz de enfrentar até mesmo as criaturas mais temíveis que vagam pelas terras distorcidas de Etheris."
                    );
                    playerClasse = "Guerreiro";
                    playerName();
                    break;
                default:
                    textArea.append("Opção inválida.\n");
                    autoScroll();
                    break;
            }
        }

        void playerName(){
            textArea.append(
                    "\n\nAenor:" +
                    "\nAgora, diga-me, qual é o nome do " + playerClasse + " que restaurará o equilíbrio?" + "\nDigite o seu nome:"
            );
            gameState = "playerName";
        }

        void inputName(String input){
            playerName = input;
            textArea.append(
                    "\n\nSeu nome é " + playerName + ", o " + playerClasse + "?" +
                            "\n1. Sim" +
                            "\n2. Não"
            );
            gameState = "questionName";
        }

        void questionName(String input) {
            switch (input) {
                case "1":
                    textArea.setText(
                            "Aenor:\n" +
                            "Muito bem, " + playerName + ". A Ruptura pode ter distorcido o tempo e o espaço, mas sua missão é clara. " +
                                    "Arcadelis, a última cidade sagrada, é onde você encontrará respostas. " +
                                    "E lá, o Conselho do Véu lhe explicará como reunir os artefatos necessários para salvar Etheris"
                    );
                    dialog();
                    break;
                case "2":
                    textArea.setText(
                            "Aenor:\n" +
                            "Ah, parece que houve um engano. O nome que carrega é de grande importância para sua jornada, Equilibrador. " +
                                    "Vamos corrigir isso. Qual será o verdadeiro nome pelo qual você será conhecido?" +
                                    "\n\nDigite o seu nome:"
                    );
                    gameState = "playerName";
                    break;
                default:
                    textArea.append("\nOpção inválida.");
                    autoScroll();
                    break;
            }
        }

        void dialog(){
            textArea.append(
                    "\n\n1. E se o Conselho do Véu não puder me ajudar?\n" +
                            "2. Quanto tempo temos até que Etheris seja completamente destruída?\n" +
                            "3. Como saberei que posso confiar neles?\n" +
                            "4. O que acontecerá se eu falhar?\n" +
                            "5. Estou pronto. Como chegamos a Arcadelis?\n"
            );
        }

    }
}



