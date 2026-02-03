import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.format.DateTimeFormatter;
import java.util.List;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;


public class Main {
    public static void main(String[] args) {
        if (args.length != 1){
            System.out.println("Please insert EXACTLY ONE Github User");
            System.exit(1);
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.findAndRegisterModules();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String username = args[0];
        String url_events = "https://api.github.com/users/" + username +"/events";
 
        HttpClient cliente = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url_events))
            .GET()
            .build();


        try {
            HttpResponse<String> response = cliente.send(request, HttpResponse.BodyHandlers.ofString());
            List<GitHubEvent> events = mapper.readValue( response.body(), new TypeReference<List<GitHubEvent>>() {} );
            System.out.println("Código de Status: " + response.statusCode());

            System.out.println("==== Recent GitHub Activities from " + username + "====");
            for (GitHubEvent event : events) {
            String formattedDate = event.getCreated_at().format(formatter);
            
            System.out.println("Data: " + formattedDate);
            System.out.println("Tipo: " + event.getType());
            System.out.println("Usuário: " + event.getAutor().getLogin());
            System.out.println("Repositório: " + event.getRepo().getName());
            System.out.println("ID do Evento: " + event.getId());
            System.out.println("─".repeat(50));
        }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}