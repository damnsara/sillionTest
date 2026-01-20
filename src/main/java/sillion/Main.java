package sillion;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Main {
    public static void main(String[] args) {
        try {
            String url = "https://www.cnnbrasil.com.br/entretenimento/sabrina-carpenter-a-trajetoria-da-cantora-ate-se-tornar-uma-estrela-pop/";
            String termo = "Sabrina";
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String conteudo = response.body();
            int totalOcorrencias = contarOcorrencias(conteudo, termo);
            System.out.println("---------------------------------------");
            System.out.println("Busca concluída!");
            System.out.println("O termo: \"" + termo + "\" aparece " + totalOcorrencias + " vezes no conteúdo da URL.");
            System.out.println("---------------------------------------");
        } catch (Exception e) {
            System.err.println("Erro ao processar a requisição: " + e.getMessage());
    }
 }

    public static int contarOcorrencias(String texto, String frase) {
        if(texto == null || frase == null || frase.isEmpty()) {
            return 0;
        }
        int contador = 0;
        int indice = 0;
        while ((indice = texto.indexOf(frase, indice)) != -1) {
            contador++;
            indice += frase.length();
        }
        return contador;
    }

}