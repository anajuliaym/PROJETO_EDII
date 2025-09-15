package Arvore;
import Utils.*;
import java.util.ArrayList;
import java.util.List;

public class Arvore {
    private Pessoa raiz;
    List<String> nomes = File.leituraDosNomes("src/nomes");

    public void criacaoArvore() {
        Pessoa filho = new Pessoa();
        Pessoa pai = new Pessoa();
        filho.setNome(nomes.get(0));
        pai.setNome(nomes.get(0));
        pai.setEsquerda(filho);



    }


}
