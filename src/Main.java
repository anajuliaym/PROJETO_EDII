import Arvore.*;
import Utils.File;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> nomes = File.leituraDosNomes("src/nomes");
        Arvore arvoreGenealogica = new Arvore();
        List<Pessoa> nomesNaoInseridos = new ArrayList<>();
        for (int i = 0; i < (nomes.size() - 1); i++) {
            Pessoa pai = new Pessoa();
            Pessoa filho = new Pessoa();

            pai.setNome(nomes.get(i));
            filho.setNome(nomes.get(i+1));
            filho.setPai(pai);
            if(!arvoreGenealogica.inserir(pai, filho)) {
                nomesNaoInseridos.add(pai);
                nomesNaoInseridos.add(filho);
            }
        }
        while(!nomesNaoInseridos.isEmpty()) {
            for (int j = 0; j < nomesNaoInseridos.size(); j++) {
                if(arvoreGenealogica.inserir(nomesNaoInseridos.get(j), nomesNaoInseridos.get(j+1))) {
                    nomesNaoInseridos.remove(j);
                    nomesNaoInseridos.remove(j);
                    j -= 2;
                }
            }
        }

        arvoreGenealogica.mostrarArvoreVisualmente();
    }
}
