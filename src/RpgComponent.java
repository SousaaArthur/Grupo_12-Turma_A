import java.util.ArrayList;
import java.util.Collections;

public class RpgComponent {

    class Enemy {
        int life;
        int attack;
        Enemy(int life, int attack){
            this.life = life;
            this.attack = attack;
        }
    }

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
}

