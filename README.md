# Código de Huffman (Palavra e Caracteres)

## Trabalho Projeto e Análise de Algoritmos

### Aluno: José Lucas Hoppe Macedo

#### 1. Introdução

Esse programa utiliza a linguagem de programação Java para simular o código de Huffman, no qual consiste na codificação de um arquivo .txt, transformar seus caracteres (ou palavras) em uma árvore binária, gravar árvore e texto codificado no .txt e decodificar o texto com a própria árvore gravada.

Atualmente é integrado no código cálculo de desempenho: Tempo de sistema e memória utilizada.

Utilizei as seguinte classes para realizar as operações

    import java.io.BufferedReader;
    import java.io.FileInputStream;
    import java.io.FileOutputStream;
    import java.io.FileReader;
    import java.io.FileWriter;
    import java.io.IOException;
    import java.io.ObjectInputStream;
    import java.io.ObjectOutputStream;

    import java.util.HashMap;
    import java.util.Map;
    import java.util.PriorityQueue;
    import java.util.Queue;

    import java.lang.management.ManagementFactory;
    import java.lang.management.ThreadMXBean;
    import java.util.concurrent.TimeUnit;
    import java.util.Scanner;

#### 2. Operações

O programa consiste nas seguintes operações:

- Compactar arquivo (usando codificaçãao por caracter)
- Descompactar arquivo (usando decodificação por caracter)
- Compactar arquivo (usando codificação por palavra)
- Descompactar arquivo (usando decodificação por palavra)

#### 3. Executar

Utilize em PowerShell/Command Prompt o seguinte comando (no mesmo diretório):

    java Menu.java

Utilize o seguinte comando (no mesmo diretório) caso tiver que lidar com arquivos grandes (Ex: 200Mb):

    java -Xmx1G Menu.java
