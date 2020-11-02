import java.util.*;
import java.time.LocalDateTime;
import java.io.*;

public class FrontEnd {
    private UserInterface ui;
    private BackEnd backend;
    private User user;

    public FrontEnd(UserInterface ui, BackEnd backend) {
        this.ui = ui;
        this.backend = backend;
    }
    
    public boolean auth(String authInfo){
        String id = authInfo.split("\n")[0];
        String password = authInfo.split("\n")[1];
        File idFile = new File(backend.getServerStorageDir() + id + "/password.txt");
        try {
            Scanner scanner = new Scanner(idFile);
            String line = scanner.nextLine();
            scanner.close();
            this.user = new User(id,password);
            if(line.equals(password)) {
                return true;
            } else {
                return false;
            }
        } catch (IOException e){
            System.out.println(e);
            e.printStackTrace();
        }
        return false;
    }

    public void post(Pair<String, String> titleContentPair) {
        String title = titleContentPair.key;
        String entireContent = titleContentPair.value;
        this.backend.post(title,entireContent,this.user);
    }
    
    public void recommend(){
        List<List<String>> recommends = this.backend.recommend(this.user);
        for(List<String> recommend : recommends) {
            ui.println("-----------------------------------");
            ui.print("id: ");
            ui.println(recommend.get(0));
            ui.print("created at: ");
            ui.println(recommend.get(1));
            ui.print("title: ");
            ui.println(recommend.get(2));
            ui.print("content: ");
            for(int i = 4; i<recommend.size(); i++) {
                ui.println(recommend.get(i));
            }
        }
    }

    public void search(String command) {
        String[] commandSlices = command.split(" ");
        Set<String> keywords = new HashSet<String>();
        for(int i = 1; i<commandSlices.length; i++){
            keywords.add(commandSlices[i]);
        }
        List<List<String>> searches = this.backend.search(keywords);
        for(List<String> search : searches) {
            String print = String.format("id: %s, created at: %s, title: %s", search.get(0), search.get(1), search.get(2));
            ui.println(print);
        }

    }
    
    User getUser(){
        return user;
    }
}
