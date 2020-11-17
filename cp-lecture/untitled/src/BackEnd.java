import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class BackEnd extends ServerResourceAccessible {
    // Use getServerStorageDir() as a default directory
    // TODO sub-program 1 ~ 4 :
    // Create helper funtions to support FrontEnd class
    private int loadPosts(String userId) {
        File directory = new File(this.getServerStorageDir());
        File[] people = directory.listFiles();
        List<Integer> ids = new LinkedList<>();
        for(int i = 0; i< people.length; i++) {
            File[] postList = (new File(this.getServerStorageDir() + people[i].getName() + "/post")).listFiles();
            if (people[i].getName().equals(userId) && postList.length == 0) {
                int id = 0;
                return id;
            } else {
                Arrays.sort(postList, Comparator.comparing(File::getName));
                String biggest = postList[postList.length - 1].getName();
                int id = Integer.parseInt(biggest.replaceAll(".txt", "")) + 1;
                ids.add(id);
            }
        }
        return Collections.max(ids);
    }
    public void post(String title, String entireContent, User user){
        int postId = loadPosts(user.id);
        Date createdDate = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String createdAt = format.format(createdDate);
        String fileName = this.getServerStorageDir() + user.id +"/post/" + postId + ".txt";
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            fileWriter.write(createdAt+"\n");
            fileWriter.write(title+"\n");
            fileWriter.write("\n");
            fileWriter.write(entireContent);
            fileWriter.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<List<String>> recommend(User user){
        TreeMap<LocalDateTime,List<String>> map = new TreeMap<LocalDateTime,List<String>>();
        for(String friend: getFriends(user)){
            File[] postlist = loadPostList(friend);
            for(int i= 0; i< postlist.length; i++){
                List<String> strings = new LinkedList<>();
                strings.add(postlist[i].getName().replaceAll(".txt",""));
                try{
                    Scanner scanner = new Scanner(postlist[i]);
                    while (scanner.hasNext()){
                        String line = scanner.nextLine();
                        strings.add(line);
                    }
                    scanner.close();
                } catch(IOException e) {
                    e.printStackTrace();
                }
                LocalDateTime createdAt = LocalDateTime.parse(strings.get(1),DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
                map.put(createdAt,strings);
            }
        }
        List<List<String>> recommends = new ArrayList<List<String>>();
        for(int i=1; i<11; i++) {
            List<String> value = (new ArrayList<List<String>>(map.values())).get(map.size()-i);
            recommends.add(value);
        }
        return recommends;
    }

    private File[] loadPostList(String userId) {
        File directory = new File(this.getServerStorageDir() + userId + "/post");
        File[] postList = directory.listFiles();
        Arrays.sort(postList, Comparator.comparing(File::getName));
        return postList;
    }

    public List<String> getFriends(User user){
        File fileName = new File(this.getServerStorageDir() + user.id +"/friend.txt");
        List<String> friends = new LinkedList<>();
        try {
            Scanner scanner = new Scanner(fileName);
            while(scanner.hasNext()) {
                String friend = scanner.nextLine();
                friends.add(friend);
            }
            scanner.close();
        } catch (IOException e){
            e.printStackTrace();
        }
        return friends;
    }

    public List<List<String>> search(Set<String> keywords){
        TreeMap<LocalDateTime,List<String>> map = new TreeMap<LocalDateTime,List<String>>();
        File directory = new File(this.getServerStorageDir());
        File[] people = directory.listFiles();
        for(int i = 0; i< people.length; i++) {
            File[] person = (new File(this.getServerStorageDir()+people[i].getName()+"/post")).listFiles();
            for(int j = 0 ; j < person.length; j++){
                List<String> strings = new LinkedList<>();
                strings.add(person[j].getName().replaceAll(".txt",""));
                //int id = Integer.parseInt(person[j].getName().replaceAll(".txt",""));
                try{
                    Scanner scanner = new Scanner(person[j]);
                    while (scanner.hasNext()){
                        String line = scanner.nextLine();
                        strings.add(line);
                    }
                    scanner.close();
                } catch(IOException e) {
                    e.printStackTrace();
                }
                LocalDateTime createdAt = LocalDateTime.parse(strings.get(1),DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
                map.put(createdAt,strings);
            }
        }
        List<Integer> frequency = new LinkedList<>();
        for(List<String> strings: map.values()){
            int freq = getFrequency(strings,keywords);
            frequency.add(freq);
        }
        List<Integer> topTen = new LinkedList<>();
        while(getMaxIdx(frequency,topTen)!= -1){
            topTen.add(getMaxIdx(frequency,topTen));
            if(topTen.size() == 10) break;
        }
        List<List<String>> searches = new ArrayList<List<String>>();
        for(int f=0; f<topTen.size();f++){
            List<String> value = (new ArrayList<List<String>>(map.values())).get(topTen.get(f));
            searches.add(value);
        }
        return searches;
    }

    public int getFrequency(List<String> strings,Set<String> keywords){
        List<String> words = new LinkedList<>();
        if(strings.size()>2) {
            for (String word : strings.get(2).split("\\s")) {
                words.add(word);
            }
            for (int k = 4; k < strings.size(); k++) {
                for (String word : strings.get(k).split("\\s")) {
                    words.add(word);
                }
            }
            int freq = 0;
            for (String keyword : keywords) {
                for (int l = 0; l < words.size(); l++) {
                    if (words.get(l).equals(keyword)) freq++;
                }
            }
            return freq;
        } else return 0;
    }

    public int getMaxIdx(List<Integer> frequency, List<Integer> lastidx){
        int max = 0;
        int index = -1;
        for(int i = 0; i<frequency.size();i++){
            if(!lastidx.contains(i)){
                if(frequency.get(i) != 0 && frequency.get(i) >= max){
                    max = frequency.get(i);
                    index = i;
                }
            }
        }
        return index;
    }

}
