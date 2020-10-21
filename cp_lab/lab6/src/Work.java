public class Work {
    private int id;
    static int num=0;

    Work() {
        this.id=this.num;
        num++;
    }

    public int getId(){
        return this.id;
    }
}
