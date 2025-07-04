public class FolhaHuffman extends ArvoreHuffman{

    private final char caracter;

    public FolhaHuffman(char caracter, int frequencia) {
        super(frequencia);
        this.caracter = caracter;
    }

    public char getCaracter() {
        return caracter;
    }
}