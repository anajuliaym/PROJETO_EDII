package Arvore;

public class Pessoa {
    private String nome;
    private Pessoa esquerda, direita, pai;
    private int nivel;
    private int arvore;

    public Pessoa (String nome, Pessoa esquerda, Pessoa direita, Pessoa pai) {
        this.nome = nome;
        this.esquerda = esquerda;
        this.direita = direita;
        this.pai = pai;
    }

    public Pessoa () {
        this.nome = "";
        this.esquerda = null;
        this.direita = null;
        this.pai = null;
    }
    public void setNome(String nome) {this.nome = nome;}
    public void setEsquerda(Pessoa esquerda) {this.esquerda = esquerda;}
    public void setDireita  (Pessoa direita) {this.direita = direita;}
    public void setPai (Pessoa pai) {this.pai = pai;}
    public void setNivel(int nivel) {this.nivel = nivel;}

    public String getNome() {return this.nome;}
    public Pessoa getEsquerda() {return this.esquerda;}
    public Pessoa getDireita  () {return this.direita;}
    public Pessoa getPai () {return this.pai;}
    public int getNivel () {return this.nivel;}
    public int getArvore(){return this.arvore;}
}
