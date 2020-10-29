public class FruitStore {
    private int balance = 10000;
    private int stock = 30;
    boolean sell (int num) {
        if(inStock(num)){
            balance += 2000 * num;
            stock -= num;
            return true;
        }
        else {
            return false;
        }
    }

    private boolean inStock(int num){
        int shortage = num - stock;
        if (shortage > 0){
            return false;
        } else {
            return true;
        }
    }

    int getBalance() {
        return this.balance;
    }
    int getStock() {
        return this.stock;
    }
}

