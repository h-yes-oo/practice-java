package Platform.Games;

import java.util.Scanner;

public class RockPaperScissors {
    public int playGame() {
        Scanner sc = new Scanner(System.in);
        String user, opponent;
        user = sc.next();
        if (check(user) < 0) {
            return -1;
        }
        opponent = randomAction();
        System.out.println(user + " " + opponent);
        return compare(user, opponent);
    }
    int compare(String user, String opponent){
        if (user.equals(opponent)) {
            return 0;
        } else {
            if(getIndex(user)==2 && getIndex(opponent)==0) return -1;
            else if(getIndex(user) < 2 && getIndex(user) + 1 == getIndex(opponent)) return -1;
            else return 1;
        }
    }
    String[] rspEnum = {"scissor","rock","paper"};
    String randomAction(){
        return rspEnum[(int)(3*Math.random())];
    }
    int getIndex(String user){
        for(int i=0; i< rspEnum.length; i++){
            if(rspEnum[i].equals(user))
                return i;
        }
        return -1;
    }
    int check(String user){
        int idx = getIndex(user);
        if(idx < 0){
            return -1;
        } else{
            return 0;
        }
    }
}
