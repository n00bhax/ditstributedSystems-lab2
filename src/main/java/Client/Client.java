package Client;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Client {

    private final HttpClient client = HttpClient.newHttpClient();
    private HttpRequest request;

    private void send(){
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(System.out::println)
                .join();
    }

    public static void main(String[] args) {

        Client c = new Client();
        c.request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost/account"))
                .POST(HttpRequest.BodyPublishers.ofString("Willem"))
                .build();

        c.send();

        c.request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost/balance/Willem"))
                .PUT(HttpRequest.BodyPublishers.ofString("5"))
                .build();

        c.send();

        c.request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost/balance/Willem"))
                .GET()
                .build();

        c.send();

    }
}


