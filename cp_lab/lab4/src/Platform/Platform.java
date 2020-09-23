package Platform;

import Platform.Games.Dice;
import Platform.Games.RockPaperScissors;

import java.util.Scanner;

public class Platform {
    RockPaperScissors rsp;
    Dice dice;
    public float run(){
        int game = setup();
        int sum = 0;
        for(int i = 0; i<rounds;i++){
            int status;
            if(game == 0){
                status = dice.playGame();
            }
            else{
                status = rsp.playGame();
            }
            if(status == 1) {
                sum +=1;
            }
        }
        return (float)sum/rounds;
    }
    int setup(){
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        if(input == 0){dice = new Dice();}
        else{
            rsp = new RockPaperScissors();
        }
        return input;
    }
    boolean roundset = false;
    int rounds = 1;
    public void setRounds(int num) {
        if(!roundset){
            roundset = true;
            rounds = num;
        }
    }
}
