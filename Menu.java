import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
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

public static void MenuCodificarLetra(String nomeTexto, String nomeFinal) {
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

public static void CodificarTesteSerialization(String nomeTexto, String nomeFinal) {
    try (BufferedReader leitor = new BufferedReader (new FileReader(nomeTexto))) {
        FileOutputStream escritorArquivo = new FileOutputStream(nomeFinal);

        ObjectOutputStream escritorDados = new ObjectOutputStream(escritorArquivo);
        
        StringBuilder texto = new StringBuilder();

        String textoTranscrito;

        while((textoTranscrito = leitor.readLine()) != null)
            texto.append(textoTranscrito);

        Huffman codificador = new Huffman(texto.toString());

        //coloquei antes de escrever arvore pois necessito que apos codificar arvore exista
        escritorDados.writeBytes(codificador.codificar());
        escritorDados.writeObject(codificador.getArvoreHuffman());

        escritorDados.close();
        escritorArquivo.close();

    } catch (Exception e) {
        System.out.println(e);
    }
}

public static void MenuDecodificarLetra(String nomeTexto, String nomeFinal) {

}

public static void MenuCodificarPalavra(String nomeTexto, String nomeFinal) {
//TODO
}

public static void MenuDecodificarPalavra(String nomeTexto, String nomeFinal) {
//TODO
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

                CodificarTesteSerialization(nomeTexto, nomeFinal);

                break;

            case 2:
                entrada.nextLine();
                nomeTexto = DigiteNomeArquivo();
                nomeFinal = DigiteNomeArquivo();

                MenuDecodificarLetra(nomeTexto, nomeFinal);

                break;

            case 3:    
                entrada.nextLine();
                nomeTexto = DigiteNomeArquivo();
                nomeFinal = DigiteNomeArquivo();

                MenuCodificarLetra(nomeTexto, nomeFinal);

                break;

            case 4:
                entrada.nextLine();
                nomeTexto = DigiteNomeArquivo();
                nomeFinal = DigiteNomeArquivo();

                MenuDecodificarLetra(nomeTexto, nomeFinal);

                break;

            default:
            System.out.print("\nOpção Invalida! Escolha Outro!");
                break;


        }
    }
}

}
