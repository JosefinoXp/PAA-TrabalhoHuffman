import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class HuffmanString {

    private ArvoreHuffman raiz;

    private final String texto;

    private Map<String, Integer> frequenciaString;

    private final Map<String, String> codigoHuffman;

    public HuffmanString(String texto) {
        this.texto = texto;

        preencherFrequenciaString();

        codigoHuffman = new HashMap<>();
    }

    public HuffmanString(ArvoreHuffman raiz) {
        setArvoreHuffman(raiz);

        frequenciaString = null;
        codigoHuffman = null;
        texto = null;
    }

    public ArvoreHuffman getArvoreHuffman() {
        return raiz;
    }

    public void setArvoreHuffman(ArvoreHuffman raiz) {
        this.raiz = raiz;
    }

    private void preencherFrequenciaString() {
        frequenciaString = new HashMap<>();

        //pega strings separadas por espaços
        for(String palavra : texto.toString().split(" ")) {
            Integer inteiro = frequenciaString.get(palavra);
            frequenciaString.put(palavra, inteiro != null ? inteiro + 1 : 1);
        }
    }

    public String codificar() {
        Queue<ArvoreHuffman> fila = new PriorityQueue<>();

        frequenciaString.forEach((palavra, frequencia) -> fila.add(new FolhaHuffman(palavra, frequencia)));

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
            codigoHuffman.put(((FolhaHuffman) arvoreHuffman).getPalavra(), codigo);
        }

        gerarHuffmanCodigos(arvoreHuffman.getNoEsquerdo(), codigo.concat("0"));
        gerarHuffmanCodigos(arvoreHuffman.getNoDireito(), codigo.concat("1"));
    }

    private String getTextoCodificado() {
        StringBuilder sb = new StringBuilder();
        
        //esse split faz com que os caracteres especiais fiquem separados
        for (String palavra : texto.split("\\s+")) {
            if (palavra.isEmpty()) continue;
            sb.append(codigoHuffman.get(palavra));
        }
        
        return sb.toString();
    }

    public String decodificar(String textoCodificado) {
        StringBuilder sb = new StringBuilder();
        ArvoreHuffman atual = raiz;

        for (char bit : textoCodificado.toCharArray()) {
            atual = bit == '0' ? atual.getNoEsquerdo() : atual.getNoDireito();
            if (atual instanceof FolhaHuffman) {
                sb.append(((FolhaHuffman) atual).getPalavra()).append(" ");
                atual = raiz;
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        
        HuffmanString huffman = new HuffmanString("ola mundoo ola !");

        String textoCodificado = huffman.codificar();
        System.out.println(textoCodificado);


        String original = huffman.decodificar(textoCodificado);
        System.out.println(original);
    }
 
}