package evolution.of.truth.agent;

import evolution.of.truth.match.Match;
import kiroong.Individual;

public class Copykitten extends Agent {
    public Copykitten(){
        super("Copykitten");
    }

    private int isCheated = 0;

    @Override
    public Individual clone() {
        return new Copykitten();
    }

    @Override
    public int choice(int previousOpponentChoice){
        if(previousOpponentChoice == Match.UNDEFINED){
            isCheated = 0;
            return Match.COOPERATE;
        } else if(previousOpponentChoice == Match.CHEAT){
            if(isCheated == 0){
                isCheated++;
                return Match.COOPERATE;
            } else {
                isCheated++;
                return previousOpponentChoice;
            }
        } else{
            return previousOpponentChoice;
        }
    }
}
