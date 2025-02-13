import java.util.Scanner;
import java.util.Random;

public class ado11Projeto {
    public static void main(String[] args) {

        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        int pontos = 0;
        int t = 0;
        
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("CAMPO MINADO");
      
        /*
        Pega o tamanho do campo que, deve ser maior que 1.
        */
        do{
        System.out.print("Informe o Tamanho do Caminho: ");
        t = scanner.nextInt();
        }while (t <= 1);
        
        /*
        Pega a quantidade de bombas que estarão presentes no campo. Se o usuário digitar um número maior que o tamanho do campo, ele fecha.
        */
        System.out.print("Informe a Quantidade de Bombas: ");
        int qnt = scanner.nextInt();

        if (qnt <= 0 || qnt >= t) {
            System.out.println("Quantidade Inválida!");
            return;

        }
        
        int[] caminho = new int[t];

        /*
        Coloca as bombas dentro do vetor caminho.
        */
        for (int i = 0; i < qnt; i++) {
            int posicao = random.nextInt(t); //gera posição aleatória para as bombas.
            while (caminho[posicao] != 0) {  //se a posição para a bomba não tiver livre no caminho (for diferente de 0). ele busca uma nova posição.
                posicao = random.nextInt(t); //gera posição aleatória para as bombas.
            }
            caminho[posicao] = -1; //atribui o valor de "-1" nas posições escolhidas pelo loop para as bombas.
        }

        /*
        Imprimir o Campo (_____...).
        */

        while (pontos < t - qnt) {
            System.out.println("\nCampo: ");
            for (int i = 0; i < t; i++) {//lê o campo.
                if (caminho[i] == 0) {//se a posição tiver o valor 0 (vazia), exibe o "_".
                    System.out.print("_");
                } else if (caminho[i] == 1) {//se a posição tiver o valor 1, exibe "x".
                    System.out.print("x ");
                } else {//se a posição tiver o valor -1, ou seja uma bomba, exibe "_".
                    System.out.print("_");
                }
            }
            
            /*
            Pega a escolha do usuário. Pede novamente se o usuário escolher a mesma posição + de 1 vez.
            */
            System.out.print("\nEscolha uma posição (0 a " + (t - 1) + "): ");
            int escolha = scanner.nextInt();

            /*
            Verifica se tem uma bomba próxima.
            */
            if ((escolha > 0 && caminho[escolha - 1] == -1) || (escolha < t - 1 && caminho[escolha + 1] == -1)) {
                //(Se a posição escolhida - 1 foi IGUAL a bomba ou; se a posição escolhida + 1 foi IGUAL a bomba, ele avisa que a bomba ta perto).
                System.out.println("Cuidado: bomba próxima!");
            }

            /*
            Soma os pontos
            */
            if (caminho[escolha] == 0) {
                caminho[escolha] = 1;
                pontos++;
            }

            /*
            Termina o jogo se acertar a bomba.
            */
            if (caminho[escolha] == -1) {
                System.out.println("Game Over!");
                break;
            }
        }

        /*
        Mostra o caminho com as bombas no final.
        */
        System.out.println("\nCaminho Final:");
        for (int i = 0; i < t; i++) {
            if (caminho[i] == 0) {
                System.out.print("x");
            } else if (caminho[i] == 1) {
                System.out.print("x");
            } else {
                System.out.print("b");
            }
        }

        // Verifica se o jogador ganhou
        if (pontos == t - qnt) {
            System.out.println("\nParabéns, você ganhou o jogo!");
        }

        // Exibe a pontuação
        System.out.println("\nPontuação: " + pontos + " de " + (t - qnt));

        scanner.close();

    }
}






