import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

import java.util.Scanner;

public class Menu {
    public static int opcao = -1;

    String nomeArquivo;

public static void ImprimeMenu() {
    System.out.println("\n" + "\n");
    System.out.println("\tMenu Trabalho1");
    System.out.println("\tEscolha as opções seguintes");
    System.out.println("\t1. Compactar Arquivo (Caracter)");
    System.out.println("\t2. Descompactar Arquivo (Caracter)");
    System.out.println("\t3. Compactar Arquivo (Palavra)");
    System.out.println("\t4. Descompactar Arquivo (Palavra)");
    System.out.println("\t0. Sair Programa");
    System.out.print("\tOpção: ");
}

public static String DigiteNomeArquivo() {
    System.out.println("Digite o nome do .txt abaixo");

    Scanner entrada = new Scanner(System.in);

    String nomeTexto = entrada.nextLine();

    return nomeTexto;
}

public static void main(String[] args) {
    Scanner entrada = new Scanner(System.in);
    
    while(true) {
        ImprimeMenu();

        opcao = entrada.nextInt();

        switch (opcao) {
            case 0:
                System.exit(0);
                break;
        
            case 1:
                entrada.nextLine();
                String nomeTexto = DigiteNomeArquivo();
                String nomeFinal = DigiteNomeArquivo();

                try (BufferedReader leitor = new BufferedReader (new FileReader(nomeTexto)) ) {
                    FileWriter escritor = new FileWriter(nomeFinal);

                    String texto;

                    while((texto = leitor.readLine()) != null)
                        escritor.write(texto);

                    escritor.close();
                } catch (Exception e) {
                    System.out.println(e);
                }

                break;

            case 2:    

                break;

            case 3:    

                break;

            case 4:    

                break;

            default:
                break;


        }
    }
}

}
