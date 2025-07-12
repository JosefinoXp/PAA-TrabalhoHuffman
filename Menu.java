import java.util.Scanner;

public class Menu {
    public static int opcao = -1;

    public static String nomeTexto;
    public static String nomeFinal;

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

public static void main(String[] args) {
    Scanner entrada = new Scanner(System.in);
    
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

                GerenciadorArquivo.CodificarLetra(nomeTexto, nomeFinal);

                break;

            case 2:
                entrada.nextLine();
                nomeTexto = DigiteNomeArquivo();
                nomeFinal = DigiteNomeArquivo();

                GerenciadorArquivo.DecodificarLetra(nomeTexto, nomeFinal);

                break;

            case 3:    
                entrada.nextLine();
                nomeTexto = DigiteNomeArquivo();
                nomeFinal = DigiteNomeArquivo();

                GerenciadorArquivo.CodificarPalavra(nomeTexto, nomeFinal);

                break;

            case 4:
                entrada.nextLine();
                nomeTexto = DigiteNomeArquivo();
                nomeFinal = DigiteNomeArquivo();

                GerenciadorArquivo.DecodificarPalavra(nomeTexto, nomeFinal);

                break;

            default:
            System.out.print("\nOpção Invalida! Escolha Outro!");
                break;


        }
    }
}

}
