import java.util.ArrayList; // Importa a classe ArrayList
import java.util.Collections; // Importa a classe Collections
import javax.sound.sampled.*; // Importa as classes necessárias para tocar música
import java.io.File; // Importa a classe File
import java.io.IOException; // Importa a classe IOException


public class RpgComponent {

    // Classe para criar um inimigo
    class Enemy {
        int life;
        int attack;
        Enemy(int life, int attack){
            this.life = life;
            this.attack = attack;
        }
    }

    // Classe para criar um jogador
    public class Player {
        int life;
        int attack;

        public Player(int life, int attack) {
            this.life = life;
            this.attack = attack;
        }
    }

    // Metodo parar gera uma pergunta de matemática
    int questionBinary() {
        ArrayList<Integer> question = new ArrayList<Integer>(); // Lista de pergunta
        question.add(20);  // 0001 0100
        question.add(19);  // 0001 0011
        question.add(17);  // 0001 0001
        question.add(14);  // 0000 1110
        question.add(13);  // 0000 1101
        question.add(11);  // 0000 1011
        question.add(15);  // 0000 1111
        question.add(9);   // 0000 1001
        question.add(10);  // 0000 1010
        question.add(7);   // 0000 0111
        question.add(6);   // 0000 0110
        question.add(5);   // 0000 0101
        question.add(2);   // 0000 0010
        question.add(4);   // 0000 0100
        question.add(8);   // 0000 1000
        question.add(16);  // 0001 0000
        question.add(18);  // 0001 0010
        question.add(1);   // 0000 0001
        question.add(3);   // 0000 0011
        question.add(12);  // 0000 1100
        question.add(134); // 1000 0110
        question.add(78);  // 0100 1110
        question.add(243); // 1111 0011
        question.add(97);  // 0110 0001
        question.add(56);  // 0011 1000
        question.add(128); // 1000 0000
        question.add(250); // 1111 1010
        question.add(512); // 0010 0000 0000
        question.add(256); // 0001 0000 0000
        question.add(321); // 0001 0100 0001
        question.add(76);  // 0100 1100
        question.add(300); // 0001 0010 1100
        question.add(436); // 0001 1011 0100
        question.add(64);  // 0100 0000


        Collections.shuffle(question); // Embaralha as perguntas
        return question.get(0); // Retorna uma pergunta aleatória
    }

    // Metodo para tocar música
    public class MusicPlayer {
        private Clip clip;

        public MusicPlayer(String filePath) {
            try {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
                clip = AudioSystem.getClip();
                clip.open(audioInputStream);
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
            }
        }

        // Metodo para tocar a música em loop
        public void play() {
            if (clip != null) {
                clip.loop(Clip.LOOP_CONTINUOUSLY); // Loop infinito
                clip.start();
            }
        }

        // Metodo para parar a música
        public void stop() {
            if (clip != null && clip.isRunning()) {
                clip.stop();
            }
        }

        public void playOnce(String filePath){
            try {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
                Clip singleClip = AudioSystem.getClip();
                singleClip.open(audioInputStream);
                singleClip.start();
                singleClip.addLineListener(event -> {
                    if (event.getType() == LineEvent.Type.STOP) {
                        singleClip.close();
                    }
                });
            }  catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
            }
        }
    }
}

