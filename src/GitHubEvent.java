import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GitHubEvent {
    private String id;
    private String type;
    private Autor autor;
    private Repo repo;
    //@JsonProperty("created_at")
    private LocalDateTime created_at;

    public GitHubEvent(){ }

    public GitHubEvent(String id, String type, Autor autor, Repo repo, LocalDateTime created_at){
        this.id = id;
        this.type = type;
        this.autor = autor;
        this.repo = repo;
        this.created_at = created_at;
    }

        public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public Autor getAutor() {
        return autor;
    }
    
    public void setActor(Autor autor) {
        this.autor = autor;
    }
    
    public Repo getRepo() {
        return repo;
    }
    
    public void setRepo(Repo repo) {
        this.repo = repo;
    }
    
    public LocalDateTime getCreated_at() {
        return created_at;
    }
    
    public void setCreatedAt(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s - %s em %s", 
            created_at, type, autor.getLogin(), repo.getName());
    }



}
