import Arvore.*;
import Utils.File;

import java.util.ArrayList;
import java.util.List;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<String> nomes = File.leituraDosNomes("src/nomes.txt");
        Arvore arvoreGenealogica = new Arvore();
        List<Pessoa> nomesNaoInseridos = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);


        for (int i = 0; i < nomes.size(); i += 2) {
            Pessoa paiTemporario = new Pessoa();
            Pessoa filho = new Pessoa();

            if (i + 1 < nomes.size()) {
                filho.setNome(nomes.get(i));
                paiTemporario.setNome(nomes.get(i + 1));

                Pessoa paiRealDaArvore = arvoreGenealogica.inserir(filho, paiTemporario);

                if (paiRealDaArvore != null) {

                    filho.setPai(paiRealDaArvore);
                } else {
                    nomesNaoInseridos.add(filho);
                    nomesNaoInseridos.add(paiTemporario);
                }
            }
        }

 
        while (!nomesNaoInseridos.isEmpty()) {
            
            for (int j = 0; j < nomesNaoInseridos.size(); j += 2) {
                Pessoa filho = nomesNaoInseridos.get(j);
                Pessoa paiTemporario = nomesNaoInseridos.get(j + 1);

                Pessoa paiRealDaArvore = arvoreGenealogica.inserir(filho, paiTemporario);

                if (paiRealDaArvore != null) {
                    filho.setPai(paiRealDaArvore);

                    nomesNaoInseridos.remove(j + 1);
                    nomesNaoInseridos.remove(j);
                    j -= 2;
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
                
                arvoreGenealogica.calculaNivel(arvoreGenealogica.getRaiz(), 0, 0);
                System.out.print("\nDigite o primeiro nome (nome.sobrenome): ");
                String nome1 = scanner.nextLine();
                Pessoa resultado1 = arvoreGenealogica.buscar(arvoreGenealogica.getRaiz(), nome1);


                System.out.print("\nDigite o segundo nome (nome.sobrenome): ");
                String nome2 = scanner.nextLine();
                Pessoa resultado2 = arvoreGenealogica.buscar(arvoreGenealogica.getRaiz(), nome2);
                arvoreGenealogica.verificaParentesco(resultado1, resultado2);


            }

            else if (escolha == 3) {
                System.out.println("\nFim do programa!\n");
                break;
            }

        }

        scanner.close();
    }


}
