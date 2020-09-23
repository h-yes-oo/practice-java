package Platform.Games;

public class Dice {
    public int playGame() {
        int user = roll();
        int opponent = roll();
        System.out.println(user+" "+opponent);
        if(user > opponent) {
            return 1;
        } else if(user < opponent){
            return -1;
        } else{
            return 0;
        }
    }
    int roll(){
        return (int)(100 * Math.random());
    }
}
