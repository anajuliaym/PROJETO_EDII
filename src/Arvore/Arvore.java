package Arvore;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class Arvore {
    private Pessoa raiz;


    public boolean inserir(Pessoa noFilho, Pessoa noPai) {
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


    public void calculaNivel(Pessoa pessoa, int nivel, int sentidoArvore) {
        if (pessoa == null) {
            return;
        }
        pessoa.setNivel(nivel);

        if (pessoa == raiz) {
            calculaNivel(pessoa.getDireita(), nivel + 1, 2);
            calculaNivel(pessoa.getEsquerda(), nivel + 1, 1);
            System.out.println("\nNivel: " + nivel + " da pessoa " + pessoa.getNome() + " | Sentido da arvore: " + sentidoArvore);
        }
        else {
            System.out.println("\nNivel: " + nivel + " da pessoa " + pessoa.getNome() + " | Sentido da arvore: " + sentidoArvore);

            calculaNivel(pessoa.getDireita(), nivel + 1, sentidoArvore);
            calculaNivel(pessoa.getEsquerda(), nivel + 1, sentidoArvore);
        }
    }


    public boolean verificaPai(Pessoa p1, Pessoa p2){
        return p1 == p2.getPai();
    }

    public boolean verificaFilho(Pessoa p1, Pessoa p2){
        return p2 == p1.getPai();
    }


    public void verificaPrimos(Pessoa p, Pessoa q){
        int m;
        int n;



    }

    public Pessoa calculaAncestral(Pessoa p, Pessoa q){
        if(p.getArvore() != q.getArvore()){
            return raiz;
        }
        else if(p.getNivel()-q.getNivel() == 0){
            return p.getPai(); //irmãos
        }
        else if (p.getNivel() < q.getNivel()) {
            int subtracao = p.getNivel() - q.getNivel();

            int counter = abs(subtracao);
            while(counter != 0) {
                p = p.getPai();
                counter--;
            }
            if (p.getPai() == q.getPai()) {
                if (p == q) {
                    return q;
                } else {
                    return q.getPai();
                }
            }

        }


    }



    public void imprimirArvore(Pessoa pessoa, String espaco) {
        if (pessoa == null) return;

        imprimirArvore(pessoa.getEsquerda(), espaco + "    ");

        System.out.println(espaco + pessoa.getNome());

        imprimirArvore(pessoa.getDireita(), espaco + "    ");
    }

}
