public class FolhaHuffman extends ArvoreHuffman{

    private final char caracter;

    private final String palavra;

    public FolhaHuffman(char caracter, int frequencia) {
        super(frequencia);
        this.caracter = caracter;
        this.palavra = null;
    }

    public FolhaHuffman(String palavra, int frequencia) {
        super(frequencia);
        this.caracter = '\0';
        this.palavra = palavra;
    }

    public char getCaracter() {
        return caracter;
    }

    public String getPalavra() {
        return palavra;
    }
}