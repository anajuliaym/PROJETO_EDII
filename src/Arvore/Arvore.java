package Arvore;
import java.util.ArrayList;
import java.util.List;

public class Arvore {
    private Pessoa raiz;


    public boolean inserir(Pessoa noPai, Pessoa noFilho) {
        if (raiz == null) {
            raiz = noPai;
            noPai.setEsquerda(noFilho);
            return true;
        }
        else {
            Pessoa verifica = buscar(raiz, noPai.getNome());
            if (verifica != null) {
                if (verifica.getEsquerda() == null) {
                    verifica.setEsquerda(noFilho);
                } else {verifica.setDireita(noFilho);}
                return true;
            }
            return false;
        }
    }

    public Pessoa buscar(Pessoa p, String nome) {
        if (p == null || nome.equals(p.getNome())) {
            return p;
        }
        Pessoa esquerda = buscar(p.getEsquerda(), nome);
        if (esquerda != null) {
            return esquerda;
        }
        return buscar(p.getDireita(),nome);
    }

    public Pessoa getRaiz() {return raiz;}
    
}
