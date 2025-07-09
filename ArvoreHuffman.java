class ArvoreHuffman implements Comparable<ArvoreHuffman>, java.io.Serializable {
    char caracter;
    
    private final int frequencia;

    private ArvoreHuffman esquerda;

    private ArvoreHuffman direita;

    ArvoreHuffman(ArvoreHuffman esquerda, ArvoreHuffman direita) {
        this.frequencia = esquerda.getFrequencia() + direita.getFrequencia();

        this.esquerda = esquerda;
        this.direita = direita;
    }

    ArvoreHuffman(int frequencia) {
        this.frequencia = frequencia;
        this.esquerda = null;
        this.direita = null;
    }

    public int getFrequencia() {
        return frequencia;
    }

    public ArvoreHuffman getNoEsquerdo() {
        return esquerda;
    }

    public ArvoreHuffman getNoDireito() {
        return direita;
    }

    @Override
    public int compareTo(ArvoreHuffman No) {
        return Integer.compare(frequencia, No.getFrequencia());
    }

    
}