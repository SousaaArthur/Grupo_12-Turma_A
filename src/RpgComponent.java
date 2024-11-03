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

    // Classe para criar um inventário
    class inventory {
        int lifePotion;
        int attackPotion;
        inventory(int lifePotion, int attackPotion){
            this.lifePotion = lifePotion;
            this.attackPotion = attackPotion;
        }
    }

    // Metodo parar gera uma pergunta de matemática
    int questionBinary() {
        ArrayList<Integer> question = new ArrayList<Integer>(); // Lista de perguntas
        question.add(134); // 1000 0110
        question.add(78);  // 0100 1110
        question.add(243); // 1111 0011
        question.add(97);  // 0110 0001
        question.add(56);  // 0011 1000
        question.add(128); // 1000 0000
        question.add(250); // 1111 1010
        question.add(512); // 1000 0000 0000
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

        public static void playOnce(String filePath){
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

