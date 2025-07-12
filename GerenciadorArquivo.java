import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class GerenciadorArquivo {

    /**
 * Escreve uma string de '0's e '1's em um arquivo, bit a bit.
 * @param escritor O ObjectOutputStream para o arquivo de destino.
 * @param textoCodificado A string contendo os bits a serem escritos.
 * @throws IOException
 */
public static void escreverBitsEmArquivo(ObjectOutputStream escritor, String textoCodificado) throws IOException {
    // Primeiro, escreve o número total de bits. Isso é crucial para a decodificação
    // saber quantos bits ler, especialmente por causa do preenchimento no último byte.
    escritor.writeInt(textoCodificado.length());

    // Itera sobre a string de bits em blocos de 8
    for (int i = 0; i < textoCodificado.length(); i += 8) {
        String byteString;
        if (i + 8 > textoCodificado.length()) {
            byteString = textoCodificado.substring(i);
        } else {
            byteString = textoCodificado.substring(i, i + 8);
        }

        // Converte a string de 8 bits para um inteiro e depois para um byte
        // O Integer.parseInt(s, 2) converte uma string binária em um int.
        byte b = (byte) Integer.parseInt(byteString, 2);
        escritor.writeByte(b);
    }
}

/**
 * Lê bits de um arquivo e os reconstrói em uma String de '0's e '1's.
 * @param leitor O ObjectInputStream do arquivo compactado.
 * @return A string de bits reconstruída.
 * @throws IOException
 */
public static String lerBitsDoArquivo(ObjectInputStream leitor) throws IOException {
    // Lê o número total de bits que foram originalmente codificados.
    int totalBits = leitor.readInt();
    StringBuilder bitsReconstruidos = new StringBuilder(totalBits);

    // Continua lendo bytes até que tenhamos reconstruído o número necessário de bits.
    while (bitsReconstruidos.length() < totalBits) {
        byte b = leitor.readByte();
        // Converte o byte de volta para uma string binária
        // (b & 0xFF) garante que o byte seja tratado como um valor sem sinal.
        // String.format garante que a string tenha 8 caracteres, preenchendo com '0's à esquerda.
        String bitsDoByte = String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
        bitsReconstruidos.append(bitsDoByte);
    }

    // Trunca a string para o comprimento original exato, removendo bits de preenchimento.
    return bitsReconstruidos.substring(0, totalBits);
}

public static void CodificarLetra(String nomeTexto, String nomeFinal) {
    try (BufferedReader leitor = new BufferedReader(new FileReader(nomeTexto))) {
        FileOutputStream escritorArquivo = new FileOutputStream(nomeFinal);
        ObjectOutputStream escritorDados = new ObjectOutputStream(escritorArquivo);
        
        StringBuilder texto = new StringBuilder();
        String linha;

        while((linha = leitor.readLine()) != null) {
            texto.append(linha);
            texto.append("\n");
        }

        // Remove o último '\n' para evitar problemas com contagem de frequência
        if (texto.length() > 0) {
            texto.setLength(texto.length() - 1);
        }

        HuffmanCaracter codificador = new HuffmanCaracter(texto.toString());
        String textoCodificado = codificador.codificar();

        // ORDEM É IMPORTANTE:
        // 1. Escreve a árvore de Huffman.
        escritorDados.writeObject(codificador.getArvoreHuffman());
        // 2. Escreve os dados compactados usando nova função de bits.
        escreverBitsEmArquivo(escritorDados, textoCodificado);

        escritorDados.close();
        escritorArquivo.close();

        System.out.println("Arquivo compactado!");

    } catch (Exception e) {
        System.out.println(e);
        e.printStackTrace();
    }
}

public static void DecodificarLetra(String nomeTexto, String nomeFinal) {
    try {
        FileInputStream leitorArquivo = new FileInputStream(nomeTexto);
        ObjectInputStream leitorDados = new ObjectInputStream(leitorArquivo);
        
        // ORDEM É IMPORTANTE:
        // 1. Lê a árvore de Huffman do arquivo.
        ArvoreHuffman raizObtida = (ArvoreHuffman) leitorDados.readObject();
        
        // 2. Lê os dados de bits usando nossa nova função e reconstrói a string de bits.
        String textoCodificado = lerBitsDoArquivo(leitorDados);
        
        // Agora, com a árvore e os dados, decodificar.
        HuffmanCaracter decodificador = new HuffmanCaracter(raizObtida);
        String textoDecodificado = decodificador.decodificar(textoCodificado);
        
        try (FileWriter escritor = new FileWriter(nomeFinal)) {
            escritor.write(textoDecodificado);
        }

        leitorDados.close();
        leitorArquivo.close();
        
        System.out.println("Arquivo descompactado!");

    } catch (Exception e) {
        System.out.println(e);
        e.printStackTrace();
    }
}

public static void CodificarPalavra(String nomeTexto, String nomeFinal) {
    try (BufferedReader leitor = new BufferedReader(new FileReader(nomeTexto))) {
        FileOutputStream escritorArquivo = new FileOutputStream(nomeFinal);
        ObjectOutputStream escritorDados = new ObjectOutputStream(escritorArquivo);
        
        StringBuilder texto = new StringBuilder();
        String linha;

        while((linha = leitor.readLine()) != null) {
            texto.append(linha).append(" ");
        }
        if (texto.length() > 0) {
            texto.setLength(texto.length() - 1);
        }

        // Remove o último '\n' para evitar problemas com contagem de frequência
        if (texto.length() > 0) {
            texto.setLength(texto.length() - 1);
        }

        HuffmanString codificador = new HuffmanString(texto.toString());
        String textoCodificado = codificador.codificar();

        // ORDEM É IMPORTANTE:
        // 1. Escreve a árvore de Huffman.
        escritorDados.writeObject(codificador.getArvoreHuffman());
        // 2. Escreve os dados compactados usando nova função de bits.
        escreverBitsEmArquivo(escritorDados, textoCodificado);

        escritorDados.close();
        escritorArquivo.close();

        System.out.println("Arquivo compactado!");

    } catch (Exception e) {
        System.out.println(e);
        e.printStackTrace();
    }
}

public static void DecodificarPalavra(String nomeTexto, String nomeFinal) {
try {
        FileInputStream leitorArquivo = new FileInputStream(nomeTexto);
        ObjectInputStream leitorDados = new ObjectInputStream(leitorArquivo);
        
        // ORDEM É IMPORTANTE:
        // 1. Lê a árvore de Huffman do arquivo.
        ArvoreHuffman raizObtida = (ArvoreHuffman) leitorDados.readObject();
        
        // 2. Lê os dados de bits usando nossa nova função e reconstrói a string de bits.
        String textoCodificado = lerBitsDoArquivo(leitorDados);
        
        // Agora, com a árvore e os dados, decodificar.
        HuffmanString decodificador = new HuffmanString(raizObtida);
        String textoDecodificado = decodificador.decodificar(textoCodificado);
        
        try (FileWriter escritor = new FileWriter(nomeFinal)) {
            escritor.write(textoDecodificado);
        }

        leitorDados.close();
        leitorArquivo.close();
        
        System.out.println("Arquivo descompactado!");

    } catch (Exception e) {
        System.out.println(e);
        e.printStackTrace();
    }
}

}