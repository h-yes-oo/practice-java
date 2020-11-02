
import java.util.*;

public class MovieApp {
    HashMap<String, Movie> movieMap;
    HashMap<String, User> userMap;
    HashMap<User, HashMap<Movie,Integer>> rateMap;
    HashMap<User,List<Movie>> matchingMovies;

    public MovieApp(){
        movieMap = new HashMap<String, Movie>();
        userMap = new HashMap<String, User>();
        rateMap = new HashMap<User, HashMap<Movie, Integer>>();
        matchingMovies = new HashMap<User, List<Movie>>();
    }

    public boolean addMovie(String title, String[] tags) {
        if(movieMap.containsKey(title)){
            return false;
        } else{
            movieMap.put(title, new Movie(title,tags));
            return true;
        }
    }

    public boolean addUser(String name) {
        if(userMap.containsKey((name))){
            return false;
        } else{
            userMap.put(name, new User(name));
            return true;
        }
    }

    public Movie findMovie(String title) {
        return movieMap.get(title);
    }

    public User findUser(String username) {
        return userMap.get(username);
    }

    public List<Movie> findMoviesWithTags(String[] tags) {
        List<Movie> moviesWithTags = new LinkedList<>();
        if(tags.length == 0) return moviesWithTags;
        for(String key: this.movieMap.keySet()){
            Movie movie = this.movieMap.get(key);
            int contain = 1;
            for (int i = 0; i<tags.length; i++){
                if(!movie.getTags().contains(tags[i])){
                    contain = 0;
                    break;
                }
            }
            if(contain == 1) {
                moviesWithTags.add(movie);
            }
        }

        Collections.sort(moviesWithTags, new Comparator<Movie>() {
            @Override
            public int compare(Movie movie1, Movie movie2){
                return movie1.getTitle().compareTo(movie2.getTitle());
            }
        });
        return moviesWithTags;
    }

    public boolean rateMovie(User user, String title, int rating) {
        if(title == null) return false;
        if(user == null) return false;
        if(rating < 1 || rating > 10) return false;
        Movie movie = this.findMovie(title);
        if(movie == null) return false;
        User ratingUser = this.findUser(user.getUsername());
        if(ratingUser != user) return false;
        if(rateMap.containsKey(ratingUser)){
            HashMap<Movie,Integer> rateTable = rateMap.get(ratingUser);
            rateTable.put(movie,rating);
            rateMap.put(ratingUser, rateTable);
            return true;
        } else {
            HashMap<Movie,Integer> rateTable = new HashMap<Movie,Integer>();
            rateTable.put(movie,rating);
            rateMap.put(ratingUser, rateTable);
            return true;
        }
    }

    public int getUserRating(User user, String title) {
        if(title == null) return -1;
        if(user == null) return -1;
        Movie movie = this.findMovie(title);
        if(movie == null) return -1;
        User ratingUser = this.findUser(user.getUsername());
        if(ratingUser != user) return -1;
        if(this.rateMap.get(ratingUser) == null) return 0;
        if(this.rateMap.get(ratingUser).get(movie) == null) return 0;
        return this.rateMap.get(ratingUser).get(movie);
    }

    public List<Movie> findUserMoviesWithTags(User user, String[] tags) {
        List<Movie> movieList = new LinkedList<Movie>();
        if(user == null ) return movieList;
        User findingUser = this.findUser(user.getUsername());
        if(findingUser != user) return movieList;
        movieList = this.findMoviesWithTags(tags);
        if(matchingMovies.get(findingUser) == null) {
            matchingMovies.put(findingUser, movieList);
        } else{
            List<Movie> list = matchingMovies.get(findingUser);
            for(Movie movie : movieList) {
                if(!list.contains(movie)) {
                    list.add(movie);
                }
            }
            Collections.sort(list, new Comparator<Movie>() {
                @Override
                public int compare(Movie movie1, Movie movie2){
                    return movie1.getTitle().compareTo(movie2.getTitle());
                }
            });
            matchingMovies.put(findingUser, list);
        }
        return movieList;
    }

    public List<Movie> recommend(User user) {
        List<Movie> movieList = new LinkedList<Movie>();
        if(user == null ) return movieList;
        User findingUser = this.findUser(user.getUsername());
        if(findingUser != user) return movieList;
        List<Movie> movies = this.matchingMovies.get(findingUser);
        List<Double> ratings = new LinkedList<Double>();
        for(Movie movie : movies) {
            ratings.add(this.getAverageRating(movie));
        }
        int first = getMaxIndex(ratings,-1);
        int second = getMaxIndex(ratings, first);
        int third = getMaxIndex(ratings, first, second);
        if(first != -1) movieList.add(movies.get(first));
        if(second != -1) movieList.add(movies.get(second));
        if(third != -1) movieList.add(movies.get(third));
        return movieList;
    }

    private double getAverageRating(Movie movie){
        double total = 0.0;
        int rateNum = 0;
        for(User user: this.rateMap.keySet()){
            if(rateMap.get(user).get(movie) != null){
                total += rateMap.get(user).get(movie);
                rateNum++;
            }
        }
        if (rateNum == 0) return 0;
        return total/rateNum;
    }

    private int getMaxIndex(List<Double> ratings, int lastidx) {
        double max = -1;
        int idx = -1;
        for(int i = 0; i<ratings.size(); i++){
            if(i != lastidx) {
                if (ratings.get(i) > max) {
                    max = ratings.get(i);
                    idx = i;
                }
            }
        }
        return idx;
    }

    private int getMaxIndex(List<Double> ratings, int lastidx, int lastidx2) {
        double max = -1;
        int idx = -1;
        for(int i = 0; i<ratings.size(); i++){
            if(i != lastidx && i!=lastidx2) {
                if (ratings.get(i) > max) {
                    max = ratings.get(i);
                    idx = i;
                }
            }
        }
        return idx;
    }

}
