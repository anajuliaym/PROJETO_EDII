package Arvore;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class Arvore {
    private Pessoa raiz;


    public Pessoa inserir(Pessoa noFilho, Pessoa noPai) {
        if (raiz == null) {
            raiz = noPai;
            raiz.setEsquerda(noFilho);
            return raiz; 
        } else {
            Pessoa paiReal = buscar(raiz, noPai.getNome());
            if (paiReal != null) {
                if (paiReal.getEsquerda() == null) {
                    paiReal.setEsquerda(noFilho);
                } else {
                    paiReal.setDireita(noFilho);
                }
                return paiReal; 
            }
            return null;
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
            // System.out.println("\nNivel: " + nivel + " da pessoa " + pessoa.getNome() + " | Sentido da arvore: " + sentidoArvore);
        }
        else {
            // System.out.println("\nNivel: " + nivel + " da pessoa " + pessoa.getNome() + " | Sentido da arvore: " + sentidoArvore);

            calculaNivel(pessoa.getDireita(), nivel + 1, sentidoArvore);
            calculaNivel(pessoa.getEsquerda(), nivel + 1, sentidoArvore);
        }
    }




    public void verificaParentesco(Pessoa p, Pessoa q){
        if(p == null || q == null){
            System.out.println("Sem relacao");
        }
        else if(p==q.getPai()){
            System.out.println("Pai");
        }
        else if(q == p.getPai()){
            System.out.println("Filho");

        }
        else if(q.getPai() == p.getPai()){
            System.out.println("Irmao");
        }
        else if(calculaAncestral(p, q) == p || calculaAncestral(p, q) == q){
            if(p.getNivel() - q.getNivel() == 2){
                System.out.println("Neto");
            }
            else if(p.getNivel() - q.getNivel() == -2){
                System.out.println("Avo");
            
            }
            else if(p.getNivel()-q.getNivel()== 3){
                System.out.println("bisneto");
            }
            else if(p.getNivel()-q.getNivel()== -3){
                System.out.println("bisavo");
            }
            else if(p.getNivel() > q.getNivel()){
                int nivel = p.getNivel() - q.getNivel();
                for(int i = 0; i< nivel - 2; i++){
                    System.out.print("ta"); 
                }
                System.out.print("raneto");

            }
            else if(p.getNivel() < q.getNivel()){
                int nivel = Math.abs(p.getNivel() - q.getNivel());
                for(int i = 0; i< nivel - 2; i++){
                    System.out.print("ta"); 
                }
                System.out.print("ravo");

            }
            else{
                int r = calculaAncestral(p, q).getNivel();
                int m = p.getNivel()-1 - r ;
                int n = q.getNivel()-1 -r;

                System.out.println("Primo-"+ Math.min(m,n)+ " em grau " + Math.abs(m -n) );

            }
         }

    }


    public Pessoa calculaAncestral(Pessoa p, Pessoa q) {
        if (p == null || q == null) {
            return null;
        }

        // Nivelamento seguro
        while (p != null && p.getNivel() > q.getNivel()) {
            p = p.getPai();
        }
        while (q != null && q.getNivel() > p.getNivel()) {
            q = q.getPai();
        }

        // Subida conjunta segura
        while (p != null && p != q) {
            p = p.getPai();
            q = q.getPai();
        }
        return p;
    }


    



    public void imprimirArvore(Pessoa pessoa, String espaco) {
        if (pessoa == null) return;

        imprimirArvore(pessoa.getEsquerda(), espaco + "    ");

        System.out.println(espaco + pessoa.getNome());

        imprimirArvore(pessoa.getDireita(), espaco + "    ");
    }

}
