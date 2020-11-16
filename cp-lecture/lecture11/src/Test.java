public class Test {
    public <T> Test(T item){
        print(item.toString());
        print(item.getClass().getName());
    }
    void print(String s) {System.out.print(s + "|");}

    public static void main(String[] args){
        Test test1 = new Test("Generic");
        Test test2 = new Test(111);
    }
}
