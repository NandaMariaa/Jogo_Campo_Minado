import java.util.Random;
import java.util.Scanner;

public class ado12Projeto {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Solicitar número de linhas e colunas.
        System.out.println("CAMPO MINADO");
        System.out.println("Número de LINHAS do Campo:");
        int linhas = scanner.nextInt();
        System.out.println("Número de COLUNAS do Campo:");
        int colunas = scanner.nextInt();

        // Criar matriz com base no tamanho determinado pelo usuário.
        int[][] matriz = criarMatriz(linhas, colunas);

        // Pede a quantidade de bombas que o usuário deseja.
        System.out.println("Quantidade de Bombas do Campo:");
        int numBombas = scanner.nextInt();

        // Inicia a função de posicionar as bombas.
        posicionarBombas(matriz, numBombas);

        // Inicia a função jogar.
        jogarCampoMinado(matriz);
    }
    
    // Criar matriz com base no tamanho determinado pelo usuário.
    public static int[][] criarMatriz(int linhas, int colunas) {
        return new int[linhas][colunas];
    }

    // Posiciona as bombas na matriz.
    public static void posicionarBombas(int[][] matriz, int numBombas) {
        // Cria objeto Random que gera objetos de números aleatórios que serão as posições das bombas.
        Random random = new Random();

        // Obtém o número de linhas e o número de colunas.
        int linhas = matriz.length;
        int colunas = matriz[0].length;
        int bombasColocadas = 0;

        // Continua enquanto o número de bombas colocadas for menor do que a quantidade que o usuário quer.
        while (bombasColocadas < numBombas) {   
            // Gera um números aleatórios entre 0 e o numero de Linhas e Colunas (Pra saber aonde as bombas podem ser colocadas).
            int i = random.nextInt(linhas);
            int j = random.nextInt(colunas);
            // Verifica se já não tem nenhuma bomba na posição, pra não repetir.
            if (matriz[i][j] != -1) {
                // Se a posição tiver livre, posiciona a bomba (-1).
                matriz[i][j] = -1;
                bombasColocadas++;
            }
        }
    }

    // Jogo

    public static void jogarCampoMinado(int[][] matriz) {

        Scanner scanner = new Scanner(System.in);

        //objeto pra contar a pontuação.
        int pontuacao = 0;

        while (true) {
            //pede para que o jogador escolha a linha seguida da coluna, o que seria o seu "chute".
            System.out.println("Escolha uma posição (Digite Primeiro o Número da Linha e, Em Seguida, a Coluna):");
            int linhaEscolhida = scanner.nextInt();
            int colunaEscolhida = scanner.nextInt();
             
            //verifica se a linha e coluna escolhidas são maiores que 0 e, se não é um número maior do que os tamanhos da linha e da coluna.
            if (linhaEscolhida < 1 || linhaEscolhida > matriz.length ||
                colunaEscolhida < 1 || colunaEscolhida > matriz[0].length) {
                System.out.println("Posição inválida! Tente Novamente.");
                continue;
            }
            
            //aqui, o "valor" da posição escolhida pelo usuário passa a ser -1
            int valor = matriz[linhaEscolhida - 1][colunaEscolhida - 1];
            
            //verifica se o a posição que o úsuário escolheu já tinha uma bomba (se já tinha o valor de -1). caso tenha, ele perdeu o jogo.
            if (valor == -1) {
                System.out.println("Game Over!");
                imprimirMatrizOculta(matriz, true); 
                break;

            //verifica se o a posição que o úsuário escolheu já havia sido escolhida anteriormente (se já tinha o valor de 1). caso tenha, ele pede novamente.  
            } else if (valor == 1) {
                System.out.println("Você já Escolheu Esta Posição! ");
                continue;
            } else { //se nenhum desses erros no laço acontem, as posições passam a ser contadas no contador e as posições escolhidas passas a valer 1.
                pontuacao++;
                matriz[linhaEscolhida - 1][colunaEscolhida - 1] = 1;
                
                //chama a função de verificar as bombas ao redor.
                if (verificarBombasAoRedor(matriz, linhaEscolhida - 1, colunaEscolhida - 1)) {
                    System.out.println("Cuidado: bomba próxima!");
                }
                //chama a função imprimir.
                imprimirMatrizOculta(matriz, false); 
            }
        }
        //imprime a pontuação
        System.out.println("Pontuação: " + pontuacao);
    }

    // verifica se há bombas ao redor de uma posição.
    public static boolean verificarBombasAoRedor(int[][] matriz, int i, int j) {
        //inicia duas variáveis com o número de linhas e colunas da matriz
        int linhas = matriz.length;
        int colunas = matriz[0].length;
        //aqui, ele verifica se existe uma bomba ao redor da posição escolhida.
        for (int linha = Math.max(0, i - 1); linha <= Math.min(linhas - 1, i + 1); linha++) {
            for (int coluna = Math.max(0, j - 1); coluna <= Math.min(colunas - 1, j + 1); coluna++) {
                if (matriz[linha][coluna] == -1) {
                    return true;
                }
            }
        }
        //retorna falso se não encontrou nenhuma bomba ao redor.
        return false;
    }

    // Imprime a matriz oculta (Oculta porque não exibe o local das bombas).
    public static void imprimirMatrizOculta(int[][] matriz, boolean mostrarBombas) {
        int linhas = matriz.length;
        int colunas = matriz[0].length;
        
        //enquanto o usuário estiver acertando as pontuações, exibira como "x" suas escolhas
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                if (matriz[i][j] == 1) {
                    System.out.print(" x ");
                //se ele errar e perder, a posição com a bomba será revelada por "b"
                } else if (mostrarBombas && matriz[i][j] == -1) {
                    System.out.print(" b ");
                } else {
                //incialemente, todas as posições da matriz são exibidas como "_"
                    System.out.print(" _ ");
                }
            }
            System.out.println();
        }
    }
}


