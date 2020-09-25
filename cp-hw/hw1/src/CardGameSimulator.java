public class CardGameSimulator {
    private static final Player[] players = new Player[2];

    public static void simulateCardGame(String inputA, String inputB) {
        // DO NOT change the skeleton code.
        // You can add codes anywhere you want.
        Card[] Adeck = new Card[10];
        Card[] Bdeck = new Card[10];

        int acard = 0;
        int bcard = 0;

        for(int i = 0; i<10; i++){
            Adeck[i] = new Card(inputA.charAt(3*i)-48,inputA.charAt(3*i+1));
            Bdeck[i] = new Card(inputB.charAt(3*i)-48,inputB.charAt(3*i+1));
        }

        Player playerA = new Player("A", Adeck);
        Player playerB = new Player("B", Bdeck);

        Card prevcard = playerA.findTheFirstCard();
        int turn = 1;
        while(turn<20) {
            if (turn % 2 == 1) {
                Card currcard = playerB.findCard(prevcard);
                if (currcard != null) {
                    playerB.playCard(currcard);
                    prevcard = currcard;
                    turn++;
                } else{
                    printLoseMessage(playerB);
                    return;
                }
            } else {
                Card currcard = playerA.findCard(prevcard);
                if (currcard != null) {
                    playerA.playCard(currcard);
                    prevcard = currcard;
                    turn++;
                } else{
                    printLoseMessage(playerA);
                    return;
                }
            }
        }
        if(turn == 20){
            printLoseMessage(playerA);
            return;
        }


    }

    private static void printLoseMessage(Player player) {
        System.out.printf("Player %s loses the game!\n", player);
    }
}


class Player {
    private String name;
    private Card[] deck;

    Player(String name, Card[] deck){
        this.name = name;
        this.deck = deck;
    }

    public Card[] getDeck(){
        return this.deck;
    }

    public Card findTheFirstCard(){
        int maxval = -1;
        int maxidx = -1;
        for(int i = 0; i<10; i++){
            if(maxval < this.getDeck()[i].getNumber()){
                maxidx = i;
                maxval = this.getDeck()[i].getNumber();
            } else if(maxval == this.getDeck()[i].getNumber()){
                if(this.getDeck()[i].getShape()=='O'){
                    maxidx = i;
                }
            }
        }
        Card currcard = this.getDeck()[maxidx];
        this.playCard(currcard);
        this.deck[maxidx] = null;
        return(currcard);
    }

    public Card findCard(Card prev){
        int num = prev.getNumber();
        char shape = prev.getShape();
        Card curr = null;
        int idx = -1;
        for(int i=0; i<10; i++){
            if(this.getDeck()[i] != null){
                if(this.getDeck()[i].getNumber() == num){
                    if(this.getDeck()[i].getShape() == 'O'){
                        curr = this.getDeck()[i];
                        idx = i;
                    } else if(curr == null){
                        curr = this.getDeck()[i];
                        idx = i;
                    }
                }
            }
        }
        if(curr == null){
            for(int i = 0; i<10 ; i++){
                if(this.getDeck()[i]!=null) {
                    if (this.getDeck()[i].getShape() == shape) {
                        if(curr == null) {
                            curr = this.getDeck()[i];
                            idx = i;
                        } else if(curr.getNumber() < this.getDeck()[i].getNumber()){
                            curr = this.getDeck()[i];
                            idx = i;
                        }
                    }
                }
            }
        }
        if(curr != null) {
            this.deck[idx] = null;
        }
        return curr;
    }

    public void playCard(Card card) {
        System.out.printf("Player %s: %s\n", name, card);
    }

    @Override
    public String toString() {
        return name;
    }
}


class Card {
    private int number;
    private char shape;

    Card(int number, char shape) {
        this.number = number;
        this.shape = shape;
    }

    public int getNumber(){
        return this.number;
    }
    public char getShape(){
        return this.shape;
    }

    @Override
    public String toString() {
        return "" + number + shape;
    }
}
