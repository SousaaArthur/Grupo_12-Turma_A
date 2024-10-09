import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Teste {
    static int vidaInimigo = 100;
    static int vidaJogador = 100;
    static int ataque = 15;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(
                "Você estava andando por uma floresta e se depara com um monstro.\n" +
                        "Escolha o que você deseja fazer:\n" +
                        "1 - Lutar contra o monstro\n" +
                        "2 - Fugir do monstro"
        );
        System.out.print("Digite a opção desejada:");
        int opcao = sc.nextInt();

        if (opcao == 1) {
            System.out.println("Você escolheu lutar contra o monstro!");
            System.out.println("Você precisa responder a seguinte pergunta para derrotar o monstro:");
            systemQuestion();
        } else if (opcao == 2) {
            System.out.println("Você escolheu fugir do monstro!");
            System.out.println("Você precisa responder a seguinte pergunta para fugir do monstro:");
            systemQuestion();
        } else {
            System.out.println("Opção inválida!");
        }
    }

    static void systemQuestion(){
        Scanner sc = new Scanner(System.in);

        RpgComponent rpg = new RpgComponent();
        int num = rpg.questionBinary();

        System.out.println("Qual é a representação binária do número " + num + "?");
        String numBinary = sc.next();

        String binary = Integer.toBinaryString(num); // 1000000

        if (binary.equals(numBinary)) {
            System.out.println("Correto!");
            lutaAcertou();
            if(vidaInimigo > 0){
                systemQuestion();
            } else {
                System.out.println("Você derrotou o monstro!");
            }
        } else {
            System.out.println("Incorreto!");
            lutaErrou();
            if(vidaJogador > 0){
                systemQuestion();
            } else {
                System.out.println("Você foi derrotado pelo monstro!");
            }
        }

        sc.close();
    }

    static void lutaAcertou() {
        vidaInimigo -= ataque;
        System.out.println("Você acertou o monstro! Vida do monstro: " + vidaInimigo);
    }

    static void lutaErrou() {
        vidaJogador -= ataque;
        System.out.println("Você errou o monstro! Vida do jogador: " + vidaJogador);
    }
}
