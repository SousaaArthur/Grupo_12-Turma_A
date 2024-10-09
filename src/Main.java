import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Collections;
import java.util.ArrayList;

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
            String menu = "THE AGE OF ETHERIS\n\n" +
                    "Selecione uma opção:\n" +
                    "1. Instruções\n" +
                    "2. Jogar\n" +
                    "3. Créditos\n" +
                    "4. Sair\n";
            textArea.setText(menu);
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
                case "gameStart":
                    gameStartInput(input);
                    break;
                case "battle":
                    battleInput(input);
                    verifyQuestion(input);
                    break;
                case "calcular":
                    calcularInput(input);
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
                    battle();
                    break;
                default:
                    textArea.append("Opção inválida.\n");
                    autoScroll();
                    break;
            }
        }

        // Métodos para exibir instruções
        void instructions(){
            String instructions = "Seja bem vindo as instruções!\n" +
                    "Aqui você podera entender como funciona todos o sistema do jogo.\n" +
                    "Escolha uma opção:\n" +
                    "1. Sistema Aritmético\n" +
                    "2. Sistema de Batalha\n" +
                    "3. Voltar\n";
            textArea.setText(instructions);
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
                    break;
            }
        }

        void arithmeticSystem(){
            String arithmeticSystem = "Sistema Aritmético\n" +
                    "O sistema aritmético é um sistema de cálculos de aritmética binaria, octal e hexadeciamal que você terá que resolver para avançar no jogo.\n\n" +
                    "Como vai funcionar?\n" +
                    "Bom, em várias partes do jogo você terá que resolver cálculos aritméticos para avançar, por exemplo, se você encontrar um baú trancado, você terá que resolver um cálculo para abri-lo.\n" +
                    "Os cáculos serão de conversão de bases numéricas para binário e de binário para decimal.\n\n" +
                    "Conversões de texto para binário e de binário para texto.\n" +
                    "Exemplo: 'Hello' em binário é '01001000 01100101 01101100 01101100 01101111'.\n\n" +
                    "Opções:\n" +
                    "1. Voltar\n"
                    ;
            textArea.setText(arithmeticSystem);
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
            String battleSystem = "Sistema de Batalha\n" +
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
                    ;
            textArea.setText(battleSystem);
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

        // Métodos para iniciar o jogo
        void gameStart(){
            String history = "Escolha a sua classe:\n" +
                    "1. Guerreiro\n" +
                    "2. Mago\n" +
                    "3. Elfa\n";

            textArea.setText(history);
            gameState = "gameStart";
        }

        void gameStartInput(String input){

        }

        RpgComponent rpgComponent = new RpgComponent();
        int questionBattleBinary = rpgComponent.questionBinary();
        int question = questionBattleBinary;
        String numBinary = Integer.toBinaryString(question);

        // Metodo para iniciar a batalha
        void battle(){
            String battle = "Enquanto você procurava por um dos artefatos, você se depara com um inimigo.\n" +
                    "Você está preparado para a batalha?\n" +
                    "Resolva o desafio para acerta-lo.\n\n" +
                    "Converta " + question + " para binairo:\n" +
                    "Opções:\n" +
                    "1. Mostrar tabela de conversão\n" +
                    "2. Calcular\n";
            textArea.setText(battle);
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
            }
        }

        void calcular(){

            String textCalc = "Questão: " + question + "\n" +
                    "Insira um dos valores para faze a soma em decimal:\n" +
                    "1, 2, 4, 8, 16, 32, 64, 128, 256, 512\n\n" +
                    "Escolha uma das opções: \n" +
                    "0. Sair\n";
            textArea.setText(textCalc);
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
                    battle();
                    viewTable = true;
                    break;
                case "1", "2", "4", "8",  "16", "32",  "64", "128", "256", "512":
                    sum += num;
                    textArea.append("\n" + sum);
                    break;
                default:
                    textArea.append("\nEsse número não pertence a base binária.");
            }
        }

        RpgComponent.Enemy enemy = rpgComponent.new Enemy(100, 10);

        void lutaAcertou(){
            enemy.life -= 10;
            textArea.append("\nVocê acertou o inimigo!\n" +
                    "Vida do inimigo: " + enemy.life);
            if(enemy.life > 0){
                newBattle();
            } else {
                textArea.append("\nVocê derrotou o inimigo!");
            }
        }

        void verifyQuestion(String input){
            if(numBinary.equals(input)){
                lutaAcertou();
            } else {
                textArea.append("\nVocê errou!");
            }
        }

        void newBattle(){
            questionBattleBinary = rpgComponent.questionBinary();
            question = questionBattleBinary;
            numBinary = Integer.toBinaryString(question);
            textArea.append("\n\nConverta " + question + " para binário:\n" +
                    "Opções:\n" +
                    "1. Mostrar tabela de conversão\n" +
                    "2. Calcular\n");
        }
    }
}



