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
            // Este objeto 'pai' é temporário, apenas para carregar o nome
            Pessoa paiTemporario = new Pessoa();
            Pessoa filho = new Pessoa();

            if (i + 1 < nomes.size()) {
                filho.setNome(nomes.get(i));
                paiTemporario.setNome(nomes.get(i + 1));

                // Inserimos e capturamos o "Pai Real" que o método retorna
                Pessoa paiRealDaArvore = arvoreGenealogica.inserir(filho, paiTemporario);

                if (paiRealDaArvore != null) {
                    // AQUI ESTÁ A CORREÇÃO DEFINITIVA
                    // O filho agora aponta para o objeto pai que realmente existe na árvore
                    filho.setPai(paiRealDaArvore);
                } else {
                    // A inserção falhou, guarde para tentar depois
                    nomesNaoInseridos.add(filho);
                    nomesNaoInseridos.add(paiTemporario);
                }
            }
        }



        int tentativas = nomesNaoInseridos.size(); // Para evitar um loop infinito
        while (!nomesNaoInseridos.isEmpty() && tentativas > 0) {
            
            // Itera de 2 em 2 (filho, pai)
            for (int j = 0; j < nomesNaoInseridos.size(); j += 2) {
                Pessoa filho = nomesNaoInseridos.get(j);
                Pessoa paiTemporario = nomesNaoInseridos.get(j + 1);

                // 1. Chamamos 'inserir' e guardamos o objeto Pessoa retornado
                Pessoa paiRealDaArvore = arvoreGenealogica.inserir(filho, paiTemporario);

                // 2. A condição do 'if' agora checa se o retorno é diferente de nulo
                if (paiRealDaArvore != null) {
                    // 3. Se a inserção deu certo, corrigimos o link do pai
                    filho.setPai(paiRealDaArvore);

                    // E removemos o par da lista de "não inseridos"
                    // Remove o pai primeiro (índice maior) para não afetar o índice do filho
                    nomesNaoInseridos.remove(j + 1);
                    nomesNaoInseridos.remove(j);
                    j -= 2; // Ajusta o índice do loop, já que removemos 2 itens
                    break; // Sai do for para reiniciar a varredura da lista modificada
                }
            }
            tentativas--;
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
