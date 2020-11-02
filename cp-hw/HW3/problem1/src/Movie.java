
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Movie {
    private String title;
    private Set<String> tags;
    public Movie(String title) { this.title = title;}
    public Movie(String title, String[] tags){
        this.title = title;
        int size = tags.length;
        this.tags = new HashSet<>();
        for(int i = 0; i<size; i++){
            this.tags.add(tags[i]);
        }
    }

    public String getTitle() {
        return title;
    }

    public Set<String> getTags() {
        return tags;
    }

    @Override
    public String toString() {
        return title;
    }
}

