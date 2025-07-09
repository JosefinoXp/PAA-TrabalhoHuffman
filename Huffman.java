import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class Huffman {

    private ArvoreHuffman raiz;

    private final String texto;

    private Map<Character, Integer> frequenciaCaracteres;

    public final Map<Character, String> codigoHuffman;

    public Huffman(String texto) {
        this.texto = texto;

        preencherFrequenciaCaracteres();

        codigoHuffman = new HashMap<>();
    }

    private void preencherFrequenciaCaracteres() {
        frequenciaCaracteres = new HashMap<>();

        for(char caracter : texto.toCharArray()) {
            Integer inteiro = frequenciaCaracteres.get(caracter);
            frequenciaCaracteres.put(caracter, inteiro != null ? inteiro + 1 : 1);
        }
    }

    public String codificar() {
        Queue<ArvoreHuffman> fila = new PriorityQueue<>();

        frequenciaCaracteres.forEach((caracter, frequencia) -> fila.add(new FolhaHuffman(caracter, frequencia)));

        while (fila.size() > 1) {
            fila.add(new ArvoreHuffman(fila.poll(), fila.poll()));
        }

        gerarHuffmanCodigos(raiz = fila.poll(), "");
        return getTextoCodificado();
    }

    private void gerarHuffmanCodigos(ArvoreHuffman arvoreHuffman, String codigo) {
        if (arvoreHuffman == null) {
            return;
        }
        
        if (arvoreHuffman instanceof FolhaHuffman) {
            codigoHuffman.put(((FolhaHuffman) arvoreHuffman).getCaracter(), codigo);
        }

        gerarHuffmanCodigos(arvoreHuffman.getNoEsquerdo(), codigo.concat("0"));
        gerarHuffmanCodigos(arvoreHuffman.getNoDireito(), codigo.concat("1"));
    }

    private String getTextoCodificado() {
        StringBuilder sb = new StringBuilder();
        
        for (char caracter : texto.toCharArray()) {
            sb.append(codigoHuffman.get(caracter));
        }
        return sb.toString();
    }

    public String decodificar(String textoCodificado) {
        StringBuilder sb = new StringBuilder();
        ArvoreHuffman atual = raiz;

        for (char caracter : textoCodificado.toCharArray()) {
            atual = caracter == '0' ? atual.getNoEsquerdo() : atual.getNoDireito();
            if (atual instanceof FolhaHuffman) {
                sb.append(((FolhaHuffman) atual).getCaracter());
                atual = raiz;
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        
        Huffman huffman = new Huffman("olaaaa12ç");

        String textoCodificado = huffman.codificar();
        System.out.println(textoCodificado);


        String original = huffman.decodificar(textoCodificado);
        System.out.println(original);
    }

}