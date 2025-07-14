import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.TimeUnit;
import java.util.Scanner;

public class Menu {
    public static int opcao = -1;

    public static String nomeTexto;
    public static String nomeFinal;

    public static long inicioTempo;
    public static long fimTempo;

    public static long inicioMemoria;
    public static long fimMemoria;

    static ThreadMXBean medidor = ManagementFactory.getThreadMXBean();
    
    static Scanner entrada = new Scanner(System.in);

public static void ImprimeMenu() {
    System.out.println("\n");
    System.out.println("\tMenu Trabalho1");
    System.out.println("\tEscolha as opções seguintes");
    System.out.println("\t1. Compactar Arquivo (Caracter)");
    System.out.println("\t2. Descompactar Arquivo (Caracter)");
    System.out.println("\t3. Compactar Arquivo (Palavra)");
    System.out.println("\t4. Descompactar Arquivo (Palavra)");
    System.out.println("\t0. Sair Programa");
    System.out.print("\tOpção: ");
}

public static void LimparTela() {
    //nem eu sei, mas isso aqui funciona no terminal do VSCode pra limpar a tela
    System.out.print("\033c");
}

public static String DigiteNomeArquivo() {
    System.out.println("Digite o nome do .txt abaixo");

    @SuppressWarnings("resource")
    Scanner entrada = new Scanner(System.in);

    String nomeTexto = entrada.nextLine();

    LimparTela();

    return nomeTexto;
}

public static void IniciarDesempenho() {
    inicioTempo = medidor.getCurrentThreadCpuTime();
    inicioMemoria = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
}

public static void ResultadoDesempenho() {
    fimTempo = medidor.getCurrentThreadCpuTime();
    fimMemoria = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

    System.out.println("Tempo de Execução (Milisegundos): " + TimeUnit.NANOSECONDS.toMillis(fimTempo - inicioTempo) + "s");
    System.out.println("Memória utilizada: " + (fimMemoria - inicioMemoria) / (1024 * 1024) + "Mb");

    System.out.println("Aperte qualquer tecla para continuar...");
    entrada.nextLine();
}

public static void main(String[] args) {
    
    while(true) {
        ImprimeMenu();

        opcao = entrada.nextInt();

        LimparTela();

        switch (opcao) {
            case 0:
                System.out.println("Saindo...");
                entrada.close();
                System.exit(0);
                break;
        
            case 1:
                entrada.nextLine();
                nomeTexto = DigiteNomeArquivo();
                nomeFinal = DigiteNomeArquivo();

                IniciarDesempenho();

                GerenciadorArquivo.CodificarLetra(nomeTexto, nomeFinal);

                ResultadoDesempenho();

                break;

            case 2:
                entrada.nextLine();
                nomeTexto = DigiteNomeArquivo();
                nomeFinal = DigiteNomeArquivo();

                IniciarDesempenho();

                GerenciadorArquivo.DecodificarLetra(nomeTexto, nomeFinal);

                ResultadoDesempenho();

                break;

            case 3:    
                entrada.nextLine();
                nomeTexto = DigiteNomeArquivo();
                nomeFinal = DigiteNomeArquivo();

                IniciarDesempenho();

                GerenciadorArquivo.CodificarPalavra(nomeTexto, nomeFinal);

                ResultadoDesempenho();

                break;

            case 4:
                entrada.nextLine();
                nomeTexto = DigiteNomeArquivo();
                nomeFinal = DigiteNomeArquivo();

                IniciarDesempenho();

                GerenciadorArquivo.DecodificarPalavra(nomeTexto, nomeFinal);

                ResultadoDesempenho();

                break;

            default:
            System.out.print("\nOpção Invalida! Escolha Outro!");
                break;


        }
    }
}

}
