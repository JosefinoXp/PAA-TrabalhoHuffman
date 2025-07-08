import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

import java.util.Scanner;

public class Menu {
    public static int opcao = -1;

    String nomeArquivo;

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

public static void MenuCodificarPalavra(String nomeTexto, String nomeFinal) {
    try (BufferedReader leitor = new BufferedReader (new FileReader(nomeTexto)) ) {
        FileWriter escritor = new FileWriter(nomeFinal);

        StringBuilder texto = new StringBuilder();

        String texto1;

        while((texto1 = leitor.readLine()) != null)
            texto.append(texto1);

        Huffman codificador = new Huffman(texto.toString());

        escritor.write(codificador.codificar());

        escritor.close();
    } catch (Exception e) {
        System.out.println(e);
    }
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
                String nomeTexto = DigiteNomeArquivo();
                String nomeFinal = DigiteNomeArquivo();

                MenuCodificarPalavra(nomeTexto, nomeFinal);

                break;

            case 2:    

                break;

            case 3:    

                break;

            case 4:    

                break;

            default:
            System.out.print("\nOpção Invalida! Escolha Outro!");
                break;


        }
    }
}

}
