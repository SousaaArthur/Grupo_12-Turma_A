import javax.swing.*; // Importa todas as classes do pacote javax.swing
import java.awt.*; // Importa todas as classes do pacote java.awt
import java.awt.event.KeyEvent; // Importa a classe KeyEvent do pacote java.awt.event
import java.net.URL;
import java.util.ArrayList; // Importa a classe ArrayList do pacote java.util
/*
*
* GRUPO 12 - TURMA A
* INTEGRANTES: Arthur Araújo e Maria Eduarda
*
*/
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
                            "4. Sair\n\n" +

                        "***AVISO!!! CASO NÃO ESTEJA SAINDO SOM OU MUSICA RECOMENDO QUE UTILIZE O VSCODE(Visual Studio Code) PARA RODAR O JOGO. PARA PODER ESCUTAR E JOGAR O JOGO COM A MUSICA***\n"
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
                case "instructionText":
                    instructionTextInput(input);
                    break;
                case "instructionsRecommendations":
                    instructionsRecommendationsInput(input);
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
                case "entradaProximaCenaAto1Cena8":
                    entradaAto1Cena9(input);
                    break;
                case "ato1Cena10":
                    entradaAto1Cena10(input);
                    break;
                case "ato1Cena11":
                    entradaAto1Cena11(input);
                    break;
                case "ato1Cena12":
                    entradaAto1Cena12(input);
                    break;
                case "ato2Cena01":
                    entradaAto2Cena01(input);
                    break;
                case "ato2Cena02":
                    entradaAto2Cena02(input);
                    break;
                case "ato2Cena03":
                    opcaoLutarMilira(input);
                    break;
                case "lutaContraMilira":
                    batalharMilira();
                    break;
                case "batalharMilira":
                battleInput(input, "verificaRespostaAto2Cena03");
                    break;
                case "verificaRespostaAto2Cena03":
                    verifyQuestion(input, "batalharMilira", "entradaProximaCenaAto2Cena03",
                            "Ao derrotar Milira, a Guardiã da Floresta, você sente uma onda de energia e vitalidade fluir em seu corpo. " +
                                    "A escuridão que a envolvia se dissipa, revelando um caminho claro e sereno à sua frente. " +
                                    "Você percebe que está mais próximo de Arcadelis, e a esperança de restaurar o equilíbrio em Etheris se fortalece em seu coração.");
                    break;
                case "entradaProximaCenaAto2Cena03":
                    entradaAto2Cena03(input);
                    break;
                case "ato2Cena04":
                    entradaAto2Cena04(input);
                    break;
                case "ato2Cena05":
                    entradaAto2Cena05(input);
                    break;
                case "ato2Cena06":
                    entradaAto2Cena06(input);
                    break;
                case "ato2Cena07":
                    entradaAto2Cena07(input);
                    break;
                case "ato2Cena08":
                    entradaAto2Cena08(input);
                    break;
                case "ato2Cena09":
                    opcaoLutarMagosAntigos(input);
                    break;
                case "lutaContraMagosAntigos":
                    batalharMagosAntigos();
                    break;
                case "batalharMagosAntigos":
                    battleInput(input, "verificaRespostaAto2Cena09");
                    break;
                case "verificaRespostaAto2Cena09":
                    verifyQuestion(input, "batalharMagosAntigos", "opcaoLutaContraEryndor",
                            "Com a queda de Lunareth, sua magia arcana se dissipa como poeira ao vento. Antes de desaparecer, ele o encara com respeito e adverte:\n" +
                                    "'Prepare-se, Equilibrador. Eryndor, o Mestre da Magia Negra, já o aguarda.'\n" +
                                    "As sombras ao redor se intensificam, e um frio inquietante toma conta da torre. " +
                                    "O próximo desafio será ainda mais sombrio e desafiador. Siga em frente, pois a luta está apenas começando.\n\n"
                            );
                    break;
                case "opcaoLutaContraEryndor":
                    opcaoLutaContraEryndor(input);
                    break;
                case "lutarContraEryndor":
                    batalharEryndor();
                    break;
                case "batalharEryndor":
                    battleInput(input, "verificaRespostaAto2Cena10");
                    break;
                case "verificaRespostaAto2Cena10":
                    verifyQuestion(input, "batalharEryndor", "opcaoLutarContraKaeltharion",
                            "Com o último golpe, Eryndor cai de joelhos, sua figura sombria se desfazendo em névoa negra que se dispersa pelo ar. Sua voz ecoa, profunda e carregada de resignação:\n" +
                                    "Você derrotou a escuridão, mas a luz pode ser igualmente implacável. Prepare-se, Equilibrador, pois Kaeltharion, o Mestre da Magia Divina, será seu próximo oponente. Que a sua força esteja à altura do julgamento divino.\n" +
                                    "Enquanto a escuridão recua, a atmosfera da torre muda. Uma luz dourada começa a emanar dos corredores à frente, trazendo consigo uma sensação de peso e reverência. O último desafio o aguarda.\n\n"
                    );
                    break;
                case "opcaoLutarContraKaeltharion":
                    opcaoLutarContraKaeltharion(input);
                    break;
                case "lutarContraKaeltharion":
                    batalharKaeltharion();
                    break;
                case "batalharKaeltharion":
                    battleInput(input, "verificaRespostaAto2Cena11");
                    break;
                case "verificaRespostaAto2Cena11":
                    verifyQuestion(input, "batalharKaeltharion", "entradaProximaCenaAto2Cena11",
                            "Com o último golpe, Kaeltharion cai de joelhos, sua figura radiante se desfazendo em luz dourada que se dispersa pelo ar. Sua voz ecoa, suave e carregada de respeito:\n" +
                                    "Você superou a luz e a escuridão, Equilibrador. Sua jornada está apenas começando, e o destino de Etheris depende de você. " +
                                    "Agora, você está pronto para enfrentar o desafio final e restaurar o equilíbrio em Etheris. Que a sua coragem e determinação o guiem até o fim.\n\n"
                    );
                    break;
                case "entradaProximaCenaAto2Cena11":
                    entradaProximaCenaAto2Cena11(input);
                    break;
                case "ato2Cena12":
                    entradaProximaCenaAto2Cena12(input);
                    break;
                case "ato2Cena13":
                    entradaProximaCenaAto2Cena13(input);
                    break;
                case "ato2Cena14":
                    entradaProximaCenaAto2Cena14(input);
                    break;
                case "ato2Cena15":
                    entradaProximaCenaAto2Cena15(input);
                    break;
                case "opcaoLutarContraTitanus":
                    opcaoLutarContraTitanus(input);
                    break;
                case "lutarContraTitanus":
                    batalharTitanus();
                    break;
                case "batalharTitanus":
                    battleInput(input, "verificaRespostaAto2Cena15");
                    break;
                case "verificaRespostaAto2Cena15":
                    verifyQuestion(input, "batalharTitanus", "entradaProximaCenaAto2Cena16",
                            "Com o último golpe, Titanus cai de joelhos, sua figura imponente se desfazendo em poeira que se dispersa pelo ar. " +
                                    "O chão treme e a torre estremece, mas você permanece firme, sua determinação inabalável. " +
                                    "A escuridão e a luz se dissipam, revelando um brilho intenso que emana do centro da torre. " +
                                    "O equilíbrio em Etheris foi restaurado, e a paz e a harmonia retornam à terra. " +
                                    "Sua jornada está completa, Equilibrador. O destino de Etheris está em suas mãos, e a esperança de um novo amanhecer brilha em seu coração.\n\n"
                    );
                    break;
                case "entradaProximaCenaAto2Cena16":
                    entradaProximaCenaAto2Cena16(input);
                    break;
                case "ato2Cena17":
                    entradaProximaCenaAto2Cena17(input);
                    break;
                case "ato2Cena18":
                    entradaProximaCenaAto2Cena18(input);
                    break;
                case "ato3Cena01":
                    entradaProximaCenaAto3Cena01(input);
                    break;
                case "ato3Cena02":
                entradaProximaCenaAto3Cena02(input);
                break;
                case "opcaoLutarContraRuptura":
                    opcaoLutarContraRuptura(input);
                    break;
                case "lutarContraRuptura":
                    batalharRuptura();
                    break;
                case "batalharRuptura":
                    battleInput(input, "verificaRespostaAto3Cena02");
                    break;
                case "verificaRespostaAto3Cena02":
                    verifyQuestion(input, "batalharRuptura", "entradaProximaCenaAto3Cena03",
                            "Com o último golpe, Ruptura cai de joelhos, sua figura sombria se desfazendo em fumaça que se dispersa pelo ar. " +
                                    "O chão treme e a torre estremece, mas você permanece firme, sua determinação inabalável. " +
                                    "A escuridão e a luz se dissipam, revelando um brilho intenso que emana do centro da torre. " +
                                    "O equilíbrio em Etheris foi restaurado, e a paz e a harmonia retornam à terra. " +
                                    "Sua jornada está completa, Equilibrador. O destino de Etheris está em suas mãos, e a esperança de um novo amanhecer brilha em seu coração.\n\n"
                    );
                    break;
                case "entradaProximaCenaAto3Cena03":
                    entradaProximaCenaAto3Cena03(input);
                    break;
                case "ato3Cena04":
                    entradaProximaCenaAto3Cena04(input);
                    break;
                case "fim":
                    fim(input);
                    break;
            }
        }

        void invalidInput(){
            outputTextArea.append("\nOpção inválida.");
            autoScroll();
        }

        // Método para exibir texto com efeito de digitação
        void displayTextWithTypingEffect(String text) {
            new Thread(() -> {
            inputTextField.setEditable(false); // Desabilita a edição da caixa de texto
            inputTextField.removeKeyListener(inputTextField.getKeyListeners()[0]); // Remove o KeyListener
            outputTextArea.setText("");
            for (char c : text.toCharArray()) {
                outputTextArea.append(String.valueOf(c));
                autoScroll();
                try {
                Thread.sleep(8); // Ajuste o tempo de espera para controlar a velocidade da digitação
                } catch (InterruptedException e) {
                e.printStackTrace();
                }
            }
            inputTextField.setEditable(true); // Habilita a edição da caixa de texto após a digitação
            inputTextField.addKeyListener(new java.awt.event.KeyAdapter() { // Adiciona novamente o KeyListener
                public void keyPressed(java.awt.event.KeyEvent evt) { // Função para verificar se a tecla pressionada é Enter
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) { // Verifica se a tecla pressionada é Enter
                    String mainInput = inputTextField.getText();
                    inputTextField.setText("");
                    handleUserInput(mainInput);
                }
                }
            });
            }).start();
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
                            "3. Instruções de digitação (IMPORTANTE)\n" +
                            "4. Recomendações (IMPORTANTE)\n" +
                            "5. Voltar\n"
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
                    instructionText();
                    if (soundSelect != null) {
                        rpgComponent.new MusicPlayer(soundSelect.getPath()).playOnce(soundSelect.getPath());
                    } else {
                        System.out.println("Efeito sonoro não encontrado!");
                    }
                    break;
                case "4":
                    instructionsRecommendations();
                    if (soundSelect != null) {
                        rpgComponent.new MusicPlayer(soundSelect.getPath()).playOnce(soundSelect.getPath());
                    } else {
                        System.out.println("Efeito sonoro não encontrado!");
                    }
                    break;
                case "5":
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
                            "Exemplo: '10' em binário é '1010'.\n\n" +
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

        void instructionText(){
            outputTextArea.setText(
                "COMO FUNCIONA O SISTEMA DE DIGITAÇÃO DO TEXTO?\n\n" +
                "O sistema de digitação do texto é um sistema que simula a digitação de um texto na tela, letra por letra, para dar um efeito de digitação.\n\n" +
                "Quando o texto estiver sendo exibido, você não poderá digitar nada na caixa de texto. Espere até que o texto seja exibido completamente para poder digitar.\n\n" +
                "Depois que o texto for exibido completamente, você poderá digitar na caixa de texto e pressionar Enter para enviar a mensagem.\n\n" +

                "Opções:\n" +
                "1. Voltar\n"
            );
            currentGameState = "instructionText";
        }

        void instructionTextInput(String input){
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

        void instructionsRecommendations(){
            outputTextArea.setText(
                    "RECOMENDAÇÕES\n\n" +
                    "Para o jogo ser mais diverto, utilize fones de ouvido para ouvir os efeitos sonoros e a música de fundo.\n" +
                    "Caso não esteja saindo som ou musica recomendo que ***utilizer o VSCODE(Visual Studio Code)*** para rodar o jogo. Para poder escutar e jogar o jogo com a musica que fara grande diferença no final do jogo\n\n" +
                    "Opções:\n" +
                    "1. Voltar\n"
            );
            currentGameState = "instructionsRecommendations";
        }

        void instructionsRecommendationsInput(String input){
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
                    "==================================BATALHA====================================\n";

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
            playerLife+=3;
            playerAttack+=3;
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
                                    "Vida do inimigo: " + urrentEnemy.life + " -" + playerAttack +
                                    "\nSua vida: " + playerLife + " +3" +
                                    "\nSua força: " + playerAttack + " +3"
                    );
                    break;
                case "Mago":
                    outputTextArea.append(
                            "Você conjura um feitiço arcano e lança uma bola de fogo contra o inimigo, causando " + playerAttack + " de dano.\n" +
                                    "Vida do inimigo: " + urrentEnemy.life + " -" + playerAttack +
                                    "\nSua vida: " + playerLife + " +3" +
                                    "\nSua força: " + playerAttack + " +3"
                    );
                    break;
                case "Guerreiro":
                    outputTextArea.append(
                            "Com sua força bruta, você desfere um golpe poderoso com sua espada contra o inimigo, causando " + playerAttack + " de dano.\n" +
                                    "Vida do inimigo: " + urrentEnemy.life + " -" + playerAttack +
                                    "\nSua vida: " + playerLife + " +3" +
                                    "\nSua força: " + playerAttack + " +3"
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
                                "Sua vida: " + playerLife + " -" + urrentEnemy.attack
                );
                autoScroll();
            } else {
                outputTextArea.append(
                        "\n==================================PERDEU====================================\n" +
                        "\nVocê acidentalmente errou o ataque contra o inimigo! O inimigo se aproveita da oportunidade e te acerta com um ataque...\n" +
                                "Sua vida: " + playerLife + " -" + urrentEnemy.attack + "\n" +
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
                            "\n==================================VENCEU====================================\n" +
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
            String text = 
            "Aenor:" + 
            "\nCorajoso como esperado, " + playerName + " o " + playerClasse + ". No entanto, a jornada Arcadelis não será facíl. As terras de Etheris foram devastadas pela Ruptura, e ocaminho que antes era claro agora é fragmentado por distorções do tempo e do espaço.\n" +
            playerName + " o " + playerClasse + " a jornada até Arcadelis é perigosa, mas necessária. Você tem dois caminhos diante de si, cada um com seus próprios perigos e recompensas. Ouça com atenção. \n\n" +
            "A primeira missão é atravessar a Floresta das Almas Perdidas, onde os espíritos das eras passadas vagam sem descanso. Esse caminho é traiçoeiro e silencioso, mas os espíritos tentarão invadir sua mente, testando sua determinação. Se conseguir passar pela floresta, você poderá encontrar o Santuário do Véu, onde segredos antigos podem ser revelados a você. No entanto, será testado espiritualmente, e aqueles que falham perdem-se para sempre. \n\n" +
            "1. Florestas das Almas Perdidas\n";
            displayTextWithTypingEffect(text);
            currentGameState = "ato1_cena2";
        }

        //  Floresta das Almas Perdidas
        void entradaAto1Cena2 (String input){
            switch (input) {
                case "1":
                    String text = "O Equilibrador escolheu o caminho da Floresta das Almas Perdidas. À medida que você avança, a temperatura cai, e a névoa começa a envolver tudo ao seu redor. As árvores, com troncos retorcidos e galhos contorcidos, parecem sussurrar em uma língua antiga, suas sombras se movendo de maneira inquietante à medida que você segue adiante. \n\n" + 
                    "A luz do sol se apaga lentamente, e o mundo parece perder suas cores, ficando cada vez mais cinza e desolado. Não há som de vida, apenas o sussurro dos espíritos que vagam pela floresta. Suas presenças são como sombras de eras passadas, almas que não encontraram descanso desde a Ruptura. Eles observam cada movimento, silenciosamente esperando por aqueles que falham na resistência mental para reivindicar suas mentes e suas almas.\n\n" +
                    "1. Continuar";
                    displayTextWithTypingEffect(text);
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
            String text = 
            "Narrador: \nCaminhando mais fundo na Floresta das Almas Perdidas, você sente uma presença intensa. O ar fica denso e uma estranha sensação de frio envolve seu corpo. Das sombras retorcidas de uma árvore, uma figura se destaca: um espírito guerreiro, conhecido como o Guardião Espectral de Selendis. \n\n" +
            "Descrição do Inimigo: \nO Guardião Espectral é uma figura encapuzada, com um manto esfarrapado que parece feito de névoa. Ele carrega uma lâmina longa, desgastada e corroída, mas com um brilho sinistro nas bordas. Seus olhos são dois pontos de luz espectral, frios e vazios, como se sua alma tivesse sido drenada há séculos. Suas mãos são garras translúcidas, e ele flutua levemente acima do chão, deixando um rastro de névoa. Sua voz ecoa como um murmúrio antigo, repetindo palavras desconexas em uma língua esquecida.\n\n" +
            "O Guardião Espectral: \nNinguém passa… ninguém escapa… aquele que busca Arcadelis, prove sua coragem ou junte-se às almas errantes desta floresta.\n\n" +
            "1. Lutar\n";

            displayTextWithTypingEffect(text);
            currentGameState = "option2Ato1Cena4";
        }

        void option2Ato1Cena4(String input){
            switch (input){
                case "1":
                    String text =
                    "Narrador:\n" +
                            "Com determinação, você encara o Guardião Espectral. " +
                            "As sombras em volta dele parecem se agitar, como se a própria floresta o tivesse escolhido para defender suas profundezas. " +
                            "Seus olhos vazios brilham com um tom pálido e ameaçador, e sua figura espectral flutua levemente sobre o solo, emoldurada pela névoa fria e densa da Floresta das Almas Perdidas.\n" +
                            "O Guardião Espectral ergue sua mão esquelética e uma lâmina etérea se materializa em sua garra, pulsando com uma energia misteriosa e antiga. Sua voz ecoa, reverberando com a força de mil almas perdidas:\n" +
                            "\nGuardião Espectral:\n" +
                            "Corajoso, mas imprudente, Equilibrador. Somente aqueles que enfrentam a morte com valor merecem cruzar para além desta floresta. Mostre-me a força da sua alma!\n\n" +
                            "Narrador:\n " +
                            "O ar ao seu redor fica mais pesado, e o frio penetra até os ossos, testando sua resistência. A névoa, que antes o ocultava, agora parece puxá-lo para a batalha. A floresta observa, silenciosa, e os sussurros das almas perdidas intensificam-se, como se aguardassem para ver quem triunfará.\n\n" +
                            "1. Lutar contra o Guardião Espectral\n";

                    displayTextWithTypingEffect(text);
                    currentGameState = "opcaoLutarAto1Cena5";
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
                            "Guardião Espetral:\n" +
                            "Vida: " + urrentEnemy.life +
                    "\nAtaque: " + urrentEnemy.attack +
                    "\n\n" + playerName + " o " + playerClasse + ":\n" +
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

        void ato1Cena7(){
            String text = 
            "Narrador:\n" +
            "Após a intensa batalha, ou a astúcia de escapar, você segue pela trilha nebulosa da Floresta das Almas Perdidas. O caminho à frente continua envolto em uma penumbra inquietante, onde as sombras das árvores retorcidas parecem observá-lo com olhos invisíveis. O sussurro dos espíritos diminui, como se até eles tivessem sido acalmados — ou advertidos — pela sua passagem.\n\n" +
            "A bruma que antes ocultava tudo ao redor começa a clarear aos poucos, revelando uma luz distante, fraca, mas constante, indicando que o final da floresta talvez esteja próximo. Em cada passo, o peso das eras perdidas parece diminuir, enquanto o silêncio, antes opressivo, se transforma em uma quietude mais serena.\n\n" +
            "Com a saída quase ao alcance, você sente que seu caminho até Arcadelis não está longe. Mas, em um lugar como este, qualquer alívio pode ser passageiro.\n\n" +
            "1. Continuar";
            displayTextWithTypingEffect(text);
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
            String text = 
            "Narrador:\n" +
            "Das sombras mais densas da floresta, emerge uma figura sinistra. Seu corpo é uma mistura de ossos e vapor etéreo, formando uma silhueta disforme e espectral. Ele se arrasta silenciosamente, como se estivesse flutuando, seus braços estendendo-se em garras retorcidas que parecem capazes de atravessar o próprio véu da realidade. Sua cabeça, envolta em rachaduras pulsantes com uma luz azul-gélida, transmite um frio penetrante e inumano\n\n" +
            "Essa criatura, conhecida como Vulto das Lamentações, carrega a energia inquieta das almas que nunca encontraram paz. Ao seu redor, o ar fica pesado, os murmúrios dos espíritos aumentam, ecoando com a dor dos tempos esquecidos. Ele avança lentamente, bloqueando seu caminho como o último teste antes de deixar a floresta. Suas intenções são claras: apenas aqueles fortes o suficiente sobreviverão a sua presença avassaladora.\n\n" +
            "1. Lutar\n";
            displayTextWithTypingEffect(text);
            currentGameState = "opcaoLutarAto1Cena8";
        }

        void entradaAto1Cena8(String input){
            switch (input) {
                case "1":
                    String text = 
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
                            "1. Lutar contra o Vulto das Lamentações\n";
                    displayTextWithTypingEffect(text);
                    currentGameState = "opcaoBatalharAto1Cena9";
                    break;
                default:
                    invalidInput();
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
                            "Vulto das Lamentações:\n" +
                            "Vida: " + urrentEnemy.life +
                            "\nAtaque: " + urrentEnemy.attack +
                            "\n\n" + playerName + " o " + playerClasse + ":\n" +
                            "Sua vida: " + playerLife +
                            "\nSua força: " + playerAttack + "\n\n", "batalharVultoLamentacoes"
            );
        }

        void entradaAto1Cena9(String input){
            switch (input) {
                case "1":
                String text = 
                "Narrador: \n" +
                        "Após o que pareceu uma eternidade, você finalmente avista uma saída à frente. A névoa densa da Floresta das Almas Perdidas começa a se dissipar, e a luz, antes apagada, retorna timidamente entre as árvores retorcidas. Os sussurros das almas, que o acompanharam por todo o caminho, tornam-se cada vez mais distantes, até desaparecerem completamente, como se a floresta reconhecesse que sua força foi suficiente para sobreviver ao seu domínio sombrio.\n\n" +
                        "Ao cruzar os limites da floresta, uma brisa fresca e revigorante o recebe. O ar aqui é diferente — puro e cheio de promessa, como se o próprio mundo estivesse celebrando sua saída. Atrás de você, a entrada da floresta parece desaparecer entre as sombras, um lembrete de que poucos conseguem vencê-la.\n\n" +
                        "Diante de seus olhos, você vê pela primeira vez a silhueta de Arcadelis, a cidade protegida por uma barreira mágica que brilha com uma luz dourada. Suas torres, embora desgastadas pelo tempo e pela Ruptura, permanecem de pé, como um símbolo de resistência em um mundo fragmentado.\n\n" +
                        "1. Continuar";
                displayTextWithTypingEffect(text);
                    currentGameState = "ato1Cena10";
                break;
                default:
                    invalidInput();
                    break;
            }
        }

        void entradaAto1Cena10(String input){
            switch (input) {
                case "1":
                String text = 
                "Narrador: \n" +
                        "Após um breve descanso em Arcadelis, você sente o chamado do seu destino mais uma vez. Guiado por fragmentos de memórias e sussurros do equilíbrio, você decide ir até o lendário Santuário do Véu, um local sagrado onde os Guardiões dos Três Véus residem. É dito que esses Guardiões possuem o conhecimento das eras perdidas e orientam aqueles que carregam o fardo de restaurar Etheris.\n\n" +
                        "A jornada até o santuário não é longa, mas o peso da missão em seus ombros torna cada passo significativo. Ao se aproximar, a paisagem muda: a terra ao redor se torna pura e imaculada, como se o próprio mundo estivesse protegido pela presença dos Guardiões. O Santuário do Véu surge diante de você, uma estrutura majestosa esculpida em cristal e pedra antiga, pulsando com energia espiritual.\n\n" +
                        "1. Entrar no Santuário do Véu";
                displayTextWithTypingEffect(text);
                    currentGameState = "ato1Cena11";
                    break;
                default:
                    invalidInput();
                    break;
            }
        }

        void entradaAto1Cena11(String input){
            switch (input){
                case "1":
                String text = 
                "Narrador: \n" +
                        "As enormes portas do santuário se abrem sozinhas, revelando um salão iluminado por uma luz suave e dourada. No centro, os três Guardiões o aguardam. Suas figuras são imponentes e envoltas em mantos que representam as três eras: o Espírito Eterno, o Arcano Perdido e o Colosso Imortal. Suas vozes ecoam em uníssono, carregadas de sabedoria e autoridade:\n\n" +
                        "Guardiões: \n" +
                        "Bem-vindo, " + playerName + ", o " + playerClasse + ". Você provou sua coragem na Floresta das Almas Perdidas e alcançou Arcadelis. Mas seu caminho ainda está repleto de desafios. Nós, os Guardiões dos Três Véus, revelaremos sua próxima missão, que o aproximará dos artefatos necessários para restaurar Etheris. Prepare-se, pois o equilíbrio exige mais do que força — ele exige sacrifício, sabedoria e determinação\n\n" +
                        "Narrador: \n" +
                        "Narrador:\n" +
                        "Os Guardiões, posicionados em um triângulo ao seu redor, começam a canalizar sua energia. O salão é preenchido por símbolos arcanos que flutuam no ar, cada um brilhando com uma cor distinta: dourado, prateado e carmesim. O Véu do Espírito fala primeiro, sua voz suave e melancólica como uma canção antiga:\n\n" +
                        "Guardião do Espírito:\n" +
                        "O próximo passo de sua jornada o levará até o Selendis, onde o tempo e a realidade se entrelaçam. Lá repousa o fragmento do Cetro do Espírito Eterno, um dos artefatos que você busca. No entanto, o caminho é traiçoeiro. Almas esquecidas vagam por lá, e enigmas antigos protegerão o artefato de olhos indignos.\n\n" +
                        "1. Continuar";
                displayTextWithTypingEffect(text);
                    currentGameState = "ato1Cena12";
                    break;
                default:
                    invalidInput();
                    break;
            }
        }

        void entradaAto1Cena12(String input){
            switch (input){
                case "1":
                String text = 
                "Narrador: \n" +
                        "Com a bênção dos Guardiões, você parte em direção a Selendis, a cidade dos espíritos. O caminho até lá é longo e repleto de desafios, mas a promessa de um artefato antigo e poderoso o impulsiona. A paisagem ao redor muda à medida que você avança, as sombras se tornando mais densas e os sussurros das almas mais intensos.\n\n" +
                        "Ao chegar aos portões de Selendis, você é recebido por uma visão assombrosa. A cidade, outrora grandiosa e majestosa, agora está envolta em uma névoa espectral, suas torres e muralhas desgastadas pelo tempo e pela Ruptura. As ruas estão desertas, mas você sente a presença de algo antigo e poderoso espreitando nas sombras.\n\n" +
                        "1. Continuar";
                displayTextWithTypingEffect(text);
                    currentGameState = "ato2Cena01";
                    break;
                default:
                    invalidInput();
                    break;
            }
        }

        void entradaAto2Cena01(String input){
            switch (input) {
                case "1":
                String text = 
                "Narrador: \n" +
                        "Ao adentrar os portões de Selendis, você sente uma presença inquietante ao seu redor. A cidade, outrora grandiosa e majestosa, agora está envolta em uma névoa espectral, suas torres e muralhas desgastadas pelo tempo e pela Ruptura. As ruas estão desertas, mas você sente a presença de algo antigo e poderoso espreitando nas sombras.\n\n" +
                        "A cidade parece viva, mas não da maneira que você esperava. As sombras se movem, as paredes sussurram e os espíritos das eras passadas parecem observá-lo com olhos vazios. Seu objetivo é claro: encontrar o fragmento do Cetro do Espírito Eterno e provar sua coragem diante dos desafios que Selendis reserva.\n\n" +
                        "1. Explorar a cidade\n";
                displayTextWithTypingEffect(text);
                    currentGameState = "ato2Cena02";
                    break;
                default:
                    invalidInput();
                    break;
            }
        }

        void entradaAto2Cena02(String input){
            switch (input){
                case "1":
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
                    String text = 
                    "Narrador: \n" +
                            "Enquanto você avança pelas ruas silenciosas de Selendis, um frio sobrenatural se apodera do ambiente. Cada passo parece ecoar por toda a cidade, e a névoa espectral começa a se agitar ao seu redor, como se algo estivesse despertando.\n\n" +
                            "De repente, uma figura emerge das sombras. É uma mulher alta e elegante, com traços esculpidos de uma beleza sombria. Sua pele tem o tom pálido da morte, e seus olhos brilham como lanternas fantasmagóricas. Seu vestido flutua ao redor dela como uma extensão da névoa, e uma aura opressiva permeia o ar.\n\n" +
                            "Milira, Guardiã das Almas Perdidas: \n" +
                            "Então, você é o " + playerName + ", o " + playerClasse + "... aquele que ousa desafiar os limites entre a vida e a morte. Selendis não é lugar para mortais, muito menos para aqueles que buscam os segredos do Cetro. Este fragmento não será entregue tão facilmente.\n\n" +
                            "Narrador: \n" +
                            "A voz de Milira é ao mesmo tempo doce e aterrorizante, reverberando como um coro de lamentações. Com um movimento gracioso, ela ergue a mão, e espíritos atormentados começam a se materializar ao seu redor, prontos para protegê-la a qualquer custo.\n\n" +
                            "Milira, Guardiã das Almas Perdidas: \n" +
                            "Se você deseja provar sua força e pureza de alma, terá que enfrentar não apenas a mim, mas também as dores de todos aqueles que se perderam neste lugar. Mostre-me se você realmente é digno de restaurar o equilíbrio de Etheris!\n\n" +
                            "Desafio: \n" +
                            "Prepare-se para enfrentar Milira, Guardiã das Almas Perdidas! Use sua estratégia e habilidades para superar seus ataques espectrais e resolver os enigmas que ela colocará em seu caminho. A batalha será árdua, mas a recompensa pode mudar o destino de Etheris.\n\n" +
                            "1. Lutar contra Milira\n";
                    displayTextWithTypingEffect(text);
                    currentGameState = "ato2Cena03";
                break;
            }
        }

        void opcaoLutarMilira(String input){
            switch (input){
                case "1":
                    batalharMilira();
                    break;
                default:
                    invalidInput();
                    break;
            }
        }

        void batalharMilira(){
            urrentEnemy.life = 45;
            urrentEnemy.attack = 20;
            randomQuestionBinary = rpgComponent.questionBinary();
            numBinary = Integer.toBinaryString(randomQuestionBinary);
            battle(
                    "Desafio:\n" +
                            "O combate com Milira, Guardiã das Almas Perdidas, começa! Use suas habilidades e estratégias com sabedoria para enfrentar seus ataques devastadores e resistir à aura opressiva que drena sua força.\n\n" +
                            "Milira é astuta e ágil, envolvendo-se em um véu de espíritos atormentados que dificultam seus movimentos e obscurecem seus ataques. Cada golpe desferido reduz sua conexão com os espíritos, mas cuidado — ela possui um poder único de manipular ilusões, confundindo sua percepção e testando sua determinação.\n\n" +
                            "Milira, Guardiã das Almas Perdidas:\n" +
                            "Vida: " + urrentEnemy.life +
                            "\nAtaque: " + urrentEnemy.attack +
                            "\n\n" + playerName + " o " + playerClasse + ":\n" +
                            "Sua vida: " + playerLife +
                            "\nSua força: " + playerAttack + "\n\n", "batalharMilira"
            );
        }

        void entradaAto2Cena03(String input){
            switch (input){
                case "1":
                String text = 
                "Narrador: \n" +
                        "A batalha com Milira, Guardiã das Almas Perdidas, é intensa e desafiadora. A cada golpe desferido, a aura opressiva ao seu redor parece se fortalecer, e os espíritos atormentados que a cercam se agitam com fúria. Milira, por sua vez, é ágil e astuta, desviando-se de seus ataques e lançando ilusões que testam sua determinação.\n\n" +
                        "No entanto, você não se deixa abater. Com coragem e determinação, você enfrenta a Guardiã, resistindo aos seus ataques e desvendando seus enigmas. A cada golpe bem-sucedido, a conexão de Milira com os espíritos se enfraquece, e sua aura opressiva começa a se dissipar.\n\n" +
                        "Finalmente, após uma batalha árdua, você desfere o golpe final. Milira, Guardiã das Almas Perdidas, cai de joelhos diante de você, sua aura desaparecendo lentamente. Seus olhos, uma vez brilhantes e aterrorizantes, agora refletem apenas a dor e a solidão de um espírito atormentado.\n\n" +
                        "Narrador: \n" +
                        "Com um suspiro final, Milira desaparece em uma névoa etérea, deixando para trás apenas um fragmento brilhante. É o fragmento do Cetro do Espírito Eterno, o artefato que você buscava. Com cuidado, você o recolhe, sentindo a energia espiritual pulsar em suas mãos.\n\n" +
                        "1. Continuar";
                displayTextWithTypingEffect(text);
                    currentGameState = "ato2Cena04";
                    break;
                default:
                    invalidInput();
                    break;
            }
        }

        void entradaAto2Cena04(String input){
            switch (input){
                case "1":
                String text = 
                "Narrador: \n" +
                        "Com o fragmento do Cetro do Espírito Eterno em mãos, você sente uma energia antiga e poderosa pulsar em seu ser. A cidade de Selendis, outrora envolta em sombras e mistérios, parece mais calma e serena, como se a presença de Milira tivesse finalmente encontrado a paz.\n\n" +
                        "O fragmento brilha com uma luz dourada, refletindo a pureza e a força do Espírito Eterno. Sua missão em Selendis está cumprida, mas o caminho à frente ainda é longo e repleto de desafios. Os Guardiões dos Três Véus aguardam sua volta, prontos para orientá-lo em sua próxima jornada.\n\n" +
                        "Narrador: \n" +
                        "Com determinação e coragem, você deixa Selendis para trás, levando consigo o fragmento do Cetro do Espírito Eterno. Seu destino o aguarda, e o equilíbrio de Etheris depende de sua força e sabedoria. A jornada para restaurar o mundo fragmentado está apenas começando.\n\n" +
                        "1. Continuar";
                displayTextWithTypingEffect(text);
                    currentGameState = "ato2Cena05";
                    break;
                default:
                    invalidInput();
                    break;
            }
        }

        void entradaAto2Cena05(String input){
            switch (input){
                case "1":
                String text = 
                "Narrador: \n" +
                        "Após deixar Selendis para trás, o ar ao seu redor parece mais leve, mas a sensação de responsabilidade se intensifica. Os Guardiões dos Três Véus manifestam-se novamente, suas vozes ecoando como um coro harmonioso, preenchendo sua mente com orientação e propósito.\n\n" +
                "Guardião do Véu do Espírito:\n\n" +
                        playerName + ", o " + playerClasse + ", você mostrou grande coragem e sabedoria em Selendis. Agora, com o fragmento do Cetro do Espírito Eterno em mãos, é hora de se voltar para o próximo artefato. Sua jornada o levará até a Torre de Arcanis, um lugar onde os segredos dos Arcanos antigos ainda resistem ao tempo. Lá, está guardado o Grimório Arcano Perdido, uma peça essencial para restaurar o equilíbrio de Etheris.\n\n" +
                "Narrador: \n" +
                        "As palavras dos Guardiões despertam uma mistura de apreensão e determinação em você. A Torre de Arcanis, envolta em mistérios e magia ancestral, será um desafio como nenhum outro. Protegido por feitiços esquecidos e criaturas de puro poder arcano, o Grimório não será conquistado facilmente.\n\n" +
                        "Com a orientação dos Guardiões e sua força renovada, você se prepara para enfrentar o desconhecido. O destino de Etheris depende de sua próxima vitória, e cada passo o aproxima mais do equilíbrio que tanto almeja restaurar.\n\n" +
                        "1. Continuar";
                displayTextWithTypingEffect(text);
                    currentGameState = "ato2Cena06";
                    break;
                default:
                    invalidInput();
                    break;
            }
        }

        void entradaAto2Cena06(String input){
            switch (input){
                case "1":
                String text = 
                "Narrador: \n" +
                        "Guiado pela orientação dos Guardiões dos Três Véus, você parte em direção à Torre de Arcanis, um lugar de magia e mistério onde os segredos dos Arcanos antigos ainda resistem ao tempo. A paisagem ao redor se transforma à medida que você avança, as sombras se tornando mais densas e a energia arcano pulsando no ar.\n\n" +
                        "Ao chegar aos portões da torre, você é recebido por uma visão impressionante. A estrutura é imponente e majestosa, suas paredes de pedra antiga brilhando com runas arcanas que parecem dançar à luz do sol. A entrada está protegida por um feitiço antigo, mas sua determinação e coragem o impulsionam a avançar.\n\n" +
                        "1. Continuar";
                displayTextWithTypingEffect(text);
                    currentGameState = "ato2Cena07";
                    break;
                default:
                    invalidInput();
                    break;
            }
        }

        void entradaAto2Cena07(String input){
            switch (input){
                case "1":
                String text = 
                "Narrador: \n" +
                        "Ao adentrar os portões da Torre de Arcanis, você sente uma energia arcano pulsante ao seu redor. A torre é um labirinto de salas e corredores, cada um repleto de segredos e perigos. As paredes brilham com runas antigas, e o ar está impregnado com a essência dos Arcanos, uma magia antiga e poderosa que desafia a compreensão.\n\n" +
                        "Seu objetivo é claro: encontrar o Grimório Arcano Perdido e provar sua coragem diante dos desafios que a torre reserva. Cada passo o leva mais fundo no coração da magia, onde criaturas arcanas e feitiços esquecidos aguardam para testar sua determinação.\n\n" +
                        "1. Explorar a Torre de Arcanis\n";
                displayTextWithTypingEffect(text);
                    currentGameState = "ato2Cena08";
                    break;
                default:
                    invalidInput();
                    break;
            }
        }

        void entradaAto2Cena08(String input){
            switch (input){
                case "1":
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
                    String text = 
                    "Narrador: \n" +
                            "Enquanto você avança pelos corredores cintilantes da Torre de Arcanis, um peso mágico quase tangível se acumula no ar. O ambiente parece vivo, com paredes adornadas por runas brilhantes que pulsavam em ritmos misteriosos. É como se a própria torre estivesse observando e julgando cada movimento seu.\n" +
                            "De repente, um brilho intenso ilumina a sala. Três figuras se materializam à sua frente — os Magos Antigos, mestres arcanos que transcenderam o tempo. Seus corpos são etéreos, envoltos em mantos ondulantes de energia mágica pura, e seus olhos brilham como estrelas, fixados em você com uma intensidade esmagadora.\n\n" +
                    "Eryndor, Mestre da Magia Negra:\n" +
                            "Então, você ousa entrar em nosso domínio, " + playerName + ", o " + playerClasse + "... Equilibrador. Buscando o Grimório Arcano Perdido? Tolice. Apenas aqueles dignos do verdadeiro poder arcano podem reivindicar tal artefato!\n\n" +
                    "Narrador: \n" +
                            "As vozes dos Magos Antigos se entrelaçam em uma harmonia assustadora, cada palavra carregada de uma magia que reverbera em sua alma. Eles levantam suas mãos, e o ar ao redor começa a crepitar com energia. Formas de feitiços antigos e incompreensíveis começam a se manifestar, enquanto a sala é envolta em um turbilhão de magia caótica.\n\n" +
                    "Lunareth, Mestre da Magia Arcana:\n" +
                            "Prepare-se, mortal. Nós somos a prova final entre você e o Grimório. Mostre-nos que é digno... ou seja consumido pela própria magia que deseja dominar!\n\n" +
                    "Desafio: \n" +
                            "Prepare-se para enfrentar os Magos Antigos, mestres arcanos que transcendem o tempo! Use sua astúcia e habilidades mágicas para superar seus feitiços e desvendar os segredos que eles guardam. A batalha será épica, mas a recompensa pode mudar o curso da magia em Etheris.\n\n" +
                    "1. Lutar contra os Magos Antigos\n";
                    displayTextWithTypingEffect(text);
                    currentGameState = "ato2Cena09";
                    break;
                default:
                    invalidInput();
                    break;
            }
        }

        void opcaoLutarMagosAntigos(String input){
            switch (input){
                case "1":
                    batalharMagosAntigos();
                    break;
                default:
                    invalidInput();
                    break;
            }
        }

        void batalharMagosAntigos(){
            urrentEnemy.life = 50;
            urrentEnemy.attack = 25;
            randomQuestionBinary = rpgComponent.questionBinary();
            numBinary = Integer.toBinaryString(randomQuestionBinary);
            battle(
                    "Desafio:\n" +
                            "Os três magos antigos se posicionam para enfreta-los um de cada vez e o primeiro mago que você terá que enfrentar será o Lunareth, Mestre da Magia Arcana. \n\n" +
                            "Ele domina o conhecimento puro dos Arcanos, manipulando o tecido da realidade com precisão impecável. Runas mágicas e símbolos antigos giram ao seu redor, amplificando seu poder. Capaz de invocar ilusões aterrorizantes, teletransportar-se em um piscar de olhos e conjurar feitiços que distorcem o tempo e o espaço, Lunareth é o guardião do equilíbrio entre o conhecimento e o caos.\n\n" +
                            "Lunareth, Mestre da Magia Arcana:\n" +
                            "Vida: " + urrentEnemy.life +
                            "\nAtaque: " + urrentEnemy.attack +
                            "\n\n" + playerName + " o " + playerClasse + ":\n" +
                            "Sua vida: " + playerLife +
                            "\nSua força: " + playerAttack + "\n\n", "batalharMagosAntigos"
            );
        }

        void opcaoLutaContraEryndor(String input){
            switch (input) {
                case "1":
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
                    batalharEryndor();
                    break;
                default:
                    invalidInput();
                    break;
            }
        }

        void batalharEryndor(){
            urrentEnemy.life = 55;
            urrentEnemy.attack = 25;
            randomQuestionBinary = rpgComponent.questionBinary();
            numBinary = Integer.toBinaryString(randomQuestionBinary);
            battle(
                    "Desafio:\n" +
                            "O segundo mago que você terá que enfrentar será o Eryndor, Mestre da Magia Negra. \n\n" +
                            "Ele manipula feitiços sombrios, drenando energia vital e invocando sombras que obscurecem a visão e confundem seus oponentes. Com sua presença sinistra, ele domina a necromancia, conjurando espíritos vingativos e escuridão que consome tudo ao seu redor. Seus encantamentos corruptores enfraquecem os inimigos, transformando sua força em desespero.\n\n" +
                            "Eryndor, Mestre da Magia Negra:\n" +
                            "Vida: " + urrentEnemy.life +
                            "\nAtaque: " + urrentEnemy.attack +
                            "\n\n" + playerName + " o " + playerClasse + ":\n" +
                            "Sua vida: " + playerLife +
                            "\nSua força: " + playerAttack + "\n\n", "batalharEryndor"
            );
        }

        void opcaoLutarContraKaeltharion(String input){
            switch (input) {
                case "1":
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
                    batalharKaeltharion();
                    break;
                default:
                    invalidInput();
                    break;
            }
        }

        void batalharKaeltharion(){
            urrentEnemy.life = 60;
            urrentEnemy.attack = 30;
            randomQuestionBinary = rpgComponent.questionBinary();
            numBinary = Integer.toBinaryString(randomQuestionBinary);
            battle(
                    "Desafio:\n" +
                            "O terceiro mago que você terá que enfrentar será o Kaeltharion, Mestre da Magia Elemental. \n\n" +
                            "Ele controla os elementos primordiais, invocando tempestades de fogo, ondas de gelo e relâmpagos devastadores. Sua magia é tão antiga quanto o próprio mundo, e sua conexão com os elementos é absoluta. Capaz de moldar a própria natureza ao seu redor, Kaeltharion é o guardião do equilíbrio entre a criação e a destruição.\n\n" +
                            "Kaeltharion, Mestre da Magia Elemental:\n" +
                            "Vida: " + urrentEnemy.life +
                            "\nAtaque: " + urrentEnemy.attack +
                            "\n\n" + playerName + " o " + playerClasse + ":\n" +
                            "Sua vida: " + playerLife +
                            "\nSua força: " + playerAttack + "\n\n", "batalharKaeltharion"
            );
        }

        void entradaProximaCenaAto2Cena11(String input){
            switch (input){
                case "1":
                String text =
                "Narrador: \n" +
                        "Com o Grimório Arcano Perdido em mãos, você sente uma energia arcano pulsante em seu ser. A Torre de Arcanis, outrora envolta em mistérios e magia ancestral, parece mais calma e serena, como se a presença dos Magos Antigos tivesse finalmente encontrado a paz.\n\n" +
                        "O Grimório brilha com uma luz prateada, refletindo a sabedoria e o poder dos Arcanos antigos. Sua missão em Arcanis está cumprida, mas o caminho à frente ainda é longo e repleto de desafios. Os Guardiões dos Três Véus aguardam sua volta, prontos para orientá-lo em sua próxima jornada.\n\n" +
                        "Narrador: \n" +
                        "Com determinação e coragem, você deixa a Torre de Arcanis para trás, levando consigo o Grimório Arcano Perdido. Seu destino o aguarda, e o equilíbrio de Etheris depende de sua força e sabedoria.\n\n" +
                        "1. Continuar";
                displayTextWithTypingEffect(text);
                    currentGameState = "ato2Cena12";
                    break;
                default:
                    invalidInput();
                    break;
            }
        }

        void entradaProximaCenaAto2Cena12(String input){
            switch (input){
                case "1":
                String text =
                "Narrador: \n" +
                        "Após deixar a Torre de Arcanis para trás, o ar ao seu redor parece mais leve, mas a sensação de responsabilidade se intensifica. Os Guardiões dos Três Véus manifestam-se novamente, suas vozes ecoando como um coro harmonioso, preenchendo sua mente com orientação e propósito.\n\n" +
                        "Guardião do Véu do Colosso Imortal:\n\n" +
                        playerName + ", o " + playerClasse + ", você mostrou grande coragem e sabedoria em Arcanis. Agora, com o Grimório Arcano Perdido em mãos, é hora de se voltar para o próximo artefato. Sua jornada o levará até a Terras dos Titãs, um lugar onde a força e a devoção se entre laçam. Lá repousa a Lâmina do Colosso Imortal, uma arma lendária que será essencial para restaurar o equilíbrio de Etheris.\n\n" +
                "Narrador: \n" +
                        "As palavras dos Guardiões despertam uma mistura de apreensão e determinação em você. O Templo de Valoria, envolto em lendas e mitos, será um desafio como nenhum outro. Protegido por guardiões divinos e desafios de força e fé, a Lâmina não será conquistada facilmente.\n\n" +
                        "Com a orientação dos Guardiões e sua força renovada, você se prepara para enfrentar o desconhecido. O destino de Etheris depende de sua próxima vitória, e cada passo o aproxima mais do equilíbrio que tanto almeja restaurar.\n\n" +
                        "1. Continuar";
                displayTextWithTypingEffect(text);
                    currentGameState = "ato2Cena13";
                    break;
                default:
                    invalidInput();
                    break;
            }
        }

        void entradaProximaCenaAto2Cena13(String input){
            switch (input){
                case "1":
                String text =
                "Narrador: \n" +
                        "Guiado pela orientação dos Guardiões dos Três Véus, você parte em direção à Terras dos Titãs, um lugar de força e devoção onde a Lâmina do Colosso Imortal repousa. A paisagem ao redor se transforma à medida que você avança, as montanhas se erguendo majestosas e os céus se abrindo em um espetáculo divino.\n\n" +
                        "Ao chegar a Terras dos Titãs, você é recebido por uma visão impressionante. A estrutura é imponente e majestosa, suas colunas de mármore brilhando à luz do sol. A entrada está protegida por guardiões divinos, mas sua determinação e coragem o impulsionam a avançar.\n\n" +
                        "1. Continuar";
                displayTextWithTypingEffect(text);
                    currentGameState = "ato2Cena14";
                    break;
                default:
                    invalidInput();
                    break;
            }
        }

        void entradaProximaCenaAto2Cena14(String input){
            switch (input) {
                case "1":
                String text =
                "Narrador: \n" +
                        "Ao adentrar os porta da Terras dos Titãs, você sente uma energia divina pulsante ao seu redor. O templo é um santuário de força e devoção, cada câmara repleta de desafios e provações. As colunas brilham com runas divinas, e o ar está impregnado com a essência dos Titãs, uma divindade que desafia a compreensão.\n\n" +
                        "Seu objetivo é claro: encontrar a Lâmina do Colosso Imortal e provar sua coragem diante dos desafios que o templo reserva. Cada passo o leva mais fundo no coração da divindade, onde guardiões divinos e desafios de força e fé aguardam para testar sua determinação.\n\n" +
                        "1. Explorar a Terras dos Titãs\n";
                displayTextWithTypingEffect(text);
                    currentGameState = "ato2Cena15";
                    break;
                default:
                    invalidInput();
                    break;
            }
        }

        void entradaProximaCenaAto2Cena15(String input) {
            switch (input) {
                case "1":
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
                    String text =
                    "Narrador:\n" +
                            "Enquanto você avança pelos salões imponentes do Santuário de Titanus, uma força esmagadora parece emanar das paredes. As colunas são gigantescas, gravadas com runas antigas que vibram com uma energia ancestral. Cada passo ecoa como um trovão, e o ar parece carregado com o peso de eras passadas.\n" +
                            "De repente, o chão treme, e uma figura colossal emerge das sombras. É Titanus, o Colosso Guardião. Sua forma é esculpida de pedra viva, fundida com metais reluzentes e adornada com cristais que pulsam com uma luz intensa. Seus olhos brilham como fornalhas ardentes, fixando-se em você com uma presença esmagadora.\n\n" +
                            "Titanus, Colosso Guardião:\n" +
                            "Pequeno mortal, você ousa desafiar a força eterna? Eu sou Titanus, o protetor da Lâmina do Colosso Imortal. Apenas aqueles que dominam o poder da terra e a resistência do tempo podem sequer sonhar em superar-me. Prove que sua força é digna!\n\n" +
                            "Narrador: \n" +
                            "A voz de Titanus reverbera como um terremoto, fazendo as paredes ao redor tremerem. Ele ergue sua mão gigantesca, e uma maça colossal de pedra se materializa, irradiando energia primal. O chão se despedaça sob seus pés, e a sala inteira parece responder à sua presença, desafiando você a avançar contra o impossível.\n\n" +
                            "1. Lutar contra Titanus\n";
                    displayTextWithTypingEffect(text);
                    currentGameState = "opcaoLutarContraTitanus";
                    break;
                default:
                    invalidInput();
                    break;
            }
        }

        void opcaoLutarContraTitanus(String input){
            switch (input){
                case "1":
                    batalharTitanus();
                    break;
                default:
                    invalidInput();
                    break;
            }
        }

        void batalharTitanus(){
            urrentEnemy.life = 65;
            urrentEnemy.attack = 30;
            randomQuestionBinary = rpgComponent.questionBinary();
            numBinary = Integer.toBinaryString(randomQuestionBinary);
            battle(
                    "Desafio:\n" +
                            "O combate com Titanus, o Colosso Guardião, começa! Use suas habilidades e estratégias com sabedoria para enfrentar seus ataques devastadores e resistir à aura opressiva que drena sua força.\n\n" +
                            "Titanus é uma força da natureza, dominando o poder da terra e a resistência do tempo. Seus golpes são devastadores, capazes de despedaçar montanhas e desafiar os deuses. Com sua presença esmagadora, ele testa sua coragem e determinação, desafiando-o a provar que é digno de empunhar a Lâmina do Colosso Imortal.\n\n" +
                            "Titanus, Colosso Guardião:\n" +
                            "Vida: " + urrentEnemy.life +
                            "\nAtaque: " + urrentEnemy.attack +
                            "\n\n" + playerName + " o " + playerClasse + ":\n" +
                            "Sua vida: " + playerLife +
                            "\nSua força: " + playerAttack + "\n\n", "batalharTitanus"
            );
        }

        void entradaProximaCenaAto2Cena16 (String input){
            switch (input){
                case "1":
                String text =
                "Narrador: \n" +
                        "A batalha com Titanus, o Colosso Guardião, é intensa e desafiadora. A cada golpe desferido, a aura opressiva ao seu redor parece se fortalecer, e a energia primal que emana de Titanus ameaça consumir tudo ao seu redor. O chão treme, as paredes tremem, e o ar parece vibrar com a força do combate.\n\n" +
                        "No entanto, você não se deixa abater. Com coragem e determinação, você enfrenta o Colosso, resistindo aos seus ataques e desvendando seus movimentos. A cada golpe bem-sucedido, a conexão de Titanus com a terra se enfraquece, e sua aura opressiva começa a se dissipar.\n\n" +
                        "Finalmente, após uma batalha épica, você desfere o golpe final. Titanus, o Colosso Guardião, cai de joelhos diante de você, sua aura desaparecendo lentamente. Seus olhos, uma vez brilhantes e aterrorizantes, agora refletem apenas a força e a resistência de um guardião caído.\n\n" +
                        "Narrador: \n" +
                        "Com um suspiro final, Titanus desaparece em uma luz brilhante, deixando para trás apenas a Lâmina do Colosso Imortal. É a arma lendária que você buscava, uma espada de poder inigualável que vibra com a energia da terra e do tempo. Com cuidado, você a recolhe, sentindo a força divina pulsar em suas mãos.\n\n" +
                        "1. Continuar";
                displayTextWithTypingEffect(text);
                    currentGameState = "ato2Cena17";
                    break;
                default:
                    invalidInput();
                    break;
            }
        }

        void entradaProximaCenaAto2Cena17 (String input) {
            switch (input) {
                case "1":
                String text =
                "Narrador: \n" +
                        "Com os três artefatos em mãos — o Cetro do Espírito Eterno, o Grimório Arcano Perdido e a Lâmina do Colosso Imortal —, você retorna ao Santuário do Véu. O lugar, uma vez sereno e imponente, agora está impregnado por uma energia pulsante, como se o próprio mundo aguardasse o desfecho de sua jornada.\n\n" +
                        "Os Guardiões dos Três Véus se materializam diante de você, suas formas brilhando com uma intensidade nunca vista antes. Suas vozes ecoam como um coro, preenchendo o espaço ao seu redor.\n\n" +
                "Véu do Espírito: \n" +
                        "Equilibrador, você conquistou o impossível. Os artefatos estão reunidos, e o equilíbrio de Etheris está ao seu alcance. Porém, há um último obstáculo: a Ruptura, a fonte de toda a fragmentação e caos, aguarda por você.\n\n" +
                "Véu do Arcano: \n" +
                        "A Ruptura não é apenas um evento, mas uma entidade que se alimenta do desequilíbrio. Ela reside no Coração de Etheris, um lugar onde as realidades colidem e as sombras do passado se tornam palpáveis. Lá, você enfrentará a manifestação do caos em sua forma mais pura.\n\n" +
                "Véu do Colosso: \n" +
                        "Você é a última esperança deste mundo. Com os artefatos unidos, você possui o poder necessário para enfrentar a Ruptura, mas lembre-se: a força bruta não será suficiente. Será preciso coragem, sabedoria e sacrifício para vencer.\n\n" +
                "Narrador: \n" +
                        "As palavras dos Guardiões pesam em seu coração enquanto você se prepara para a última batalha. O destino de Etheris está em jogo, e cada escolha feita até aqui culmina neste momento final. Com os artefatos brilhando intensamente em suas mãos, você respira fundo e segue em direção ao Coração de Etheris.\n" +
                        "\n" +
                        "A jornada chegou ao fim, mas o verdadeiro teste está apenas começando. O equilíbrio do mundo fragmentado depende de sua coragem e determinação. O destino de Etheris está em suas mãos.\n\n" +
                        "1. Continuar";
                displayTextWithTypingEffect(text);
                    currentGameState = "ato2Cena18";
                    break;
                default:
                    invalidInput();
                    break;
            }
        }

        void entradaProximaCenaAto2Cena18 (String input){
            switch (input) {
                case "1":
                String text =
                "Narrador: \n" +
                        "Guiado pela orientação dos Guardiões dos Três Véus, você parte em direção ao Coração de Etheris, um lugar onde as realidades colidem e as sombras do passado se tornam palpáveis. A paisagem ao redor se transforma à medida que você avança, as sombras se tornando mais densas e a energia caótica pulsando no ar.\n\n" +
                        "Ao chegar ao Coração de Etheris, você é recebido por uma visão impressionante. O lugar é um vórtice de luz e sombra, onde as realidades se entrelaçam e os limites do tempo e espaço se dissolvem. A entrada está protegida por uma barreira de energia, mas sua determinação e coragem o impulsionam a avançar.\n\n" +
                        "1. Continuar";
                displayTextWithTypingEffect(text);
                    currentGameState = "ato3Cena01";
                    break;
                default:
                    invalidInput();
                    break;
            }
        }

        void entradaProximaCenaAto3Cena01 (String input){
            switch (input) {
                case "1":
                String text =
                "Narrador: \n" +
                        "Ao adentrar os portões do Coração de Etheris, você sente uma energia caótica pulsante ao seu redor. O lugar é um vórtice de luz e sombra, onde as realidades se entrelaçam e os limites do tempo e espaço se dissolvem. As paredes brilham com runas antigas, e o ar está impregnado com a essência do caos, uma energia que desafia a compreensão.\n\n" +
                        "Seu objetivo é claro: enfrentar a Ruptura e restaurar o equilíbrio de Etheris. Cada passo o leva mais fundo no coração do caos, onde sombras do passado e visões do futuro se misturam em um turbilhão de energia. A batalha final está prestes a começar.\n\n" +
                        "1. Enfrentar a Ruptura\n";
                displayTextWithTypingEffect(text);
                    currentGameState = "ato3Cena02";
                    break;
                default:
                    invalidInput();
                    break;
            }
        }

        void entradaProximaCenaAto3Cena02(String input){
            switch (input) {
                case "1":
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
                    String text =
                    "Narrador: \n" +
                            "À medida que você avança para o coração pulsante de Etheris, o ambiente ao seu redor parece dobrar as leis da realidade. O ar é denso e pesado, impregnado com a energia caótica que sustenta a Ruptura. Runas incandescentes percorrem as paredes, mudando de forma e cor enquanto pulsos rítmicos ecoam no espaço, como o batimento cardíaco de um mundo moribundo.\n\n" +
                            "De repente, o caos se condensa em uma forma à sua frente. A Ruptura finalmente se manifesta — uma entidade de energia bruta e destrutiva. Sua figura é uma amalgama de luz e sombra, infinitamente mutável, com tentáculos etéreos que se estendem e retraem ao seu redor. Seus olhos, brilhantes e hipnotizantes, fixam-se em você, carregados de um poder avassalador e uma malevolência silenciosa.\n\n" +
                            "Ruptura, Fonte do Caos:\n" +
                            "Um mortal ousando desafiar a eternidade! Eu sou o caos, a Ruptura que desfez Etheris, o início e o fim. Você acredita que três artefatos insignificantes e uma vontade frágil podem deter minha existência? Mostre-me, então, a extensão de sua fúria e de sua determinação. Prove que pode resistir ao colapso absoluto!\n\n" +
                            "Narrador: \n" +
                            "A voz da Ruptura ecoa como um trovão em uma tempestade infinita, cada palavra enviando ondas de energia pelo chão e paredes ao seu redor. Com um gesto, ela desencadeia um redemoinho de pura energia caótica, envolvendo o campo de batalha. Fragmentos de Etheris, memórias distorcidas e ecos de eras passadas aparecem e desaparecem no vórtice, testando sua sanidade.\n" +
                            "O momento final chegou. A Ruptura se ergue diante de você, não como um inimigo comum, mas como a personificação do desequilíbrio que ameaça destruir tudo. É uma batalha contra o caos, contra a própria fragmentação do mundo. O destino de Etheris está em suas mãos.\n\n" +
                            "1. Lutar contra a Ruptura\n";
                    displayTextWithTypingEffect(text);
                    currentGameState = "opcaoLutarContraRuptura";
                    break;
                default:
                    invalidInput();
                    break;
            }
        }

        void opcaoLutarContraRuptura(String input){
            switch (input){
                case "1":
                    batalharRuptura();
                    break;
                default:
                    invalidInput();
                    break;
            }
        }

        void batalharRuptura(){
            urrentEnemy.life = 140;
            urrentEnemy.attack = 35;
            randomQuestionBinary = rpgComponent.questionBinary();
            numBinary = Integer.toBinaryString(randomQuestionBinary);
            battle(
                    "Desafio:\n" +
                            "A batalha final com a Ruptura, a Fonte do Caos, começa! Use todas as suas habilidades e estratégias para enfrentar seus ataques devastadores e resistir à energia caótica que ameaça consumir tudo ao seu redor.\n\n" +
                            "A Ruptura é a personificação do desequilíbrio, a entidade que desfez Etheris e ameaça destruir tudo. Sua forma é uma mistura de luz e sombra, infinitamente mutável e imprevisível. Com tentáculos etéreos e olhos brilhantes, ela desafia sua existência e sua determinação, testando sua coragem até o limite.\n\n" +
                            "Ruptura, Fonte do Caos:\n" +
                            "Vida: " + urrentEnemy.life +
                            "\nAtaque: " + urrentEnemy.attack +
                            "\n\n" + playerName + " o " + playerClasse + ":\n" +
                            "Sua vida: " + playerLife +
                            "\nSua força: " + playerAttack + "\n\n", "batalharRuptura"
            );
        }

        void entradaProximaCenaAto3Cena03(String input){
            switch (input){
                case "1":
                String text =
                "Narrador: \n" +
                        "A batalha com a Ruptura, a Fonte do Caos, é intensa e desafiadora. A cada golpe desferido, a energia caótica ao seu redor parece se fortalecer, e a entidade mutável que é a Ruptura ameaça consumir tudo ao seu redor. O chão treme, as paredes tremem, e o ar parece vibrar com a força do combate.\n\n" +
                        "No entanto, você não se deixa abater. Com coragem e determinação, você enfrenta a Ruptura, resistindo aos seus ataques e desvendando seus movimentos. A cada golpe bem-sucedido, a conexão da Ruptura com o caos se enfraquece, e sua energia caótica começa a se dissipar.\n\n" +
                        "Finalmente, após uma batalha épica, você desfere o golpe final. A Ruptura, a Fonte do Caos, se desfaz em uma explosão de luz e sombra, sua forma mutável se desfazendo em fragmentos de energia. O vórtice de caos que envolvia o Coração de Etheris se dissipa, e a paz retorna ao mundo fragmentado.\n\n" +
                        "Narrador: \n" +
                        "Com um suspiro final, a Ruptura desaparece, deixando para trás apenas um vórtice de energia pura. Os artefatos brilham intensamente em suas mãos, sua luz refletindo a harmonia restaurada de Etheris. O mundo, uma vez à beira do colapso, agora se estabiliza, as realidades se fundindo em um equilíbrio perfeito.\n\n" +
                        "1. Continuar";
                displayTextWithTypingEffect(text);
                    currentGameState = "ato3Cena04";
                    break;
                default:
                    invalidInput();
                    break;
            }
        }

        void entradaProximaCenaAto3Cena04(String input){
            switch (input) {
                case "1":
                String text =
                "Narrador: \n" +
                    "Com a Ruptura derrotada, o mundo de Etheris respira aliviado. O caos que uma vez envolveu o Coração de Etheris se dissipa, dando lugar a uma tranquilidade que você nunca havia sentido antes. A luz suave das eras perdidas começa a emanar do núcleo do mundo, renovando as terras fragmentadas e unificando novamente os véus da existência.\n" +
                    "Os Guardiões dos Três Véus surgem diante de você mais uma vez, suas presenças majestosas irradiando gratidão e reverência. Cada um se aproxima, suas vozes harmonizando-se em uma melodia de reconhecimento eterno.\n\n" +
                "Véu do Espírito: \n" +
                    "Equilibrador, você enfrentou as sombras mais profundas de Etheris e triunfou. O Cetro do Espírito Eterno, que outrora simbolizava a harmonia perdida, agora brilha com renovada força. Suas ações ecoarão em cada alma viva, inspirando coragem para as gerações futuras.\n\n" +
                "Véu do Arcano: \n" +
                        "O Grimório Arcano Perdido, outrora um receptáculo de segredos incontáveis, agora guarda o conhecimento da união e da força. Você mostrou que o poder mais verdadeiro vem da sabedoria e da perseverança. Etheris florescerá sob a luz que você trouxe.\n\n" +
                "Véu do Colosso: \n" +
                        "A Lâmina do Colosso Imortal, símbolo de força e resiliência, encontrou em você um verdadeiro portador. Sua determinação restaurou a ordem que parecia perdida para sempre. O equilíbrio de Etheris está seguro graças à sua coragem inabalável.\n\n" +
                "Narrador: \n" +
                    "Com suas palavras, os Guardiões se desfazem em brilhos de luz, cada partícula ascendendo para fundir-se ao Coração de Etheris. O mundo se transforma diante de seus olhos: terras antes desoladas ganham vida, céus tempestuosos tornam-se límpidos, e o povo de Etheris sente a renovação que você trouxe.\n" +
                        "O equilíbrio foi restaurado, mas você sabe que Etheris sempre exigirá vigilância e coragem para preservar o que foi conquistado. Como o Equilibrador, você deixa sua marca eterna na história deste mundo.\n" +
                        "Etheris está em paz — por agora.\n\n" +
                "1. Fim";
                displayTextWithTypingEffect(text);
                    currentGameState = "fim";
                    break;
                default:
                    invalidInput();
                    break;
            }
        }

        void fim(String input){
            switch (input){
                case "1":
                    if (musicPlayer != null) { // Verifica se o musicPlayer não é nulo
                        musicPlayer.stop();
                    }

                    URL url = getClass().getResource("/Sounds/musicEtheris.wav");

                    if (url != null) { // Verifica se a URL não é nula
                        musicPlayer = rpgComponent.new MusicPlayer(url.getPath());
                        musicPlayer.play();
                    } else {
                        System.out.println("Arquivo de música não encontrado!");
                    }
                    String text =
                    "Narrador: \n" +
                            "E assim, a jornada do Equilibrador chega ao fim. O mundo de Etheris, uma vez à beira do colapso, agora floresce sob a luz da harmonia restaurada. As terras fragmentadas se unem, os véus da existência se entrelaçam, e o povo de Etheris olha para o futuro com esperança e gratidão.\n\n" +
                            "O Equilibrador, cuja coragem e determinação salvaram o mundo, é lembrado como um herói eterno. Seu nome ecoa em canções e lendas, sua jornada reverenciada por gerações futuras. O Cetro do Espírito Eterno, o Grimório Arcano Perdido e a Lâmina do Colosso Imortal permanecem como símbolos de sua bravura e sacrifício.\n\n" +
                            "E assim, Etheris encontra a paz, mas o Equilibrador sabe que o equilíbrio do mundo é frágil e sempre exigirá vigilância e coragem. A jornada do Equilibrador pode ter chegado ao fim, mas o legado de sua coragem viverá para sempre.\n\n" +
                            "Fim\n\n" +
                            "Muito obrigado por jogar A era de Etheris, Espero que tenha gostado :)" +
                            "Créditos\n\n" +
                            "Desenvolvido por: Arthur Araújo Sousa e Maria Eduarda\n" +
                            "Agradecimentos Especiais aos professores: Eduardo Takeo e Fabio Brussolo\n\n";
                    displayTextWithTypingEffect(text);
                    break;
                default:
                    invalidInput();
                    break;
            }
        }

    }
}
