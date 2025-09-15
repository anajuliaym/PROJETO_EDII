package Utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class File {

    public static void main (String[] args){
        System.out.println(leituraDosNomes("src/nomes"));
    }

    public static List<String> leituraDosNomes(String caminho) {
        List<String> nomes = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                String[] pares = linha.split(" ");
                nomes.add(pares[0]);
                nomes.add(pares[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return nomes;
    }
}
