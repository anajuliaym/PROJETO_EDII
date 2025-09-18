import Arvore.*;
import Utils.File;

import java.util.ArrayList;
import java.util.List;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<String> nomes = File.leituraDosNomes("src/nomes");
        Arvore arvoreGenealogica = new Arvore();
        List<Pessoa> nomesNaoInseridos = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < (nomes.size() - 1); i++) {
            Pessoa pai = new Pessoa();
            Pessoa filho = new Pessoa();

            filho.setNome(nomes.get(i));
            pai.setNome(nomes.get(i + 1));
            filho.setPai(pai);

            // System.out.println("\nFilho: "+ nomes.get(i));
            // System.out.println("\nPai: "+ nomes.get(i+1));
            if (!arvoreGenealogica.inserir(filho, pai)) {
                nomesNaoInseridos.add(filho);
                nomesNaoInseridos.add(pai);
                // System.out.println("Lista dos nomes não inseridos: " + nomesNaoInseridos);
            }
            i++;
        }
        while (!nomesNaoInseridos.isEmpty()) {
            for (int j = 0; j < nomesNaoInseridos.size(); j++) {
                if (arvoreGenealogica.inserir(nomesNaoInseridos.get(j), nomesNaoInseridos.get(j + 1))) {
                    nomesNaoInseridos.remove(j);
                    nomesNaoInseridos.remove(j);
                    j--;
                }
            }
        }

        while (true) {
            System.out.println("\n----------ARVORE GENEALOGICA----------\n");
            System.out.println("1. Mostrar a árvore\n");
            System.out.println("2. Buscar parentesco\n");
            System.out.println("3. Sair do programa\n");

            int escolha = scanner.nextInt();
            scanner.nextLine();

            if (escolha == 1) {
                arvoreGenealogica.imprimirArvore(arvoreGenealogica.getRaiz(), "");

                arvoreGenealogica.calculaNivel(arvoreGenealogica.getRaiz(), 0, 0);
            }

            else if (escolha == 2) {
                System.out.print("\nDigite o primeiro nome (nome.sobrenome): ");
                String nome1 = scanner.nextLine();
                Pessoa resultado1 = arvoreGenealogica.buscar(arvoreGenealogica.getRaiz(), nome1);

                System.out.print("\nDigite o segundo nome (nome.sobrenome): ");
                String nome2 = scanner.nextLine();
                Pessoa resultado2 = arvoreGenealogica.buscar(arvoreGenealogica.getRaiz(), nome2);

                if (resultado1 == null || resultado2 == null) {
                    System.out.println("Sem relação");
                }
                else {
                    
                }


                // Espaço para fazer a funcao de buscar parentesco
            }

            else if (escolha == 3) {
                System.out.println("\nFim do programa!\n");
                break;
            }

        }

        scanner.close();
    }
}
